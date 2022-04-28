import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);
        System.out.println("Insert the folder path: ");

        // Getting the path of the homeDirectory
        // C:\\Users\\Isaac\\Downloads\\teste
        String homeDirectoryPath = "C:\\Users\\Isaac\\Downloads\\arquivosTeste"; // input.nextLine();
        input.close();

        // Setting the directory to check files
        File homeDirectory = new File(homeDirectoryPath);
        // All files to filter if they are .txt
        Vector<File> filesToCheck = new Vector<>();
        // Only .txt files to find the biggest prime number
        Vector<File> filesToProcess = new Vector<>();

        // Adding all files of the homeDirectory to filesCheck Vector
        Collections.addAll(filesToCheck, homeDirectory.listFiles());

        while (!filesToCheck.isEmpty()) {
            // Removing the first item of filesToCheck to verify
            File currentFile = filesToCheck.remove(0);

            // If the currentFile is a file and .txt file, add that to the filesToProcess
            if (currentFile.isFile() && currentFile.getAbsolutePath().endsWith(".txt")) {
                filesToProcess.add(currentFile);

                // Otherwise, add that again to the filesToCheck
            } else if (currentFile.isDirectory()) {
                Collections.addAll(filesToCheck, currentFile.listFiles());
            }
        }

        System.out.println("Were found " + filesToProcess.size() +
                " files '.txt' to process at " + homeDirectoryPath + "\n");
        System.out.println("Searching the biggest prime number...");

        Vector<Integer> allNumbers = new Vector<>();

        do {
            try {
                FileReader readableFile = new FileReader(filesToProcess.remove(0));
                BufferedReader readerBuffer = new BufferedReader(readableFile);
                // Reading each row
                String fileRow = readerBuffer.readLine();

                do {
                    // Creating an array by ' ' or ,
                    String[] stringData = fileRow.split("[ ,]+");

                    for (String string : stringData) {
                        // Removing all chars != than 0-9 and .
                        String stringNumber = string.replaceAll("[^0-9.]+", "");

                        // If is a number(int or double) add to allNumbers array
                        if (stringNumber.matches("([0-9]*[.])?[0-9]+")) {
                            allNumbers.add((int) Math.round(Double.parseDouble(stringNumber)));
                            System.out.println(stringNumber);
                            // calculatePrimeNumber
                        }
                    }

                    // reading next line
                    fileRow = readerBuffer.readLine();

                } while (fileRow != null);

            } catch (Exception error) {
                System.out.println("ops, an error has occurred :(");
            }

        // Searching all numbers from filesToProcess
        } while (filesToProcess.size() != 0);

        // Printing biggest number
        System.out.println("Maior: " + Collections.max(allNumbers));
    }
}
