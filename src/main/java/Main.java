import realization.list.ListOfArrays;
import realization.list.SerializeList;
import realization.types.IntegerType;
import realization.types.userTypes.IntegerUserType;
import realization.types.userTypes.UserType;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws XMLStreamException, IOException {

        ListOfArrays arr = new ListOfArrays();

        UserType usertype = new IntegerUserType();

        ArrayList<IntegerType> intType = new ArrayList<>();
        intType.add((IntegerType) usertype.create());
        intType.add((IntegerType) usertype.create());
        intType.add((IntegerType) usertype.create());

        ArrayList<IntegerType> intType2 = new ArrayList<>();
        intType2.add((IntegerType) usertype.create());
        intType2.add((IntegerType) usertype.create());

        ArrayList<IntegerType> intType3 = new ArrayList<>();
        intType3.add((IntegerType) usertype.create());

        ArrayList<IntegerType> intType4 = new ArrayList<>();
        intType4.add((IntegerType) usertype.create());
        intType4.add((IntegerType) usertype.create());
        intType4.add((IntegerType) usertype.create());
        intType4.add((IntegerType) usertype.create());
        intType4.add((IntegerType) usertype.create());
        intType4.add((IntegerType) usertype.create());

        arr.push_back(intType4);
        arr.push_back(intType);
        arr.push_back(intType2);
        arr.push_back(intType3);

        System.out.println("BEFORE");
        System.out.println(arr.toString());
        System.out.println("AFTER");

        SerializeList.save(arr, "integer.xml", usertype);
        SerializeList.load("integer.xml");
    }
}