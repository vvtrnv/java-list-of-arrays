package realization.types.userTypes;

import realization.types.Point2DType;
import realization.types.comporators.Comporator;
import realization.types.comporators.Point2DComporator;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Point2DUserType implements UserType {

    private static final String REG_EXPR = "\\(([-]?[0-9]+(?:[.,][0-9]+){0,1});([-]?[0-9]+(?:[.,][0-9]+){0,1})\\)";

    @Override
    public String typeName() {
        return "Point2D";
    }

    @Override
    public Object create() {
        Random rand = new Random();
        return new Point2DType(rand.nextFloat(1000.0F),
                rand.nextFloat(1000.0F));
    }

    @Override
    public Object clone(Object object) {
        return new Point2DType(((Point2DType)object).getX(),
                ((Point2DType)object).getY());
    }

    @Override
    public Object readValue(InputStreamReader in) throws IOException {
        return in.read();
    }

    @Override
    public Object parseValue(String ss) {
        Pattern ptrnString = Pattern.compile(REG_EXPR);
        Matcher matcher = ptrnString.matcher(ss);

        if(matcher.find()) {
            return new Point2DType(Float.valueOf(matcher.group(1)),
                    Float.valueOf(matcher.group(2)));
        }

        return null;
    }

    @Override
    public Comporator getTypeComparator() {
        return new Point2DComporator();
    }

    @Override
    public String toString(Object object) {
        return object.toString();
    }
}
