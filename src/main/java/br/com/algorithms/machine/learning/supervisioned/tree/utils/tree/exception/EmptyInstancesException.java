package br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.exception;

public class EmptyInstancesException extends Exception {

  public EmptyInstancesException() {
    super("The number of instances should be bigger than zero.");
  }
}
