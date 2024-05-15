package com.example.boothibernate;

import com.example.boothibernate.dao.StudentDao;
import com.example.boothibernate.domain.Group;
import com.example.boothibernate.domain.Student;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import jakarta.persistence.EntityTransaction;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@EnableTransactionManagement
@Slf4j
public class BootHibernateApplication implements ApplicationRunner {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private StudentDao studentDao;
//    @PersistenceContext
//    private EntityManager entityManager;

    public static void main(String[] args) {
        SpringApplication.run(BootHibernateApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("http://localhost:9000/h2-console");
        System.out.println("http://localhost:9000/swagger-ui/index.html");

        {
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            Student student;
            try {
                student = entityManager.find(Student.class, 101L);
            } finally {
                entityManager.close();
            }
            System.out.println(student);
        }

        {
            Student newStudent = new Student(null, "new", 5, null);
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            try {
                EntityTransaction transaction = entityManager.getTransaction();
                transaction.begin();
                entityManager.persist(newStudent);
                entityManager.getTransaction().commit();
            } catch (HibernateException ex) {
                entityManager.getTransaction().rollback();
            } finally {
                if (entityManager != null) {
                    entityManager.close();
                }
            }
        }

        {
//            Group group;
//            try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
//                EntityGraph<?> entityGraph = entityManager.getEntityGraph("groupWithStudents");
//                group = entityManager.createQuery("FROM Group g WHERE g.id = :id", Group.class)
//                        .setParameter("id", 100L)
//                        .setHint("jakarta.persistence.fetchgraph", entityGraph)
//                        .getSingleResult();
//            }
//            System.out.println(group);
       }

/*        List<Student> groups;
        List<Group> students;
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            //Group group = entityManager.find(Group.class, 100L);
            Query query = entityManager.createQuery("SELECT DISTINCT g FROM Group g JOIN FETCH g.students s WHERE s.group = g AND g.id = :id", Group.class);
            query.setParameter("id", 100L);
            Query query1 = entityManager.createQuery("select s from Student s, Group g where s.group = g and s.group.id= :groupId", Student.class);
            query1.setParameter("groupId", 100L);
            groups = query.getResultList();
            students = query1.getResultList();
        }
        System.out.println(groups);
        System.out.println(students);
*/
//        System.out.println(studentDao.getAllByIdGroup(100L));
//        {
//            Group group;
//            try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
//                EntityGraph<?> entityGraph = entityManager.getEntityGraph("groupWithStudents");
//                group = entityManager.createQuery("FROM Group g WHERE g.id = :id", Group.class)
//                        .setParameter("id", 100L)
//                        .setHint("jakarta.persistence.fetchgraph", entityGraph)
//                        .getSingleResult();
//            }
//            System.out.println(group);
//        }
//    }

////    @Bean
//    public OpenAPI springShopOpenAPI() {
//        return new OpenAPI()
//                .info(new Info().title("EIS API")
//                        .description("Employee Information System sample application")
//                        .version("v0.0.1")
//                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
//                        .description("SpringShop Wiki Documentation")
//                        .contact(new Contact().email("test@test.com").url("http://fullstackcode.dev")))
//                ;
    }

}
