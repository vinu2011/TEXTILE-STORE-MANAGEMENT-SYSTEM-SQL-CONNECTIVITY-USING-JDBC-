import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStockForm extends JFrame {
    private JTextField stockNameField, stockQuantityField, stockPriceField;
    private JTextField supplierNameField, supplierContactField, supplierAddressField;

    public AddStockForm() {
        setTitle("Add Stock");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2));

        JLabel stockNameLabel = new JLabel("Stock Name:");
        stockNameField = new JTextField();
        JLabel stockQuantityLabel = new JLabel("Stock Quantity:");
        stockQuantityField = new JTextField();
        JLabel stockPriceLabel = new JLabel("Stock Price:");
        stockPriceField = new JTextField();

        JLabel supplierNameLabel = new JLabel("Supplier Name:");
        supplierNameField = new JTextField();
        JLabel supplierContactLabel = new JLabel("Supplier Contact:");
        supplierContactField = new JTextField();
        JLabel supplierAddressLabel = new JLabel("Supplier Address:");
        supplierAddressField = new JTextField();

        JButton addButton = new JButton("Add");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Supplier supplier = new Supplier();
                    supplier.setSupplierName(supplierNameField.getText());
                    supplier.setContactInfo(supplierContactField.getText());
                    supplier.setAddress(supplierAddressField.getText());

                    SupplierDAO supplierDAO = new SupplierDAO();
                    supplierDAO.addSupplier(supplier);

                    Stock stock = new Stock();
                    stock.setStockName(stockNameField.getText());
                    stock.setQuantity(Integer.parseInt(stockQuantityField.getText()));
                    stock.setPrice(Double.parseDouble(stockPriceField.getText()));

                    StockDAO stockDAO = new StockDAO();
                    stockDAO.addStock(stock);

                    JOptionPane.showMessageDialog(null, "Stock and Supplier added successfully!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        add(stockNameLabel);
        add(stockNameField);
        add(stockQuantityLabel);
        add(stockQuantityField);
        add(stockPriceLabel);
        add(stockPriceField);
        add(supplierNameLabel);
        add(supplierNameField);
        add(supplierContactLabel);
        add(supplierContactField);
        add(supplierAddressLabel);
        add(supplierAddressField);
        add(new JLabel()); // Empty label for spacing
        add(addButton);
    }
}
