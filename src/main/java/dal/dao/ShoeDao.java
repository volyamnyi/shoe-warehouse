package dal.dao;

import dal.entity.Shoe;
import dal.utils.EntityManagerUtils;
import lombok.Getter;

import java.util.List;


@Getter
public class ShoeDao {
    public List<Shoe> getAllShoes() {
        return EntityManagerUtils.performReturningWithinPersistenceContext(entityManager ->
                entityManager.createQuery("SELECT s FROM Shoe s", Shoe.class).getResultList()
        );
    }

    public Shoe getShoeById(long id) {
        return EntityManagerUtils.performReturningWithinPersistenceContext(entityManager -> entityManager.find(Shoe.class, id));
    }

    public void addNewShoe(Shoe shoe) {
        EntityManagerUtils.performWithinPersistenceContext(entityManager -> entityManager.persist(shoe));
    }

    public void updateShoeById(Shoe updatedShoe, long id) {
        EntityManagerUtils.performWithinPersistenceContext(entityManager -> {
                    Shoe shoe = entityManager.find(Shoe.class, id);
                    if (shoe != null) {
                        shoe.setBrand(updatedShoe.getBrand());
                        shoe.setModel(updatedShoe.getModel());
                        shoe.setSize(updatedShoe.getSize());
                        shoe.setStock(updatedShoe.getStock());
                        shoe.setWarehouse(updatedShoe.getWarehouse());
                    }
                }
        );
    }

    public void deleteShoeById(long id) {
        EntityManagerUtils.performWithinPersistenceContext(entityManager -> {
            Shoe shoe = entityManager.find(Shoe.class, id);
            if (shoe != null) {
                entityManager.remove(shoe);
            }
        });
    }

    public void deleteAllShoes() {
        EntityManagerUtils.performWithinPersistenceContext(
                entityManager -> entityManager
                        .createQuery("DELETE FROM Shoe")
                        .executeUpdate()
        );
    }
}