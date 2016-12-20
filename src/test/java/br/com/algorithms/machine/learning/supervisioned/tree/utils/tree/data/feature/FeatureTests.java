package br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.data.feature;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FeatureTests {

  private Feature feature;

  @Before
  public void instantiateFeature() {

    String name = "Feature";
    String value1 = "Value1";
    String value2 = "Value2";

    this.feature = new FeatureImpl(name);
    this.feature.addNewValue(value1);
    this.feature.addNewValue(value2);
  }

  @Test
  public void testFeatureInstantiation() {

    String name = "Test";

    new FeatureImpl(name);
  }

  @Test
  public void testDataInsertion() {

    String value = "Value1";

    this.feature.addNewValue(value);
  }

  @Test
  public void testDataRetrieve() {

    String value1 = "Value1";
    String value2 = "Value2";
    String name = "Feature";

    assertEquals(name, this.feature.getName());
    assertEquals(value1, this.feature.getValues().get(0));
    assertEquals(value2, this.feature.getValues().get(1));
  }

  @Test
  public void testValuesSize() {

    Integer quantity = 2;

    assertEquals(quantity, this.feature.getNumberOfValues());
  }
}
