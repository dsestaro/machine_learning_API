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

    checkIfFeatureExists(featureName, featureDistribution);

    return featureDistribution;
  }

  public FeatureValueDistributionMatrix addFeatureValueQuantity(String featureName, String featureValue) throws InvalidFeatureInDistributionMatrixException {

    Map<String, Integer> featureDistribution = this.featureValueDistribution.get(featureName);

    checkIfFeatureExists(featureName, featureDistribution);

    Integer quantity = getQuantityByFeatureValue(featureName, featureValue);

    featureDistribution.put(featureValue, ++quantity);

    return this;
  }

  protected void setNewFeatureValue(String featureName, String featureValue) throws InvalidFeatureInDistributionMatrixException {

    Map<String, Integer> featureDistribution = this.featureValueDistribution.get(featureName);

    checkIfFeatureExists(featureName, featureDistribution);

    featureDistribution.put(featureValue, 1);
  }

  private void checkIfFeatureExists(String featureName, Map<String, Integer> featureDistribution) throws InvalidFeatureInDistributionMatrixException {

    if(featureDistribution == null) {

      throw new InvalidFeatureInDistributionMatrixException(featureName);
    }
  }

  public Integer getQuantityByFeatureValue(String featureName, String featureValue) throws InvalidFeatureInDistributionMatrixException {

    Integer quantity = this.featureValueDistribution.get(featureName).get(featureValue);

    if(quantity == null) {

      setNewFeatureValue(featureName, featureValue);

      quantity = new Integer(0);
    }

    return quantity;
  }
}
