package vn.myclass.core.data.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import vn.myclass.core.common.CoreConstant;
import vn.myclass.core.data.dao.GenericDao;
import vn.myclass.core.utils.HibernateUtils;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class AbstractDao<ID extends Serializable, T> implements GenericDao<ID, T> {
    //1. Tên bảng
    private Class<T> persistenceClass;
    public AbstractDao() {
        this.persistenceClass = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }
    private String getPersistenceClass() {
        return this.persistenceClass.getSimpleName();
    }
    //2. Select * from table
    public List<T> findAll() {
        List<T> list = new ArrayList<T>();
        Transaction transaction = null;
        Session session = new HibernateUtils().getSessionFactory().openSession();
        transaction = session.beginTransaction();
        try {
            StringBuilder sql = new StringBuilder("from ");
            sql.append(getPersistenceClass());
            Query query = session.createQuery(sql.toString());
            list = query.list();
            transaction.commit();
        }catch (HibernateException ex) {
            transaction.rollback();
            throw ex;
        }finally {
            session.close();
        }
        return list;
    }
    //3. Update dữ liệu

    public T update(T entity) {
        T result = null;
        Transaction transaction = null;
        Session session = new HibernateUtils().getSessionFactory().openSession();
        transaction = session.beginTransaction();
        try {
            result = (T) session.merge(entity);
            transaction.commit();
        }catch (HibernateException e) {
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
        return result;
    }
    //4. Thêm vào hay insert vào bảng

    public void save(T entity) {
        Transaction transaction = null;
        Session session = new HibernateUtils().getSessionFactory().openSession();
        transaction = session.beginTransaction();
        try {
            session.persist(entity);
            transaction.commit();
        }catch (HibernateException e) {
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
    }
    //5. Tỉm kiếm theo điều kiện where id

    public T findById(ID id) {
        T result = null;
        Transaction transaction = null;
        Session session = new HibernateUtils().getSessionFactory().openSession();
        transaction = session.beginTransaction();
        try {
            result = (T) session.get(persistenceClass,id);
            transaction.commit();
        }catch (HibernateException e) {
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
        return result;
    }
    //6. Detete trong bảng

    public Object detele(List<Object> list) {
        Integer count =0;
        Transaction transaction = null;
        Session session = new  HibernateUtils().getSessionFactory().openSession();
        transaction = session.beginTransaction();
        try {
            for(Object item: list) {
                T t = (T) session.get(persistenceClass, (Serializable) item);
                session.delete(t);
                count++;
            }
            transaction.commit();
        }catch (HibernateException e) {
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
        return count;
    }
    // 7. Tìm kiếm theo điều kiến where và sort theo thuộc tính order by

    public List<T> findByproperty(String property, Object value, String sortProperty, String condition) {
        List<T> list;
        Transaction transaction = null;
        Session session  = new HibernateUtils().getSessionFactory().openSession();
        transaction = session.beginTransaction();
        try {
            StringBuilder sql1 = new StringBuilder("from ");
            sql1.append(getPersistenceClass());
            if(property!=null&& value!=null) {
                sql1.append(" where ").append(property).append("= :value ");
            }
            if(sortProperty!=null&& condition!=null){
                sql1.append(" order by ").append(sortProperty).append(condition.equals(CoreConstant.SORT_ASC)?" ASC ":" DESC ");
            }
            Query query = session.createQuery(sql1.toString());
            if(value!=null){ query.setParameter("value", value); }
            list = query.list();
            transaction.commit();
        }catch (HibernateException e) {
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
        return list;
    }
}
