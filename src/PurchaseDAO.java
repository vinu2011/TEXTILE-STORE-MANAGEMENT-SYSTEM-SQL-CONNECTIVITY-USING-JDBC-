import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PurchaseDAO {
    public int addPurchase(Connection connection, Purchase purchase) throws SQLException {
        String query = "INSERT INTO purchase (customer_id, stock_id, quantity, total_amount) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query,
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, purchase.getCustomerId());
            statement.setInt(2, purchase.getStockId());
            statement.setInt(3, purchase.getQuantity());
            statement.setDouble(4, purchase.getTotalAmount());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
        }
        return -1; // Return -1 or any indicator of failure to insert the purchase
    }
}
