package br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.data.feature;

import java.util.List;

public interface Feature {

  String getName();

  List<String> getValues();

  FeatureImpl addNewValue(String value);

  Integer getNumberOfValues();
}
