import java.util.Scanner;
/**
 * Contains methods to create a menu with options to run methods in Files.java.
 *
 * @author Nick De Villiers
 * @version 1.0
 */
public class Menu {

    private Files files = new Files();

    public static void main(String[] args){
        Menu menu = new Menu();
        menu.processChoice();
    }

    private void displayMenu(){
        System.out.println("\nPlease select one of the following options:");
        System.out.println("\n1: Read from a file");
        System.out.println("2: Write to a file");
        System.out.println("3: Decipher mystery.txt");
        System.out.println("4: Calculate scores from details.txt");
        System.out.println("5: Copy a file");
        System.out.println("6: Run all");
        System.out.println("0: Exit");
    }

    private void processChoice(){
        boolean finished = false; // set to true when the user wants to exit to break the loop
        Scanner choiceScanner = new Scanner(System.in);
        while (!finished) { // keeps going while the user hasn't chosen to exit
            displayMenu();
            String userChoice = choiceScanner.nextLine();
            switch (userChoice){
                case "1":
                    files.readFromFile();
                    break;
                case "2":
                    files.writeToFile();
                    break;
                case "3":
                    files.decipherFile();
                    break;
                case "4":
                    files.calculateScores();
                    break;
                case "5":
                    files.copyFile();
                    break;
                case "6":
                    files.runFileTests();
                    break;
                case "0":
                    finished = true; // Big while loop that this is in will now break.
                    break;
                default:
                    System.out.println("That input was invalid, please try again.");
                    break;
            }
        }
        System.out.println("Goodbye.");
        System.exit(0); // Closes program like normal.
    }
}
