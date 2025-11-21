import java.io.*;
import java.util.*;

public class EmployeeManager {
    
    private static String[] readEmployeesFromFile() throws IOException {
        BufferedReader fileReader = new BufferedReader(
            new InputStreamReader(new FileInputStream(Constants.FILE_NAME)));
        String fileLine = fileReader.readLine();
        fileReader.close();
        return fileLine.split(Constants.COMMA_DELIMITER);
    }
    
    private static void writeEmployeesToFile(String[] employees) throws IOException {
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(Constants.FILE_NAME));
        fileWriter.write(String.join(Constants.COMMA_DELIMITER, employees));
        fileWriter.close();
    }
    
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
    
    // Task #8: New method for simplified count operation
    private static void countEmployeesAndCharacters() {
        System.out.println(Constants.LOADING_DATA_MSG);
        try {
            String[] employees = readEmployeesFromFile();
            
            // Task #8: Simplified logic - just count array length for employees
            int employeeCount = employees.length;
            int totalCharacters = String.join(Constants.COMMA_DELIMITER, employees).length();
            
            System.out.println(employeeCount + Constants.WORDS_FOUND_MSG + totalCharacters);
            
        } catch (Exception error) {
            System.out.println("Error reading file: " + error.getMessage());
        }
        System.out.println(Constants.DATA_LOADED_MSG);
    }
    
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java EmployeeManager <argument>");
            System.out.println("Arguments: 1, s, +<name>, ?<name>, c, u<name>, d<name>");
            return;
        }
        
        if (args[0].equals("1")) {
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
        } else if (args[0].equals("s")) {
            System.out.println(Constants.LOADING_DATA_MSG);
            try {
                String[] employees = readEmployeesFromFile();
                System.out.println(employees[new Random().nextInt(employees.length)]);
            } catch (Exception error) {
                System.out.println("Error reading file: " + error.getMessage());
            }
            System.out.println(Constants.DATA_LOADED_MSG);
        } else if (args[0].contains("+")) {
            System.out.println(Constants.LOADING_DATA_MSG);
            try {
                BufferedWriter fileWriter = new BufferedWriter(
                    new FileWriter(Constants.FILE_NAME, true));
                fileWriter.write(Constants.COMMA_DELIMITER + args[0].substring(1));
                fileWriter.close();
            } catch (Exception error) {
                System.out.println("Error writing to file: " + error.getMessage());
            }
            System.out.println(Constants.DATA_LOADED_MSG);
        } else if (args[0].contains("?")) {
            searchEmployee(args[0].substring(1));
        } else if (args[0].contains("c")) {
            // Task #8: Using simplified count method
            countEmployeesAndCharacters();
        } else if (args[0].contains("u")) {
            System.out.println(Constants.LOADING_DATA_MSG);
            try {
                String[] employees = readEmployeesFromFile();
                String employeeToUpdate = args[0].substring(1);
                
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
        } else if (args[0].contains("d")) {
            System.out.println(Constants.LOADING_DATA_MSG);
            try {
                String[] employees = readEmployeesFromFile();
                String employeeToDelete = args[0].substring(1);
                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                employeeList.remove(employeeToDelete);
                
                writeEmployeesToFile(employeeList.toArray(new String[0]));
            } catch (Exception error) {
                System.out.println("Error deleting from file: " + error.getMessage());
            }
            System.out.println(Constants.DATA_DELETED_MSG);
        }
    }
}