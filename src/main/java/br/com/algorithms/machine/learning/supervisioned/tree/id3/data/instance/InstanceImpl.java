package br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance;

import java.util.HashMap;
import java.util.Map;

public class InstanceImpl implements Instance {

  private String expectedOutput;
  private Map<String, String> features;

  public InstanceImpl() {

    this.features = new HashMap<String, String>();
  }

  public InstanceImpl setExpectedOutput(String expectedOutput) {

    this.expectedOutput = expectedOutput;

    return this;
  }

  public InstanceImpl setNewFeature(String featureName, String featureValue) {

    this.features.put(featureName, featureValue);

    return this;
  }

  public String getFeatureValue(String featureName) {

    return this.features.get(featureName);
  }

  public String getExpectedOutput() {

    return this.expectedOutput;
  }
}
