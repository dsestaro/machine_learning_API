package br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature;

import br.com.algorithms.machine.learning.supervisioned.tree.id3.exception.InvalidFeatureInformationException;
import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FeatureImpl implements Feature {

  private static final String INVALID_NAME = "The feature name cannot be null or empty";

  private String name;
  private List<String> values;

  public FeatureImpl (String name) {

    validateParameters(name);

    this.name = name;
    this.values = new ArrayList<String>();
  }

  private void validateParameters(String name) {

    if(name == null || name.length() == 0) {

      throw new InvalidFeatureInformationException(INVALID_NAME);
    }
  }

  public String getName() {

    return name;
  }

  public List<String> getValues() {

    return values;
  }

  public FeatureImpl addNewValue(String value) {

    if(!this.values.contains(value)) {

      this.values.add(value);
    }

    return this;
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
