package nl.joshuaslik.tudelft.SEM.utility.xml;

/**
 * Exception class.
 * @author Joshua Slik
 *
 */
public class NoSuchElementException extends RuntimeException {

    private static final long serialVersionUID = -3704509663698448420L;

    /**
     * Constructor.
     */
    public NoSuchElementException() {
        super();
    }

    /**
     * The exception message.
     * @param message is the exception message to pass through.
     */
    public NoSuchElementException(String message) {
        super(message);
    }

}
