package es.example.onlineshop.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utils {

  private static final String DATE_PATTERN = "yyyy-MM-dd-HH.mm.ss";


  /**
   * Format date to string with date format.
   *
   * @param date Date to format.
   * @return formatted date.
   */
  public static String formatDate(final Timestamp date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
    return formatter.format(date.toLocalDateTime());
  }

  /**
   * Convert date to timestamp with date format.
   *
   * @param date String date to convert.
   * @return timestamp date.
   */
  public static Timestamp toTimestamp(final String date) throws ParseException {
    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
    return new Timestamp(dateFormat.parse(date).getTime());
  }

  /**
   * Convert date to LocalDateTime with date format.
   *
   * @param date String date to convert.
   * @return timestamp date.
   */
  public static LocalDateTime toLocalDateTime(final String date)  {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
    return LocalDateTime.parse(date, formatter);
  }

}
