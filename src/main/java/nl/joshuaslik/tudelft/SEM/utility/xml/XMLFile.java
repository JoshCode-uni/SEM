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
     * @param root root tag of this file
     */
    public XMLFile(XMLTag root) {
        this.root = root;
    }

    /**
     *
     * @param name is the name of the element to get
     * @return the content of the element at location <b>name</b>
     * @throws NoSuchElementException is thrown if element is not found
     */
    public String getContent(String name) throws NoSuchElementException {
        return root.getContent(name);
    }

    /**
     *
     * @param name is the name of the element to get
     * @param iteration is the iteration of the element to get. It will return the i-th element
     * @return the content of the element at location <b>name</b>
     * @throws NoSuchElementException is thrown when there's no such element is thrown if element is
     * not found
     */
    public String getContent(String name, int iteration) throws NoSuchElementException {
        return root.getContent(name, iteration);
    }

    /**
     *
     * @param name is the name of the element to get
     * @return the element at the location <b>name</b>
     * @throws NoSuchElementException is thrown if element is not found
     */
    public XMLTag getElement(String name) throws NoSuchElementException {
        return root.getElement(name);
    }

    /**
     *
     * @param name is the name of the element to get
     * @param iteration is the iteration of the element to get. It will return the i-th element
     * @return the element at the location <b>name</b>
     * @throws NoSuchElementException is thrown if element is not found
     */
    public XMLTag getElement(String name, int iteration) throws NoSuchElementException {
        return root.getElement(name, iteration);
    }

    /**
     * Save the to file.
     * @param location location to save the file.
     * @param args optional encoding to use (Default: UTF-8)
     */
    public void save(String location, String... args) {
        String retstr = root.toString();
        String encoding;
        if (args.length > 0) {
            encoding = args[0];
        } else {
            encoding = "UTF-8";
        }
        File target = new File(location);
        makeDirs(location);
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(location, encoding);
        }
        catch (FileNotFoundException | UnsupportedEncodingException e) {
        }
        assert(writer != null);
        writer.write(retstr);
        writer.flush();
        writer.close();
    }

    /**
     * @return a string representation of this object.
     */
    @Override
    public String toString() {
        return root.toString();
    }

    /**
     * Make directory if nonexistent.
     * @param location the path.
     */
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
