import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ContentFilter {
    public static void main(String[] args) {
        String inputFileName = "sample.txt";

        try (BufferedReader reader = Files.newBufferedReader(
                Paths.get(inputFileName), 
                StandardCharsets.UTF_8);
            FileManager fileManager = new FileManager()) {
        
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                DataType lineType = TypeDetector.detectType(line);
                System.out.println(lineType);
                fileManager.writeLine(lineType, line);
            }
            fileManager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}