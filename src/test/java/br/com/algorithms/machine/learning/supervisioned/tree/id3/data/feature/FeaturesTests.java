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

    this.features = new FeaturesImpl();

    this.features.addFeature(new FeatureImpl("Feature1"));
    this.features.addFeature(new FeatureImpl("Feature2"));
  }

  @Test
  public void testFeaturesInstantiation() {

    new FeaturesImpl();
  }

  @Test
  public void testAddFeatureUsingFeatureObject() {

    Feature feature = new FeatureImpl("Feature1");

    this.features.addFeature(feature);
  }

  @Test
  public void testAddFeatureUsingFTwoeatureObjectsWithSameName() {

    Feature feature = new FeatureImpl("Feature1");

    this.features.addFeature(feature);

    assertEquals(new Integer(2), this.features.getNumberOfFeatures());
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
