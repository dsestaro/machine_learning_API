package br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature;

import br.com.algorithms.machine.learning.supervisioned.tree.id3.exception.feature.InvalidFeatureParameterException;
import br.com.algorithms.machine.learning.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FeatureImpl implements Feature {

  public static final String INVALID_NAME = "The feature name cannot be null or empty";

  private String name;
  private List<String> values;

  public FeatureImpl (String name) {

    validateParameters(name);

    this.name = name;
    this.values = new ArrayList<String>();
  }

  private void validateParameters(String name) {

    if(StringUtils.isEmpty(name)) {

      throw new InvalidFeatureParameterException(INVALID_NAME);
    }
  }

  public String getName() {

    return name;
  }

  public FeatureImpl addNewValue(String value) {

    if(!this.values.contains(value)) {

      this.values.add(value);
    }

    return this;
  }

  public List<String> getValues() {

    return values;
  }

  public Integer getNumberOfValues() {

    return this.values.size();
  }

  public boolean equals(Object obj) {

    if (obj == null) {
      return false;
    }

    if (!Feature.class.isAssignableFrom(obj.getClass())) {
      return false;
    }

    Feature feature = (Feature) obj;

    return this.name.equals(feature.getName());
  }
}
