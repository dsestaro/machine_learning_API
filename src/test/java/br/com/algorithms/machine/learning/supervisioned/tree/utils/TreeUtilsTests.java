package br.com.algorithms.machine.learning.supervisioned.tree.utils;

import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature.Feature;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature.FeatureImpl;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature.FeaturesImpl;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.Instance;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.InstanceImpl;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.Instances;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.InstancesImpl;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.exception.InvalidFeatureValueException;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TreeUtilsTests {

  private Instances instances;
  private Feature feature;

  @Before
  public void instantiateTree() {

    this.instances = new InstancesImpl();

    this.feature = new FeatureImpl("Wind");
    this.feature.addNewValue("Weak");
    this.feature.addNewValue("Strong");

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
  public void testCalculateOutputMap() {

    Map<String, Integer> quantity = TreeUtils.calculateQuantityOutput(this.instances);

    assertEquals(new Integer(5), quantity.get("No"));
    assertEquals(new Integer(9), quantity.get("Yes"));
  }

  @Test
  public void testCalculateQuantityByFeatureValue() throws InvalidFeatureValueException {

    Map<String, Instances> quantity = TreeUtils.calculateQuantityByFeatureValue(this.instances, this.feature);

    assertEquals(new Integer(8), quantity.get("Weak").getNumberOfInstances());
    assertEquals(new Integer(6), quantity.get("Strong").getNumberOfInstances());
  }

  @Test(expected = InvalidFeatureValueException.class)
  public void testCalculateQuantityByFeatureValueWithInvalidValue() throws InvalidFeatureValueException {

    Instance instance = new InstanceImpl();
    instance.setExpectedOutput("Yes");
    instance.setNewFeature("Outlook", "Invalid");
    instance.setNewFeature("Temperature", "Invalid");
    instance.setNewFeature("Humidity", "Invalid");
    instance.setNewFeature("Wind", "Invalid");
    this.instances.addNewInstance(instance);

    Map<String, Instances> quantity = TreeUtils.calculateQuantityByFeatureValue(this.instances, this.feature);
  }
}
