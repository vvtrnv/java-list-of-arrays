import programUI.GUI;
import realization.list.MyListOfArrays;
import test.TestList;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws  IOException {
//        TestList test = new TestList();
//        test.testingIntegerUserType();
//        test.testingPoint2DUserType();
//
//        GUI mainFrame = new GUI();
//        mainFrame.showWindow();

        // new list:
        MyListOfArrays list = new MyListOfArrays(10);

        for (int i = 0; i < 6; i++) {
            list.add(i);
        }
        System.out.println("TEST = " + list.getTotalElements());
        list.show();
        System.out.println(list.get(3));
        list.insert(1000, 3);
        System.out.println("TEST = " + list.getTotalElements());
        list.show();
        System.out.println(list.get(3));
    }
}