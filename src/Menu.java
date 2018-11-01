import java.util.Scanner;

/**
 * Contains methods to create a menu with options to run methods in Files.java.
 *
 * @author Nick De Villiers
 * @version 1.0
 */
public class Menu {

    private Files files = new Files();

    /**
     * Main method.
     *
     * @param args args
     */
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.processChoice();
    }

    /**
     * Displays menu in terminal.
     */
    private void displayMenu() {
        System.out.println("\nPlease select one of the following options:");
        System.out.println("\n1: Read from a file");
        System.out.println("2: Write to a file");
        System.out.println("3: Decipher mystery.txt");
        System.out.println("4: Calculate scores from details.txt");
        System.out.println("5: Copy a file");
        System.out.println("6: Run all");
        System.out.println("7: Check if a file is readable");
        System.out.println("8: Write a 2D array to out.txt");
        System.out.println("0: Exit");
    }

    /**
     * Processes the user's input from the menu. Options are:
     * 1 - Read from file
     * 2 - Write to a file
     * 3 - Decipher mystery.txt
     * 4 - Calculate scores from details.txt
     * 5 - Copy a file
     * 6 - Run all the commands
     * 7 - Check if a file is readable
     * 8 - Write a 2D array to out.txt
     * 0 - Exit program
     */
    private void processChoice() {
        boolean finished = false; // set to true when the user wants to exit to break the loop
        Scanner choiceScanner = new Scanner(System.in);
        while (!finished) { // keeps going while the user hasn't chosen to exit
            displayMenu();
            String userChoice = choiceScanner.nextLine();
            switch (userChoice) {
                case "1": // user chooses 1
                    files.readFromFile();
                    break;
                case "2": // user chooses 2
                    files.writeToFile();
                    break;
                case "3": // user chooses 3
                    files.decipherFile();
                    break;
                case "4": // user chooses 4
                    files.calculateScores();
                    break;
                case "5": // user chooses 5
                    files.copyFile();
                    break;
                case "6": // user chooses 6
                    files.runFileTests();
                    break;
                case "7": // user chooses 7
                    Scanner userInput = new Scanner(System.in);
                    System.out.println("Enter the file name");
                    String fileName = userInput.nextLine() + ".txt";
                    boolean readable = files.checkFile(fileName);
                    if (!readable) {
                        System.out.println("That file is not readable or does not exist.");
                    } else {
                        System.out.println("That file exists and is readable.");
                    }
                    break;
                case "8":
                    files.write2DArray();
                    break;
                case "0": // user chooses 0
                    finished = true; // Big while loop that this is in will now break.
                    break;
                default: // user enters invalid input
                    System.out.println("That input was invalid, please try again.");
                    break;
            }
        }
        System.out.println("Goodbye.");
        System.exit(0); // Closes program like normal.
    }
}
