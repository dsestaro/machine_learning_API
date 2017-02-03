package br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature;

import br.com.algorithms.machine.learning.math.entropy.Entropy;
import br.com.algorithms.machine.learning.math.information.gain.InformationGain;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.Instances;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.exception.feature.InvalidInstancesException;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.exception.feature.InvalidQuantityException;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.TreeUtils;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.exception.InvalidFeatureValueException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FeaturesImpl implements Features {

  public static final String INVALID_INSTANCES = "Instances cannot be empty or null.";
  public static final String INVALID_QUANTITIES = "The quantity map cannot be empty or null.";

  private List<Feature> features;

  public FeaturesImpl() {

    this.features = new ArrayList<Feature>();
  }

  public FeaturesImpl addFeature(Feature feature) {

    if(feature != null && !this.features.contains(feature)) {

      this.features.add(feature);
    }

    return this;
  }

  public List<Feature> getFeatures() {

    return this.features;
  }

  public Integer getNumberOfFeatures() {

    return this.features.size();
  }

  public Feature getBestFeature(Instances instances, Map<String, Integer> outputQuant) throws InvalidFeatureValueException {

    validateParameters(instances, outputQuant);

    Double entropy = Entropy.calculateEntropy(outputQuant, instances.getNumberOfInstances());

    Double bestInformationGain = 0.0;
    Feature bestFeature = null;

    for(Feature feature : this.features) {

      Map<String, Instances> quantityByFeatureValue = TreeUtils.generateMapOfInstancesByFeatureValue(instances, feature);

      Double featureInformationGain = InformationGain.calculateInformationGain(entropy, feature, quantityByFeatureValue, instances.getNumberOfInstances());

      if(bestInformationGain < featureInformationGain) {

        bestInformationGain = featureInformationGain;
        bestFeature = feature;
      }
    }

    return bestFeature;
  }

  private void validateParameters(Instances instances, Map<String, Integer> outputQuant) {

    if(instances == null || instances.getNumberOfInstances() == 0) {

      throw new InvalidInstancesException(INVALID_INSTANCES);
    }

    if(outputQuant == null || outputQuant.size() == 0) {

      throw new InvalidQuantityException(INVALID_QUANTITIES);
    }
  }
}
