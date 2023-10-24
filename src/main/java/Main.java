import dal.dao.DatabaseConnection;
import dal.dao.ShoeDao;
import dal.dao.WarehouseDao;
import dal.model.Shoe;
import dal.model.Warehouse;
import dal.utils.MyBatisScriptUtility;

import java.util.List;


public class Main {
    public static void main(String[] args) throws Exception {
        MyBatisScriptUtility.runScript(DatabaseConnection.getConnection());
        shoesCrud();
        warehouseCrud();
    }

    private static void shoesCrud() {
        ShoeDao shoeDao = new ShoeDao();

        int shoeId = 5;
        Shoe shoeById = shoeDao.getShoeById(shoeId);
        if (shoeById != null)
            System.out.println("Shoe with id " + shoeId + " " + shoeById);
        else
            System.out.println("Shoe with id " + shoeId + " not found");

        printAllShoes(shoeDao);

        Shoe newShoe = Shoe.builder()
                .brand("Nike NEW")
                .model("Fresh Foam NEW")
                .size(38)
                .stock(145)
                .build();

        shoeDao.addShoe(newShoe);
        System.out.println("\nNew shoe with model " + newShoe.getModel() + " has been added");
        printAllShoes(shoeDao);


        Shoe updatedShoe = Shoe.builder()
                .brand("Nike UPDATED")
                .model("Fresh Foam UPDATED")
                .size(35)
                .stock(143)
                .build();

        System.out.println("\nShoe with id " + shoeId + " has been updated");
        shoeDao.updateShoeById(updatedShoe, shoeId);
        printAllShoes(shoeDao);

        shoeDao.deleteShoeById(shoeId);
        System.out.println("\nShoe with id " + shoeId + " has been deleted");
        printAllShoes(shoeDao);

        shoeDao.deleteAllShoes();
        System.out.println("\nAll shoes have been deleted");
        printAllShoes(shoeDao);

    }

    private static void warehouseCrud() {
        WarehouseDao warehouseDao = new WarehouseDao();

        int warehouseId = 5;
        Warehouse warehouseById = warehouseDao.getWarehouseById(warehouseId);
        if (warehouseById != null)
            System.out.println("Warehouse with id " + warehouseId + " " + warehouseById);
        else
            System.out.println("Warehouse with id " + warehouseId + " not found");

        printAllWarehouses(warehouseDao);

        Warehouse newWarehouse = Warehouse.builder()
                .name("Rippin Wilderman and Predovic NEW")
                .location("268 Green Ridge Terrace NEW")
                .build();

        warehouseDao.addWarehouse(newWarehouse);
        System.out.println("\nNew warehouse with name " + newWarehouse.getName() + " has been added");
        printAllWarehouses(warehouseDao);


        Warehouse updatedWarehouse = Warehouse.builder()
                .name("Rippin Wilderman and Predovic UPDATED")
                .location("268 Green Ridge Terrace UPDATED")
                .build();

        warehouseDao.updateWarehouseById(updatedWarehouse, warehouseId);
        System.out.println("\nWarehouse with id " + warehouseId + " has been updated");
        printAllWarehouses(warehouseDao);

        warehouseDao.deleteWarehouseById(warehouseId);
        System.out.println("\nWarehouse with id " + warehouseId + " has been deleted");
        printAllWarehouses(warehouseDao);

        warehouseDao.deleteAllWarehouses();
        System.out.println("\nAll warehouses have been deleted");
        printAllWarehouses(warehouseDao);
    }

    private static void printAllShoes(ShoeDao shoeDao) {
        System.out.println("------------------------------");
        List<Shoe> shoes = shoeDao.getAllShoes();
        shoes.forEach(System.out::println);
    }

    private static void printAllWarehouses(WarehouseDao warehouseDao) {
        System.out.println("------------------------------");
        List<Warehouse> warehouses = warehouseDao.getAllWarehouses();
        warehouses.forEach(System.out::println);
    }

}