package realization.types.userTypes;

import realization.types.IntegerType;
import realization.types.comporators.Comporator;
import realization.types.comporators.IntegerComporator;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class IntegerUserType implements UserType {
    @Override
    public String typeName() {
        return "Integer";
    }

    @Override
    public Object create() {
        Random rand = new Random();
        return new IntegerType(rand.nextInt(1000));
    }

    @Override
    public Object clone(Object object) {
        return new IntegerType(((IntegerType)object).getIntValue());
    }

    @Override
    public Object readValue(InputStreamReader in) throws IOException {
        return in.read();
    }

    @Override
    public Object parseValue(String ss) {
        return new IntegerType(Integer.parseInt(ss));
    }

    @Override
    public Comporator getTypeComparator() {
        return new IntegerComporator();
    }

    @Override
    public String toString(Object object) {
        return object.toString();
    }
}
