package parser;

import entity.Address;
import utils.AddressCounter;
import utils.FloorCounter;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class XMLParser implements Parser {

    private static void counter(List<Address> addressList) {
        int maxThread = Runtime.getRuntime().availableProcessors();
        ExecutorService threadPool = Executors.newFixedThreadPool(maxThread);
        for (Address address : addressList) {
            threadPool.submit(new AddressCounter(address));
            threadPool.submit(new FloorCounter(
                    address.getCity(),
                    address.getFloor()));
        }
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void parse(File file) {
        List<Address> resultReadAddressList = readAddress(file);
        XMLParser.counter(resultReadAddressList);
        consolePrint();
    }

    //     todo дополнить реализацию, это чисто пример
    private List<Address> readAddress(File file) {
        List<Address> addressList = new ArrayList<>();
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader parser = null;

        try {
            parser = factory.createXMLStreamReader(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            System.out.println("Check file path");
        } catch (XMLStreamException e) {
            System.out.println(e.getMessage());
        }

        try {
            while (true) {
                assert parser != null;
                if (!parser.hasNext()) break;
                int event = parser.next();
                if (event == XMLStreamConstants.START_ELEMENT) {
                    if (parser.getLocalName().equals("address")) {
                        int id = Integer.parseInt(parser.getAttributeValue(0));
                        String city = parser.getAttributeValue(1);
                        String street = parser.getAttributeValue(2);
                        int house = Integer.parseInt(parser.getAttributeValue(3));
                        int floor = Integer.parseInt(parser.getAttributeValue(4));
                        int flatNumber = Integer.parseInt(parser.getAttributeValue(5));
                        Address address = new Address(id, city, street, house, floor, flatNumber);
                        addressList.add(address);
                    }
                }
            }
        } catch (XMLStreamException e) {
            System.out.println(e.getMessage());
        }
        return addressList;
    }

    // можно проще вывод сделать
    private void consolePrint() {
        int cityNumber = 1;
        for (Map.Entry<String, TreeMap<Integer, Integer>> floor : FloorCounter.getFloorMap().entrySet()) {
            System.out.println(cityNumber + ". " + floor.getKey());
            cityNumber++;
            for (Map.Entry<Integer, Integer> innerEntry : floor.getValue().entrySet()) {
                System.out.println(" " + innerEntry.getKey() + "-floor(s): " + innerEntry.getValue());
            }
        }
    }
}