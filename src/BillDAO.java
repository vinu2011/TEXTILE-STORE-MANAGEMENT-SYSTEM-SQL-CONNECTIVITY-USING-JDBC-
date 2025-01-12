import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillDAO {
    public void addBill(Connection connection, Bill bill) throws SQLException {
        String query = "INSERT INTO bill (purchase_id, total_amount, date) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query,
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, bill.getPurchaseId());
            statement.setDouble(2, bill.getTotalAmount());
            statement.setDate(3, new java.sql.Date(bill.getDate().getTime()));

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        bill.setBillId(generatedKeys.getInt(1));
                    }
                }
            }
        }
    }

    public Bill getBillById(Connection connection, int billId) throws SQLException {
        String query = "SELECT * FROM bill WHERE bill_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, billId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Bill bill = new Bill();
                    bill.setBillId(resultSet.getInt("bill_id"));
                    bill.setPurchaseId(resultSet.getInt("purchase_id"));
                    bill.setTotalAmount(resultSet.getDouble("total_amount"));
                    bill.setDate(resultSet.getDate("date"));
                    return bill;
                } else {
                    return null;
                }
            }
        }
    }
}
