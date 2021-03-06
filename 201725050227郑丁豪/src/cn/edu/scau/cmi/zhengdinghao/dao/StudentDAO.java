package cn.edu.scau.cmi.zhengdinghao.dao;

import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.Session;

import cn.edu.scau.cmi.zhengdinghao.domain.Student;
import cn.edu.scau.cmi.zhengdinghao.hibernate.HibernateSessionFactory;


public class StudentDAO  {
  private static final Logger log = LoggerFactory.getLogger(StudentDAO.class);
  // property constants
  public static final String NAME = "name";
  
  public Session getSession() {
		return HibernateSessionFactory.getSession();
  }

  public void save(Student transientInstance) {
    log.debug("saving Student instance");
    try {
      getSession().save(transientInstance);
      log.debug("save successful");
    } catch (RuntimeException re) {
      log.error("save failed", re);
      throw re;
    }
  }

  public void delete(Student persistentInstance) {
    log.debug("deleting Student instance");
    try {
      getSession().delete(persistentInstance);
      log.debug("delete successful");
    } catch (RuntimeException re) {
      log.error("delete failed", re);
      throw re;
    }
  }

  public Student findById(java.lang.Long id) {
    log.debug("getting Student instance with id: " + id);
    try {
      Student instance = (Student) getSession().get("cn.edu.scau.cmi.zhengdinghao.domain.Student", id);
      return instance;
    } catch (RuntimeException re) {
      log.error("get failed", re);
      throw re;
    }
  }

  public List findByExample(Student instance) {
    log.debug("finding Student instance by example");
    try {
      List results = getSession().createCriteria("cn.edu.scau.cmi.zhengdinghao.domain.Student")
          .add(Example.create(instance)).list();
      log.debug("find by example successful, result size: " + results.size());
      return results;
    } catch (RuntimeException re) {
      log.error("find by example failed", re);
      throw re;
    }
  }

  public List findByProperty(String propertyName, Object value) {
    log.debug("finding Student instance with property: " + propertyName + ", value: " + value);
    try {
      String queryString = "from Student as model where model." + propertyName + "= ?";
      Query queryObject = getSession().createQuery(queryString);
      queryObject.setParameter(0, value);
      return queryObject.list();
    } catch (RuntimeException re) {
      log.error("find by property name failed", re);
      throw re;
    }
  }

  public List findByName(Object name) {
    return findByProperty(NAME, name);
  }

  public List findAll() {
    log.debug("finding all Student instances");
    try {
      String queryString = "from Student";
      Query queryObject = getSession().createQuery(queryString);
      return queryObject.list();
    } catch (RuntimeException re) {
      log.error("find all failed", re);
      throw re;
    }
  }

  public Student merge(Student detachedInstance) {
    log.debug("merging Student instance");
    try {
      Student result = (Student) getSession().merge(detachedInstance);
      log.debug("merge successful");
      return result;
    } catch (RuntimeException re) {
      log.error("merge failed", re);
      throw re;
    }
  }

  public void attachDirty(Student instance) {
    log.debug("attaching dirty Student instance");
    try {
      getSession().saveOrUpdate(instance);
      log.debug("attach successful");
    } catch (RuntimeException re) {
      log.error("attach failed", re);
      throw re;
    }
  }

  public void attachClean(Student instance) {
    log.debug("attaching clean Student instance");
    try {
      getSession().buildLockRequest(LockOptions.NONE).lock(instance);
      log.debug("attach successful");
    } catch (RuntimeException re) {
      log.error("attach failed", re);
      throw re;
    }
  }
}

