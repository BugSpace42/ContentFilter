import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ContentFilter {
    public static void main(String[] args) {
        String fileName = "sample.txt";
        String encoding = "UTF-8";

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileName), encoding))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}