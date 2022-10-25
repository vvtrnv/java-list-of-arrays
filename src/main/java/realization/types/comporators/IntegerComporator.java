package realization.types.comporators;

import realization.types.IntegerType;

import java.io.Serializable;

public class IntegerComporator implements Comporator, Serializable {
    @Override
    public float compare(Object object1, Object object2) {
        return ((IntegerType)object1).getIntValue() - ((IntegerType)object2).getIntValue();
    }
}
