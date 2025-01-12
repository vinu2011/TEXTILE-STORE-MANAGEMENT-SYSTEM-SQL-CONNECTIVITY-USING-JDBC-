import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {

    public boolean addSupplier(Supplier supplier) throws SQLException {
        String query = "INSERT INTO supplier (supplier_name, contact_info, address) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query,
                        PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, supplier.getSupplierName());
            statement.setString(2, supplier.getContactInfo());
            statement.setString(3, supplier.getAddress());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        supplier.setSupplierId(generatedKeys.getInt(1));
                        return true;
                    }
                }
            }
        }
        return false; // Return false if the supplier was not added successfully
    }

    public List<Supplier> searchSuppliers(String keyword) throws SQLException {
        List<Supplier> suppliers = new ArrayList<>();
        String query = "SELECT * FROM supplier WHERE supplier_name LIKE ? OR contact_info LIKE ? OR address LIKE ?";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            statement.setString(3, "%" + keyword + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Supplier supplier = new Supplier();
                supplier.setSupplierId(resultSet.getInt("supplier_id"));
                supplier.setSupplierName(resultSet.getString("supplier_name"));
                supplier.setContactInfo(resultSet.getString("contact_info"));
                supplier.setAddress(resultSet.getString("address"));
                suppliers.add(supplier);
            }
        }
        return suppliers;
    }

    public Supplier getSupplierById(int supplierId) throws SQLException {
        String query = "SELECT * FROM supplier WHERE supplier_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, supplierId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Supplier supplier = new Supplier();
                supplier.setSupplierId(resultSet.getInt("supplier_id"));
                supplier.setSupplierName(resultSet.getString("supplier_name"));
                supplier.setContactInfo(resultSet.getString("contact_info"));
                supplier.setAddress(resultSet.getString("address"));
                return supplier;
            }
        }
        return null; // Return null if the supplier was not found
    }
}
