package br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.exception;

public class EmptyFeaturesException extends Exception {

  public EmptyFeaturesException() {
    super("The number of features should be bigger than zero.");
  }
}
