package br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance;

import br.com.algorithms.machine.learning.supervisioned.tree.id3.exception.InvalidInstanceInformationException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class InstanceTests {

  @Test
  public void testFeatureInstantiation() {

    Instance instance = new InstanceImpl();

    assertNotNull(instance);
  }

  @Test
  public void testSetGetOutput() {

    Instance instance = new InstanceImpl();
    String expectedOutput = "Output";

    instance.setExpectedOutput(expectedOutput);

    assertEquals(expectedOutput, instance.getExpectedOutput());
  }

  @Test
  public void testSetGetFeatureValue() {

    Instance instance = new InstanceImpl();
    String featureName = "NewFeature";
    String featureValue = "FeatureValue";

    instance.setNewFeature(featureName, featureValue);

    assertEquals(featureValue, instance.getFeatureValue(featureName));
  }

  @Test
  public void testSetFeatureValue_NullFeatureName() {

    Instance instance = new InstanceImpl();
    String featureName = null;
    String featureValue = "FeatureValue";

    try {

      instance.setNewFeature(featureName, featureValue);

      fail("InvalidInstanceInformationException should be thrown.");
    } catch (InvalidInstanceInformationException e) {

      assertEquals(InstanceImpl.INVALID_FEATURE_NAME, e.getMessage());
    }
  }

  @Test
  public void testSetFeatureValue_EmptyFeatureName() {

    Instance instance = new InstanceImpl();
    String featureName = "";
    String featureValue = "FeatureValue";

    try {

      instance.setNewFeature(featureName, featureValue);

      fail("InvalidInstanceInformationException should be thrown.");
    } catch (InvalidInstanceInformationException e) {

      assertEquals(InstanceImpl.INVALID_FEATURE_NAME, e.getMessage());
    }
  }

  @Test
  public void testSetGetFeatureValue_InsertingSameValueTwiceWithSameFeature() {

    Instance instance = new InstanceImpl();
    String featureName = "NewFeature";
    String featureValue = "FeatureValue";

    instance.setNewFeature(featureName, featureValue);
    instance.setNewFeature(featureName, featureValue);

    assertEquals(featureValue, instance.getFeatureValue(featureName));
  }

  @Test
  public void testSetGetFeatureValue_InsertingDifferentValuesWithSameFeature() {

    Instance instance = new InstanceImpl();
    String featureName = "NewFeature";
    String featureFirstValue = "FeatureValue";
    String featureSecondValue = "Invalid";

    try {

      instance.setNewFeature(featureName, featureFirstValue);
      instance.setNewFeature(featureName, featureSecondValue);

      fail("InvalidInstanceInformationException should be thrown.");
    } catch (InvalidInstanceInformationException e) {

      assertEquals(InstanceImpl.INVALID_FEATURE, e.getMessage());
    }
  }
}
