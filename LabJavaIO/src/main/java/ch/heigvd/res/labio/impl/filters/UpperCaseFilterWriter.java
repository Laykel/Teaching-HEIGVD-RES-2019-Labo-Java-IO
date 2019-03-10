package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    // String to uppercase
    str = str.toUpperCase();
    // Then call parent's method to write
    super.write(str, off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    // All wanted characters to uppercase
    for (int i = off; i < off + len; i++) {
      cbuf[i] = Character.toUpperCase(cbuf[i]);
    }

    // Then call parent's method to write
    super.write(cbuf, off, len);
  }

  @Override
  public void write(int c) throws IOException {
    // If the character sent is lowercase
    if (Character.isLowerCase((char) c)) {
      // We make it uppercase
      c = Character.toUpperCase(c);
    }
    // And we call the parent's method to write
    super.write(c);
  }

}
