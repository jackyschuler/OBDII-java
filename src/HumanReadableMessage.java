import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Map;

/**
 * Object that stores and contains methods to convert the data to a human readable format
 */
public class HumanReadableMessage {
    //private enum messageType{REQUEST, RESPONSE, ERROR};
    private PIDMessageType msgType;  // Request or response
    private String timestamp;
    private String description;
    private double value;
    private String unit;

    public HumanReadableMessage(DataFrameEntry data, DBCEntry dbcEntry) {
        msgType = determineMessageType(data.getCanID());
        timestamp = data.getTimestamp();
        description = dbcEntry.getDescription();
        value = calculateValue(dbcEntry.getFormula(), data.getVariables());
        unit = dbcEntry.getUnits();
    }

    private PIDMessageType determineMessageType(int i) {

        if(i == Integer.parseInt("7DF", 16)) {
            return PIDMessageType.REQUEST;
        }
        else if (i >= Integer.parseInt("7E8", 16) && i <= Integer.parseInt("7EF", 16)) {
            return PIDMessageType.RESPONSE;
        }
        //TODO: handle this properly
        else {
            return PIDMessageType.ERROR;
        }

    }

    private double calculateValue(String expression, Map<String, Double> vars) {
        Expression e = new ExpressionBuilder(expression)
                .variables(vars.keySet())
                .build()
                .setVariables(vars);
        return e.evaluate();
    }

    //TODO: think later about whether this is icky or not
    public String toCSVString() {
        return msgType + "," + timestamp + "," + description + "," + value + "," + unit + "\n";
    }

    public PIDMessageType getMsgType() {
        return msgType;
    }

    public void setMsgType(PIDMessageType msgType) {
        this.msgType = msgType;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "HumanReadableMessage{" +
                "msgType=" + msgType +
                ", timestamp='" + timestamp + '\'' +
                ", description='" + description + '\'' +
                ", value=" + value +
                ", unit='" + unit + '\'' +
                '}';
    }
}
