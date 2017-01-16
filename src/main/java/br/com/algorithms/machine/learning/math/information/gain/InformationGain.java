package br.com.algorithms.machine.learning.math.information.gain;

import br.com.algorithms.machine.learning.math.entropy.Entropy;
import br.com.algorithms.machine.learning.math.information.gain.exception.InvalidQuantityMapByFeatureValueInitializationException;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature.Feature;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.Instances;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.TreeUtils;

import java.util.Map;

public class InformationGain {

  protected InformationGain() {

  }

  public static Double calculateInformationGain(Double entropy, Feature feature, Map<String, Instances> quantityByFeatureValue, double instancesQuantity) {

    Double featureInformationGain = entropy;

    for(String featureValue : feature.getValues()) {

      try {

        Map<String, Integer> quantityInstancesByOutput = TreeUtils.calculateQuantityOutput(quantityByFeatureValue.get(featureValue));

        Double featureEntropy = Entropy.calculateEntropy(quantityInstancesByOutput, quantityByFeatureValue.get(featureValue).getNumberOfInstances());

        featureInformationGain -= quantityByFeatureValue.get(featureValue).getNumberOfInstances() / instancesQuantity * featureEntropy;
      } catch (NullPointerException e) {

        throw new InvalidQuantityMapByFeatureValueInitializationException();
      }
    }

    return featureInformationGain;
  }
}
