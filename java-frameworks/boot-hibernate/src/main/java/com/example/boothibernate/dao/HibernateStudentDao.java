package com.example.boothibernate.dao;

import com.example.boothibernate.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class HibernateStudentDao implements StudentDao{
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;


    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public Student get(Long id) {
        try(EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return entityManager.find(Student.class, id);
        } catch (HibernateException ex){
            log.error("Error with getting student " + ex);
            return null;
        }
    }

    @Override
    public void create(Student student) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            student.setId(null);
            entityManager.persist(student);
            entityManager.getTransaction().commit();
        } catch (HibernateException ex){
            log.error("Error with creating student " + student, ex);
            entityManager.getTransaction().rollback();
        } finally {
            if (entityManager != null){
                entityManager.close();
            }
        }

    }

    @Override
    public void update(Student student) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(student);
            entityManager.getTransaction().commit();
        } catch (HibernateException ex){
            log.error("Error with updating student " + student, ex);
            entityManager.getTransaction().rollback();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public void delete(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Student newStudent = entityManager.find(Student.class, id);
            entityManager.remove(newStudent);
            entityManager.getTransaction().commit();
        } catch (HibernateException ex){
            log.error("Error with deleting student " + id, ex);
            entityManager.getTransaction().rollback();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public Optional<List<Student>> getAllByIdGroup(Long groupId) {

        return Optional.empty();
    }
}
