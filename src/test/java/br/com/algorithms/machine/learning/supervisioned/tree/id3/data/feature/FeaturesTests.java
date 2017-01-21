package br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FeaturesTests {

  @Test
  public void testFeaturesInstantiation() {

    Features features = new FeaturesImpl();

    assertNotNull(features);
  }

  @Test
  public void testAddGetFeature() {

    String featureName = "testAddFeature";
    int initialQuantity = 0;
    int finalQuantity = 1;

    Feature feature = new FeatureImpl(featureName);
    Features features = new FeaturesImpl();

    assertEquals(initialQuantity, features.getFeatures().size());

    features.addFeature(feature);

    assertEquals(finalQuantity, features.getFeatures().size());
  }

  @Test
  public void testAddFeature_TwoFeaturesWithSameName() {

    String featureName = "testAddFeature";
    int initialQuantity = 0;
    int finalQuantity = 1;

    Feature feature = new FeatureImpl(featureName);
    Feature featureCopy = new FeatureImpl(featureName);
    Features features = new FeaturesImpl();

    assertEquals(initialQuantity, features.getFeatures().size());

    features.addFeature(feature);
    features.addFeature(featureCopy);

    assertEquals(finalQuantity, features.getFeatures().size());
  }

  @Test
  public void testAddFeature_NullFeature() {

    int initialQuantity = 0;
    int finalQuantity = 0;

    Feature feature = null;
    Features features = new FeaturesImpl();

    assertEquals(initialQuantity, features.getFeatures().size());

    features.addFeature(feature);

    assertEquals(finalQuantity, features.getFeatures().size());
  }

  @Test
  public void testGetNumberOfFeatures() {

    String firstFeatureName = "Feature1";
    String secondFeatureName = "Feature2";

    Integer finalQuantity = 2;

    Feature firstFeature = new FeatureImpl(firstFeatureName);
    Feature secondFeature = new FeatureImpl(secondFeatureName);
    Features features = new FeaturesImpl();

    features.addFeature(firstFeature);
    features.addFeature(secondFeature);

    assertEquals(finalQuantity, features.getNumberOfFeatures());
  }
}
