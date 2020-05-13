package book;

import book.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Main {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");

    public static void main(String[] args) {


        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            for (int i = 0; i < 1000; i++) {
                em.persist(BookGenerator.generateRandomBook());
            }
            em.getTransaction().commit();
            bookLister().forEach(System.out::println);
        } finally {
            em.close();
            emf.close();
        }

    }

    public static List<Book> bookLister() {
        EntityManager em = emf.createEntityManager();
        try{
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Book> cq = cb.createQuery(Book.class);
            Root<Book> rootEntry = cq.from(Book.class);
            CriteriaQuery<Book> all = cq.select(rootEntry);

            TypedQuery<Book> allQuery = em.createQuery(all);
            return allQuery.getResultList();
        }
        finally {
            em.close();
        }
    }
}
