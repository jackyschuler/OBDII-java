import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

/**
 * OBDII takes a CSV created with the mf4 to CSV drag-and-drop tool from CSSElectronics.com and returns a list of
 * human-readable information
 *
 * @author Jacky Schuler
 * @version 0.1
 *
 */
public class OBDII {
    public static void main(String[] args) throws IOException {

        final String DBC_FILE = "DBC.csv";
        final String MF4_FILE = "data.csv";


        Map<String, DBCEntry> DBCMap = loadDBC(DBC_FILE);

        //Loads OBDII data from the CSV file
        //MF4->CSV via https://www.csselectronics.com/pages/mdf4-converters-mf4-asc-csv#void
        //TODO: would be cool to figure out how to convert MF4 -> CSV myself!
        //TODO: is this icky passing in the DBCMap???
        Map<String, HumanReadableMessage> messageMap = OBDFileReader.LoadMF4(MF4_FILE, DBCMap);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Output file name: ");
        //TODO: validate input or GUI
        String outputFilename = scanner.nextLine();
        FileIO.writeFile(outputFilename + ".csv", messageMap);
    }

    private static Map<String, DBCEntry> loadDBC(String filePath) throws IOException {
        //TODO: does returning a Map make sense?
        //TODO: enable various types of files
        //TODO: Load the data from an actual DBC file, not a cleaned-up CSV

        FileIO dbc = new FileIO();
        Map<String, DBCEntry> map = dbc.readFile(filePath);
        map.forEach((key, value) -> System.out.println(key + ":" + value));
        return map;
    }
}
