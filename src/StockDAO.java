import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StockDAO {

    public List<Stock> searchStocks(Connection connection, String searchText) {
        List<Stock> stocks = new ArrayList<>();
        String query = "SELECT * FROM stock WHERE item_name LIKE ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + searchText + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Stock stock = new Stock();
                stock.setStockId(resultSet.getInt("stock_id"));
                stock.setStockName(resultSet.getString("item_name"));
                stock.setQuantity(resultSet.getInt("quantity"));
                stock.setPrice(resultSet.getDouble("price"));
                stocks.add(stock);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stocks;
    }

    public boolean addStock(Stock stock) {
        String query = "INSERT INTO stock (item_name, quantity, price) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, stock.getStockName());
            statement.setInt(2, stock.getQuantity());
            statement.setDouble(3, stock.getPrice());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
