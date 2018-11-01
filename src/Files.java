import java.util.Scanner;
import java.io.*;

/**
 * Collection of methods for text file I/O.
 */
public class Files {

    /**
     * Main method. Creates instance of Files and calls runFileTests.
     * @param args args
     */
    public static void main(String[] args){
        Files files = new Files();
        files.runFileTests();
    }

    /**
     * Runs I/O methods in this class.
     */
    private void runFileTests(){
        readFromFile();
        writeToFile();
        copyFile();
    }

    /**
     * Reads from a file specified by the user and prints the content of it.
     */
    private void readFromFile() {
        Scanner askUser = new Scanner(System.in);

        boolean fileReadCorrectly = false;
        do{
            try{
                System.out.println("Enter the name of the file you want to read.");
                String fileName = askUser.nextLine() + ".txt";

                // setting up file reader
                FileReader fileReader = new FileReader("src/" + fileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                // grabs first line from file
                String nextFileLine = bufferedReader.readLine();

                // prints every line in file to console
                while (nextFileLine != null){
                    System.out.println(nextFileLine);
                    nextFileLine = bufferedReader.readLine();
                }

                bufferedReader.close();
                fileReadCorrectly = true;
            } catch (IOException e) {
                System.out.println("That file doesn't exist.");
            }
         } while (!fileReadCorrectly);
    }

    /**
     * Writes user input to "out.txt".
     */
    private void writeToFile() {
        Scanner userWrite = new Scanner(System.in);

        // setting up file writer
        FileOutputStream outputStream;
        PrintWriter printWriter = null;

        try {
            // specifies output file
            outputStream = new FileOutputStream("out.txt");
            printWriter = new PrintWriter(outputStream);

            String line;

            do {
                System.out.println("Enter something to be written to the file, or hit Enter to finish.");
                line = userWrite.nextLine();
                printWriter.println(line);
            } while (!line.isEmpty()); // keeps going until user enters empty line
        } catch (IOException e) { // in case something goes wrong
            System.out.println("Something has gone wrong!");
        } finally {
            // printWriter will be null if something has gone wrong
            if (printWriter != null) {
                System.out.println("Written to out.txt.");
                printWriter.close();
            }
        }
    }

    /**
     * Copies the content of one file to another, the names of which are user-specified.
     */
    private void copyFile() {

        Scanner askUser = new Scanner(System.in);
        boolean noErrors = false;

        // setup for file writer
        FileOutputStream outputStream;
        PrintWriter printWriter;

        do {
            try {
                System.out.println("Enter the name of the file you want to copy.");
                String sourceFileName = askUser.nextLine() + ".txt";

                // sets up reader from specified input file
                FileReader fileReader = new FileReader("src/" + sourceFileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                System.out.println("Enter the name of the file you want to copy to.");
                String destinationFileName = askUser.nextLine() + ".txt";

                // output file is set to what user specifies
                outputStream = new FileOutputStream(destinationFileName);
                printWriter = new PrintWriter(outputStream);

                // grabs first line from input file
                String nextFileLine = bufferedReader.readLine();

                // goes through entire file until end is reached
                while (nextFileLine != null){
                    printWriter.println(nextFileLine);
                    nextFileLine = bufferedReader.readLine();
                }

                noErrors = true;
                bufferedReader.close();
                printWriter.close();
            } catch (IOException e) {
                System.out.println("Something has gone wrong! Please try again.");
            }
        } while (!noErrors);

    }

}
