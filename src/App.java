import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;
import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);

        System.out.println("Insert the folder path: ");

        // Getting the path of the homeDirectory
        String homeDirectoryPath = input.nextLine();
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
                " files '.txt' to process at " + homeDirectoryPath);
    }
}
