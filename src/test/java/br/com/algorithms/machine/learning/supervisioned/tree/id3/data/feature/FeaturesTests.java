package br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature;

import br.com.algorithms.machine.learning.math.entropy.Entropy;
import br.com.algorithms.machine.learning.math.entropy.exception.InvalidEntropyParametersException;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.Instance;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.InstanceImpl;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.Instances;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.InstancesImpl;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.exception.feature.InvalidInstancesException;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.exception.feature.InvalidQuantityException;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.TreeUtils;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.exception.InvalidFeatureValueException;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

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

  @Test
  public void testGetBestFeature() {

    int outputTrueQuantity = 1;
    int outputFalseQuantity = 2;

    String firstFeatureName = "Feature1";
    String secondFeatureName = "Feature2";
    String firstValue = "Value1";
    String secondValue = "Value2";
    String outputFalse = "No";
    String outputTrue = "Yes";

    Instance firstInstance = new InstanceImpl();
    firstInstance.setExpectedOutput(outputTrue);
    firstInstance.setNewFeature(firstFeatureName, firstValue);
    firstInstance.setNewFeature(secondFeatureName, firstValue);

    Instance secondInstance = new InstanceImpl();
    secondInstance.setExpectedOutput(outputFalse);
    secondInstance.setNewFeature(firstFeatureName, firstValue);
    secondInstance.setNewFeature(secondFeatureName, firstValue);

    Instance thirdInstance = new InstanceImpl();
    thirdInstance.setExpectedOutput(outputFalse);
    thirdInstance.setNewFeature(firstFeatureName, secondValue);
    thirdInstance.setNewFeature(secondFeatureName, firstValue);

    Feature firstFeature = new FeatureImpl(firstFeatureName);
    firstFeature.addNewValue(firstValue);
    firstFeature.addNewValue(secondValue);

    Feature secondFeature = new FeatureImpl(secondFeatureName);
    secondFeature.addNewValue(firstValue);
    secondFeature.addNewValue(secondValue);

    Features features = new FeaturesImpl();
    Instances instances = new InstancesImpl();

    Map<String, Integer> quantity = new HashMap<String, Integer>();

    quantity.put(outputTrue, outputTrueQuantity);
    quantity.put(outputFalse, outputFalseQuantity);

    instances.addNewInstance(firstInstance);
    instances.addNewInstance(secondInstance);
    instances.addNewInstance(thirdInstance);

    features.addFeature(firstFeature);
    features.addFeature(secondFeature);

    try {

      Feature feature = features.getBestFeature(instances, quantity);

      assertEquals(firstFeatureName, feature.getName());
    } catch (InvalidFeatureValueException e) {

      fail("InvalidFeatureValueException should not be thrown.");
    }
  }



  @Test
  public void testGetBestFeature_InvalidValueOnAInstance() throws InvalidFeatureValueException {

    int outputTrueQuantity = 1;

    String firstFeatureName = "Feature1";
    String validFeatureValue = "value";
    String invalidFeatureValue = "Invalid";
    String outputTrue = "Yes";

    String expectedMessage = String.format(TreeUtils.INVALID_FEATURE, invalidFeatureValue);

    Instance invalidInstance = new InstanceImpl();
    invalidInstance.setNewFeature(firstFeatureName, invalidFeatureValue);

    Feature feature = new FeatureImpl(firstFeatureName);
    feature.addNewValue(validFeatureValue);

    Features features = new FeaturesImpl();
    Instances instances = new InstancesImpl();

    Map<String, Integer> quantity = new HashMap<String, Integer>();

    quantity.put(outputTrue, outputTrueQuantity);

    instances.addNewInstance(invalidInstance);

    features.addFeature(feature);

    try {

      features.getBestFeature(instances, quantity);

      fail("InvalidFeatureValueException should be thrown.");
    } catch (InvalidFeatureValueException e) {

      assertEquals(expectedMessage, e.getMessage());
    }
  }

  @Test
  public void testGetBestFeature_NullMap() throws InvalidFeatureValueException {

    Features features = new FeaturesImpl();
    Instances instances = new InstancesImpl();

    Instance instance = new InstanceImpl();

    Map<String, Integer> quantity = null;

    instances.addNewInstance(instance);

    try {

      features.getBestFeature(instances, quantity);

      fail("InvalidQuantityException should be thrown.");
    } catch (InvalidQuantityException e) {

      assertEquals(FeaturesImpl.INVALID_QUANTITIES, e.getMessage());
    }
  }

  @Test
  public void testGetBestFeature_EmptyQuantityMap() throws InvalidFeatureValueException {

    Features features = new FeaturesImpl();
    Instances instances = new InstancesImpl();

    Instance instance = new InstanceImpl();

    Map<String, Integer> quantity = new HashMap<String, Integer>();

    instances.addNewInstance(instance);

    try {

      features.getBestFeature(instances, quantity);

      fail("InvalidQuantityException should be thrown.");
    } catch (InvalidQuantityException e) {

      assertEquals(FeaturesImpl.INVALID_QUANTITIES, e.getMessage());
    }
  }

  @Test
  public void testGetBestFeature_NullInstances() throws InvalidFeatureValueException {

    Features features = new FeaturesImpl();
    Instances instances = null;

    Map<String, Integer> quantity = null;

    try {

      features.getBestFeature(instances, quantity);

      fail("InvalidInstancesException should be thrown.");
    } catch (InvalidInstancesException e) {

      assertEquals(FeaturesImpl.INVALID_INSTANCES, e.getMessage());
    }
  }

  @Test
  public void testGetBestFeature_EmptyInstances() throws InvalidFeatureValueException {

    Features features = new FeaturesImpl();
    Instances instances = new InstancesImpl();

    Map<String, Integer> quantity = null;

    try {

      features.getBestFeature(instances, quantity);

      fail("InvalidInstancesException should be thrown.");
    } catch (InvalidInstancesException e) {

      assertEquals(FeaturesImpl.INVALID_INSTANCES, e.getMessage());
    }
  }
}
