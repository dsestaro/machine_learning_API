package br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FeatureTests {

  private Feature feature;

  @Before
  public void instantiateFeature() {

    this.feature = new FeatureImpl("Feature");
    this.feature.addNewValue("Value1");
    this.feature.addNewValue("Value2");
  }

  @Test
  public void testFeatureInstantiation() {

    new FeatureImpl("Test");
  }

  @Test
  public void testDataInsertion() {

    this.feature.addNewValue("Value1");
  }

  @Test
  public void testDataInsertionWithTheSameValue() {

    this.feature.addNewValue("Value1");
    this.feature.addNewValue("Value1");

    assertEquals(2, this.feature.getValues().size());
  }

  @Test
  public void testDataRetrieve() {

    assertEquals("Feature", this.feature.getName());
    assertEquals("Value1", this.feature.getValues().get(0));
    assertEquals("Value2", this.feature.getValues().get(1));
  }

  @Test
  public void testValuesSize() {

    assertEquals(new Integer(2), this.feature.getNumberOfValues());
  }
}
