import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OBDFileReader {

    //TODO: maybe this shouldn't be a map since one might pull up data by any of the parameters
    //TODO: validate data
    public static Map<String, HumanReadableMessage> LoadMF4(String fileName, Map<String, DBCEntry> dbcMap) throws FileNotFoundException {
        File dataFile = new File(fileName);
        System.out.println(dataFile.getAbsolutePath());

        Scanner scanner = new Scanner(dataFile);
        Map<String, HumanReadableMessage> messages = new HashMap<>();

        while(scanner.hasNextLine()) {
            DataFrameEntry currentDataFrameEntry = new DataFrameEntry(scanner.nextLine(), ";");

            String currentPID = currentDataFrameEntry.getPid();
            if(dbcMap.containsKey(currentPID)) {
                HumanReadableMessage message = new HumanReadableMessage(currentDataFrameEntry, dbcMap.get(currentPID));
                System.out.println(message);
                messages.put(message.getTimestamp(), message);
            }
        }

        return messages;
    }
}
