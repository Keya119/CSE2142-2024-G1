import java.io.*;
import java.util.*;

public class EmployeeManager {
    
    // Task #4: Refactored file read method with Constants
    private static String[] readEmployeesFromFile() throws IOException {
        BufferedReader fileReader = new BufferedReader(
            new InputStreamReader(new FileInputStream(Constants.FILE_NAME)));
        String fileLine = fileReader.readLine();
        fileReader.close();
        return fileLine.split(Constants.COMMA_DELIMITER);
    }
    
    // Task #4: Refactored file write method with Constants
    private static void writeEmployeesToFile(String[] employees) throws IOException {
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(Constants.FILE_NAME));
        fileWriter.write(String.join(Constants.COMMA_DELIMITER, employees));
        fileWriter.close();
    }
    
    public static void main(String[] args) {
        // Task #2: Argument check maintained
        if (args.length != 1) {
            System.out.println("Usage: java EmployeeManager <argument>");
            System.out.println("Arguments: 1, s, +<name>, ?<name>, c, u<name>, d<name>");
            return;
        }
        
        // Task #3: Meaningful variable names maintained
        String userArgument = args[0];
        
        if (userArgument.equals("1")) {
            // Task #5: Using constants for messages
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
        } else if (userArgument.equals("s")) {
            // Task #5: Using constants for messages
            System.out.println(Constants.LOADING_DATA_MSG);
            try {
                String[] employees = readEmployeesFromFile();
                Random randomGenerator = new Random();
                int randomIndex = randomGenerator.nextInt(employees.length);
                System.out.println(employees[randomIndex]);
            } catch (Exception error) {
                System.out.println("Error reading file: " + error.getMessage());
            }
            System.out.println(Constants.DATA_LOADED_MSG);
        } else if (userArgument.contains("+")) {
            // Task #5: Using constants for messages and file operations
            System.out.println(Constants.LOADING_DATA_MSG);
            try {
                BufferedWriter fileWriter = new BufferedWriter(
                    new FileWriter(Constants.FILE_NAME, true));
                String newEmployeeName = userArgument.substring(1);
                fileWriter.write(Constants.COMMA_DELIMITER + newEmployeeName);
                fileWriter.close();
            } catch (Exception error) {
                System.out.println("Error writing to file: " + error.getMessage());
            }
            System.out.println(Constants.DATA_LOADED_MSG);
        } else if (userArgument.contains("?")) {
            // Task #5: Using constants for messages
            System.out.println(Constants.LOADING_DATA_MSG);
            try {
                String[] employees = readEmployeesFromFile();
                boolean employeeFound = false;
                String searchName = userArgument.substring(1);
                
                for (int i = 0; i < employees.length && !employeeFound; i++) {
                    if (employees[i].equals(searchName)) {
                        System.out.println(Constants.EMPLOYEE_FOUND_MSG);
                        employeeFound = true;
                    }
                }
            } catch (Exception error) {
                System.out.println("Error reading file: " + error.getMessage());
            }
            System.out.println(Constants.DATA_LOADED_MSG);
        } else if (userArgument.contains("c")) {
            // Task #5: Using constants for messages
            System.out.println(Constants.LOADING_DATA_MSG);
            try {
                String[] employees = readEmployeesFromFile();
                char[] characterArray = String.join(Constants.COMMA_DELIMITER, employees).toCharArray();
                boolean inWord = false;
                int wordCount = 0;
                
                for (char currentChar : characterArray) {
                    if (currentChar == ' ') {
                        if (!inWord) {
                            wordCount++;
                            inWord = true;
                        }
                    } else {
                        inWord = false;
                    }
                }
                
                System.out.println(wordCount + Constants.WORDS_FOUND_MSG + characterArray.length);
            } catch (Exception error) {
                System.out.println("Error reading file: " + error.getMessage());
            }
            System.out.println(Constants.DATA_LOADED_MSG);
        } else if (userArgument.contains("u")) {
            // Task #5: Using constants for messages and update flag
            System.out.println(Constants.LOADING_DATA_MSG);
            try {
                String[] employees = readEmployeesFromFile();
                String employeeToUpdate = userArgument.substring(1);
                
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
        } else if (userArgument.contains("d")) {
            // Task #5: Using constants for messages
            System.out.println(Constants.LOADING_DATA_MSG);
            try {
                String[] employees = readEmployeesFromFile();
                String employeeToDelete = userArgument.substring(1);
                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                employeeList.remove(employeeToDelete);
                
                writeEmployeesToFile(employeeList.toArray(new String[0]));
            } catch (Exception error) {
                System.out.println("Error deleting from file: " + error.getMessage());
            }
            System.out.println(Constants.DATA_DELETED_MSG);
        }
    }
}// Task #5: New Constants class for storing string literals
public class Constants {
    public static final String FILE_NAME = "employees.txt";
    public static final String DATA_LOADED_MSG = "Data Loaded.";
    public static final String LOADING_DATA_MSG = "Loading data ...";
    public static final String EMPLOYEE_FOUND_MSG = "Employee found!";
    public static final String DATA_UPDATED_MSG = "Data Updated.";
    public static final String DATA_DELETED_MSG = "Data Deleted.";
    public static final String WORDS_FOUND_MSG = " word(s) found ";
    public static final String COMMA_DELIMITER = ",";
    public static final String UPDATED_FLAG = "Updated";
}