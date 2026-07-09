package ppp.ppp.ppp.report;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 * Contains utility methods for working with Strings.
 * 
 * Some of the utilities include line counting and different
 * encoding of a string
 * 
 * @author gwessels
 */
public final class StringUtils extends org.apache.commons.lang3.StringUtils
{
    /** Hidden default constructor */
    private StringUtils(){}

    /**
     * Escapes a string for HTML mark-up.
     * 
     * @param input the string to encode
     * @return the encoded string for use in HTML
     */
    public static String htmlEscape(final String input)
    {
        return StringEscapeUtils.escapeHtml3(input);
    }

    /**
     * Escapes a string for JavaScript mark-up.
     *
     * @param input the string to encode
     * @return the encoded string for use in JavaScript
     */
    public static String javaScriptEscape(final String input)
    {
        return StringEscapeUtils.escapeJava(input);
    }
    
    /**
     * Escapes a string for Xml mark-up.
     * 
     * This method also ensures that the output String has only
     * valid XML unicode characters as specified by the
     * XML 1.0 standard. For reference, please see
     * <a href="http://www.w3.org/TR/2000/REC-xml-20001006#NT-Char">the
     * standard</a>. This method will return an empty
     * String if the input is null or empty.
     * 
     * @param input the string to encode
     * @return the encoded string for use in Xml
     */
    public static String xmlEscape(final String input)
    {
        String rVal = input;
        
        if (StringUtils.isNotBlank(input))
        {
            final StringBuffer out = new StringBuffer();


            char cur; // Used to reference the current character.
            for (int i = 0; i < input.length(); i++)
            {
                cur = input.charAt(i);
                if ((cur == 0x9) ||
                        (cur == 0xA) ||
                        (cur == 0xD) ||
                        ((cur >= 0x20) && (cur <= 0xD7FF)) ||
                        ((cur >= 0xE000) && (cur <= 0xFFFD)) ||
                        ((cur >= 0x10000) && (cur <= 0x10FFFF)))
                {
                    out.append(cur);
                }
            }

            rVal = StringEscapeUtils.escapeXml(out.toString());
        }
        return rVal;
    }

    /**
     * Removes the escape characters for a string for Xml mark-up.
     *
     * @param input the string to unescape
     * @return String containing the unescaped Xml string
     */
    public static String xmlUnescape(final String input)
    {
        return StringEscapeUtils.unescapeXml(input);
    }

    /**
     * Counts the # of lines in the given string.
     * @param input the multi-line string
     * @return # of lines
     */
    public static int getLineCount(final String input)
    {       
        int lines = 0;
        if (input != null)
        {

            final Pattern pattern = Pattern.compile("$", Pattern.MULTILINE);
            final Matcher matcher = pattern.matcher(input);

            while (matcher.find())
            {
                lines++;

            }// while
        }
        return lines;
    }
    
    /**
     * Helper method to simplify the apache join function
     * @param values Collection of string values to join
     * @param separator character to place between the values
     * @return String containing the concatenated result
     */
    public static String join(Collection<String> values, String separator) {
        String result = "";
        if (values != null) {
            result = org.apache.commons.lang3.StringUtils.join(values.toArray(), separator);
        }
        return result;
    }

    /**
     * Repeat a String repeat times to form a new String.
     * @param str the String to repeat, may be null
     * @param repeat number of times to repeat str, negative treated as zero
     * @return String consisting of the original String repeated, null if null String input
     */
    public static String repeat(String str, Integer repeat) {
        return org.apache.commons.lang3.StringUtils.repeat(str, repeat);
    }

    /**
     * Helper method to truncate a string adding an ellipsis to make a more
     * readable trimmed string for display purposes
     * @param input the input string that needs to be trimmed
     * @param maxChars the maximum number of characters of the output string
     * @param right boolean indicating which side of the ellipsis the input string
     * should be displayed on
     * @return String containing the trimmed string with ellipsis
     */
    public static String trimPrint(String input, int maxChars, boolean right)
    {
        if (input == null || input.length() <= maxChars){
            return input;
        }

        String ellipsis = "...";


        StringBuilder sb = new StringBuilder();

        if (right){
            sb.append(ellipsis);
            sb.append(input.substring(input.length() - (maxChars-ellipsis.length())));
        }
        else{
            sb.append(input.substring(0, (maxChars-ellipsis.length())));
            sb.append(ellipsis);
        }

        return sb.toString();

    }

    /**
     * Helper method for converting the stack trace for an Exception into a String
     * @param ex java Exception
     * @return String containing the contents of the Exception stack trace
     */
    public static String stackTraceToString(Exception ex) {

        String result = "";
        try {
            if (ex != null) {
                final StringWriter sw = new StringWriter();
                final PrintWriter pw = new PrintWriter(sw);
                ex.printStackTrace(pw);
                result = sw.toString();
                pw.close();
                sw.close();
            }
        } catch (Exception ignore) {
            // ignore this excaption
        }
        return result;
    }

}
