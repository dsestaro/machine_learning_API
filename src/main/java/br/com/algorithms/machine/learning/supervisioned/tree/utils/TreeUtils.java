package br.com.algorithms.machine.learning.supervisioned.tree.utils;

import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature.Feature;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.Instance;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.Instances;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.InstancesImpl;

import java.util.HashMap;
import java.util.Map;

public class TreeUtils {

  public static Map<String, Integer> calculateQuantityOutput(Instances instances) {

    Map<String, Integer> outputQuant = new HashMap<String, Integer>();

    for(Instance instance : instances.getInstances()) {

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

  public static Map<String, Instances> calculateQuantityByFeatureValue(Instances instances, Feature feature) {

    Map<String, Instances> quantityByFeatureValue = new HashMap<String, Instances>();

    for(String featureValue : feature.getValues()) {

      quantityByFeatureValue.put(featureValue, new InstancesImpl());
    }

    for(Instance instance : instances.getInstances()) {

      quantityByFeatureValue.get(instance.getFeatureValue(feature.getName())).addNewInstance(instance);
    }

    return quantityByFeatureValue;
  }
}
