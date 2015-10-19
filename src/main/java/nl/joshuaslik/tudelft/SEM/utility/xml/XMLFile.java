package nl.joshuaslik.tudelft.SEM.utility.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Class to create an xml file
 * 
 * @author Joshua Slik
 *
 */
public class XMLFile {

	XMLTag root;

	/**
	 * Constructor
	 * 
	 * @param root
	 *            root tag of this file
	 */
	public XMLFile(XMLTag root) {
		this.root = root;
	}

	/**
	 * 
	 * @param name
	 *            is the name of the element to get
	 * @return the content of the element at location <b>name</b>
	 * @throws NoSuchElementException
	 *             is thrown if element is not found
	 */
	public String getContent(String name) throws NoSuchElementException {
		return root.getContent(name);
	}

	/**
	 * 
	 * @param name
	 *            is the name of the element to get
	 * @param iteration
	 *            is the iteration of the element to get. It will return the
	 *            i-th element
	 * @return the content of the element at location <b>name</b>
	 * @throws NoSuchElementException
	 *             is thrown when there's no such element is thrown if element
	 *             is not found
	 */
	public String getContent(String name, int iteration) throws NoSuchElementException {
		return root.getContent(name, iteration);
	}

	/**
	 * 
	 * @param name
	 *            is the name of the element to get
	 * @return the element at the location <b>name</b>
	 * @throws NoSuchElementException
	 *             is thrown if element is not found
	 */
	public XMLTag getElement(String name) throws NoSuchElementException {
		return root.getElement(name);
	}

	/**
	 * 
	 * @param name
	 *            is the name of the element to get
	 * @param iteration
	 *            is the iteration of the element to get. It will return the
	 *            i-th element
	 * @return the element at the location <b>name</b>
	 * @throws NoSuchElementException
	 *             is thrown if element is not found
	 */
	public XMLTag getElement(String name, int iteration) throws NoSuchElementException {
		return root.getElement(name, iteration);
	}

	// /**
	// * This method is depreciated. Please use
	// * {@link XMLTag#getAttribute(String)} until functionality has been added.
	// *
	// * This method will get the attribute of an element in this XMLFile. It
	// * expects that you specify where to find the attribute like so:
	// * roottag.child1.child2.targetelement.attributeX
	// *
	// * @param name
	// * is the name of the attribute to get
	// * @return the attribute from element <b>name</b>
	// * @deprecated
	// */
	// public String getAttribute(String name) {
	// String retstr = null;
	// return retstr;
	// }

	/**
	 * 
	 * @param location
	 *            location to save the file
	 * @param args
	 *            optional encoding to use (Default: UTF-8)
	 */
	public void save(String location, String... args) {
		String retstr = root.toString();
		String encoding;
		if (args.length > 0)
			encoding = args[0];
		else
			encoding = "UTF-8";
		File target = new File(location);
		makeDirs(location);
		PrintWriter writer = null;

		try {
			writer = new PrintWriter(location, encoding);
		} catch (FileNotFoundException e) {
			System.err.println("[ERROR] The file \"" + target + "\" is not writable or not acceptable!");
			System.err.println(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			System.err.println("[ERROR] The encoding \"" + encoding + "\" is not supported!");
		}
		writer.write(retstr);
		writer.flush();
		writer.close();
	}

	/**
	 * @return a string representation of this object.
	 */
	public String toString() {
		return root.toString();
	}

	private void makeDirs(String location) {
		File target = new File(location);
		String apath = target.getAbsolutePath();
		System.out.println(apath);
		apath = apath.replace("\\", "/");
		apath = apath.substring(0, apath.lastIndexOf('/'));
		File file = new File(apath);
		file.mkdirs();
	}

}
