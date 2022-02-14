package com.gui.coursesystem.hibernate;

import com.gui.coursesystem.ds.Course;
import com.gui.coursesystem.ds.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class CourseHibController {

    EntityManagerFactory emf = null;

    public CourseHibController(EntityManagerFactory entityManagerFactory) {this.emf = entityManagerFactory;}

    public EntityManager getEntityManager() {return emf.createEntityManager();}

    public void createCourse(Course course) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(course);
            em.getTransaction().commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Course course) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.flush();
            course = em.merge(course);
            em.getTransaction().commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void remove(int id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Course course = null;
            try {
                course = em.find(Course.class, id);
                course.getId();
                //em.merge(course);
                for(User u : course.getResponsibleUsers()){
                    u.removeCourse(course);
                    em.merge(u);
                }
            } catch (Exception e) {
                //throw new Exception("The category with id" + id + "no longer exists", e);
            }
            em.remove(course);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void AddResponsibleUserToCourse(Course course, User user) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            try {
                course.addUser(user);
                user.addCourse(course);
                em.merge(user);
                em.merge(course);
                em.flush();
                course.getResponsibleUsers().clear();
                user.getMyCourses().clear();
            } catch (EntityNotFoundException enfe) {
                throw new Exception("Error adding course", enfe);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void RemoveResponsibleUserFromCourse(Course course, User user) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            try {
                course.removeUser(user);
                user.removeCourse(course);
                em.merge(user);
                em.merge(course);
                em.flush();
                course.getResponsibleUsers().clear();
                user.getMyCourses().clear();
            } catch (EntityNotFoundException enfe) {
                throw new Exception("Error adding course", enfe);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Course> getCourseList() {
        return getCourseList(true, -1, -1);
    }

    public List<Course> getCourseList(boolean all, int maxRes, int firstRes) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery criteriaQuery = em.getCriteriaBuilder().createQuery();
            criteriaQuery.select(criteriaQuery.from(Course.class));
            Query query = em.createQuery(criteriaQuery);

            if (!all) {
                query.setMaxResults(maxRes);
                query.setFirstResult(firstRes);
            }
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return null;
    }

    public Course findCourse(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Course.class, id);
        } finally {
            em.close();
        }
    }
}
