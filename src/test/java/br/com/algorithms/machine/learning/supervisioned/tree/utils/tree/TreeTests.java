package br.com.algorithms.machine.learning.supervisioned.tree.utils.tree;

import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.data.feature.Feature;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.data.feature.FeatureImpl;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.data.feature.Features;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.data.feature.FeaturesImpl;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.data.instance.Instance;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.data.instance.InstanceImpl;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.data.instance.Instances;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.data.instance.InstancesImpl;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.exception.EmptyFeaturesException;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.exception.EmptyInstancesException;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.node.NodeType;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
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
    featureTemperature.addNewValue("Cold");
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
    this.instances.addNewInstance(instance1);

    Instance instance2 = new InstanceImpl();
    instance2.setExpectedOutput("No");
    this.instances.addNewInstance(instance2);

    Instance instance3 = new InstanceImpl();
    instance3.setExpectedOutput("Yes");
    this.instances.addNewInstance(instance3);

    Instance instance4 = new InstanceImpl();
    instance4.setExpectedOutput("Yes");
    this.instances.addNewInstance(instance4);

    Instance instance5 = new InstanceImpl();
    instance5.setExpectedOutput("Yes");
    this.instances.addNewInstance(instance5);

    Instance instance6 = new InstanceImpl();
    instance6.setExpectedOutput("No");
    this.instances.addNewInstance(instance6);

    Instance instance7 = new InstanceImpl();
    instance7.setExpectedOutput("Yes");
    this.instances.addNewInstance(instance7);

    Instance instance8 = new InstanceImpl();
    instance8.setExpectedOutput("No");
    this.instances.addNewInstance(instance8);

    Instance instance9 = new InstanceImpl();
    instance9.setExpectedOutput("Yes");
    this.instances.addNewInstance(instance9);

    Instance instance10 = new InstanceImpl();
    instance10.setExpectedOutput("Yes");
    this.instances.addNewInstance(instance10);

    Instance instance11 = new InstanceImpl();
    instance11.setExpectedOutput("Yes");
    this.instances.addNewInstance(instance11);

    Instance instance12 = new InstanceImpl();
    instance12.setExpectedOutput("Yes");
    this.instances.addNewInstance(instance12);

    Instance instance13 = new InstanceImpl();
    instance13.setExpectedOutput("Yes");
    this.instances.addNewInstance(instance13);

    Instance instance14 = new InstanceImpl();
    instance14.setExpectedOutput("No");
    this.instances.addNewInstance(instance14);
  }

  @Test(expected = EmptyFeaturesException.class)
  public void testNullFeatures() throws EmptyFeaturesException, EmptyInstancesException {

    this.tree.buildDecisionTree(null, null);
  }

  @Test(expected = EmptyFeaturesException.class)
  public void testEmptyFeatures() throws EmptyFeaturesException, EmptyInstancesException {

    this.tree.buildDecisionTree(new FeaturesImpl(), null);
  }

  @Test(expected = EmptyInstancesException.class)
  public void testNullInstances() throws EmptyFeaturesException, EmptyInstancesException {

    this.tree.buildDecisionTree(this.features, null);
  }

  @Test(expected = EmptyInstancesException.class)
  public void testEmptyInstances() throws EmptyFeaturesException, EmptyInstancesException {

    this.tree.buildDecisionTree(this.features, new InstancesImpl());
  }

  @Test
  public void testPopulateOutputMap() {

    Map<String, Integer> quantity = this.tree.calculateQuantityOutput(this.instances);

    assertEquals(new Integer(5), quantity.get("No"));
    assertEquals(new Integer(9), quantity.get("Yes"));
  }

  @Test
  public void testIsOnlyOnePossibleOutputWithMoreThanOne() {

    Map<String, Integer> quantity = this.tree.calculateQuantityOutput(this.instances);

    assertFalse(this.tree.isOnlyOnePossibleOutput(quantity));
  }

  @Test
  public void testIsOnlyOnePossibleOutputWithOnlyOne() {

    Map<String, Integer> quantity = new HashMap<String, Integer>();

    quantity.put("Yes", 1);

    assertTrue(this.tree.isOnlyOnePossibleOutput(quantity));
  }

  @Test
  public void testIfLeafIsBeingReturnedWhenOnlyOneOutputIsSent() throws EmptyInstancesException, EmptyFeaturesException {

    Instances instances = new InstancesImpl();
    Instance instance = new InstanceImpl();

    instance.setExpectedOutput("Yes");

    instances.addNewInstance(instance);

    assertEquals(NodeType.OUTPUT_LEAF_NODE, this.tree.buildDecisionTree(this.features, instances).getNodeType());
  }

  @Test
  public void testFullFlow() throws EmptyInstancesException, EmptyFeaturesException {

    this.tree.buildDecisionTree(this.features, this.instances);
  }
}
