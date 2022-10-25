package realization.types.comporators;

import realization.types.Point2DType;

import java.io.Serializable;

public class Point2DComporator implements Comporator, Serializable {

    private float getLengthOfVector(float x, float y) {;
        return (float)Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    @Override
    public float compare(Object object1, Object object2) {
        float obj1_x = ((Point2DType)object1).getX();
        float obj2_x = ((Point2DType)object2).getX();
        float obj1_y = ((Point2DType)object1).getY();
        float obj2_y = ((Point2DType)object2).getY();

        return getLengthOfVector(obj1_x, obj1_y) - getLengthOfVector(obj2_x, obj2_y);
    }
}
