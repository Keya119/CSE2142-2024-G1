
import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {
        // Task #2: Argument check maintained
        if (args.length != 1) {
            System.out.println("Usage: java EmployeeManager <argument>");
            System.out.println("Arguments: 1, s, +<name>, ?<name>, c, u<name>, d<name>");
            return;
        }
        
        // Task #3: Meaningful variable names instead of single letters
        String userArgument = args[0]; // Changed from args[0] to userArgument
        
        if (userArgument.equals("1")) {
            System.out.println("Loading data ...");
            try {
                // Task #3: Changed r -> fileReader, l -> fileLine, e -> employees
                BufferedReader fileReader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );
                String fileLine = fileReader.readLine();
                String employees[] = fileLine.split(","); // Changed e -> employees
                
                for (String employee : employees) { // Changed emp -> employee
                    System.out.println(employee);
                }
                
                fileReader.close();
            } catch (Exception error) { // Changed e -> error
                System.out.println("Error reading file: " + error.getMessage());
            }
            System.out.println("Data Loaded.");
        } else if (userArgument.equals("s")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader fileReader = new BufferedReader( // r -> fileReader
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );
                String fileLine = fileReader.readLine(); // l -> fileLine
                String employees[] = fileLine.split(","); // e -> employees
                Random randomGenerator = new Random(); // rand -> randomGenerator
                int randomIndex = randomGenerator.nextInt(employees.length); // idx -> randomIndex
                System.out.println(employees[randomIndex]);
                
                fileReader.close();
            } catch (Exception error) {
                System.out.println("Error reading file: " + error.getMessage());
            }
            System.out.println("Data Loaded.");
        } else if (userArgument.contains("+")) {
            System.out.println("Loading data ...");
            try {
                BufferedWriter fileWriter = new BufferedWriter( // w -> fileWriter
                    new FileWriter("employees.txt", true)
                );
                String newEmployeeName = userArgument.substring(1); // n -> newEmployeeName
                fileWriter.write("," + newEmployeeName);
                fileWriter.close();
            } catch (Exception error) {
                System.out.println("Error writing to file: " + error.getMessage());
            }
            System.out.println("Data Loaded.");
        } else if (userArgument.contains("?")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader fileReader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );
                String fileLine = fileReader.readLine();
                String employees[] = fileLine.split(",");
                boolean employeeFound = false; // found -> employeeFound
                String searchName = userArgument.substring(1); // s -> searchName
                
                for (int i = 0; i < employees.length && !employeeFound; i++) {
                    if (employees[i].equals(searchName)) {
                        System.out.println("Employee found!");
                        employeeFound = true;
                    }
                }
                
                fileReader.close();
            } catch (Exception error) {
                System.out.println("Error reading file: " + error.getMessage());
            }
            System.out.println("Data Loaded.");
        } else if (userArgument.contains("c")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader fileReader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );
                String fileLine = fileReader.readLine();
                char[] characterArray = fileLine.toCharArray(); // chars -> characterArray
                boolean inWord = false;
                int wordCount = 0; // count -> wordCount
                
                for (char currentChar : characterArray) { // c -> currentChar
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
                fileReader.close();
            } catch (Exception error) {
                System.out.println("Error reading file: " + error.getMessage());
            }
            System.out.println("Data Loaded.");
        } else if (userArgument.contains("u")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader fileReader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );
                String fileLine = fileReader.readLine();
                String employees[] = fileLine.split(",");
                String employeeToUpdate = userArgument.substring(1); // n -> employeeToUpdate
                
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(employeeToUpdate)) {
                        employees[i] = "Updated";
                    }
                }
                
                BufferedWriter fileWriter = new BufferedWriter(
                    new FileWriter("employees.txt")
                );
                fileWriter.write(String.join(",", employees));
                fileWriter.close();
                fileReader.close();
            } catch (Exception error) {
                System.out.println("Error updating file: " + error.getMessage());
            }
            System.out.println("Data Updated.");
        } else if (userArgument.contains("d")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader fileReader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );
                String fileLine = fileReader.readLine();
                String employees[] = fileLine.split(",");
                String employeeToDelete = userArgument.substring(1); // n -> employeeToDelete
                List<String> employeeList = new ArrayList<>(Arrays.asList(employees)); // list -> employeeList
                employeeList.remove(employeeToDelete);
                
                BufferedWriter fileWriter = new BufferedWriter(
                    new FileWriter("employees.txt")
                );
                fileWriter.write(String.join(",", employeeList));
                fileWriter.close();
                fileReader.close();
            } catch (Exception error) {
                System.out.println("Error deleting from file: " + error.getMessage());
            }
            System.out.println("Data Deleted.");
        }
    }
}
