package com.michaelabrahamson;

import java.util.ArrayList;
import java.util.Scanner;

public class ExpenseManager {
    final static String[] ExpenseMenu = {"1. Create Expense","2. View Expenses", "3. Update Expense", "4. Delete Expense", "5. View Summary", "6. View Monthly Summary"};
    final static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {


        ArrayList<Expense> expenses = ExpenseStorage.loadExpenses();
        if (expenses == null) {
            expenses = new ArrayList<>();
        }
        System.out.println(expenses.toString());
        for (int i = 0; i < ExpenseMenu.length; i++) {
            System.out.println(ExpenseMenu[i]);
        }

        int choice;
        while (true) { 
            System.out.println("Enter your choice:");

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
            case 1 -> createExpense(expenses);
            case 2 -> viewExpenses(expenses);
            case 3 -> updateExpense(expenses);
            case 4 -> deleteExpense(expenses);
            case 5 -> viewSummary(expenses);
            case 6 -> viewMonthlySummary(expenses);
        }
    }

    public static void createExpense(ArrayList<Expense> expenses) {

        System.out.print("Enter the description of the expense: ");
        String description = scanner.next();
        System.out.print("Enter the amount of the expense: ");
        double amount = scanner.nextDouble();
        System.out.print("Enter the date of the expense (YYYY-MM-DD): ");
        String date = scanner.next();

        expenses.add(new Expense(expenses.size() + 1, description, amount, date));
        ExpenseStorage.saveExpenses(expenses);
        // expenses.forEach(System.out::println);
        
    }

    public static void updateExpense(ArrayList<Expense> expenses) {


    }

    public static void deleteExpense(ArrayList<Expense> expenses) {

    }

    public static void viewSummary(ArrayList<Expense> expenses) {

    }

    public static void viewMonthlySummary(ArrayList<Expense> expenses) {

    }

    public static void viewExpenses(ArrayList<Expense> expenses) {
    }
}