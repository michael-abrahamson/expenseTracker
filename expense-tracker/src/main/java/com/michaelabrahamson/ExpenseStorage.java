package com.michaelabrahamson;

import java.io.File;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class is responsible for writing the expenses to a JSON file. and
 * reading the expenses from a json file.
 */
public class ExpenseStorage {

    static ObjectMapper mapper = new ObjectMapper();
    final static String filePath = "expenses.json";

    public static void saveExpenses(ArrayList<Expense> expenses) {
        try {
            mapper.writeValue(new File(filePath), expenses);
        } catch (Exception e) {
            System.out.println("Error converting expense to JSON: " + e.getMessage());
        }

    }

    /**
     * This method reads the expenses from a JSON file and returns an ArrayList
     * of Expense objects.
     *
     * @return
     */
    public static ArrayList<Expense> loadExpenses() {

        try {
            File file = new File(filePath);
            if (!file.exists()) return new ArrayList<>();
            return mapper.readValue(file, new TypeReference<ArrayList<Expense>>() {});
        } catch (Exception e) {
            System.out.println("Error reading expenses from JSON: " + e.getMessage());
        }
        return new ArrayList<>();

    }
}
