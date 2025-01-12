import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {
    public int addCustomer(Connection connection, Customer customer) throws SQLException {
        String query = "INSERT INTO customer (customer_name, contact_info, address) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query,
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, customer.getCustomerName());
            statement.setString(2, customer.getContactInfo());
            statement.setString(3, customer.getAddress());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
        }
        return -1; // Return -1 or any indicator of failure to insert the customer
    }
}
