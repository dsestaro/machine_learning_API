package br.com.algorithms.machine.learning.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StringUtilsTests {

  @Test
  public void testStringUtilsInstantiation() {

    StringUtils utils = new StringUtils();

    assertNotNull(utils);
  }

  @Test
  public void testIsEmpty_EmptyString() {

    boolean expectedResult = true;

    String value = "";

    assertEquals(expectedResult, StringUtils.isEmpty(value));
  }

  @Test
  public void testIsEmpty_NullString() {

    boolean expectedResult = true;

    String value = null;

    assertEquals(expectedResult, StringUtils.isEmpty(value));
  }

  @Test
  public void testIsEmpty_ValidString() {

    boolean expectedResult = false;

    String value = "Valid";

    assertEquals(expectedResult, StringUtils.isEmpty(value));
  }
}
