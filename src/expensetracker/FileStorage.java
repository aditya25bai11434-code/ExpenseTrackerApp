package expensetracker;

import java.io.*;
import java.util.List;

public class FileStorage {
    private static final String FILE_NAME = "expenses.txt";

    public static void saveExpenses(List<Expense> expenses) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Expense e : expenses) {
                writer.write(e.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving expenses: " + e.getMessage());
        }
    }
}