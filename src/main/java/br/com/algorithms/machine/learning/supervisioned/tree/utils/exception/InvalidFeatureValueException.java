package br.com.algorithms.machine.learning.supervisioned.tree.utils.exception;

public class InvalidFeatureValueException extends Throwable {

  public InvalidFeatureValueException(String featureValue) {

    super("The value " + featureValue + " does not exist in the features map.");
  }
}
