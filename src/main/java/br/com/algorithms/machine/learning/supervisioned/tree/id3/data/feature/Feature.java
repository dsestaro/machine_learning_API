package br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature;

import java.util.List;

public interface Feature {

  String getName();

  List<String> getValues();

  FeatureImpl addNewValue(String value);

  Integer getNumberOfValues();
}
