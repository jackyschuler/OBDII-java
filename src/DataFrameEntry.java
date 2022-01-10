import java.util.HashMap;
import java.util.Map;

/**
 * Parse data from raw CAN frame
 */
public class DataFrameEntry {
    //TODO: make a data class that this and PIDEntry inherit from???
    //TODO: change to LocalDateTime from String
    // private LocalDateTime timestamp;
    private String timestamp;
    private int canID;
    //TODO: Not using but want to figure out why this is 1 in the converter -- maybe because the request was mode 1? YES!
    private String pid;
    private Map<String, Double> variables;
    private double ah;
    private double bh;
    private double ch;
    private double dh;

    public DataFrameEntry(String str, String regEx) {
        String dataBytes = parseRawString(str, regEx);
        parseDataBytes(dataBytes);
    }

    private String parseRawString(String str, String regEx) {
        String[] arr = str.split(regEx);
        timestamp = arr[0];
        canID = Integer.parseInt(arr[2], 16);
        String dataBytes = arr[9].substring(2,16);
        variables = new HashMap<>();
        return dataBytes;
    }

    private void parseDataBytes(String bytes) {
        pid = bytes.substring(2,4);

        ah = Integer.parseInt(bytes.substring(4,6), 16);
        bh = Integer.parseInt(bytes.substring(6,8), 16);
        ch = Integer.parseInt(bytes.substring(8,10), 16);
        dh = Integer.parseInt(bytes.substring(10,12), 16);

        //TODO:ugly??? other options besides 255!!!
        if(ah != 255) { variables.put("a", ah); }
        if(bh != 255) { variables.put("b", bh); }
        if(ch != 255) { variables.put("c", ch); }
        if(dh != 255) { variables.put("d", dh); }
    }

    @Override
    public String toString() {
        return "DataFrame{" +
                "timestamp='" + timestamp + '\'' +
                ", canID=" + Integer.toHexString(canID) +
                //", dataLength=" + dataLength +
                ", pid=" + pid +
                ", ah=" + ah +
                ", bh=" + bh +
                ", ch=" + ch +
                ", dh=" + dh +
                '}';
    }

    public Map<String, Double> getVariables() {
        return variables;
    }

    //TODO: I don't want setters, do I?
    public void setVariables(Map<String, Double> variables) {
        this.variables = variables;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getCanID() {
        return canID;
    }

    public void setCanID(int canID) {
        this.canID = canID;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public double getAh() {
        return ah;
    }

    public void setAh(double ah) {
        this.ah = ah;
    }

    public double getBh() {
        return bh;
    }

    public void setBh(double bh) {
        this.bh = bh;
    }

    public double getCh() {
        return ch;
    }

    public void setCh(double ch) {
        this.ch = ch;
    }

    public double getDh() {
        return dh;
    }

    public void setDh(double dh) {
        this.dh = dh;
    }
}
