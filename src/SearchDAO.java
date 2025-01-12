import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SearchDAO {

    public List<Stock> searchStocks(String keyword) throws SQLException {
        List<Stock> stocks = new ArrayList<>();
        String query = "SELECT * FROM stock WHERE stock_name LIKE ? OR stock_id LIKE ?";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Stock stock = new Stock();
                stock.setStockId(resultSet.getInt("stock_id"));
                stock.setStockName(resultSet.getString("stock_name"));
                stock.setQuantity(resultSet.getInt("quantity"));
                stock.setPrice(resultSet.getDouble("price"));
                stocks.add(stock);
            }
        }
        return stocks;
    }

    public List<Customer> searchCustomers(String keyword) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customer WHERE customer_name LIKE ? OR customer_id LIKE ?";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(resultSet.getInt("customer_id"));
                customer.setCustomerName(resultSet.getString("customer_name"));
                customer.setContactInfo(resultSet.getString("contact_info"));
                customer.setAddress(resultSet.getString("address"));
                customers.add(customer);
            }
        }
        return customers;
    }

    public List<Purchase> searchPurchases(String keyword) throws SQLException {
        List<Purchase> purchases = new ArrayList<>();
        String query = "SELECT * FROM purchase WHERE purchase_id LIKE ? OR customer_id LIKE ?";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Purchase purchase = new Purchase();
                purchase.setPurchaseId(resultSet.getInt("purchase_id"));
                purchase.setCustomerId(resultSet.getInt("customer_id"));
                purchase.setStockId(resultSet.getInt("stock_id"));
                purchase.setQuantity(resultSet.getInt("quantity"));
                purchase.setTotalAmount(resultSet.getDouble("total_amount"));
                purchases.add(purchase);
            }
        }
        return purchases;
    }
}
