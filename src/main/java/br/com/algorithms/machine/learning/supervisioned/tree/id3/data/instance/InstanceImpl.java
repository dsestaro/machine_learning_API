package br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance;

import br.com.algorithms.machine.learning.supervisioned.tree.id3.exception.InvalidInstanceInformationException;
import br.com.algorithms.machine.learning.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class InstanceImpl implements Instance {

  private static final String INVALID_FEATURE_NAME = "The feature name cannot be null or empty.";
  private static final String INVALID_FEATURE = "The same instance cannot have two different values for the same feature.";

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

    validateFeature(featureName);

    this.features.put(featureName, featureValue);

    return this;
  }

  private void validateFeature(String featureName) {

    if(StringUtils.isEmpty(featureName)) {

      throw new InvalidInstanceInformationException(INVALID_FEATURE_NAME);
    }

    if(this.features.containsKey(featureName)) {

      throw new InvalidInstanceInformationException(INVALID_FEATURE);
    }
  }

  public String getFeatureValue(String featureName) {

    return this.features.get(featureName);
  }

  public String getExpectedOutput() {

    return this.expectedOutput;
  }
}
