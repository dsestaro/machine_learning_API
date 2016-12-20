package br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.data.feature.value.matrix;

import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.exception.InvalidFeatureInDistributionMatrixException;

import java.util.HashMap;
import java.util.Map;

public class FeatureValueDistributionMatrixImpl implements FeatureValueDistributionMatrix {

  private Map<String,Map<String,Integer>> featureValueDistribution;

  public FeatureValueDistributionMatrixImpl() {

    this.featureValueDistribution = new HashMap<String, Map<String, Integer>>();
  }

  public FeatureValueDistributionMatrix setNewFeature(String featureName) {

    this.featureValueDistribution.put(featureName, new HashMap<String, Integer>());

    return this;
  }

  public Map<String, Integer> getFeatureDistribution(String featureName) throws InvalidFeatureInDistributionMatrixException {

    Map<String, Integer> featureDistribution = this.featureValueDistribution.get(featureName);

    if(featureDistribution == null) {
      throw new InvalidFeatureInDistributionMatrixException(featureName);
    }

    return featureDistribution;
  }

  public FeatureValueDistributionMatrix setNewFeatureValue(String featureName, String featureValue) throws InvalidFeatureInDistributionMatrixException {

    Map<String, Integer> featureDistribution = this.featureValueDistribution.get(featureName);

    if(featureDistribution == null) {
      throw new InvalidFeatureInDistributionMatrixException(featureName);
    }

    featureDistribution.put(featureValue, 0);

    return this;
  }
}
