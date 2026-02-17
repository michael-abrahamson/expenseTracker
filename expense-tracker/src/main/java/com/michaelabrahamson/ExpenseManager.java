package com.michaelabrahamson;

import java.util.ArrayList;
import java.util.Scanner;

public class ExpenseManager {

    final static String[] ExpenseMenu = {"\n1. Create Expense", "2. View Expenses", "3. Update Expense", "4. Delete Expense", "5. View Summary", "6. View Monthly Summary", "7. Exit"};
    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        ArrayList<Expense> expenses = ExpenseStorage.loadExpenses();
        if (expenses == null) {
            expenses = new ArrayList<>();
        }

        for (String menuItem : ExpenseMenu) {
            System.out.println(menuItem);
        }
        while (true) {

            int choice;
            while (true) {
                System.out.print("\nEnter your choice: ");

                try {
                    choice = scanner.nextInt();
                    if (choice >= 1 && choice <= ExpenseMenu.length) {
                        break;
                    }
                    System.out.println("Invalid choice. Enter a number between 1 and " + ExpenseMenu.length);
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next(); // Clear the invalid input
                }
            }
            switch (choice) {
                case 1 ->
                    createExpense(expenses);
                case 2 ->
                    viewExpenses(expenses);
                case 3 ->
                    updateExpense(expenses);
                case 4 ->
                    deleteExpense(expenses);
                case 5 ->
                    viewSummary(expenses);
                case 6 ->
                    viewMonthlySummary(expenses);
                case 7 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
            }
        }
    }

    public static void createExpense(ArrayList<Expense> expenses) {

        System.out.print("\nEnter the description of the expense: ");
        String description = scanner.next();
        System.out.print("Enter the amount of the expense: ");
        double amount = scanner.nextDouble();
        System.out.print("Enter the date of the expense (YYYY-MM-DD): ");
        String date = scanner.next();

        int maxId = expenses.stream().mapToInt(Expense::getId).max().orElse(0);

        expenses.add(new Expense(maxId + 1, description, amount, date));
        ExpenseStorage.saveExpenses(expenses);

        System.out.println("\nExpense created successfully.");

    }

    public static void updateExpense(ArrayList<Expense> expenses) {

    }

    /**
     * This method deletes an expense from the saved expenses in the JSON file.
     * Via user given ID of the record to delete.
     *
     * @param expenses
     */
    public static void deleteExpense(ArrayList<Expense> expenses) {
        viewExpenses(expenses);

        System.out.println("\nPlease enter the ID of the expense you wish to delete: ");
        int id = scanner.nextInt();
        expenses.removeIf(expense -> expense.getId() == id);
        ExpenseStorage.saveExpenses(expenses);
        System.out.println("\nExpense deleted successfully.");
    }

    public static void viewSummary(ArrayList<Expense> expenses) {

    }

    public static void viewMonthlySummary(ArrayList<Expense> expenses) {

    }

    public static void viewExpenses(ArrayList<Expense> expenses) {
        if (expenses.isEmpty()) {
            System.out.println("\nNo expenses found.");
            return;
        }
        System.out.println("\nExpenses List:");
        expenses.forEach(System.out::println);
    }
}
