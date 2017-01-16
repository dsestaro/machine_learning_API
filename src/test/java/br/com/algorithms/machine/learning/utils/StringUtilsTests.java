package br.com.algorithms.machine.learning.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringUtilsTests {

  @Test
  public void testValidateOfEmtpyString() {

    assertEquals(true, StringUtils.isEmpty(""));
  }

  @Test
  public void testValidateOfNullString() {

    assertEquals(true, StringUtils.isEmpty(null));
  }

  @Test
  public void testValidateOfValidString() {

    assertEquals(false, StringUtils.isEmpty("Valid"));
  }
}
