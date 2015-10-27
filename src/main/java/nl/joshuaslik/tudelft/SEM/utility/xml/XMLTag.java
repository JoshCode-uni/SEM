package nl.joshuaslik.tudelft.SEM.utility.xml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

/**
 * Class for the XML tag
 *
 * @author <a href="http://www.joshuaslik.nl/" target="_blank">Joshua Slik</a>
 */
public class XMLTag {

    private String name;
    private final ArrayList<XMLTag> elements;
    private final ArrayList<String> elementnames;
    private LinkedHashMap<String, String> atts;
    private String content;

    /**
     * Defining an xml tag
     *
     * @param name is a string with the name of the tag
     * @param atts are the attributes the xml tag has
     */
    public XMLTag(String name, LinkedHashMap<String, String> atts) {
        this.name = name;
        this.atts = atts;
        this.elements = new ArrayList<>();
        this.elementnames = new ArrayList<>();
        this.content = null;
    }

    /**
     * Defining an xml tag
     *
     * @param name is a string with the name of the tag
     */
    public XMLTag(String name) {
        this.name = name;
        this.atts = new LinkedHashMap<>();
        this.elements = new ArrayList<>();
        this.elementnames = new ArrayList<>();
        this.content = null;
    }

    /**
     * counting the size of the tag
     *
     * @return the amount of elements in the tag
     */
    public int elements() {
        return elements.size();
    }

    /**
     * Count the elemnts
     *
     * @param name is the name of the tag
     * @return is the amount of elements
     */
    public int elements(String name) {
        int count = 0;
        for (XMLTag element : elements) {
            if (element.getName().equals(name)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Checking if the tag has an element
     *
     * @return true or false depending on if the element exists
     */
    public boolean hasElements() {
        return !elements.isEmpty();
    }

    /**
     * check if elements contain a certain name
     *
     * @param name is the name of the xml tag
     * @return elements
     */
    public boolean hasElement(String name) {
        return elementnames.contains(name);
    }

    /**
     * checking if the tag has more than one element
     *
     * @param name is the name of the tag
     * @return the tags with more than one element
     */
    public boolean hasMoreThanOneElement(String name) {
        return (Collections.frequency(elementnames, name) > 1);
    }

    /**
     * Add an element to a tag
     *
     * @param element is a part of the xml tag
     */
    public void addElement(XMLTag element) {
        elements.add(element);
        elementnames.add(element.getName());
    }

    /**
     * add an attribute to the tag
     *
     * @param attribute is a string
     * @param value is the amount of attributes added
     */
    public void addAttribute(String attribute, String value) {
        atts.put(attribute, value);
    }

    /**
     * get an element of the xml tag
     *
     * @param element is the index of the element to get
     * @return the element
     * @throws NoSuchElementException is thrown if the element is not found
     */
    public XMLTag getElement(String element) throws NoSuchElementException {
        if (element.equals(name)) {
            return this;
        }
        int splitpoint = element.indexOf(".") + 1;
        element = element.substring(splitpoint, element.length());
        String subelement = element.split("\\.")[0];
        if (Collections.frequency(elementnames, subelement) > 1) {
            throw new ElementNotUniqueException(this.name
                    + " contains more than one element called '" + subelement
                    + "'");
        }
        if (elementnames.contains(subelement)) {
            return elements.get(elementnames.indexOf(subelement)).getElement(
                    element);
        }
        throw new NoSuchElementException(this.name + " does not have element '"
                + element + "'");
    }

    /**
     * Get an element of the xml tag
     *
     * @param element is a string which is a part of the tag
     * @param iteration repeating the tag
     * @return the element
     * @throws NoSuchElementException is thrown when there's no such element
     */
    public XMLTag getElement(String element, int iteration)
            throws NoSuchElementException {
        if (element.equals(name)) {
            return this;
        }
        int splitpoint = element.indexOf(".") + 1;
        element = element.substring(splitpoint, element.length());
        String[] subelements = element.split("\\.");
        String subelement = subelements[0];
        if (elementnames.contains(subelement)) {
            if (subelements.length > 1) {
                return elements.get(elementnames.indexOf(subelement))
                        .getElement(element, iteration);
            }
            return elements.get(indexOfIteration(subelement, iteration))
                    .getElement(element, iteration);
        }
        throw new NoSuchElementException(this.name + " does not have element '"
                + element + "'");
    }
    public XMLTag getElementByAttribute(String attribute, String value) {
        ArrayList<XMLTag> list = this.getElements();
        for (XMLTag x : list) {
            LinkedHashMap<String, String> lhm = x.getAttributes();
            if (lhm.containsKey(attribute)) {
                if (lhm.get(attribute).equals(value)) {
                    return x;
                }
            }
        }
        return null;
    }

    /**
     * get the content of the tag
     *
     * @param element is a string which is a part of the tag
     * @return the content
     * @throws NoSuchElementException is thrown when there's no such exception
     */
    public String getContent(String element) throws NoSuchElementException {
        if (element.equals(name)) {
            return content;
        }
        int splitpoint = element.indexOf(".") + 1;
        element = element.substring(splitpoint, element.length());
        String subelement = element.split("\\.")[0];
        if (Collections.frequency(elementnames, subelement) > 1) {
            throw new ElementNotUniqueException(this.name
                    + " contains more than one element called '" + subelement
                    + "'");
        }
        if (elementnames.contains(subelement)) {
            return elements.get(elementnames.indexOf(subelement)).getContent(
                    element);
        }
        throw new NoSuchElementException(this.name + " does not have element '"
                + element + "'");
    }

    /**
     * get the content of the tag
     *
     * @param element is a string which is a part of the tag
     * @param iteration is repeating the tag
     * @return the content
     * @throws NoSuchElementException is thrown when there's no such exception
     */
    public String getContent(String element, int iteration)
            throws NoSuchElementException {
        if (element.equals(name)) {
            return content;
        }
        int splitpoint = element.indexOf(".") + 1;
        element = element.substring(splitpoint, element.length());
        String[] subelements = element.split("\\.");
        String subelement = subelements[0];
        if (elementnames.contains(subelement)) {
            if (subelements.length > 1) {
                return elements.get(elementnames.indexOf(subelement))
                        .getContent(element, iteration);
            }
            return elements.get(indexOfIteration(subelement, iteration))
                    .getContent(element, iteration);
        }
        throw new NoSuchElementException(this.name + " does not have element '"
                + element + "'");
    }

    /**
     * checking if the tag has a certain attribute
     *
     * @return true or false
     */
    public boolean hasAttribute() {
        return !(atts.isEmpty());
    }

    /**
     * Checking if the tag has a certain attribute
     *
     * @param str is a string which is an attribute of the tag
     * @return true or false depending on if the tag has the attribute
     */
    public boolean hasAttribute(String str) {
        return atts.containsKey(str);
    }

    /**
     * Checking if the tag has an attribute
     *
     * @param attribute is a part of the tag
     * @return the attribute
     * @throws NoSuchAttributeException is thrown when there's no such attribute
     */
    public String getAttribute(String attribute)
            throws NoSuchAttributeException {
        if (atts.containsKey(attribute)) {
            return atts.get(attribute);
        }
        throw new NoSuchAttributeException(this.name
                + " does not have attribute " + attribute + "!");
    }

    /**
     * Getter
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter
     *
     * @param name is the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter
     *
     * @return the elements
     */
    public ArrayList<XMLTag> getElements() {
        return elements;
    }

    /**
     * Getter
     *
     * @return the attributes
     */
    public LinkedHashMap<String, String> getAttributes() {
        return atts;
    }

    /**
     * Setter
     *
     * @param atts is the attributes to set
     */
    public void setAttributes(LinkedHashMap<String, String> atts) {
        this.atts = atts;
    }

    /**
     * Getter
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Setter
     *
     * @param content is the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        String retstr = toString(0);
        return retstr.substring(0, retstr.length() - 1);
    }

    /**
     * Making the tag into a string.
     *
     * @param indent is the length of the arraylist
     * @return the string
     */
    public String toString(int indent) {
        String retstr = "";
        String dent = "";
        for (int i = 0; i < indent; i++) {
            dent = dent + " ";
        }
        retstr = retstr + dent + "<" + name;
        if (!(atts.isEmpty())) {
            ArrayList<String> keys = new ArrayList<>(atts.keySet());
            ArrayList<String> values = new ArrayList<>(atts.values());
            for (int i = 0; i < atts.size(); i++) {
                retstr = retstr + " " + keys.get(i) + "=\"" + values.get(i)
                        + "\"";
            }
        }
        if (elements.isEmpty() && (content == null || content.isEmpty())) {
            return retstr + " />" + "\n";
        }
        retstr = retstr + ">";
        if (!(content == null) && !(content.isEmpty())) {
            retstr = retstr + content;
        }
        if (elements.size() > 0) {
            retstr = retstr + "\n";
            for (XMLTag element : elements) {
                retstr = retstr + element.toString(indent + 4);
            }
            retstr = retstr + dent + "</" + name + ">" + "\n";
        } else {
            retstr = retstr + "</" + name + ">" + "\n";
        }
        return retstr;
    }

    /**
     * Get the index.
     * @param str the string.
     * @param it the iterator.
     * @return the result.
     */
    private int indexOfIteration(String str, int it) {
        int count = 0;
        for (int i = 0; i < elementnames.size(); i++) {
            if (elementnames.get(i).equals(str)) {
                count++;
                if (count == it) {
                    return i;
                }
            }
        }
        throw new NoSuchElementException();
    }
}
