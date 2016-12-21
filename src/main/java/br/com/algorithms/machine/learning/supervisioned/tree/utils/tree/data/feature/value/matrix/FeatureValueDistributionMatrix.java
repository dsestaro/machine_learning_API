package br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.data.feature.value.matrix;

import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.exception.InvalidFeatureInDistributionMatrixException;

import java.util.Map;

public interface FeatureValueDistributionMatrix {

  FeatureValueDistributionMatrix setNewFeature(String featureName);

  Map<String, Integer> getFeatureDistribution(String feature) throws InvalidFeatureInDistributionMatrixException;

  FeatureValueDistributionMatrix addFeatureValueQuantity(String featureName, String featureValue) throws InvalidFeatureInDistributionMatrixException;

  Integer getQuantityByFeatureValue(String featureName, String featureValue) throws InvalidFeatureInDistributionMatrixException;
}
