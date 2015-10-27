package nl.joshuaslik.tudelft.SEM.utility.xml;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    /**
     * parsing a file as an xml file
     *
     * @param is is a input stream of the xml file
     * @return the xml file
     */
    public static XMLFile parseFile(InputStream is) {
        XMLReader xr;
        SAXParser handler = new SAXParser();
        try {
            xr = XMLReaderFactory.createXMLReader();
            xr.setContentHandler(handler);
            xr.setErrorHandler(handler);
            xr.parse(new InputSource(is));
        }
        catch (IOException | NullPointerException | SAXException e) {
            Logger.getLogger(SAXParser.class.getName()).log(Level.SEVERE, null, e);
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

    /**
     * Start of document: do nothing.
     */
    @Override
    public void startDocument() {
    }

    /**
     * End of document: create xml file.
     */
    @Override
    public void endDocument() {
        this.file = new XMLFile(tagstack.get(0));
    }

    /**
     * Start of element.
     * @param uri the path.
     * @param name the name.
     * @param qName the q name.
     * @param atts the attributes.
     */
    @Override
    public void startElement(String uri, String name, String qName,
            Attributes atts) {
        this.inElement = true;
        LinkedHashMap<String, String> attributes = new LinkedHashMap<>();
        for (int i = 0; i < atts.getLength(); i++) {
            attributes.put(atts.getQName(i), atts.getValue(i));
        }
        XMLTag current = new XMLTag(qName, attributes);
        tagstack.add(current);
    }

    /**
     * End of element.
     * @param uri the path.
     * @param name the name.
     * @param qName the q name.
     */
    @Override
    public void endElement(String uri, String name, String qName) {
        this.inElement = false;
        if (tagstack.size() > 1) {
            XMLTag ended = tagstack.remove(tagstack.size() - 1);
            tagstack.get(tagstack.size() - 1).addElement(ended);
        }
    }

    /**
     * Override of characters methods.
     * @param ch the characters.
     * @param start the start.
     * @param length the length.
     */
    @Override
    public void characters(char ch[], int start, int length) {
        if (this.inElement == true) {
            String content = "";
            for (int i = start; i < start + length; i++) {
                if (ch[i] != '\\' && ch[i] != '"' && ch[i] != '\n' && ch[i] != '\r'
                        && ch[i] != '\t') {
                    content = content + ch[i];
                }
            }
            tagstack.get(tagstack.size() - 1).setContent(content);
        }
    }

    /**
     * Get the xml file.
     * @return xml file.
     */
    private XMLFile getXMLFile() {
        return file;
    }

}
