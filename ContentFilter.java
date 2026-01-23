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

        BufferedWriter fileInteger = null;
        BufferedWriter fileFloat = null;
        BufferedWriter fileString = null;

        try (BufferedReader reader = Files.newBufferedReader(
                Paths.get(inputFileName), 
                StandardCharsets.UTF_8)) {
        
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                String lineType = TypeDetector.detectType(line);
                System.out.println(lineType);
                switch (lineType) {
                    case "Integer" -> {
                        if (fileInteger == null) {
                            fileInteger = Files.newBufferedWriter(
                                Paths.get("integers.txt"), 
                                StandardCharsets.UTF_8,
                                StandardOpenOption.CREATE,
                                StandardOpenOption.TRUNCATE_EXISTING);
                        }
                        fileInteger.write(line + "\n");
                    }
                    case "Float" -> {
                        if (fileFloat == null) {
                            fileFloat = Files.newBufferedWriter(
                                Paths.get("floats.txt"), 
                                StandardCharsets.UTF_8,
                                StandardOpenOption.CREATE,
                                StandardOpenOption.TRUNCATE_EXISTING);
                        }
                        fileFloat.write(line + "\n");
                    }
                    case "String" -> {
                        if (fileString == null) {
                            fileString = Files.newBufferedWriter(
                                Paths.get("strings.txt"), 
                                StandardCharsets.UTF_8,
                                StandardOpenOption.CREATE,
                                StandardOpenOption.TRUNCATE_EXISTING);
                        }
                        fileString.write(line + "\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Закрываем только те файлы, которые были созданы
            closeQuietly(fileInteger);
            closeQuietly(fileFloat);
            closeQuietly(fileString);
        }
    }
    
    private static void closeQuietly(BufferedWriter writer) {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}