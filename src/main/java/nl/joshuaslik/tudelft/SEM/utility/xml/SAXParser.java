package nl.joshuaslik.tudelft.SEM.utility.xml;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * class for the saxparser
 *
 * @author Joshua Slik
 *
 */
public class SAXParser extends DefaultHandler {

    private XMLFile file = null;
    private final ArrayList<XMLTag> tagstack = new ArrayList<>();
    private boolean inElement = false;

    /*
     * public SAXParser() { }
     */
    /**
     * parsing a file as an xml file
     *
     * @param filename is a string for the name of the xml file
     * @return the xml file
     */
    public static XMLFile parseFile(String filename) {
        filename = filename.replace("\\", "/");
        XMLReader xr;
        SAXParser handler = new SAXParser();
        try {
            xr = XMLReaderFactory.createXMLReader();
            xr.setContentHandler(handler);
            xr.setErrorHandler(handler);
            InputStream is = Class.class.getResourceAsStream(filename);
            xr.parse(new InputSource(is));
        }
        catch (IOException | NullPointerException e) {
            System.err.println("File \"" + filename + "\" does not exist");
        }
        catch (SAXException e) {
            System.err.println("Something went wrong parsing the file: \""
                    + filename + "\"");
            System.err.println(e.getMessage());
        }
        return handler.getXMLFile();
    }

    /**
     * parsing a local xml file
     *
     * @param filename name of the xml file
     * @return the xml file
     */
    public static XMLFile parseLocalFile(String filename) {
        XMLReader xr;
        SAXParser handler = new SAXParser();
        try {
            xr = XMLReaderFactory.createXMLReader();
            xr.setContentHandler(handler);
            xr.setErrorHandler(handler);

            InputStream is;
            is = new FileInputStream(new File(filename));
            xr.parse(new InputSource(is));
        }
        catch (IOException | NullPointerException e) {
            System.err.println("File \"" + filename + "\" does not exist");
        }
        catch (SAXException e) {
            System.err.println("Something went wrong parsing the file: \"" + filename + "\"");
            System.err.println(e.getMessage());
        }
        return handler.getXMLFile();
    }

    /**
     * parsing a local xml file as a string
     *
     * @param xmlstring is a string with the xml in it
     * @return the xml file
     */
    public static XMLFile parseString(String xmlstring) {
        XMLReader xr;
        SAXParser handler = new SAXParser();
        try {
            xr = XMLReaderFactory.createXMLReader();
            xr.setContentHandler(handler);
            xr.setErrorHandler(handler);
            InputStream is = new ByteArrayInputStream(xmlstring.getBytes(StandardCharsets.UTF_8));
            xr.parse(new InputSource(is));
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
        catch (SAXException e) {
            System.err.println("Something went wrong parsing your string.");
            System.err.println(e.getMessage());
        }
        return handler.getXMLFile();
    }

    // //////////////////////////////////////////////////////////////////
    // Event handlers.
    // //////////////////////////////////////////////////////////////////
    @Override
    public void startDocument() {
    }

    @Override
    public void endDocument() {
        this.file = new XMLFile(tagstack.get(0));
    }

    @Override
    public void startElement(String uri, String name, String qName,
            Attributes atts) {
        this.inElement = true;
        // Generate HashMap for the attributes
        LinkedHashMap<String, String> attributes = new LinkedHashMap<>();
        for (int i = 0; i < atts.getLength(); i++) {
            attributes.put(atts.getQName(i), atts.getValue(i));
        }
        // Create new XMLTag and push it onto the stack
        XMLTag current = new XMLTag(qName, attributes);
        tagstack.add(current);
    }

    @Override
    public void endElement(String uri, String name, String qName) {
        this.inElement = false;
        if (tagstack.size() > 1) {
            XMLTag ended = tagstack.remove(tagstack.size() - 1);
            tagstack.get(tagstack.size() - 1).addElement(ended);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) {
        if (this.inElement == true) {
            String content = "";
            for (int i = start; i < start + length; i++) {
                if (ch[i] != '\\' && ch[i] != '"' && ch[i] != '\n' && ch[i] != '\r' && 
                        ch[i] != '\t') {
                    content = content + ch[i];
                }
            }
            tagstack.get(tagstack.size() - 1).setContent(content);
        }
    }

    private XMLFile getXMLFile() {
        return file;
    }

}
