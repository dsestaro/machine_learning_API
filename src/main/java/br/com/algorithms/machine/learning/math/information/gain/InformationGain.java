package br.com.algorithms.machine.learning.math.information.gain;

import br.com.algorithms.machine.learning.math.entropy.Entropy;
import br.com.algorithms.machine.learning.math.information.gain.exception.InvalidFeatureValueException;
import br.com.algorithms.machine.learning.math.information.gain.exception.InvalidInformationGainParametersException;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature.Feature;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.Instances;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.TreeUtils;

import java.util.Map;

public class InformationGain {

  public static final String INVALID_FEATURE = "The Feature object can not be null.";
  public static final String INVALID_MAP = "The instances quantity map can not be null.";
  public static final String INVALID_INSTANCES_QUANTITY = "The number of instances should be bigger than 0.";

  protected InformationGain() {

  }

  public static Double calculateInformationGain(Double entropy, Feature feature, Map<String, Instances> quantityByFeatureValue, double instancesQuantity) {

    validateParameters(feature, quantityByFeatureValue, instancesQuantity);

    Double featureInformationGain = entropy;

    for(String featureValue : feature.getValues()) {

      featureInformationGain -= getFeatureValueInformationGain(quantityByFeatureValue, instancesQuantity, featureValue);
    }

    return featureInformationGain;
  }

  private static void validateParameters(Feature feature, Map<String, Instances> quantityByFeatureValue, double instancesQuantity) {

    if(feature == null) {

      throw new InvalidInformationGainParametersException(INVALID_FEATURE);
    }

    if(quantityByFeatureValue == null) {

      throw new InvalidInformationGainParametersException(INVALID_MAP);
    }

    if(instancesQuantity < 1) {

      throw new InvalidInformationGainParametersException(INVALID_INSTANCES_QUANTITY);
    }
  }

  private static Double getFeatureValueInformationGain(Map<String, Instances> quantityByFeatureValue, double instancesQuantity, String featureValue) {

    Double featureInformationGain = 0.0;

    try {

      Instances instances = quantityByFeatureValue.get(featureValue);
      Integer numberOfInstances = instances.getNumberOfInstances();

      if(numberOfInstances > 0) {

        featureInformationGain = calculate(instancesQuantity, instances, numberOfInstances);
      }
    } catch (NullPointerException e) {

      throw new InvalidFeatureValueException();
    }

    return featureInformationGain;
  }

  private static Double calculate(double instancesQuantity, Instances instances, Integer numberOfInstances) {

    Double featureInformationGain;

    Map<String, Integer> quantityInstancesByOutput = TreeUtils.calculateQuantityOutput(instances);

    Double featureEntropy = Entropy.calculateEntropy(quantityInstancesByOutput, numberOfInstances);

    featureInformationGain = numberOfInstances / instancesQuantity * featureEntropy;

    return featureInformationGain;
  }
}
