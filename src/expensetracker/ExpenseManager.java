package expensetracker;

import java.util.ArrayList;
import java.util.List;

public class ExpenseManager {
    private List<Expense> expenses = new ArrayList<>();
    private double totalExpense = 0.0;

    public void addExpense(Expense expense) {
        expenses.add(expense);
        totalExpense += expense.getAmount();
    }

    public void removeExpense(int index) {
        if (index >= 0 && index < expenses.size()) {
            Expense removed = expenses.remove(index);
            totalExpense -= removed.getAmount();
        }
    }

    public double getTotalExpense() { return totalExpense; }
    public List<Expense> getExpenses() { return expenses; }
}