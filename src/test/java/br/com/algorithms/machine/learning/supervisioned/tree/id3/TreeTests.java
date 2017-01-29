package br.com.algorithms.machine.learning.supervisioned.tree.id3;

import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature.Feature;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature.Features;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature.FeaturesImpl;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.Instance;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.InstanceImpl;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.Instances;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.InstancesImpl;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.exception.feature.EmptyFeaturesException;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.exception.instance.EmptyInstancesException;
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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TreeTests {

  private TreeImpl tree;
  private Instances instances;
  private Features features;

  @Before
  public void instantiateTree() {

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

  @Test(expected = EmptyFeaturesException.class)
  public void testNullFeatures() throws EmptyFeaturesException, EmptyInstancesException, InvalidFeatureValueException {

    this.tree.buildDecisionTree(null, null);
  }

  @Test(expected = EmptyFeaturesException.class)
  public void testEmptyFeatures() throws EmptyFeaturesException, EmptyInstancesException, InvalidFeatureValueException {

    this.tree.buildDecisionTree(new FeaturesImpl(), null);
  }

  @Test(expected = EmptyInstancesException.class)
  public void testNullInstances() throws EmptyFeaturesException, EmptyInstancesException, InvalidFeatureValueException {

    this.tree.buildDecisionTree(this.features, null);
  }

  @Test(expected = EmptyInstancesException.class)
  public void testEmptyInstances() throws EmptyFeaturesException, EmptyInstancesException, InvalidFeatureValueException {

    this.tree.buildDecisionTree(this.features, new InstancesImpl());
  }

  @Test
  public void testIsOnlyOnePossibleOutputWithMoreThanOne() {

    Map<String, Integer> quantity = TreeUtils.calculateQuantityOutput(this.instances);

    assertFalse(this.tree.isOnlyOnePossibleOutput(quantity));
  }

  @Test
  public void testIsOnlyOnePossibleOutputWithOnlyOne() {

    Map<String, Integer> quantity = new HashMap<String, Integer>();

    quantity.put("Yes", 1);

    assertTrue(this.tree.isOnlyOnePossibleOutput(quantity));
  }

  @Test
  public void testIfLeafIsBeingReturnedWhenOnlyOneOutputIsSent() throws EmptyInstancesException, EmptyFeaturesException, InvalidFeatureValueException {

    Instances instances = new InstancesImpl();
    Instance instance = new InstanceImpl();

    instance.setExpectedOutput("Yes");

    instances.addNewInstance(instance);

    assertEquals(NodeType.OUTPUT_LEAF_NODE, this.tree.buildDecisionTree(this.features, instances).getNodeType());
  }

  @Test
  public void testGetBestFeature() throws InvalidFeatureValueException {

    Map<String, Integer> quantity = TreeUtils.calculateQuantityOutput(this.instances);

    Feature feature = this.tree.getBestFeature(this.features, this.instances, quantity);

    assertEquals("Outlook", feature.getName());
  }

  @Test(expected = InvalidFeatureValueException.class)
  public void testGetBestFeatureWithInvalidValueOnAInstance() throws InvalidFeatureValueException {

    Instance instance = new InstanceImpl();
    instance.setExpectedOutput("Yes");
    instance.setNewFeature("Outlook", "Invalid");
    instance.setNewFeature("Temperature", "Invalid");
    instance.setNewFeature("Humidity", "Invalid");
    instance.setNewFeature("Wind", "Invalid");
    this.instances.addNewInstance(instance);

    Map<String, Integer> quantity = TreeUtils.calculateQuantityOutput(this.instances);

    Feature feature = this.tree.getBestFeature(this.features, this.instances, quantity);
  }

  @Test
  public void testFilterBestFeatureFromTheFeaturesList() {

    Feature feature = new FeatureImpl("Outlook");
    feature.addNewValue("Sunny");
    feature.addNewValue("Overcast");
    feature.addNewValue("Rain");
    this.features.addFeature(feature);

    Features featuresFiltered = this.tree.getRemaingFeatures(this.features, feature);

    assertEquals(new Integer(3), featuresFiltered.getNumberOfFeatures());

    for(Feature filteredFeature : featuresFiltered.getFeatures()) {

      assertNotEquals(feature.getName(), filteredFeature.getName());
    }
  }

  @Test
  public void testBuildFeatureNode() {

    Feature feature = new FeatureImpl("Wind");
    feature.addNewValue("Weak");
    feature.addNewValue("Strong");

    Node node = this.tree.buildFeatureNode(feature);

    assertEquals(NodeType.FEATURE_NODE, node.getNodeType());
    assertEquals("Wind", node.getFeature().getName());
  }

  @Test
  public void testPopulateExamplesOnChildNode() throws InvalidFeatureValueException {

    Feature bestFeature = new FeatureImpl("Wind");
    bestFeature.addNewValue("Weak");
    bestFeature.addNewValue("Strong");

    Map<String, Instances> examples = this.tree.populateExamples(bestFeature, this.instances);

    assertEquals(new Integer(8), examples.get("Weak").getNumberOfInstances());
    assertEquals(new Integer(6), examples.get("Strong").getNumberOfInstances());
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
  public void testFullFlow() throws EmptyInstancesException, EmptyFeaturesException, InvalidFeatureValueException {

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
