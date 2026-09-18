package expensetracker;

import javax.swing.*;
import java.awt.*;

public class InputPanel extends JPanel {
    public JTextField nameField = new JTextField();
    public JTextField amountField = new JTextField();
    public JComboBox<String> categoryBox = new JComboBox<>(new String[]{"Food", "Transport", "Academics", "Entertainment", "Other"});
    public JButton addButton = new JButton("Add Expense");
    public JButton deleteButton = new JButton("Delete Selected");

    public InputPanel() {
        setLayout(new GridLayout(4, 2, 8, 8));
        setBorder(BorderFactory.createTitledBorder("Add New Expense"));

        add(new JLabel("Expense Description:"));
        add(nameField);
        add(new JLabel("Amount ($):"));
        add(amountField);
        add(new JLabel("Category:"));
        add(categoryBox);
        add(addButton);
        add(deleteButton);
    }
}