package br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FeaturesTests {

  private Features features;

  @Before
  public void instantiateFeatures() {

    String name1 = "Feature1";
    String name2 = "Feature2";

    this.features = new FeaturesImpl();

    this.features.addFeature(name1);
    this.features.addFeature(name2);
  }

  @Test
  public void testFeaturesInstantiation() {

    new FeaturesImpl();
  }

  @Test
  public void testAddFeatureUsingName() {

    String name = "Feature1";

    this.features.addFeature(name);
  }

  @Test
  public void testAddFeatureUsingNameAndValues() {

    String name = "Feature1";
    List<String> values = new ArrayList<String>();

    values.add("Value1");
    values.add("Value2");

    this.features.addFeature(name, values);
  }

  @Test
  public void testAddFeatureUsingFeatureObject() {

    String name = "Feature1";

    Feature feature = new FeatureImpl(name);

    this.features.addFeature(feature);
  }

  @Test
  public void testGetFeatures() {

    assertEquals(2, this.features.getFeatures().size());
  }

  @Test
  public void testGetNumberOfFeatures() {

    assertEquals(new Integer(2), this.features.getNumberOfFeatures());
  }
}
