package dal.dao;

import dal.entity.Warehouse;
import dal.utils.EntityManagerUtils;
import lombok.Getter;

import java.util.List;

@Getter
public class WarehouseDao {

    public List<Warehouse> getAllWarehouses() {
        return EntityManagerUtils.performReturningWithinPersistenceContext(entityManager ->
                entityManager.createQuery("SELECT w FROM Warehouse w", Warehouse.class).getResultList()
        );
    }

    public Warehouse getWarehouseById(long id) {
        return EntityManagerUtils.performReturningWithinPersistenceContext(entityManager -> entityManager.find(Warehouse.class, id));
    }

    public void addNewWarehouse(Warehouse warehouse) {
        EntityManagerUtils.performWithinPersistenceContext(entityManager -> entityManager.merge(warehouse));
    }

    public void updateWarehouseById(Warehouse updatedWarehouse, int id) {
        EntityManagerUtils.performWithinPersistenceContext(entityManager -> {
                    Warehouse warehouse = entityManager.find(Warehouse.class, id);
                    if (warehouse != null) {
                        warehouse.setName(updatedWarehouse.getName());
                        warehouse.setLocation(updatedWarehouse.getLocation());
                    }
                }
        );
    }

    public void deleteWarehouseById(long id) {
        EntityManagerUtils.performWithinPersistenceContext(entityManager -> {
            Warehouse warehouse = entityManager.find(Warehouse.class, id);
            if (warehouse != null) {
                entityManager.remove(warehouse);
            }
        });
    }

    public void deleteAllWarehouses() {
        EntityManagerUtils.performWithinPersistenceContext(
                entityManager -> entityManager
                        .createQuery("DELETE FROM Warehouse")
                        .executeUpdate()
        );
    }
}