import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class makeCSV {
    private static final String RELATIVE_FOLDER_PATH = "../data"; // Relative folder path

    public static void CSVprinter(long[] wasp, String filename) throws IOException {
        String filePath = RELATIVE_FOLDER_PATH + File.separator + filename;
        
        // Extract the folder path from the file path
        String folderPath = filePath.substring(0, filePath.lastIndexOf(File.separator));
        
        // Create the folder if it doesn't exist
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Create the FileWriter
        FileWriter writer = new FileWriter(filePath);

        for (int j = 0; j < wasp.length; j++) {
            writer.append(String.valueOf(wasp[j]));
            writer.append("\n");
        }
        writer.close();
    }
}
