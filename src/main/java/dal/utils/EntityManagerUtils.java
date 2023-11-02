package dal.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.function.Consumer;
import java.util.function.Function;

public class EntityManagerUtils {
    private EntityManagerUtils() {
    }

    public static EntityManager getEntityManager() {
        var entityManagerFactory = Persistence.createEntityManagerFactory("embedded");
        return entityManagerFactory.createEntityManager();
    }

    public static void performWithinPersistenceContext(Consumer<EntityManager> entityManagerConsumer) {
        performReturningWithinPersistenceContext(entityManager -> {
            entityManagerConsumer.accept(entityManager);
            return null;
        });
    }

    public static <T> T performReturningWithinPersistenceContext(Function<EntityManager, T> entityManagerFunction) {
        EntityManager entityManager = EntityManagerUtils.getEntityManager();
        entityManager.getTransaction().begin();
        T result;
        try {
            result = entityManagerFunction.apply(entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
        return result;
    }
}
