package br.com.algorithms.machine.learning.supervisioned.tree.utils;

import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature.Feature;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.Instance;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.Instances;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.InstancesImpl;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.exception.InvalidFeatureValueException;
import com.sun.javafx.binding.StringFormatter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeUtils {

  public static final String INVALID_FEATURE = "The value \"%1$s\" does not exist in the features map.";

  protected TreeUtils() {

  }

  public static Map<String, Integer> calculateQuantityOutput(Instances instances) {

    return calculateQuantityOutput(instances.getInstances());
  }

  public static Map<String, Integer> calculateQuantityOutput(List<Instance> instances) {

    Map<String, Integer> outputQuant = new HashMap<String, Integer>();

    for(Instance instance : instances) {

      populateOutputMap(outputQuant, instance.getExpectedOutput());
    }

    return outputQuant;
  }

  private static void populateOutputMap(Map<String, Integer> outputQuant, String output) {

    if(outputQuant.containsKey(output)) {

      outputQuant.put(output, outputQuant.get(output) + 1);
    } else {

      outputQuant.put(output, 1);
    }
  }

  public static Map<String, Instances> generateMapOfInstancesByFeatureValue(Instances instances, Feature feature) throws InvalidFeatureValueException {

    Map<String, Instances> quantityByFeatureValue = new HashMap<String, Instances>();

    instantiateFeaturesInstancesMap(feature, quantityByFeatureValue);

    populateMapOfInstancesByFeatureValue(instances, feature, quantityByFeatureValue);

    return quantityByFeatureValue;
  }

  public static void populateMapOfInstancesByFeatureValue(Instances instances, Feature feature, Map<String, Instances> quantityByFeatureValue) throws InvalidFeatureValueException {

    for(Instance instance : instances.getInstances()) {

      try {

        quantityByFeatureValue.get(instance.getFeatureValue(feature.getName())).addNewInstance(instance);
      } catch (NullPointerException e) {

        throw new InvalidFeatureValueException(String.format(INVALID_FEATURE, instance.getFeatureValue(feature.getName())));
      }
    }
  }

  public static void instantiateFeaturesInstancesMap(Feature feature, Map<String, Instances> map) {

    for(String featureValue : feature.getValues()) {

      map.put(featureValue, new InstancesImpl());
    }
  }
}
