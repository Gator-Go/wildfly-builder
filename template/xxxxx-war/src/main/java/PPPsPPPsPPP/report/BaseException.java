package ppp.ppp.ppp.report;

/**
 * Lllll
 *
 * This is a simple custom exception class (BaseException) that extends java.lang.Exception.
 * It provides the standard constructors for:
 * - No-argument
 * - Message only
 * - Message + cause (for exception chaining)
 * - Cause only
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

public class BaseException extends Exception
{
    private static final long serialVersionUID = 7198786298987050147L;

    /**
     * default no-arg constructor
     */
    public BaseException()
    {
        super();
    }
    
    /**
     * A constructor that takes a String.
     * @param message The message to include with the exception
     */
    public BaseException(String message)
    {
        super(message);
    }
    
    /**
     * For exception chaining purposes.
     * 
     * @param message The message to include with the exception
     * @param cause The causing exception
     */
    public BaseException(String message, Throwable cause)
    {
        super(message, cause);
    }
    
    /**
     * 
     * For exception chaining purposes.
     * 
     * @param cause The causing exception
     */
    public BaseException(Throwable cause)
    {
        super(cause);
    }
    
}
