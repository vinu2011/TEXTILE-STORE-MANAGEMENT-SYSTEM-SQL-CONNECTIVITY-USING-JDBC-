import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Inventory Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(3, 1));

        JButton addStockButton = new JButton("Add Stock");
        JButton billButton = new JButton("Bill");
        JButton searchButton = new JButton("Search");

        addStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddStockForm().setVisible(true);
            }
        });

        billButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BillForm().setVisible(true);
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SearchForm().setVisible(true);
            }
        });

        frame.add(addStockButton);
        frame.add(billButton);
        frame.add(searchButton);

        frame.setVisible(true);
    }
}
