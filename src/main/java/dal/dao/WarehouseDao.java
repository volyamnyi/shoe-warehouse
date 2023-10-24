package dal.dao;

import dal.model.Warehouse;
import lombok.Getter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class WarehouseDao {
    private Connection connection;

    public WarehouseDao() {
        try {
            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addWarehouse(Warehouse warehouse) {
        String query = "INSERT INTO warehouses (name, location) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, warehouse.getName());
            statement.setString(2, warehouse.getLocation());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Warehouse> getAllWarehouses() {
        List<Warehouse> warehouses = new ArrayList<>();
        String query = "SELECT * FROM warehouses";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Warehouse warehouse = Warehouse.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .location(resultSet.getString("location"))
                        .build();

                warehouses.add(warehouse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return warehouses;
    }

    public Warehouse getWarehouseById(int id) {
        Warehouse warehouse = null;
        String query = "SELECT * FROM warehouses WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    warehouse = Warehouse.builder()
                            .id(resultSet.getInt("id"))
                            .name(resultSet.getString("name"))
                            .location(resultSet.getString("location"))
                            .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return warehouse;
    }

    public void updateWarehouseById(Warehouse warehouse, int id) {
        String query = "UPDATE warehouses SET name = ?, location = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, warehouse.getName());
            statement.setString(2, warehouse.getLocation());
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteWarehouseById(int id) {
        String query = "DELETE FROM warehouses WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllWarehouses() {
        String query = "DELETE FROM warehouses";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}