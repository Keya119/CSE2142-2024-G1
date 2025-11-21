import java.io.*;
import java.util.*;

public class EmployeeManager {
    
    // Task #4: Refactor duplicate file read logic into method
    private static String[] readEmployeesFromFile() throws IOException {
        BufferedReader fileReader = new BufferedReader(
            new InputStreamReader(new FileInputStream("employees.txt")));
        String fileLine = fileReader.readLine();
        fileReader.close();
        return fileLine.split(",");
    }
    
    // Task #4: Refactor duplicate file write logic into method
    private static void writeEmployeesToFile(String[] employees) throws IOException {
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter("employees.txt"));
        fileWriter.write(String.join(",", employees));
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
            System.out.println("Loading data ...");
            try {
                // Task #4: Using refactored method instead of duplicate code
                String[] employees = readEmployeesFromFile();
                for (String employee : employees) {
                    System.out.println(employee);
                }
            } catch (Exception error) {
                System.out.println("Error reading file: " + error.getMessage());
            }
            System.out.println("Data Loaded.");
        } else if (userArgument.equals("s")) {
            System.out.println("Loading data ...");
            try {
                // Task #4: Using refactored method
                String[] employees = readEmployeesFromFile();
                Random randomGenerator = new Random();
                int randomIndex = randomGenerator.nextInt(employees.length);
                System.out.println(employees[randomIndex]);
            } catch (Exception error) {
                System.out.println("Error reading file: " + error.getMessage());
            }
            System.out.println("Data Loaded.");
        } else if (userArgument.contains("+")) {
            System.out.println("Loading data ...");
            try {
                // Task #4: For append operation, still using direct write
                BufferedWriter fileWriter = new BufferedWriter(
                    new FileWriter("employees.txt", true));
                String newEmployeeName = userArgument.substring(1);
                fileWriter.write("," + newEmployeeName);
                fileWriter.close();
            } catch (Exception error) {
                System.out.println("Error writing to file: " + error.getMessage());
            }
            System.out.println("Data Loaded.");
        } else if (userArgument.contains("?")) {
            System.out.println("Loading data ...");
            try {
                // Task #4: Using refactored method
                String[] employees = readEmployeesFromFile();
                boolean employeeFound = false;
                String searchName = userArgument.substring(1);
                
                for (int i = 0; i < employees.length && !employeeFound; i++) {
                    if (employees[i].equals(searchName)) {
                        System.out.println("Employee found!");
                        employeeFound = true;
                    }
                }
            } catch (Exception error) {
                System.out.println("Error reading file: " + error.getMessage());
            }
            System.out.println("Data Loaded.");
        } else if (userArgument.contains("c")) {
            System.out.println("Loading data ...");
            try {
                // Task #4: Using refactored method
                String[] employees = readEmployeesFromFile();
                char[] characterArray = String.join(",", employees).toCharArray();
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
                
                System.out.println(wordCount + " word(s) found " + characterArray.length);
            } catch (Exception error) {
                System.out.println("Error reading file: " + error.getMessage());
            }
            System.out.println("Data Loaded.");
        } else if (userArgument.contains("u")) {
            System.out.println("Loading data ...");
            try {
                // Task #4: Using refactored methods
                String[] employees = readEmployeesFromFile();
                String employeeToUpdate = userArgument.substring(1);
                
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(employeeToUpdate)) {
                        employees[i] = "Updated";
                    }
                }
                
                writeEmployeesToFile(employees);
            } catch (Exception error) {
                System.out.println("Error updating file: " + error.getMessage());
            }
            System.out.println("Data Updated.");
        } else if (userArgument.contains("d")) {
            System.out.println("Loading data ...");
            try {
                // Task #4: Using refactored methods
                String[] employees = readEmployeesFromFile();
                String employeeToDelete = userArgument.substring(1);
                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                employeeList.remove(employeeToDelete);
                
                writeEmployeesToFile(employeeList.toArray(new String[0]));
            } catch (Exception error) {
                System.out.println("Error deleting from file: " + error.getMessage());
            }
            System.out.println("Data Deleted.");
        }
    }
}
