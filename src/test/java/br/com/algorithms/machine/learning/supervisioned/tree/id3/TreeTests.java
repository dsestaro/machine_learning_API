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
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class TreeTests {

  private TreeImpl tree;
  private Instances instances;
  private Features features;

  @Before
  public void instanatiateTree() {

    this.tree = new TreeImpl();

    this.instances = new InstancesImpl();
    this.features = new FeaturesImpl();

    Feature featureOutlook = new FeatureImpl("Outlook");
    featureOutlook.addNewValue("Sunny");
    featureOutlook.addNewValue("Overcast");
    featureOutlook.addNewValue("Rain");
    this.features.addFeature(featureOutlook);

    Feature featureTemperature = new FeatureImpl("Temperature");
    featureTemperature.addNewValue("Hot");
    featureTemperature.addNewValue("Mild");
    featureTemperature.addNewValue("Cool");
    this.features.addFeature(featureTemperature);

    Feature featureHumidity = new FeatureImpl("Humidity");
    featureHumidity.addNewValue("High");
    featureHumidity.addNewValue("Normal");
    this.features.addFeature(featureHumidity);

    Feature featureWind = new FeatureImpl("Wind");
    featureWind.addNewValue("Weak");
    featureWind.addNewValue("Strong");
    this.features.addFeature(featureWind);

    Instance instance1 = new InstanceImpl();
    instance1.setExpectedOutput("No");
    instance1.setNewFeature("Outlook", "Sunny");
    instance1.setNewFeature("Temperature", "Hot");
    instance1.setNewFeature("Humidity", "High");
    instance1.setNewFeature("Wind", "Weak");
    this.instances.addNewInstance(instance1);

    Instance instance2 = new InstanceImpl();
    instance2.setExpectedOutput("No");
    instance2.setNewFeature("Outlook", "Sunny");
    instance2.setNewFeature("Temperature", "Hot");
    instance2.setNewFeature("Humidity", "High");
    instance2.setNewFeature("Wind", "Strong");
    this.instances.addNewInstance(instance2);

    Instance instance3 = new InstanceImpl();
    instance3.setExpectedOutput("Yes");
    instance3.setNewFeature("Outlook", "Overcast");
    instance3.setNewFeature("Temperature", "Hot");
    instance3.setNewFeature("Humidity", "High");
    instance3.setNewFeature("Wind", "Weak");
    this.instances.addNewInstance(instance3);

    Instance instance4 = new InstanceImpl();
    instance4.setExpectedOutput("Yes");
    instance4.setNewFeature("Outlook", "Rain");
    instance4.setNewFeature("Temperature", "Mild");
    instance4.setNewFeature("Humidity", "High");
    instance4.setNewFeature("Wind", "Weak");
    this.instances.addNewInstance(instance4);

    Instance instance5 = new InstanceImpl();
    instance5.setExpectedOutput("Yes");
    instance5.setNewFeature("Outlook", "Rain");
    instance5.setNewFeature("Temperature", "Cool");
    instance5.setNewFeature("Humidity", "Normal");
    instance5.setNewFeature("Wind", "Weak");
    this.instances.addNewInstance(instance5);

    Instance instance6 = new InstanceImpl();
    instance6.setExpectedOutput("No");
    instance6.setNewFeature("Outlook", "Rain");
    instance6.setNewFeature("Temperature", "Cool");
    instance6.setNewFeature("Humidity", "Normal");
    instance6.setNewFeature("Wind", "Strong");
    this.instances.addNewInstance(instance6);

    Instance instance7 = new InstanceImpl();
    instance7.setExpectedOutput("Yes");
    instance7.setNewFeature("Outlook", "Overcast");
    instance7.setNewFeature("Temperature", "Cool");
    instance7.setNewFeature("Humidity", "Normal");
    instance7.setNewFeature("Wind", "Strong");
    this.instances.addNewInstance(instance7);

    Instance instance8 = new InstanceImpl();
    instance8.setExpectedOutput("No");
    instance8.setNewFeature("Outlook", "Sunny");
    instance8.setNewFeature("Temperature", "Mild");
    instance8.setNewFeature("Humidity", "High");
    instance8.setNewFeature("Wind", "Weak");
    this.instances.addNewInstance(instance8);

    Instance instance9 = new InstanceImpl();
    instance9.setExpectedOutput("Yes");
    instance9.setNewFeature("Outlook", "Sunny");
    instance9.setNewFeature("Temperature", "Cool");
    instance9.setNewFeature("Humidity", "Normal");
    instance9.setNewFeature("Wind", "Weak");
    this.instances.addNewInstance(instance9);

    Instance instance10 = new InstanceImpl();
    instance10.setExpectedOutput("Yes");
    instance10.setNewFeature("Outlook", "Rain");
    instance10.setNewFeature("Temperature", "Mild");
    instance10.setNewFeature("Humidity", "Normal");
    instance10.setNewFeature("Wind", "Weak");
    this.instances.addNewInstance(instance10);

    Instance instance11 = new InstanceImpl();
    instance11.setExpectedOutput("Yes");
    instance11.setNewFeature("Outlook", "Sunny");
    instance11.setNewFeature("Temperature", "Mild");
    instance11.setNewFeature("Humidity", "Normal");
    instance11.setNewFeature("Wind", "Strong");
    this.instances.addNewInstance(instance11);

    Instance instance12 = new InstanceImpl();
    instance12.setExpectedOutput("Yes");
    instance12.setNewFeature("Outlook", "Overcast");
    instance12.setNewFeature("Temperature", "Mild");
    instance12.setNewFeature("Humidity", "High");
    instance12.setNewFeature("Wind", "Strong");
    this.instances.addNewInstance(instance12);

    Instance instance13 = new InstanceImpl();
    instance13.setExpectedOutput("Yes");
    instance13.setNewFeature("Outlook", "Overcast");
    instance13.setNewFeature("Temperature", "Hot");
    instance13.setNewFeature("Humidity", "Normal");
    instance13.setNewFeature("Wind", "Weak");
    this.instances.addNewInstance(instance13);

    Instance instance14 = new InstanceImpl();
    instance14.setExpectedOutput("No");
    instance14.setNewFeature("Outlook", "Rain");
    instance14.setNewFeature("Temperature", "Mild");
    instance14.setNewFeature("Humidity", "High");
    instance14.setNewFeature("Wind", "Strong");
    this.instances.addNewInstance(instance14);
  }

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

    Feature bestFeature = new FeatureImpl(featureName);

    bestFeature.addNewValue(featureFirstValue);
    bestFeature.addNewValue(featureSecondValue);

    Map<String, Instances> examples = tree.populateExamples(bestFeature, this.instances);

    assertEquals(expectedNumberOfFeaturesWithFirstValue, examples.get(featureFirstValue).getNumberOfInstances());
    assertEquals(expectedNumberOfFeaturesWithSecondValue, examples.get(featureSecondValue).getNumberOfInstances());
  }

  @Test(expected = InvalidFeatureValueException.class)
  public void testPopulateExamplesOnChildNodeWithInvalidFeatureValue() throws InvalidFeatureValueException {

    Feature bestFeature = new FeatureImpl("Wind");
    bestFeature.addNewValue("Weak");
    bestFeature.addNewValue("Strong");

    Instance instance = new InstanceImpl();
    instance.setExpectedOutput("Yes");
    instance.setNewFeature("Outlook", "Invalid");
    instance.setNewFeature("Temperature", "Invalid");
    instance.setNewFeature("Humidity", "Invalid");
    instance.setNewFeature("Wind", "Invalid");
    this.instances.addNewInstance(instance);

    Map<String, Instances> examples = this.tree.populateExamples(bestFeature, this.instances);
  }

  @Test
  public void testFullFlow() throws InvalidFeatureValueException {

    Node tree = this.tree.buildDecisionTree(this.features, this.instances);

    assertEquals(NodeType.FEATURE_NODE, tree.getNodeType());
    assertEquals("Outlook", tree.getFeature().getName());

    assertEquals(NodeType.OUTPUT_LEAF_NODE, tree.getChildNode("Overcast").getNodeType());
    assertEquals("Yes", tree.getChildNode("Overcast").getOutput());

    assertEquals(NodeType.FEATURE_NODE, tree.getChildNode("Sunny").getNodeType());
    assertEquals("Humidity", tree.getChildNode("Sunny").getFeature().getName());

    assertEquals(NodeType.OUTPUT_LEAF_NODE, tree.getChildNode("Sunny").getChildNode("High").getNodeType());
    assertEquals("No", tree.getChildNode("Sunny").getChildNode("High").getOutput());

    assertEquals(NodeType.OUTPUT_LEAF_NODE, tree.getChildNode("Sunny").getChildNode("Normal").getNodeType());
    assertEquals("Yes", tree.getChildNode("Sunny").getChildNode("Normal").getOutput());

    assertEquals(NodeType.FEATURE_NODE, tree.getChildNode("Rain").getNodeType());
    assertEquals("Wind", tree.getChildNode("Rain").getFeature().getName());

    assertEquals(NodeType.OUTPUT_LEAF_NODE, tree.getChildNode("Rain").getChildNode("Strong").getNodeType());
    assertEquals("No", tree.getChildNode("Rain").getChildNode("Strong").getOutput());

    assertEquals(NodeType.OUTPUT_LEAF_NODE, tree.getChildNode("Rain").getChildNode("Weak").getNodeType());
    assertEquals("Yes", tree.getChildNode("Rain").getChildNode("Weak").getOutput());
  }
}
