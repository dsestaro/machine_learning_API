package br.com.algorithms.machine.learning.supervisioned.tree.id3.exception.instance;

public class NonExistentInstanceException extends RuntimeException {

  public static final String NON_EXISTENT_INSTANCE = "The searched instance does not exist.";

  public NonExistentInstanceException() {

    super(NON_EXISTENT_INSTANCE);
  }
}
