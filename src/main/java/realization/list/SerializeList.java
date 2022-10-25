package realization.list;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.xml.sax.InputSource;
import realization.types.IntegerType;
import realization.types.factory.FactoryUserType;
import realization.types.userTypes.UserType;

import javax.xml.XMLConstants;
import javax.xml.stream.*;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;

public class SerializeList extends ListOfArrays{

    public static void save(ListOfArrays list, String filename, UserType userType) throws IOException {

        OutputStreamWriter streamWriter = new OutputStreamWriter(new FileOutputStream(filename),
                "utf-8");

        XStream xStream = new XStream();
        IntegerType.setXMLParser(xStream);
        xStream.toXML(list, streamWriter);
        streamWriter.write("\n");
        streamWriter.flush();
        streamWriter.close();


    }

    public static ListOfArrays load(String filename) throws FileNotFoundException, XMLStreamException {
        return null;
    }

    private static ArrayList parseStringFromXML(ArrayList list, String s) {
        s = s.replace("[", "");
        s = s.replace("]", "");
        String[] arrayFromString = s.split(", ");

        //выводим строки:
        for (int i = 0; i < arrayFromString.length; i++) {
            System.out.println(arrayFromString[i]);
        }
        return list;
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

}
