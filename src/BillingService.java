import java.sql.Connection;
import java.sql.SQLException;

public class BillingService {
    private CustomerDAO customerDAO;
    private PurchaseDAO purchaseDAO;
    private BillDAO billDAO;

    public BillingService() {
        this.customerDAO = new CustomerDAO();
        this.purchaseDAO = new PurchaseDAO();
        this.billDAO = new BillDAO();
    }

    public void generateBill(Customer customer, Purchase purchase, Bill bill) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false);

            try {
                int customerId = customerDAO.addCustomer(connection, customer);
                customer.setCustomerId(customerId);

                purchase.setCustomerId(customer.getCustomerId());
                int purchaseId = purchaseDAO.addPurchase(connection, purchase);
                purchase.setPurchaseId(purchaseId);

                bill.setPurchaseId(purchase.getPurchaseId());
                billDAO.addBill(connection, bill);

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException("Error generating bill: " + e.getMessage(), e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to database: " + e.getMessage(), e);
        }
    }

    public Bill searchBillById(int billId) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            return billDAO.getBillById(connection, billId);
        } catch (SQLException e) {
            throw new RuntimeException("Error searching for bill: " + e.getMessage(), e);
        }
    }
}
