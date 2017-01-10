package br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature;

import java.util.ArrayList;
import java.util.List;

public class FeatureImpl implements Feature {

  private String name;
  private List<String> values;

  public FeatureImpl (String name) {

    this.name = name;
    this.values = new ArrayList<String>();
  }

  public String getName() {

    return name;
  }

  public List<String> getValues() {

    return values;
  }

  public FeatureImpl addNewValue(String value) {

    this.values.add(value);

    return this;
  }

  public Integer getNumberOfValues() {

    return this.values.size();
  }
}
