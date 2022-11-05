package realization.list;


import org.jetbrains.annotations.NotNull;
import org.xml.sax.InputSource;
import realization.types.IntegerType;
import realization.types.userTypes.UserType;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;

public class SerializeList {

    private static ArrayList parseString(ArrayList list, String s, UserType userType) {
        s = s.replace("[", "");
        s = s.replace("]", "");
        String[] arrayFromString = s.split(", ");

        ArrayList arrayToAddInList = new ArrayList();

        for (int i = 0; i < arrayFromString.length; i++) {
            arrayToAddInList.add(userType.parseValue(arrayFromString[i]));
        }
        return arrayToAddInList;
    }

    public static void saveToFile(ListOfArrays list, String filename, UserType userType) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.println(userType.typeName());
            list.forEach(el -> writer.println(el.toString()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static ListOfArrays loadFromFile(String filename, UserType userType) throws Exception {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;

            ListOfArrays list = new ListOfArrays();

            line = bufferedReader.readLine();
            if (!userType.typeName().equals(line)) {
                throw new Exception("Wrong file structure");
            }

            while ((line = bufferedReader.readLine()) != null) {
                ArrayList<IntegerType> arr = null;
                arr = parseString(arr, line, userType);
                list.push_back(arr);
                //System.out.println(arr.toString());
            }
            return list;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
    }

}
