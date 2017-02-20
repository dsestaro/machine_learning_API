package br.com.algorithms.machine.learning.utils;

public class StringUtils {

  protected StringUtils() {

  }

  public static boolean isEmpty(String value) {

    return (value == null || value.length() == 0);
  }
}
