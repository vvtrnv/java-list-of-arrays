import programUI.GUI;
import test.TestList;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws  IOException {
        TestList test = new TestList();
        test.testingIntegerUserType();
        test.testingPoint2DUserType();

        GUI mainFrame = new GUI();
        mainFrame.showWindow();
    }
}