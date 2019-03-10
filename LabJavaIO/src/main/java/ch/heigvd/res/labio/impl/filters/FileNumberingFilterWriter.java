package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\tHello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  private boolean firstChar = true;
  private int counter = 0;
  private char previousChar = '\0';

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    // Call method for one character on each character of the string
    for (int i = off; i < off + len; i++) {
      write(str.charAt(i));
    }
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    // Call method for one character on each element of the array
    for (int i = off; i < off + len; i++) {
      write(cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    // If the previous character was \r and the current isn't \n,
    // we apply the start of line changes
    if (previousChar == '\r' && c != '\n') {
      out.write(++counter + "\t");
    }

    // The very first character must be written after a line number and a tab
    if (firstChar) {
      out.write(++counter + "\t" + (char) c);
      firstChar = false;
    }
    else {
      // We simply write the character
      out.write(c);
    }

    // And if it was a line separator of type \n,
    if (c == '\n') {
      // We also write the next line number and tab
      out.write(++counter + "\t");
    }

    // Set previous character
    previousChar = (char) c;
  }
  /*
   * Quand je lis un \r, je sonne la cloche
   * Par contre, quand je lis un \n, je me demande si je viens de lire un \r et, dans ce cas, je ne fais rien
   * ou
   * Quand je lis un \n, je sonne la cloche. Par contre, quand je lis un \r, je ne sonne pas tout suite.
   * J'attends d'avoir lu le caractère suivant ou d'avoir atteint la fin du fichier
   */

}
