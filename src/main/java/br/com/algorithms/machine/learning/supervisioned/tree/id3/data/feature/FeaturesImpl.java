package br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature;

import java.util.ArrayList;
import java.util.List;

public class FeaturesImpl implements Features {

  private List<Feature> features;

  public FeaturesImpl() {

    this.features = new ArrayList<Feature>();
  }

  public FeaturesImpl addFeature(String name) {

    this.features.add(createNewFeature(name));

    return this;
  }

  public FeaturesImpl addFeature(String name, List<String> values) {

    Feature feature = createNewFeature(name);

    for(String value : values) {

      feature.addNewValue(value);
    }

    this.features.add(feature);

    return this;
  }

  public FeaturesImpl addFeature(Feature feature) {

    this.features.add(feature);

    return this;
  }

  public List<Feature> getFeatures() {

    return this.features;
  }

  public Integer getNumberOfFeatures() {

    return this.features.size();
  }

  private FeatureImpl createNewFeature(String name) {

    return new FeatureImpl(name);
  }
}
