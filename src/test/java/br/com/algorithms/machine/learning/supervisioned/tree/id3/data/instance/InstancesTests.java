package br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InstancesTests {

  private Instances instances;

  @Before
  public void instantiateInstances() {

    this.instances = new InstancesImpl();

    this.instances.addNewInstance(new InstanceImpl());
    this.instances.addNewInstance(new InstanceImpl());
  }

  @Test
  public void testFeatureInstantiation() {

    new InstancesImpl();
  }

  @Test
  public void testAddANewInstance() {

    Instance instance = new InstanceImpl();

    this.instances.addNewInstance(instance);
  }

  @Test
  public void testGetNumberOfInstances() {

    assertEquals(new Integer(2), this.instances.getNumberOfInstances());
  }

  @Test
  public void testGetInstancesByIndex() {

    String output = "Valid";
    Instance instance = new InstanceImpl();

    instance.setExpectedOutput(output);

    this.instances.addNewInstance(instance);

    assertEquals(output, this.instances.getInstanceByIndex(2).getExpectedOutput());
  }

  @Test
  public void testGetInstances() {

    assertEquals(2, this.instances.getInstances().size());
  }
}
