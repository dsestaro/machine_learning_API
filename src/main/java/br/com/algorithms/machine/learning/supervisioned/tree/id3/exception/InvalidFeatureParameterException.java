package br.com.algorithms.machine.learning.supervisioned.tree.id3.exception;

public class InvalidFeatureParameterException extends RuntimeException {

  public static final String INVALID_NAME = "The feature name cannot be null or empty";

  public InvalidFeatureParameterException() {

    super(INVALID_NAME);
  }
}