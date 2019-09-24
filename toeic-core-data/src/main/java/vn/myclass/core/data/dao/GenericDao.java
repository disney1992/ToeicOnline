package vn.myclass.core.data.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<ID extends Serializable, T> {
    List<T> findAll();
    T update(T entity);
    void save(T entity);
    T findById(ID id);
    Object detele(List<Object> list);
    List<T> findByproperty(String property, Object value,String sortProperty, String condition );
}
