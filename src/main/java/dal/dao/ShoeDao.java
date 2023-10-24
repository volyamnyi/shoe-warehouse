package dal.dao;

import dal.model.Shoe;
import lombok.Getter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Getter
public class ShoeDao {
    private Connection connection;

    public ShoeDao() {
        try {
            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Shoe> getAllShoes() {
        List<Shoe> shoes = new ArrayList<>();
        String query = "SELECT * FROM shoes";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
               Shoe shoe = Shoe.builder()
                       .id(resultSet.getInt("id"))
                       .brand(resultSet.getString("brand"))
                       .model(resultSet.getString("model"))
                       .size(resultSet.getInt("size"))
                       .stock(resultSet.getInt("stock"))
                       .build();

               shoes.add(shoe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shoes;
    }

    public Shoe getShoeById(int id) {
        Shoe shoe = null;
        String query = "SELECT * FROM shoes WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    shoe = Shoe.builder()
                            .id(resultSet.getInt("id"))
                            .brand(resultSet.getString("brand"))
                            .model(resultSet.getString("model"))
                            .size(resultSet.getInt("size"))
                            .stock(resultSet.getInt("stock"))
                            .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shoe;
    }

    public void addShoe(Shoe shoe) {
        String query = "INSERT INTO shoes (brand, model, size, stock) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, shoe.getBrand());
            statement.setString(2, shoe.getModel());
            statement.setInt(3, shoe.getSize());
            statement.setInt(4, shoe.getStock());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateShoeById(Shoe shoe, int id) {
        String query = "UPDATE shoes SET brand = ?, model = ?, size = ? , stock = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, shoe.getBrand());
            statement.setString(2, shoe.getModel());
            statement.setInt(3, shoe.getSize());
            statement.setInt(4, shoe.getStock());
            statement.setInt(5, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteShoeById(int id) {
        String query = "DELETE FROM shoes WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllShoes() {
        String query = "DELETE FROM shoes ";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}