package br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature;

import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.Instances;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.exception.InvalidFeatureValueException;

import java.util.List;
import java.util.Map;

public interface Features {

  FeaturesImpl addFeature(Feature feature);

  List<Feature> getFeatures();

  Integer getNumberOfFeatures();

  Feature getBestFeature(Instances instances, Map<String, Integer> outputQuant) throws InvalidFeatureValueException;
}
