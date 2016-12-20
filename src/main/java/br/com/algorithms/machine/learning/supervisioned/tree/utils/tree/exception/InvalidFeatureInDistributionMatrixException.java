package br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.exception;

public class InvalidFeatureInDistributionMatrixException  extends Exception {

  public InvalidFeatureInDistributionMatrixException(String feature) {
    super("The feature " + feature + " doesn't exist in the distribution matrix.");
  }
}
