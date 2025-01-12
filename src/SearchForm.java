import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchForm extends JFrame {
    private JTextField searchField;
    private JTextArea searchResultsArea;
    private BillingService billingService = new BillingService();

    public SearchForm() {
        setTitle("Search Bills");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        searchField = new JTextField(20);
        searchResultsArea = new JTextArea();
        searchResultsArea.setEditable(false);

        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(new JLabel("Enter Bill ID:"));
        panel.add(searchField);

        JButton searchButton = new JButton("Search");
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(searchButton);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(searchResultsArea), BorderLayout.CENTER);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBill();
            }
        });
    }

    private void searchBill() {
        try {
            int billId = Integer.parseInt(searchField.getText());

            Bill bill = billingService.searchBillById(billId);
            if (bill != null) {
                searchResultsArea.setText("");
                searchResultsArea.append("Bill ID: " + bill.getBillId() + "\n");
                searchResultsArea.append("Purchase ID: " + bill.getPurchaseId() + "\n");
                searchResultsArea.append("Total Amount: " + bill.getTotalAmount() + "\n");
            } else {
                searchResultsArea.setText("Bill not found.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
        }
    }
}
