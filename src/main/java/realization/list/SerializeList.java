package realization.list;


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

public class SerializeList extends ListOfArrays{

//    public static void save(ListOfArrays list, String filename, UserType userType) throws IOException {
//        OutputStreamWriter streamWriter = new OutputStreamWriter(new FileOutputStream(filename),
//                "utf-8");
//
//        ArrayList<ArrayList<Object>> arraysToXML = new ArrayList<>();
//        for (int i = 0; i < list.size; i++) {
//            arraysToXML.add((ArrayList<Object>)list.get(i));
//        }
//
//        XStream xStream = new XStream();
//        IntegerType.setXMLParser(xStream);
//        xStream.toXML(arraysToXML, streamWriter);
//        streamWriter.write("\n");
//
//        streamWriter.flush();
//        streamWriter.close();
//
//
//    }

//    public static ListOfArrays load(String filename) throws FileNotFoundException, XMLStreamException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
//
//        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(filename));
//
//        // TEST
//        ListOfArrays list = new ListOfArrays();
//        UserType userType = new IntegerUserType();
//
//
//        ArrayList<IntegerType> intType1 = new ArrayList<>();
//        intType1.add((IntegerType) userType.create());
//        intType1.add((IntegerType) userType.create());
//        intType1.add((IntegerType) userType.create());
//
//        ArrayList<IntegerType> intType2 = new ArrayList<>();
//        intType2.add((IntegerType) userType.create());
//        intType2.add((IntegerType) userType.create());
//        intType2.add((IntegerType) userType.create());
//        intType2.add((IntegerType) userType.create());
//
//        list.push_back(intType1);
//        list.push_back(intType2);
//
//        ArrayList<ArrayList<Object>> arraysToXML = new ArrayList<>();
//        for (int i = 0; i < list.size; i++) {
//            arraysToXML.add((ArrayList<Object>)list.get(i));
//        }
//
//
//        XStream xStream = new XStream();
//        IntegerType.setXMLParser(xStream);
////        Point2DType.setXMLParser(xStream);
//        // ArrayList<ArrayList<Object>> arrays =  (ArrayList<ArrayList<Object>>)xStream.fromXML(inputStreamReader);
//
//
//        //TEST
//        String str = xStream.toXML(arraysToXML);
//        System.out.println(formatXml(str));
//
//        ArrayList<ArrayList<Object>> arraysfromXML = (ArrayList) xStream.fromXML(str);
//
////        ListOfArrays list = new ListOfArrays();
////        for (int i = 0; i < arrays.size(); i++) {
////            list.push_back(arrays);
////        }
//
//       System.out.println(arraysfromXML.toString());
//
//        return null;
//    }

    private static ArrayList parseString(ArrayList list, String s, UserType userType) {
        s = s.replace("[", "");
        s = s.replace("]", "");
        String[] arrayFromString = s.split(", ");
        ArrayList arrayToAddInList = new ArrayList();
        //выводим строки:
        for (int i = 0; i < arrayFromString.length; i++) {
            arrayToAddInList.add(userType.parseValue(arrayFromString[i]));
//            System.out.println(arrayToAddInList.get(i).toString());
        }
        return arrayToAddInList;
    }


    public static String formatXml(String xml) {

        try {
            Transformer serializer = SAXTransformerFactory.newInstance().newTransformer();

            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            Source xmlSource = new SAXSource(new InputSource(
                    new ByteArrayInputStream(xml.getBytes())));
            StreamResult res =  new StreamResult(new ByteArrayOutputStream());

            serializer.transform(xmlSource, res);

            return new String(((ByteArrayOutputStream)res.getOutputStream()).toByteArray());

        } catch(Exception e) {
            return xml;
        }
    }



    // SERIALIZE
    public static void saveToFile(ListOfArrays list, String filename, UserType userType) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.println(userType.typeName());
            list.forEach(el -> writer.println(el.toString()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static ListOfArrays loadFromFile(String filename, UserType userType) {
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
                System.out.println(arr.toString());
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
