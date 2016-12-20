package br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.data.instance;

public interface Instance {

  InstanceImpl setExpectedOutput(String expectedOutput);

  InstanceImpl setNewFeature(String featureName, String featureValue);

  String getFeatureValue(String featureName);

  String getExpectedOutput();
}
