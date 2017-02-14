package br.com.algorithms.machine.learning.supervisioned.tree.id3;

import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature.Feature;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature.Features;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature.FeaturesImpl;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.Instance;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.InstanceImpl;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.Instances;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.InstancesImpl;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.exception.tree.InvalidTreeParametersException;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.node.Node;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.node.NodeType;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature.FeatureImpl;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.TreeUtils;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.exception.InvalidFeatureValueException;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class TreeTests {

  @Test
  public void testTreeInstantiatoin() {

    Tree tree = new TreeImpl();

    assertNotNull(tree);
  }

  @Test
  public void testBuildTree_NullFeatures() throws InvalidFeatureValueException {

    Features features = null;
    Instances instances = null;

    Tree tree = new TreeImpl();

    try {

      tree.buildDecisionTree(features, instances);

      fail("InvalidTreeParametersException should be thrown.");
    } catch (InvalidTreeParametersException e) {

      assertEquals(TreeImpl.INVALID_FEATURES, e.getMessage());
    }
  }

  @Test
  public void testBuildTree_EmptyFeatures() throws InvalidFeatureValueException {


    Features features = new FeaturesImpl();
    Instances instances = null;

    Tree tree = new TreeImpl();

    try {

      tree.buildDecisionTree(features, instances);

      fail("InvalidTreeParametersException should be thrown.");
    } catch (InvalidTreeParametersException e) {

      assertEquals(TreeImpl.INVALID_FEATURES, e.getMessage());
    }
  }

  @Test
  public void testBuildTree_NullInstances() throws InvalidFeatureValueException {

    Features features = new FeaturesImpl();
    Feature feature = new FeatureImpl("Outlook");
    Instances instances = null;

    features.addFeature(feature);

    Tree tree = new TreeImpl();

    try {

      tree.buildDecisionTree(features, instances);

      fail("InvalidTreeParametersException should be thrown.");
    } catch (InvalidTreeParametersException e) {

      assertEquals(TreeImpl.INVALID_INSTANCES, e.getMessage());
    }
  }

  @Test
  public void testBuildTree_EmptyInstances() throws InvalidFeatureValueException {

    Features features = new FeaturesImpl();
    Feature feature = new FeatureImpl("Outlook");
    Instances instances = new InstancesImpl();

    features.addFeature(feature);

    Tree tree = new TreeImpl();

    try {

      tree.buildDecisionTree(features, instances);

      fail("InvalidTreeParametersException should be thrown.");
    } catch (InvalidTreeParametersException e) {

      assertEquals(TreeImpl.INVALID_INSTANCES, e.getMessage());
    }
  }

  @Test
  public void testBuildDecisionTree_OnlyOneOutputSent() throws InvalidFeatureValueException {

    String output = "Yes";
    String featureName = "Outlook";

    NodeType expectedNodeType = NodeType.OUTPUT_LEAF_NODE;

    Tree tree = new TreeImpl();

    Instances instances = new InstancesImpl();

    Feature feature = new FeatureImpl(featureName);
    Features features = new FeaturesImpl();

    Instance instance = new InstanceImpl();

    instance.setExpectedOutput(output);
    instances.addNewInstance(instance);

    features.addFeature(feature);

    assertEquals(expectedNodeType, tree.buildDecisionTree(features, instances).getNodeType());
  }

  @Test
  public void testGetRemaingFeatures() {

    Integer expectedQuantity = 3;

    String featureOutlookName = "Outlook";
    String featureTemperatureName = "Temperature";
    String featureHumidityName = "Humidity";
    String featureWindName = "Wind";

    Features features = new FeaturesImpl();
    Features featuresFiltered = null;

    TreeImpl tree = new TreeImpl();

    Feature featureOutlook = new FeatureImpl(featureOutlookName);
    Feature featureTemperature = new FeatureImpl(featureTemperatureName);
    Feature featureHumidity = new FeatureImpl(featureHumidityName);
    Feature featureWind = new FeatureImpl(featureWindName);

    features.addFeature(featureOutlook);
    features.addFeature(featureTemperature);
    features.addFeature(featureHumidity);
    features.addFeature(featureWind);

    featuresFiltered = tree.getRemaingFeatures(features, featureOutlook);

    assertEquals(expectedQuantity, featuresFiltered.getNumberOfFeatures());

    for(Feature filteredFeature : featuresFiltered.getFeatures()) {

      assertNotEquals(featureOutlook.getName(), filteredFeature.getName());
    }
  }

  @Test
  public void testBuildFeatureNode() {

    String featureName = "Wind";
    String featureFirstValue = "Weak";
    String featureSecondValue = "Strong";

    NodeType expectedNodeType = NodeType.FEATURE_NODE;

    TreeImpl tree = new TreeImpl();

    Feature feature = new FeatureImpl(featureName);

    feature.addNewValue(featureFirstValue);
    feature.addNewValue(featureSecondValue);


    Node node = tree.buildFeatureNode(feature);

    assertEquals(expectedNodeType, node.getNodeType());
    assertEquals(featureName, node.getFeature().getName());
  }

  @Test
  public void testPopulateExamples() throws InvalidFeatureValueException {

    Integer expectedNumberOfFeaturesWithFirstValue = 8;
    Integer expectedNumberOfFeaturesWithSecondValue = 6;

    String featureName = "Wind";
    String featureFirstValue = "Weak";
    String featureSecondValue = "Strong";

    TreeImpl tree = new TreeImpl();
    Instances instances = new InstancesImpl();

    Feature bestFeature = new FeatureImpl(featureName);

    for(int i = 0; i < expectedNumberOfFeaturesWithFirstValue; i++) {

      Instance instance = new InstanceImpl();
      instance.setNewFeature(featureName, featureFirstValue);
      instances.addNewInstance(instance);
    }

    for(int i = 0; i < expectedNumberOfFeaturesWithSecondValue; i++) {

      Instance instance = new InstanceImpl();
      instance.setNewFeature(featureName, featureSecondValue);
      instances.addNewInstance(instance);
    }

    bestFeature.addNewValue(featureFirstValue);
    bestFeature.addNewValue(featureSecondValue);

    Map<String, Instances> examples = tree.populateExamples(bestFeature, instances);

    assertEquals(expectedNumberOfFeaturesWithFirstValue, examples.get(featureFirstValue).getNumberOfInstances());
    assertEquals(expectedNumberOfFeaturesWithSecondValue, examples.get(featureSecondValue).getNumberOfInstances());
  }

  @Test
  public void testPopulateExamples_InvalidFeatureValue() throws InvalidFeatureValueException {

    String featureName = "Wind";
    String featureFirstValue = "Weak";
    String featureSecondValue = "Strong";
    String featureInvalidValue = "Invalid";

    String expectedMessage = String.format(TreeUtils.INVALID_FEATURE, featureInvalidValue);

    TreeImpl tree = new TreeImpl();
    Instances instances = new InstancesImpl();

    Feature bestFeature = new FeatureImpl(featureName);

    Instance instance = new InstanceImpl();
    instance.setNewFeature(featureName, featureInvalidValue);
    instances.addNewInstance(instance);

    bestFeature.addNewValue(featureFirstValue);
    bestFeature.addNewValue(featureSecondValue);

    try {

      tree.populateExamples(bestFeature, instances);

      fail("InvalidFeatureValueException should be thrown.");
    } catch (InvalidFeatureValueException e) {

      assertEquals(expectedMessage, e.getMessage());
    }
  }

  @Test
  public void testBuildDecisionTree() throws InvalidFeatureValueException {

    NodeType nodeFeature = NodeType.FEATURE_NODE;
    NodeType nodeLeaf = NodeType.OUTPUT_LEAF_NODE;

    String featureOutlook = "Outlook";
    String firstOutlookValue = "Overcast";
    String secondOutlookValue = "Sunny";
    String thirdOutlookValue = "Rain";

    String featureHumidity = "Humidity";
    String firstHumidityValue = "High";
    String secondHumidityValue = "Normal";

    String featureWind = "Wind";
    String firstWindValue = "Strong";
    String secondWindValue = "Weak";

    String outputPositive = "Yes";
    String outputNegative = "No";

    Tree id3 = new TreeImpl();

    Instances instances = new InstancesImpl();
    Features features = new FeaturesImpl();

    testBuildDecisionTree_PopulateFeatures(features);
    testBuildDecisionTree_PopulateInstances(instances);

    Node tree = id3.buildDecisionTree(features, instances);

    assertEquals(nodeFeature, tree.getNodeType());
    assertEquals(featureOutlook, tree.getFeature().getName());

    assertEquals(nodeLeaf, tree.getChildNode(firstOutlookValue).getNodeType());
    assertEquals(outputPositive, tree.getChildNode(firstOutlookValue).getOutput());

    assertEquals(nodeFeature, tree.getChildNode(secondOutlookValue).getNodeType());
    assertEquals(featureHumidity, tree.getChildNode(secondOutlookValue).getFeature().getName());

    assertEquals(nodeLeaf, tree.getChildNode(secondOutlookValue).getChildNode(firstHumidityValue).getNodeType());
    assertEquals(outputNegative, tree.getChildNode(secondOutlookValue).getChildNode(firstHumidityValue).getOutput());

    assertEquals(nodeLeaf, tree.getChildNode(secondOutlookValue).getChildNode(secondHumidityValue).getNodeType());
    assertEquals(outputPositive, tree.getChildNode(secondOutlookValue).getChildNode(secondHumidityValue).getOutput());

    assertEquals(nodeFeature, tree.getChildNode(thirdOutlookValue).getNodeType());
    assertEquals(featureWind, tree.getChildNode(thirdOutlookValue).getFeature().getName());

    assertEquals(nodeLeaf, tree.getChildNode(thirdOutlookValue).getChildNode(firstWindValue).getNodeType());
    assertEquals(outputNegative, tree.getChildNode(thirdOutlookValue).getChildNode(firstWindValue).getOutput());

    assertEquals(nodeLeaf, tree.getChildNode(thirdOutlookValue).getChildNode(secondWindValue).getNodeType());
    assertEquals(outputPositive, tree.getChildNode(thirdOutlookValue).getChildNode(secondWindValue).getOutput());
  }

  private void testBuildDecisionTree_PopulateFeatures(Features features) {

    Feature feature = new FeatureImpl("Outlook");
    feature.addNewValue("Sunny");
    feature.addNewValue("Overcast");
    feature.addNewValue("Rain");
    features.addFeature(feature);

    feature = new FeatureImpl("Temperature");
    feature.addNewValue("Hot");
    feature.addNewValue("Mild");
    feature.addNewValue("Cool");
    features.addFeature(feature);

    feature = new FeatureImpl("Humidity");
    feature.addNewValue("High");
    feature.addNewValue("Normal");
    features.addFeature(feature);

    feature = new FeatureImpl("Wind");
    feature.addNewValue("Weak");
    feature.addNewValue("Strong");
    features.addFeature(feature);
  }

  private void testBuildDecisionTree_PopulateInstances(Instances instances) {

    Instance instance;

    instance = new InstanceImpl();
    instance.setNewFeature("Outlook", "Sunny");
    instance.setNewFeature("Temperature", "Hot");
    instance.setNewFeature("Humidity", "High");
    instance.setNewFeature("Wind", "Weak");
    instance.setExpectedOutput("No");

    instance = new InstanceImpl();
    instance.setExpectedOutput("No");
    instance.setNewFeature("Outlook", "Sunny");
    instance.setNewFeature("Temperature", "Hot");
    instance.setNewFeature("Humidity", "High");
    instance.setNewFeature("Wind", "Strong");
    instances.addNewInstance(instance);

    instance = new InstanceImpl();
    instance.setExpectedOutput("Yes");
    instance.setNewFeature("Outlook", "Overcast");
    instance.setNewFeature("Temperature", "Hot");
    instance.setNewFeature("Humidity", "High");
    instance.setNewFeature("Wind", "Weak");
    instances.addNewInstance(instance);

    instance = new InstanceImpl();
    instance.setExpectedOutput("Yes");
    instance.setNewFeature("Outlook", "Rain");
    instance.setNewFeature("Temperature", "Mild");
    instance.setNewFeature("Humidity", "High");
    instance.setNewFeature("Wind", "Weak");
    instances.addNewInstance(instance);

    instance = new InstanceImpl();
    instance.setExpectedOutput("Yes");
    instance.setNewFeature("Outlook", "Rain");
    instance.setNewFeature("Temperature", "Cool");
    instance.setNewFeature("Humidity", "Normal");
    instance.setNewFeature("Wind", "Weak");
    instances.addNewInstance(instance);

    instance = new InstanceImpl();
    instance.setExpectedOutput("No");
    instance.setNewFeature("Outlook", "Rain");
    instance.setNewFeature("Temperature", "Cool");
    instance.setNewFeature("Humidity", "Normal");
    instance.setNewFeature("Wind", "Strong");
    instances.addNewInstance(instance);

    instance = new InstanceImpl();
    instance.setExpectedOutput("Yes");
    instance.setNewFeature("Outlook", "Overcast");
    instance.setNewFeature("Temperature", "Cool");
    instance.setNewFeature("Humidity", "Normal");
    instance.setNewFeature("Wind", "Strong");
    instances.addNewInstance(instance);

    instance = new InstanceImpl();
    instance.setExpectedOutput("No");
    instance.setNewFeature("Outlook", "Sunny");
    instance.setNewFeature("Temperature", "Mild");
    instance.setNewFeature("Humidity", "High");
    instance.setNewFeature("Wind", "Weak");
    instances.addNewInstance(instance);

    instance = new InstanceImpl();
    instance.setExpectedOutput("Yes");
    instance.setNewFeature("Outlook", "Sunny");
    instance.setNewFeature("Temperature", "Cool");
    instance.setNewFeature("Humidity", "Normal");
    instance.setNewFeature("Wind", "Weak");
    instances.addNewInstance(instance);

    instance = new InstanceImpl();
    instance.setExpectedOutput("Yes");
    instance.setNewFeature("Outlook", "Rain");
    instance.setNewFeature("Temperature", "Mild");
    instance.setNewFeature("Humidity", "Normal");
    instance.setNewFeature("Wind", "Weak");
    instances.addNewInstance(instance);

    instance = new InstanceImpl();
    instance.setExpectedOutput("Yes");
    instance.setNewFeature("Outlook", "Sunny");
    instance.setNewFeature("Temperature", "Mild");
    instance.setNewFeature("Humidity", "Normal");
    instance.setNewFeature("Wind", "Strong");
    instances.addNewInstance(instance);

    instance = new InstanceImpl();
    instance.setExpectedOutput("Yes");
    instance.setNewFeature("Outlook", "Overcast");
    instance.setNewFeature("Temperature", "Mild");
    instance.setNewFeature("Humidity", "High");
    instance.setNewFeature("Wind", "Strong");
    instances.addNewInstance(instance);

    instance = new InstanceImpl();
    instance.setExpectedOutput("Yes");
    instance.setNewFeature("Outlook", "Overcast");
    instance.setNewFeature("Temperature", "Hot");
    instance.setNewFeature("Humidity", "Normal");
    instance.setNewFeature("Wind", "Weak");
    instances.addNewInstance(instance);

    instance = new InstanceImpl();
    instance.setExpectedOutput("No");
    instance.setNewFeature("Outlook", "Rain");
    instance.setNewFeature("Temperature", "Mild");
    instance.setNewFeature("Humidity", "High");
    instance.setNewFeature("Wind", "Strong");
    instances.addNewInstance(instance);
  }
}
