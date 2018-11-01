import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

/**
 * Collection of methods for text file I/O.
 */
public class Files {

    private static final String crypt1 = "cipherabdfgjk";
    private static final String crypt2 = "lmnoqstuvwxyz";

    /**
     * Runs I/O methods in this class.
     */
    void runFileTests() {
        readFromFile();
        writeToFile();
        copyFile();
        decipherFile();
        calculateScores();
    }

    /**
     * Reads from a file specified by the user and prints the content of it.
     */
    void readFromFile() {
        Scanner askUser = new Scanner(System.in);

        boolean fileReadCorrectly = false;
        do {
            try {
                System.out.println("Enter the name of the file you want to read.");
                String fileName = askUser.nextLine() + ".txt";

                // setting up file reader
                FileReader fileReader = new FileReader("src/" + fileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                // grabs first line from file
                String nextFileLine = bufferedReader.readLine();

                // prints every line in file to console
                while (nextFileLine != null) {
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
    void writeToFile() {
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
    void copyFile() {

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
                while (nextFileLine != null) {
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

    /**
     * Deciphers mystery.txt.
     */
    void decipherFile() {

        // setup for file writer
        FileOutputStream outputStream;
        PrintWriter printWriter;

        try {
            // sets up reader from mystery.txt
            FileReader fileReader = new FileReader("src/mystery.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // output file is set to deciphered.txt
            outputStream = new FileOutputStream("deciphered.txt");
            printWriter = new PrintWriter(outputStream);

            // grabs first line from input file
            String nextFileLine = bufferedReader.readLine();

            // goes through entire file until end is reached
            while (nextFileLine != null) {
                String decipheredString = cipherDecipherString(nextFileLine);
                System.out.println(decipheredString);
                printWriter.println(decipheredString);
                nextFileLine = bufferedReader.readLine();
            }

            bufferedReader.close();
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Something has gone wrong!");
        }
    }

    /**
     * Method to encipher and decipher a given String using parallel arrays (crypt1 & crypt2)
     *
     * @param text A String containing text that is to be enciphered or deciphered
     * @return A new String containing the result, e.g. the en/deciphered version of the String provided as an input
     */
    private static String cipherDecipherString(String text) {
        // declare variables we need
        int i, j;
        boolean found = false;
        String temp = ""; // empty String to hold converted text

        for (i = 0; i < text.length(); i++) // look at every character in text
        {
            found = false;
            if ((j = crypt1.indexOf(text.charAt(i))) > -1) // is char in crypt1?
            {
                found = true; // yes!
                temp = temp + crypt2.charAt(j); // add the cipher character to temp
            } else if ((j = crypt2.indexOf(text.charAt(i))) > -1) // and so on
            {
                found = true;
                temp = temp + crypt1.charAt(j);
            }

            if (!found) // to deal with cases where char is NOT in crypt2 or 2
            {
                temp = temp + text.charAt(i); // just copy across the character
            }
        }
        return temp;
    }

    /**
     * Calculates final scores from details.txt, prints in console, and writes to "final scores.txt".
     */
    void calculateScores() {
        // setup for file writer
        FileOutputStream outputStream;
        PrintWriter printWriter;

        boolean fileReadCorrectly = false;
        do {
            try {
                // setting up file reader
                FileReader fileReader = new FileReader("src/details.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                // output file is set to deciphered.txt
                outputStream = new FileOutputStream("final scores.txt");
                printWriter = new PrintWriter(outputStream);

                // grabs first line from file
                String nextFileLine = bufferedReader.readLine();

                String[] list;
                String firstName;
                String lastName;
                String scoreStatement;

                while (nextFileLine != null) {
                    list = nextFileLine.split(" ");
                    firstName = list[0];
                    lastName = list[1];
                    int count = 0;
                    int total = 0;
                    int score;

                    for (int i = 2; i < list.length; i++) {
                        count++;
                        score = Integer.parseInt(list[i]);
                        total += score;
                    }

                    //noinspection IntegerDivisionInFloatingPointContext
                    float average = total / count;
                    scoreStatement = lastName + ", " + firstName + ": Final score is %.2f";

                    System.out.printf(("\n" + scoreStatement), average);
                    printWriter.printf(("\n" + scoreStatement), average);
                    nextFileLine = bufferedReader.readLine();
                }

                bufferedReader.close();
                printWriter.close();
                fileReadCorrectly = true;
            } catch (IOException e) {
                System.out.println("That file doesn't exist.");
            }
        } while (!fileReadCorrectly);
    }

    /**
     * Checks if a file is readable.
     *
     * @param fileName name of the file to be checked
     * @return fileWorks - boolean of true or false; true if file is readable, false if not.
     */
    boolean checkFile(String fileName) {
        /* You *could* use the Java "File" class, but if you're only returning one boolean, it doesn't matter if it
        exists but isn't readable or if it just doesn't exist, the boolean will be the same. So I might as well use a
        try/catch block to see if the file can be read from, regardless of whether or not it actually exists.
         */
        boolean fileWorks;
        try {

            // setting up file reader
            FileReader fileReader = new FileReader("src/" + fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // grabs first line from file
            String nextFileLine = bufferedReader.readLine();

            // reads every line
            while (nextFileLine != null) {
                nextFileLine = bufferedReader.readLine();
            }

            bufferedReader.close();
            fileWorks = true;
        } catch (IOException e) {
            fileWorks = false;
        }
        return fileWorks;
    }

    /**
     * Writes a 2D array to out.txt such that each internal array is printed on one line.
     */
    void write2DArray() {
        int[][] a2DArray = new int[][]{
                {1, 0, 8, 0, 0, 0, 0, 0, 0, 0},
                {0, 7, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 9, 0, 0, 5, 0, 0, 0, 0},
                {0, 0, 0, 0, 8, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 2, 0, 0, 0}
        };

        // setting up file writer
        FileOutputStream outputStream;
        PrintWriter printWriter = null;

        try {
            // specifies output file
            outputStream = new FileOutputStream("out.txt");
            printWriter = new PrintWriter(outputStream);

            for (int[] array : a2DArray) {
                printWriter.println(Arrays.toString(array));
            }
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        } finally {
            // printWriter will be null if something has gone wrong
            if (printWriter != null) {
                System.out.println("Written to out.txt.");
                printWriter.close();
            }
        }
    }

}
