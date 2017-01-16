package br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature;

import java.util.List;

public interface Features {

  FeaturesImpl addFeature(Feature feature);

  List<Feature> getFeatures();

  Integer getNumberOfFeatures();
}
