package ch.heigvd.res.labio.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

  private static final Logger LOG = Logger.getLogger(Utils.class.getName());

  // Different systems' line separators (order of elements is important)
  private static final String[] SEPARATORS = new String[] {"\r\n", "\n", "\r"};

  /**
   * This method looks for the next new line separators (\r, \n, \r\n) to extract
   * the next line in the string passed in arguments. 
   * 
   * @param lines a string that may contain 0, 1 or more lines
   * @return an array with 2 elements; the first element is the next line with
   * the line separator, the second element is the remaining text. If the argument does not
   * contain any line separator, then the first element is an empty string.
   */
  public static String[] getNextLine(String lines) {
      String nextLine = "";
      int index = 0;

      // Iterate through possible separator types
      for (String separator : SEPARATORS) {
          // If the text uses this type of separator
          // This is why the order of SEPARATORS is important
          // (\r\n contains the other separators but not the other way around)
          if (lines.contains(separator)) {
              // Set the index to the end of the first line
              index = lines.indexOf(separator) + separator.length();

              // Put the first line in a String
              nextLine = lines.substring(0, index);

              // And get out of the loop
              break;
          }
      }

      // Return the first line, and the rest of the text
      return new String[] {nextLine, lines.substring(index)};
  }

}
