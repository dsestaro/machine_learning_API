package br.com.algorithms.machine.learning.supervisioned.tree.utils;

import br.com.algorithms.machine.learning.exception.parameters.InvalidParameterException;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature.Feature;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature.FeatureImpl;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.InstanceImpl;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.Instances;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.InstancesImpl;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.exception.InvalidFeatureValueException;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;


public class TreeUtilsTests {

  @Test
  public void testTreeUtilsInstantiation() {

    TreeUtils utils = new TreeUtils();

    assertNotNull(utils);
  }

  @Test
  public void testCalculateOutputMap() {

    String positiveOutput = "Yes";
    String negativeOutput = "No";

    Integer expectedPositives = 5;
    Integer expectedNegatives = 3;

    Instances instances = new InstancesImpl();

    instances.addNewInstance(new InstanceImpl().setExpectedOutput(positiveOutput));
    instances.addNewInstance(new InstanceImpl().setExpectedOutput(positiveOutput));
    instances.addNewInstance(new InstanceImpl().setExpectedOutput(positiveOutput));
    instances.addNewInstance(new InstanceImpl().setExpectedOutput(positiveOutput));
    instances.addNewInstance(new InstanceImpl().setExpectedOutput(positiveOutput));

    instances.addNewInstance(new InstanceImpl().setExpectedOutput(negativeOutput));
    instances.addNewInstance(new InstanceImpl().setExpectedOutput(negativeOutput));
    instances.addNewInstance(new InstanceImpl().setExpectedOutput(negativeOutput));

    Map<String, Integer> quantity = TreeUtils.calculateQuantityOutput(instances);

    assertEquals(expectedNegatives, quantity.get(negativeOutput));
    assertEquals(expectedPositives, quantity.get(positiveOutput));
  }

  @Test
  public void testCalculateOutputMap_InstanceList() {

    String positiveOutput = "Yes";
    String negativeOutput = "No";

    Integer expectedPositives = 5;
    Integer expectedNegatives = 3;

    Instances instances = new InstancesImpl();

    instances.addNewInstance(new InstanceImpl().setExpectedOutput(positiveOutput));
    instances.addNewInstance(new InstanceImpl().setExpectedOutput(positiveOutput));
    instances.addNewInstance(new InstanceImpl().setExpectedOutput(positiveOutput));
    instances.addNewInstance(new InstanceImpl().setExpectedOutput(positiveOutput));
    instances.addNewInstance(new InstanceImpl().setExpectedOutput(positiveOutput));

    instances.addNewInstance(new InstanceImpl().setExpectedOutput(negativeOutput));
    instances.addNewInstance(new InstanceImpl().setExpectedOutput(negativeOutput));
    instances.addNewInstance(new InstanceImpl().setExpectedOutput(negativeOutput));

    Map<String, Integer> quantity = TreeUtils.calculateQuantityOutput(instances.getInstances());

    assertEquals(expectedNegatives, quantity.get(negativeOutput));
    assertEquals(expectedPositives, quantity.get(positiveOutput));
  }

  @Test
  public void testGenerateMapOfInstancesByFeatureValue() throws InvalidFeatureValueException {

    String featureName = "Wind";
    String weakValue = "Weak";
    String strongValue = "Strong";

    Integer expectedWeak = 1;
    Integer expectedStrong = 2;

    Feature feature = new FeatureImpl(featureName);

    feature.addNewValue(weakValue);
    feature.addNewValue(strongValue);

    Instances instances = new InstancesImpl();

    instances.addNewInstance(new InstanceImpl().setNewFeature(featureName, weakValue));
    instances.addNewInstance(new InstanceImpl().setNewFeature(featureName, strongValue));
    instances.addNewInstance(new InstanceImpl().setNewFeature(featureName, strongValue));

    Map<String, Instances> quantity = TreeUtils.generateMapOfInstancesByFeatureValue(instances, feature);

    assertEquals(expectedWeak, quantity.get(weakValue).getNumberOfInstances());
    assertEquals(expectedStrong, quantity.get(strongValue).getNumberOfInstances());
  }

  @Test
  public void testCalculateQuantityByFeatureValue_InvalidValue() throws InvalidFeatureValueException {

    String featureName = "Wind";
    String weakValue = "Weak";
    String strongValue = "Strong";
    String invalidValue = "Invalid";

    String expectedMessage = String.format(TreeUtils.INVALID_FEATURE, invalidValue);

    Feature feature = new FeatureImpl(featureName);

    feature.addNewValue(weakValue);
    feature.addNewValue(strongValue);

    Instances instances = new InstancesImpl();

    instances.addNewInstance(new InstanceImpl().setNewFeature(featureName, weakValue));
    instances.addNewInstance(new InstanceImpl().setNewFeature(featureName, strongValue));
    instances.addNewInstance(new InstanceImpl().setNewFeature(featureName, strongValue));
    instances.addNewInstance(new InstanceImpl().setNewFeature(featureName, invalidValue));

    try {

      TreeUtils.generateMapOfInstancesByFeatureValue(instances, feature);

      fail("InvalidFeatureValueException should be thrown.");
    } catch (InvalidFeatureValueException e) {

      assertEquals(expectedMessage, e.getMessage());
    }
  }

  @Test
  public void testCalculateQuantityByFeatureValue_EmptyInstances() throws InvalidFeatureValueException {

    String featureName = "Wind";
    String weakValue = "Weak";
    String strongValue = "Strong";

    Feature feature = new FeatureImpl(featureName);

    feature.addNewValue(weakValue);
    feature.addNewValue(strongValue);

    Instances instances = new InstancesImpl();

    Map<String, Instances> quantity = TreeUtils.generateMapOfInstancesByFeatureValue(instances, feature);

    assertNotNull(quantity);
  }

  @Test
  public void testCalculateQuantityByFeatureValue_NullInstances() throws InvalidFeatureValueException {

    String featureName = "Wind";
    String weakValue = "Weak";
    String strongValue = "Strong";

    Feature feature = new FeatureImpl(featureName);

    feature.addNewValue(weakValue);
    feature.addNewValue(strongValue);

    Instances instances = null;

    try {

      TreeUtils.generateMapOfInstancesByFeatureValue(instances, feature);

      fail("InvalidParameterException should be thrown.");
    } catch (InvalidParameterException e) {

      assertEquals(TreeUtils.INVALID_INSTANCES, e.getMessage());
    }
  }

  @Test
  public void testCalculateQuantityByFeatureValue_NullFeatures() throws InvalidFeatureValueException {

    String featureName = "Wind";
    String weakValue = "Weak";
    String strongValue = "Strong";

    Feature feature = null;

    Instances instances = new InstancesImpl();

    instances.addNewInstance(new InstanceImpl().setNewFeature(featureName, weakValue));
    instances.addNewInstance(new InstanceImpl().setNewFeature(featureName, strongValue));
    instances.addNewInstance(new InstanceImpl().setNewFeature(featureName, strongValue));

    try {

      TreeUtils.generateMapOfInstancesByFeatureValue(instances, feature);

      fail("InvalidParameterException should be thrown.");
    } catch (InvalidParameterException e) {

      assertEquals(TreeUtils.INVALID_NULL_FEATURE, e.getMessage());
    }
  }

  @Test
  public void testPopulateMapOfInstancesByFeatureValue() throws InvalidFeatureValueException {

    String featureName = "Wind";
    String weakValue = "Weak";
    String strongValue = "Strong";

    Integer weakQuantity = 1;
    Integer strongQuantity = 2;

    Map<String, Instances> map = new HashMap<String, Instances>();

    Feature feature = new FeatureImpl(featureName);

    feature.addNewValue(weakValue);
    feature.addNewValue(strongValue);

    Instances instances = new InstancesImpl();

    instances.addNewInstance(new InstanceImpl().setNewFeature(featureName, weakValue));
    instances.addNewInstance(new InstanceImpl().setNewFeature(featureName, strongValue));
    instances.addNewInstance(new InstanceImpl().setNewFeature(featureName, strongValue));

    TreeUtils.instantiateFeaturesInstancesMap(feature, map);

    TreeUtils.populateMapOfInstancesByFeatureValue(instances, feature, map);

    assertEquals(weakQuantity, map.get(weakValue).getNumberOfInstances());
    assertEquals(strongQuantity, map.get(strongValue).getNumberOfInstances());
  }

  @Test
  public void testPopulateMapOfInstancesByFeatureValue_InvalidFeatures() throws InvalidFeatureValueException {

    String featureName = "Wind";
    String weakValue = "Weak";
    String strongValue = "Strong";
    String invalidValue = "Invalid";

    String expectedMessage = String.format(TreeUtils.INVALID_FEATURE, invalidValue);

    Map<String, Instances> map = new HashMap<String, Instances>();

    Feature feature = new FeatureImpl(featureName);

    feature.addNewValue(weakValue);
    feature.addNewValue(strongValue);

    Instances instances = new InstancesImpl();

    instances.addNewInstance(new InstanceImpl().setNewFeature(featureName, weakValue));
    instances.addNewInstance(new InstanceImpl().setNewFeature(featureName, strongValue));
    instances.addNewInstance(new InstanceImpl().setNewFeature(featureName, strongValue));
    instances.addNewInstance(new InstanceImpl().setNewFeature(featureName, invalidValue));

    TreeUtils.instantiateFeaturesInstancesMap(feature, map);

    try {

      TreeUtils.populateMapOfInstancesByFeatureValue(instances, feature, map);

      fail("InvalidFeatureValueException should be thrown.");
    } catch (InvalidFeatureValueException e) {

      assertEquals(expectedMessage, e.getMessage());
    }
  }

  @Test
  public void testPopulateMapOfInstancesByFeatureValue_NullInstances() throws InvalidFeatureValueException {

    String featureName = "Wind";
    String weakValue = "Weak";
    String strongValue = "Strong";

    Map<String, Instances> map = new HashMap<String, Instances>();

    Feature feature = new FeatureImpl(featureName);

    feature.addNewValue(weakValue);
    feature.addNewValue(strongValue);

    Instances instances = null;

    TreeUtils.instantiateFeaturesInstancesMap(feature, map);

    try {

      TreeUtils.populateMapOfInstancesByFeatureValue(instances, feature, map);

      fail("InvalidParameterException should be thrown.");
    } catch (InvalidParameterException e) {

      assertEquals(TreeUtils.INVALID_INSTANCES, e.getMessage());
    }
  }

  @Test
  public void testPopulateMapOfInstancesByFeatureValue_NullMap() throws InvalidFeatureValueException {

    String featureName = "Wind";

    Map<String, Instances> map = null;

    Feature feature = new FeatureImpl(featureName);
    Instances instances = new InstancesImpl();

    try {

      TreeUtils.populateMapOfInstancesByFeatureValue(instances, feature, map);

      fail("InvalidParameterException should be thrown.");
    } catch (InvalidParameterException e) {

      assertEquals(TreeUtils.INVALID_MAP, e.getMessage());
    }
  }

  @Test
  public void testPopulateMapOfInstancesByFeatureValue_NullFeature() throws InvalidFeatureValueException {

    Map<String, Instances> map = new HashMap<String, Instances>();

    Feature feature = null;

    Instances instances = new InstancesImpl();

    try {

      TreeUtils.populateMapOfInstancesByFeatureValue(instances, feature, map);

      fail("InvalidParameterException should be thrown.");
    } catch (InvalidParameterException e) {

      assertEquals(TreeUtils.INVALID_NULL_FEATURE, e.getMessage());
    }
  }

  @Test
  public void testInstantiateFeaturesInstancesMap() {

    String featureName = "Wind";
    String weakValue = "Weak";
    String strongValue = "Strong";

    Map<String, Instances> map = new HashMap<String, Instances>();

    Feature feature = new FeatureImpl(featureName);

    feature.addNewValue(weakValue);
    feature.addNewValue(strongValue);

    TreeUtils.instantiateFeaturesInstancesMap(feature, map);

    for(String value : feature.getValues()) {

      assertNotNull(map.get(value));
    }
  }

  @Test
  public void testInstantiateFeaturesInstancesMap_NullFeature() {

    Map<String, Instances> map = new HashMap<String, Instances>();

    Feature feature = null;

    try {

      TreeUtils.instantiateFeaturesInstancesMap(feature, map);

      fail("InvalidParameterException should be thrown.");
    } catch (InvalidParameterException e) {

      assertEquals(TreeUtils.INVALID_NULL_FEATURE, e.getMessage());
    }
  }

  @Test
  public void testInstantiateFeaturesInstancesMap_NullMap() {

    String featureName = "Wind";
    String weakValue = "Weak";
    String strongValue = "Strong";

    Map<String, Instances> map = null;

    Feature feature = new FeatureImpl(featureName);

    feature.addNewValue(weakValue);
    feature.addNewValue(strongValue);

    try {

      TreeUtils.instantiateFeaturesInstancesMap(feature, map);

      fail("InvalidParameterException should be thrown.");
    } catch (InvalidParameterException e) {

      assertEquals(TreeUtils.INVALID_MAP, e.getMessage());
    }
  }
}
