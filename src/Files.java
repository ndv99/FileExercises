import java.util.Scanner;
import java.io.*;

public class Files {

    public static void main(String[] args){
        Files files = new Files();
        files.runFileTests();
    }

    public void runFileTests(){
        askForFile();
    }

    public void askForFile() {
        String fileName = ".txt";
        Scanner askUser = new Scanner(System.in);

        System.out.println("Enter the name of the file you want to read.");
        fileName = askUser.nextLine() + fileName;
        boolean fileReadCorrectly = false;
        do{
            try{
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
                System.out.println("Something has gone wrong!");
            }
         } while (!fileReadCorrectly);
    }

}
