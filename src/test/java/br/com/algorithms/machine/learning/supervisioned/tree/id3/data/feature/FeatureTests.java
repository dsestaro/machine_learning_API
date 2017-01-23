package br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature;

import br.com.algorithms.machine.learning.supervisioned.tree.id3.exception.InvalidFeatureParameterException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class FeatureTests {

  @Test
  public void testFeatureInstantiation() {

    String featureName = "Test";

    Feature feature = new FeatureImpl(featureName);

    assertNotNull(feature);
    assertEquals(featureName, feature.getName());
  }

  @Test
  public void testFeatureInstantiation_NullParameters() {

    String featureName = null;

    try {

      Feature feature = new FeatureImpl(featureName);

      fail("InvalidFeatureParameterException should be thrown.");
    } catch(InvalidFeatureParameterException e) {

      assertEquals(InvalidFeatureParameterException.INVALID_NAME, e.getMessage());
    }
  }

  @Test
  public void testFeatureInstantiation_EmptyParameter() {

    String featureName = "";

    try {

      Feature feature = new FeatureImpl(featureName);

      fail("InvalidFeatureParameterException should be thrown.");
    } catch(InvalidFeatureParameterException e) {

      assertEquals(InvalidFeatureParameterException.INVALID_NAME, e.getMessage());
    }
  }

  @Test
  public void testSetGetFeatureValue() {

    String featureName = "Test";
    String featureValue = "Value";
    int expectedQuantity = 1;

    Feature feature = new FeatureImpl(featureName);

    feature.addNewValue(featureValue);

    assertEquals(expectedQuantity, feature.getValues().size());
    assertEquals(featureValue, feature.getValues().get(0));
  }

  @Test
  public void testSetGetFeatureValue_SameValue() {

    String featureName = "Test";
    String featureValue = "Value";
    int expectedQuantity = 1;

    Feature feature = new FeatureImpl(featureName);

    feature.addNewValue(featureValue);
    feature.addNewValue(featureValue);

    assertEquals(expectedQuantity, feature.getValues().size());
    assertEquals(featureValue, feature.getValues().get(0));
  }

  @Test
  public void testValuesSize() {

    String featureName = "Test";
    String firstFeatureValue = "Value1";
    String secondFeatureValue = "Value2";
    Integer expectedQuantity = 2;

    Feature feature = new FeatureImpl(featureName);

    feature.addNewValue(firstFeatureValue);
    feature.addNewValue(secondFeatureValue);

    assertEquals(expectedQuantity, feature.getNumberOfValues());
  }

  @Test
  public void testEqualMethod_EqualObjects() {

    String featureName = "Test";

    Feature firstFeature = new FeatureImpl(featureName);
    Feature secondFeature = new FeatureImpl(featureName);

    assertTrue(firstFeature.equals(secondFeature));
  }

  @Test
  public void testEqualMethod_NotEqualObjects() {

    String firstFeatureName = "Test1";
    String secondFeatureName = "Test2";

    Feature firstFeature = new FeatureImpl(firstFeatureName);
    Feature secondFeature = new FeatureImpl(secondFeatureName);

    assertFalse(firstFeature.equals(secondFeature));
  }
}
