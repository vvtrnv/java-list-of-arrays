package realization.list;


import realization.types.IntegerType;
import realization.types.factory.FactoryUserType;
import realization.types.userTypes.UserType;

import javax.xml.XMLConstants;
import javax.xml.stream.*;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SerializeList extends ListOfArrays{

    public static void save(ListOfArrays list, String filename, UserType userType) {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = null;

        try (FileWriter fileWriter = new FileWriter(filename)) {
            writer = factory.createXMLStreamWriter(fileWriter);

            writer.writeStartDocument();

            writer.writeStartElement("list");
            writer.writeAttribute("usertype", userType.typeName());

            writer.writeStartElement("nodes");

            Node tmp = list.getHead();

            while (tmp != null) {
                writer.writeStartElement("node");
                writer.writeCharacters(tmp.toString());
                writer.writeEndElement();

                tmp = tmp.next;
            }

            writer.writeEndElement();
            writer.writeEndDocument();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }

    public static ListOfArrays load(String filename) throws FileNotFoundException, XMLStreamException {
        FactoryUserType factoryUserType = new FactoryUserType();
        UserType userType = null;

        ListOfArrays loadList = new ListOfArrays();

        XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
        xmlInputFactory.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        xmlInputFactory.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

        XMLStreamReader reader = xmlInputFactory.createXMLStreamReader(new FileInputStream(filename));

        int eventType = reader.getEventType();
        String type = "";
        while (reader.hasNext()) {
            eventType = reader.next();

            if(eventType == XMLEvent.START_ELEMENT) {
                switch (reader.getName().getLocalPart()) {
                    case "list":
                        type = reader.getAttributeValue(null, "usertype");
                        userType =  factoryUserType.getBuilderByTypeName(type);
                        break;
                    case "node":
                        eventType = reader.next();
                        if (eventType == XMLEvent.CHARACTERS) {
                            String str = reader.getText();
                            if(type == "Integer") {
                                ArrayList<IntegerType> newArray = new ArrayList<>();
                                parseStringFromXML(newArray, str);
                            }
                            else if (type == "Point2D") {

                            }
                            ArrayList<IntegerType> newArray = new ArrayList<>();
                            parseStringFromXML(newArray, str);

                        }
                        break;
                }
            }
        }
        return loadList;
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
}
