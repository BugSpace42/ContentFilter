import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class FileManager implements AutoCloseable {
    private final Map<DataType, BufferedWriter> writers = new EnumMap<>(DataType.class);
    private final Map<DataType, String> fileNames = Map.of(
        DataType.INTEGER, "integers.txt",
        DataType.FLOAT, "floats.txt",
        DataType.STRING, "strings.txt"
    );
    
    public void writeLine(DataType type, String line) throws IOException {
        BufferedWriter writer = getWriter(type);
        writer.write(line + "\n");
    }
    
    private BufferedWriter getWriter(DataType type) throws IOException {
        BufferedWriter writer = writers.get(type);
        if (writer == null) {
            String fileName = fileNames.get(type);
            writer = Files.newBufferedWriter(
                Paths.get(fileName),
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE
            );
            writers.put(type, writer);
        }
        return writer;
    }
    
    @Override
    public void close() {
        for (BufferedWriter writer : writers.values()) {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}