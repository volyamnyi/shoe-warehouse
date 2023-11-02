import dal.dao.ShoeDao;
import dal.dao.WarehouseDao;
import dal.entity.Shoe;
import dal.entity.Warehouse;
import dal.utils.DatabaseConnection;
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

        long shoeId = 5;
        long warehouseId = 3;

        Shoe shoeById = shoeDao.getShoeById(shoeId);
        if (shoeById != null)
            System.out.println("Shoe with id " + shoeId + " " + shoeById);
        else
            System.out.println("Shoe with id " + shoeId + " not found");

        Shoe newShoe = Shoe.builder()
                .brand("Nike NEW")
                .model("Fresh Foam NEW")
                .size(38)
                .stock(145)
                .warehouse(Warehouse
                        .builder()
                        .id(warehouseId)
                        .build())
                .build();
        shoeDao.addNewShoe(newShoe);
        printAllShoes(shoeDao);
        System.out.println("\nNew shoe with model " + newShoe.getModel() + " with warehouse id " + warehouseId + " has been added");


        Shoe updatedShoe = Shoe.builder()
                .brand("Nike UPDATED")
                .model("Fresh Foam UPDATED")
                .size(35)
                .stock(143)
                .warehouse(Warehouse.builder().id(5L).build())
                .build();


        shoeDao.updateShoeById(updatedShoe, shoeId);
        printAllShoes(shoeDao);
        System.out.println("\nShoe with id " + shoeId + " has been updated");

        shoeDao.deleteShoeById(shoeId);
        printAllShoes(shoeDao);
        System.out.println("\nShoe with id " + shoeId + " has been deleted");

        shoeDao.deleteAllShoes();
        printAllShoes(shoeDao);
        System.out.println("\nAll shoes have been deleted");

    }

    private static void warehouseCrud() {
        WarehouseDao warehouseDao = new WarehouseDao();

        int warehouseId = 5;
        Warehouse warehouseById = warehouseDao.getWarehouseById(warehouseId);


        printAllWarehouses(warehouseDao);
        if (warehouseById != null)
            System.out.println("Warehouse with id " + warehouseId + " " + warehouseById);
        else
            System.out.println("Warehouse with id " + warehouseId + " not found");

        Warehouse newWarehouse = Warehouse.builder()
                .name("Rippin Wilderman and Predovic NEW")
                .location("268 Green Ridge Terrace NEW")
                .build();

        warehouseDao.addNewWarehouse(newWarehouse);
        printAllWarehouses(warehouseDao);
        System.out.println("\nNew warehouse with name " + newWarehouse.getName() + " has been added");


        Warehouse updatedWarehouse = Warehouse.builder()
                .name("Rippin Wilderman and Predovic UPDATED")
                .location("268 Green Ridge Terrace UPDATED")
                .build();

        warehouseDao.updateWarehouseById(updatedWarehouse, warehouseId);
        printAllWarehouses(warehouseDao);
        System.out.println("\nWarehouse with id " + warehouseId + " has been updated");

        warehouseDao.deleteWarehouseById(warehouseId);
        printAllWarehouses(warehouseDao);
        System.out.println("\nWarehouse with id " + warehouseId + " has been deleted");

        warehouseDao.deleteAllWarehouses();
        printAllWarehouses(warehouseDao);
        System.out.println("\nAll warehouses have been deleted");
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