package br.com.algorithms.machine.learning.math.information.gain.exception;

public class InvalidFeatureValueException extends RuntimeException {

  public static final String ERROR_MESSAGE = "All the keys on the quantity map should match the values in the feature.";

  public InvalidFeatureValueException() {

    super(ERROR_MESSAGE);
  }
}
