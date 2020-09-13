package sample;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class ReadFile {
    private static String fileName;
    private static Scanner input;

    public static void main (String [] args) {
        openFile();
        readFile();
        closeFile();
    }

    public static void openFile() {
        try {
            fileName = "C:\\Users\\alexm\\IdeaProjects\\Assignment_3_S2020\\src\\sample\\alice.txt";
            input = new Scanner(Paths.get(fileName));
    } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error when trying to open the file.");
        }    }

    public static String readFile() {
        String output = "";
        try {
            while (input.hasNext()) {
                String s = input.next();
                output += s;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error when trying to read the file.");
        }
        //System.out.println(output);
        return output;
    }

    public static void closeFile() {
        if (input != null) {
            input.close();
        }
        else
            System.out.println("Error when trying to close the file.");
    }
}
