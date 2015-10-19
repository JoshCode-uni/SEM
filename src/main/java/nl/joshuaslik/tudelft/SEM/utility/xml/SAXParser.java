package nl.joshuaslik.tudelft.SEM.utility.xml;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
	private ArrayList<XMLTag> tagstack = new ArrayList<XMLTag>();
	private boolean inElement = false;

	/*
	 * public SAXParser() { }
	 */

	/**
	 * parsing a file as an xml file
	 * 
	 * @param filename
	 *            is a string for the name of the xml file
	 * @return the xml file
	 */
	public static XMLFile parseFile(String filename) {
		filename = filename.replace("\\", "/");

		XMLReader xr = null;
		try {
			xr = XMLReaderFactory.createXMLReader();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		SAXParser handler = new SAXParser();
		xr.setContentHandler(handler);
		xr.setErrorHandler(handler);

		InputStream is = Class.class.getResourceAsStream(filename);
		try {
			xr.parse(new InputSource(is));
		} catch (IOException | NullPointerException e) {
			System.err.println("File \"" + filename + "\" does not exist");
		} catch (SAXException e) {
			System.err.println("Something went wrong parsing the file: \""
					+ filename + "\"");
			System.err.println(e.getMessage());
		}
		return handler.getXMLFile();

	}

	/**
	 * parsing a local xml file
	 * 
	 * @param filename
	 *            name of the xml file
	 * @return the xml file
	 */
	public static XMLFile parseLocalFile(String filename) {
		XMLReader xr = null;
		try {
			xr = XMLReaderFactory.createXMLReader();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		SAXParser handler = new SAXParser();
		xr.setContentHandler(handler);
		xr.setErrorHandler(handler);

		InputStream is = null;
		try {
			is = new FileInputStream(new File(filename));
		} catch (FileNotFoundException e1) {
			System.err.println("File \"" + filename + "\" does not exist");
			System.err.println(e1.getMessage());
		}

		try {
			xr.parse(new InputSource(is));
		} catch (IOException | NullPointerException e) {
			System.err.println("File \"" + filename + "\" does not exist");
		} catch (SAXException e) {
			System.err.println("Something went wrong parsing the file: \""
					+ filename + "\"");
			System.err.println(e.getMessage());
		}
		return handler.getXMLFile();

	}

	/**
	 * parsing a local xml file as a string
	 * 
	 * @param xmlstring
	 *            is a string with the xml in it
	 * @return the xml file
	 */
	public static XMLFile parseString(String xmlstring) {
		XMLReader xr = null;
		try {
			xr = XMLReaderFactory.createXMLReader();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		SAXParser handler = new SAXParser();
		xr.setContentHandler(handler);
		xr.setErrorHandler(handler);
		InputStream is = new ByteArrayInputStream(
				xmlstring.getBytes(StandardCharsets.UTF_8));
		try {
			xr.parse(new InputSource(is));
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (SAXException e) {
			System.err.println("Something went wrong parsing your string.");
			System.err.println(e.getMessage());
		}
		return handler.getXMLFile();
	}

	// //////////////////////////////////////////////////////////////////
	// Event handlers.
	// //////////////////////////////////////////////////////////////////

	public void startDocument() {
		// System.out.println("Start document");
	}

	public void endDocument() {
		// System.out.println("End document");
		this.file = new XMLFile(tagstack.get(0));
	}

	public void startElement(String uri, String name, String qName,
			Attributes atts) {
		// Default behaviour
		// if ("".equals(uri)) {
		// System.out.println("Start element: " + qName);
		// for (int i = 0; i < atts.getLength(); i++) {
		// System.out.println("   Attribute : " + atts.getQName(i) + "=\""
		// + atts.getValue(i) + "\"");
		// }
		// } else {
		// System.out.println("Start element: {" + uri + "}" + name);
		// }

		// My behaviour
		this.inElement = true;
		// Generate HashMap for the attributes
		LinkedHashMap<String, String> attributes = new LinkedHashMap<String, String>();
		for (int i = 0; i < atts.getLength(); i++) {
			attributes.put(atts.getQName(i), atts.getValue(i));
		}

		// Create new XMLTag and push it onto the stack
		XMLTag current = new XMLTag(qName, attributes);
		tagstack.add(current);
	}

	public void endElement(String uri, String name, String qName) {
		// Default behaviour
		// if ("".equals(uri))
		// System.out.println("End element: " + qName);
		// else
		// System.out.println("End element:   {" + uri + "}" + name);

		// My behaviour

		/*
		 * Element has ended, we are no longer inside an element This has been
		 * added because after endElement is called, the parser calls the
		 * characters() method again, and we don't want it to run, because it
		 * will override the previous content with nothing.
		 */
		this.inElement = false;
		// Pop the current XMLTag off the stack (only if it is not the root
		// element)
		if (tagstack.size() > 1) {
			XMLTag ended = tagstack.remove(tagstack.size() - 1);

			tagstack.get(tagstack.size() - 1).addElement(ended);
		}
	}

	public void characters(char ch[], int start, int length) {
		if (this.inElement == true) {
			// System.out.print("   Characters: \"");
			String content = "";
			for (int i = start; i < start + length; i++) {
				switch (ch[i]) {
				case '\\':
					// System.out.print("\\\\");
					// content = content + "\\\\";
					break;
				case '"':
					// System.out.print("\\\"");
					// content = content + "\\\"";
					break;
				case '\n':
					// System.out.print("\\n");
					// content = content + "\\n";
					break;
				case '\r':
					// System.out.print("\\r");
					// content = content + "\\r";
					break;
				case '\t':
					// System.out.print("\\t");
					// content = content + "\\t";
					break;
				default:
					// System.out.print(ch[i]);
					content = content + ch[i];
					break;
				}
			}
			// System.out.println("Characters: " + content);
			// System.out.println(tagstack.get(tagstack.size() - 1).getName());
			tagstack.get(tagstack.size() - 1).setContent(content);
			// System.out.print("\n");
			// System.out.print("\"\n");
		}
	}

	private XMLFile getXMLFile() {
		return file;
	}

}