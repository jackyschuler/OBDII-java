import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Reads from a DBC file (currently a modified CSV file) using the following format
 * {@code 1569601200.069200;1;7E8;0;8;8;0;0;0;03410559FFFFFFFF}.
 */
public class FileIO {

    //TODO: rethink this whole class -- this might want to be somewhere else b/c it's different for each type of IO file
    //TODO: are there duplicate PIDs in the real DBC files? -- HashMap doesn't allow duplicate keys
    //TODO: validate data (? terminal operation (LinkedIn Learning / Functional Programming with Streams in Java 9 ))

    /**
     * Loads data from a DBC file
     * @param filePath The path of the DBC file
     * @return A map containing the DBC entries contained in the CSV files
     */
    public Map<String, DBCEntry> readFile(String filePath) throws IOException {
        Path path = Path.of(filePath);
        return Files.lines(path) // returns a Stream of String objects
                .map(this::parseEntry) // returns a Stream of DBCEntry objects using parseEntry(filePath)
                .collect(Collectors.toMap(DBCEntry::getPid, t -> t)); // collect() : returns a Collector (encapsulates the functions used as arguments to collect(Supplier, BiConsumer, BiConsumer) )
                                                                      // toMap() makes a map out of them
    }

    /**
     * Writes human-readable data to a CSV file
     * @param filePath The path of the CSV file
     * @param map The HashMap containing the data
     * @throws IOException
     */
    public static void writeFile(String filePath, Map<String, HumanReadableMessage> map) throws IOException {
        Path path = Path.of(filePath);
        BufferedWriter writer = Files.newBufferedWriter(path);

        //TODO: validate data? -- at least check for null
        map.values().stream().map(HumanReadableMessage::toCSVString).forEach(message -> {
            try {
                writer.write(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        writer.close();
    }

    protected DBCEntry parseEntry(String line) {
        return new DBCEntry(line);
    }
}