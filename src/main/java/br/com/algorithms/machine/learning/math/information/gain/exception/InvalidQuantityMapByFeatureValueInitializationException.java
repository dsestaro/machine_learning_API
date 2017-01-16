package br.com.algorithms.machine.learning.math.information.gain.exception;

public class InvalidQuantityMapByFeatureValueInitializationException extends RuntimeException {

  public InvalidQuantityMapByFeatureValueInitializationException() {

    super("All the keys on the quantity map should match the values in the feature.");
  }
}
