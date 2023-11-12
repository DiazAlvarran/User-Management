package com.nisum.users.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Implements useful methods
 * 
 * @author Jorge Diaz
 * @version 1.0
 */
public class Utils {
  
  private Utils() {
    
  }
  
  /**
   * Generate and return a random UUID
   * 
   * @return String new random UUID
   */
  public static String getNewUuid() {
    return String.valueOf(UUID.randomUUID());
  }
  
  /**
   * Convert a Date to String according to the indicated format
   * 
   * @param date any date
   * @param format date format for String (example: yyyy-MM-dd)
   * @return String formatted date
   */
  public static String convertDateToFormattedString(Date date, String format) {
    return date == null ? null : new SimpleDateFormat(format).format(date);
  }
  
  /**
   * Validate a text according to a regular expression
   * 
   * @param regex regular expression
   * @param text any text
   * @return Boolean validation result
   */
  public static Boolean isInvalidString(String regex, String text) {
    return !Pattern.matches(regex, text);
  }

}
