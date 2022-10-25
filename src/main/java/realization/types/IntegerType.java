package realization.types;

import com.thoughtworks.xstream.XStream;

public class IntegerType {

    private int value;

    public IntegerType(int value) {
        this.value = value;
    }

    public int getIntValue() {
        return this.value;
    }

    public void setIntValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static void setXMLParser(XStream xStream) {
        xStream.alias("IntegerType", IntegerType.class);
        xStream.useAttributeFor(IntegerType.class, "value");
    }
}
