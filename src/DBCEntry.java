public class DBCEntry {
    private String pid;
    private int dataBytes;
    private String description;
    private float minValue;
    private float maxValue;
    private String units;
    private String formula;

    DBCEntry(String str) {

        String[] arr = str.split(";");

        pid = arr[0];
        dataBytes = Integer.parseInt(arr[1]);
        description = arr[2];
        String toSplit = arr[3].substring(1, arr[3].length() - 1);
        String[] minMax = toSplit.split("\\|");
        minValue = Float.parseFloat(minMax[0]);
        maxValue = Float.parseFloat(minMax[1]);
        units = arr[4];
        formula = arr[5];

    }

    @Override
    public String toString() {
        return "DBCEntry{" +
                "pid='" + pid + '\'' +
                ", dataBytes=" + dataBytes +
                ", description='" + description + '\'' +
                ", minValue=" + minValue +
                ", maxValue=" + maxValue +
                ", units='" + units + '\'' +
                ", formula='" + formula + '\'' +
                '}';
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getDataBytes() {
        return dataBytes;
    }

    public void setDataBytes(int dataBytes) {
        this.dataBytes = dataBytes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getMinValue() {
        return minValue;
    }

    public void setMinValue(float minValue) {
        this.minValue = minValue;
    }

    public float getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }
}