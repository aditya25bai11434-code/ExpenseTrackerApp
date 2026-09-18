package expensetracker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Main extends JFrame {
    private ExpenseManager manager = new ExpenseManager();
    private InputPanel inputPanel = new InputPanel();
    private DefaultTableModel tableModel;
    private JTable expenseTable;
    private JLabel totalLabel = new JLabel("Total Spent: $0.00");

    public Main() {
        setTitle("Personal Expense Tracker");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        add(inputPanel, BorderLayout.NORTH);

        String[] columnNames = {"Description", "Category", "Amount ($)"};
        tableModel = new DefaultTableModel(columnNames, 0);
        expenseTable = new JTable(tableModel);
        add(new JScrollPane(expenseTable), BorderLayout.CENTER);

        JPanel summaryPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        summaryPanel.add(totalLabel);
        add(summaryPanel, BorderLayout.SOUTH);

        inputPanel.addButton.addActionListener(e -> addExpense());
        inputPanel.deleteButton.addActionListener(e -> deleteExpense());
    }

    private void addExpense() {
        String name = inputPanel.nameField.getText().trim();
        String amountText = inputPanel.amountField.getText().trim();
        String category = (String) inputPanel.categoryBox.getSelectedItem();

        if (name.isEmpty() || amountText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both description and amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double amount = Double.parseDouble(amountText);
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Amount must be greater than zero.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Expense expense = new Expense(name, category, amount);
            manager.addExpense(expense);
            tableModel.addRow(new Object[]{name, category, String.format("%.2f", amount)});
            totalLabel.setText(String.format("Total Spent: $%.2f", manager.getTotalExpense()));

            FileStorage.saveExpenses(manager.getExpenses());

            inputPanel.nameField.setText("");
            inputPanel.amountField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteExpense() {
        int selectedRow = expenseTable.getSelectedRow();
        if (selectedRow != -1) {
            manager.removeExpense(selectedRow);
            tableModel.removeRow(selectedRow);
            totalLabel.setText(String.format("Total Spent: $%.2f", manager.getTotalExpense()));
            FileStorage.saveExpenses(manager.getExpenses());
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.", "Selection Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}