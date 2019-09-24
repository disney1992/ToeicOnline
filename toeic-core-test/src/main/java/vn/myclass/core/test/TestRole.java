package vn.myclass.core.test;
import org.junit.jupiter.api.Test;
import vn.myclass.core.dao.RoleEntityDao;
import vn.myclass.core.daoimpl.RoleEntityDaoImpl;
import vn.myclass.core.persistence.data.entity.RoleEntity;

import java.util.ArrayList;
import java.util.List;

public class TestRole {
    @Test
    public void checkFindAll() {
        RoleEntityDao roleEntityDao = new RoleEntityDaoImpl();
        List<RoleEntity> list = roleEntityDao.findAll();
    }
    @Test
    public void checkUpdate() {
        RoleEntityDao roleEntityDao = new RoleEntityDaoImpl();
        RoleEntity entity = new RoleEntity();
        entity.setRoleId(2);
        entity.setName("Admin");
        roleEntityDao.update(entity);
    }
    @Test
    public void checkSave() {
        RoleEntityDao roleEntityDao = new RoleEntityDaoImpl();
        RoleEntity entity = new RoleEntity();
        entity.setRoleId(1);
        entity.setName("ADMIN");
        roleEntityDao.save(entity);
    }
    @Test
    public void checkFindById() {
        RoleEntityDao roleEntityDao = new RoleEntityDaoImpl();
        roleEntityDao.findById(5);
    }
    @Test
    public void checkDelete() {
        RoleEntityDao roleEntityDao = new RoleEntityDaoImpl();
        List<Object> list = new ArrayList<Object>();
        list.add(5);
        list.add(6);
        Integer count = (Integer) roleEntityDao.detele(list);
    }
    @Test
    public void checkFindByProperty() {
        RoleEntityDao roleEntityDao = new RoleEntityDaoImpl();
        roleEntityDao.findByproperty("name","ADMIN","roleid","2");
    }
}