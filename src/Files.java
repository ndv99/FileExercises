import java.util.Scanner;
import java.io.*;

public class Files {

    public static void main(String[] args){
        Files files = new Files();
        files.runFileTests();
    }

    public void runFileTests(){
        writeToFile();
    }

    public void askForFile() {
        Scanner askUser = new Scanner(System.in);

        boolean fileReadCorrectly = false;
        do{
            try{
                System.out.println("Enter the name of the file you want to read.");
                String fileName = askUser.nextLine() + ".txt";

                FileReader fileReader = new FileReader("src/" + fileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String nextFileLine = bufferedReader.readLine();

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

    public void writeToFile() {
        Scanner userWrite = new Scanner(System.in);

        FileOutputStream outputStream;
        PrintWriter printWriter = null;

        try {
            outputStream = new FileOutputStream("out.txt");
            printWriter = new PrintWriter(outputStream);

            String line;
            do {
                System.out.println("Enter something to be written to the file, or hit Enter to finish.");
                line = userWrite.nextLine();
                printWriter.println(line);
            } while (!line.isEmpty());
        } catch (IOException e) {
            System.out.println("Something has gone wrong!");
        } finally {
            if (printWriter != null) {
                System.out.println("Written to out.txt.");
                printWriter.close();
            }
        }
    }

    public void copyFile() {

    }

}
