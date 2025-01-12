import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class BillForm extends JFrame {
    private JTextField customerNameField;
    private JTextField contactInfoField;
    private JTextField addressField;
    private JTextField stockIdField;
    private JTextField quantityField;
    private JTextField totalAmountField;
    private JTextArea billDetailsArea;

    private BillingService billingService = new BillingService();

    public BillForm() {
        setTitle("Bill");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        customerNameField = new JTextField(20);
        contactInfoField = new JTextField(20);
        addressField = new JTextField(20);
        stockIdField = new JTextField(20);
        quantityField = new JTextField(20);
        totalAmountField = new JTextField(20);
        billDetailsArea = new JTextArea();
        billDetailsArea.setEditable(false);

        JPanel panel = new JPanel(new GridLayout(7, 2));
        panel.add(new JLabel("Customer Name:"));
        panel.add(customerNameField);
        panel.add(new JLabel("Contact Info:"));
        panel.add(contactInfoField);
        panel.add(new JLabel("Address:"));
        panel.add(addressField);
        panel.add(new JLabel("Stock ID:"));
        panel.add(stockIdField);
        panel.add(new JLabel("Quantity:"));
        panel.add(quantityField);
        panel.add(new JLabel("Total Amount:"));
        panel.add(totalAmountField);

        JButton generateBillButton = new JButton("Generate Bill");
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(generateBillButton);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(billDetailsArea), BorderLayout.CENTER);

        generateBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateBill();
            }
        });
    }

    private void generateBill() {
        try {
            String customerName = customerNameField.getText();
            String contactInfo = contactInfoField.getText();
            String address = addressField.getText();
            int stockId = Integer.parseInt(stockIdField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            double totalAmount = Double.parseDouble(totalAmountField.getText());

            Customer customer = new Customer();
            customer.setCustomerName(customerName);
            customer.setContactInfo(contactInfo);
            customer.setAddress(address);

            Purchase purchase = new Purchase();
            purchase.setStockId(stockId);
            purchase.setQuantity(quantity);
            purchase.setTotalAmount(totalAmount);

            Bill bill = new Bill();
            bill.setTotalAmount(totalAmount);
            bill.setDate(new Date()); // Set the current date

            billingService.generateBill(customer, purchase, bill);

            billDetailsArea.setText("");
            billDetailsArea.append("Customer Name: " + customerName + "\n");
            billDetailsArea.append("Contact Info: " + contactInfo + "\n");
            billDetailsArea.append("Address: " + address + "\n");
            billDetailsArea.append("Stock ID: " + stockId + "\n");
            billDetailsArea.append("Quantity: " + quantity + "\n");
            billDetailsArea.append("Total Amount: " + totalAmount + "\n");
            billDetailsArea.append("Bill ID: " + bill.getBillId() + "\n");
            billDetailsArea.append("Date: " + bill.getDate() + "\n"); // Display the date

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error generating bill: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BillForm().setVisible(true);
            }
        });
    }
}
