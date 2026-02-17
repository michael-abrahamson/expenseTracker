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

        while (true) {
            for (String menuItem : ExpenseMenu) {
                System.out.println(menuItem);
            }

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
                    viewYearSummary(expenses, "2023");
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
    /**
     * This method updates an existing record of an expense from the JSON file. Via user provided id
     * @param expenses
     */
    public static void updateExpense(ArrayList<Expense> expenses) {
        viewExpenses(expenses);
        System.out.println("\nPlease enter the ID of the expense you wish to update: ");
        int id = scanner.nextInt();
        
        Expense expense = expenses.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
        if (expense == null) {
            System.out.println("\nExpense with ID " + id + " not found.");
            return;
        }

        expenses.remove(expense);
        System.out.println("\n 1. Update Description \n 2. Update Amount \n 3. Update Date");
        System.out.print("\n Please enter the field you wish to change: ");
        int field = scanner.nextInt();
        switch (field) {
            case 1 -> {
                System.out.print("Enter the new description: ");
                String newDescription = scanner.next();
                expenses.add(new Expense(id, newDescription, expense.getAmount(), expense.getDate()));

            }
            case 2 -> {
                System.out.print("Enter the new amount: ");
                double newAmount = scanner.nextDouble();
                expenses.add(new Expense(id, expense.getDescription(), newAmount, expense.getDate()));

            }
            case 3 -> {
                System.out.print("Enter the new date (YYYY-MM-DD): ");
                String newDate = scanner.next();
                expenses.add(new Expense(id, expense.getDescription(), expense.getAmount(), newDate));

            }
        }

        System.out.println("\nExpense updated successfully.");
        ExpenseStorage.saveExpenses(expenses);
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
        double total = expenses.stream().mapToDouble(Expense::getAmount).sum();
        System.out.println("\nTotal Expenses: $" + total);
    }

    public static void viewYearSummary(ArrayList<Expense> expenses, String year) {

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
