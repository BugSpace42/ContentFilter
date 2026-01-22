import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ContentFilter {
    public static void main(String[] args) {
        String inputFileName = "sample.txt";

        try (BufferedReader reader = Files.newBufferedReader(
                Paths.get(inputFileName), 
                StandardCharsets.UTF_8);
            BufferedWriter fileInteger = Files.newBufferedWriter(
                 Paths.get("integers.txt"), 
                 StandardCharsets.UTF_8,
                 StandardOpenOption.CREATE,
                 StandardOpenOption.TRUNCATE_EXISTING);
            BufferedWriter fileFloat = Files.newBufferedWriter(
                Paths.get("floats.txt"), 
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
            BufferedWriter fileString = Files.newBufferedWriter(
                Paths.get("strings.txt"), 
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING)) {
        
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                String lineType = TypeDetector.detectType(line);
                System.out.println(lineType);
                switch (lineType) {
                    case "Integer" -> fileInteger.write(line + "\n");
                    case "Float" -> fileFloat.write(line + "\n");
                    case "String" -> fileString.write(line + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}