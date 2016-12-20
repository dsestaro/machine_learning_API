package br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.data.feature;

import java.util.List;

public interface Features {

  FeaturesImpl addFeature(Feature feature);

  FeaturesImpl addFeature(String name);

  FeaturesImpl addFeature(String name, List<String> values);

  List<Feature> getFeatures();

  Integer getNumberOfFeatures();
}
