/**
 * EmployeeManager - Main class for managing employee data
 * Task #10: Added comprehensive class documentation
 */
import java.io.*;
import java.util.*;

public class EmployeeManager {
    
    /**
     * Reads employee data from file and returns as array
     * Task #10: Added method documentation
     */
    private static String[] readEmployeesFromFile() throws IOException {
        BufferedReader fileReader = new BufferedReader(
            new InputStreamReader(new FileInputStream(Constants.FILE_NAME)));
        String fileLine = fileReader.readLine();
        fileReader.close();
        return fileLine.split(Constants.COMMA_DELIMITER);
    }
    
    /**
     * Writes employee data to file
     * Task #10: Added method documentation
     */
    private static void writeEmployeesToFile(String[] employees) throws IOException {
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(Constants.FILE_NAME));
        fileWriter.write(String.join(Constants.COMMA_DELIMITER, employees));
        fileWriter.close();
    }
    
    /**
     * Displays all employees from the data file
     * Task #10: Added method documentation
     */
    private static void displayAllEmployees() {
        System.out.println(Constants.LOADING_DATA_MSG);
        try {
            String[] employees = readEmployeesFromFile();
            for (String employee : employees) {
                System.out.println(employee);
            }
        } catch (Exception error) {
            System.out.println("Error reading file: " + error.getMessage());
        }
        System.out.println(Constants.DATA_LOADED_MSG);
    }
    
    /**
     * Displays a random employee from the file
     * Task #10: Added method documentation
     */
    private static void displayRandomEmployee() {
        System.out.println(Constants.LOADING_DATA_MSG);
        try {
            String[] employees = readEmployeesFromFile();
            System.out.println(employees[new Random().nextInt(employees.length)]);
        } catch (Exception error) {
            System.out.println("Error reading file: " + error.getMessage());
        }
        System.out.println(Constants.DATA_LOADED_MSG);
    }
    
    /**
     * Adds a new employee to the file
     * Task #10: Added method documentation
     */
    private static void addEmployee(String employeeName) {
        System.out.println(Constants.LOADING_DATA_MSG);
        try {
            BufferedWriter fileWriter = new BufferedWriter(
                new FileWriter(Constants.FILE_NAME, true));
            fileWriter.write(Constants.COMMA_DELIMITER + employeeName);
            fileWriter.close();
        } catch (Exception error) {
            System.out.println("Error writing to file: " + error.getMessage());
        }
        System.out.println(Constants.DATA_LOADED_MSG);
    }
    
    /**
     * Searches for an employee in the file
     * Task #10: Added method documentation
     */
    private static void searchEmployee(String employeeName) {
        System.out.println(Constants.LOADING_DATA_MSG);
        try {
            String[] employees = readEmployeesFromFile();
            
            for (String employee : employees) {
                if (employee.equals(employeeName)) {
                    System.out.println(Constants.EMPLOYEE_FOUND_MSG);
                    System.out.println(Constants.DATA_LOADED_MSG);
                    return;
                }
            }
            
            System.out.println("Employee '" + employeeName + "' not found!");
            
        } catch (Exception error) {
            System.out.println("Error reading file: " + error.getMessage());
        }
        System.out.println(Constants.DATA_LOADED_MSG);
    }
    
    /**
     * Counts employees and total characters in the file
     * Task #10: Added method documentation
     */
    private static void countEmployeesAndCharacters() {
        System.out.println(Constants.LOADING_DATA_MSG);
        try {
            String[] employees = readEmployeesFromFile();
            
            int employeeCount = employees.length;
            int totalCharacters = String.join(Constants.COMMA_DELIMITER, employees).length();
            
            System.out.println(employeeCount + Constants.WORDS_FOUND_MSG + totalCharacters);
            
        } catch (Exception error) {
            System.out.println("Error reading file: " + error.getMessage());
        }
        System.out.println(Constants.DATA_LOADED_MSG);
    }
    
    /**
     * Updates an employee's name in the file
     * Task #10: Added method documentation
     */
    private static void updateEmployee(String employeeName) {
        System.out.println(Constants.LOADING_DATA_MSG);
        try {
            String[] employees = readEmployeesFromFile();
            String employeeToUpdate = employeeName;
            
            for (int i = 0; i < employees.length; i++) {
                if (employees[i].equals(employeeToUpdate)) {
                    employees[i] = Constants.UPDATED_FLAG;
                }
            }
            
            writeEmployeesToFile(employees);
        } catch (Exception error) {
            System.out.println("Error updating file: " + error.getMessage());
        }
        System.out.println(Constants.DATA_UPDATED_MSG);
    }
    
    /**
     * Deletes an employee from the file
     * Task #10: Added method documentation
     */
    private static void deleteEmployee(String employeeName) {
        System.out.println(Constants.LOADING_DATA_MSG);
        try {
            String[] employees = readEmployeesFromFile();
            String employeeToDelete = employeeName;
            List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
            employeeList.remove(employeeToDelete);
            
            writeEmployeesToFile(employeeList.toArray(new String[0]));
        } catch (Exception error) {
            System.out.println("Error deleting from file: " + error.getMessage());
        }
        System.out.println(Constants.DATA_DELETED_MSG);
    }
    
    /**
     * Handles invalid command line arguments with helpful error message
     * Task #10: Added method documentation
     */
    private static void handleInvalidArgument(String invalidArgument) {
        System.out.println("Error: Invalid argument '" + invalidArgument + "'");
        System.out.println("Valid arguments:");
        System.out.println("  1           - Display all employees");
        System.out.println("  s           - Show random employee");
        System.out.println("  +<name>     - Add new employee");
        System.out.println("  ?<name>     - Search employee");
        System.out.println("  c           - Count employees and characters");
        System.out.println("  u<name>     - Update employee");
        System.out.println("  d<name>     - Delete employee");
    }
    
    /**
     * Main method - handles command line arguments and executes appropriate operations
     * Task #10: Added method documentation
     */
    public static void main(String[] args) {
        // Validate command line arguments
        if (args.length != 1) {
            System.out.println("Usage: java EmployeeManager <argument>");
            System.out.println("Arguments: 1, s, +<name>, ?<name>, c, u<name>, d<name>");
            return;
        }
        
        // Task #10: Improved variable name
        String userArgument = args[0];
        
        // Route to appropriate functionality based on user input
        if (userArgument.equals("1")) {
            displayAllEmployees();
        } else if (userArgument.equals("s")) {
            displayRandomEmployee();
        } else if (userArgument.startsWith("+") && userArgument.length() > 1) {
            addEmployee(userArgument.substring(1));
        } else if (userArgument.startsWith("?") && userArgument.length() > 1) {
            searchEmployee(userArgument.substring(1));
        } else if (userArgument.equals("c")) {
            countEmployeesAndCharacters();
        } else if (userArgument.startsWith("u") && userArgument.length() > 1) {
            updateEmployee(userArgument.substring(1));
        } else if (userArgument.startsWith("d") && userArgument.length() > 1) {
            deleteEmployee(userArgument.substring(1));
        } else {
            handleInvalidArgument(userArgument);
        }
    }
}