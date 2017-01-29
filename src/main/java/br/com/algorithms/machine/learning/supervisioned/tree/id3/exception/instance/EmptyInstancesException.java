package br.com.algorithms.machine.learning.supervisioned.tree.id3.exception.instance;

public class EmptyInstancesException extends Exception {

  public EmptyInstancesException() {
    super("The number of instances should be bigger than zero.");
  }
}
