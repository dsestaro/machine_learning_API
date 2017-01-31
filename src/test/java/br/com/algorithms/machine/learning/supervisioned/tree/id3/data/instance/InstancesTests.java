package br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance;

import br.com.algorithms.machine.learning.supervisioned.tree.id3.exception.instance.NonExistentInstanceException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class InstancesTests {

  @Test
  public void testFeatureInstantiation() {

    Instances instances = new InstancesImpl();

    assertNotNull(instances);
    assertNotNull(instances.getInstances());
  }

  @Test
  public void testSetGetInstance() {

    Instances instances = new InstancesImpl();
    Instance instance = new InstanceImpl();

    int initialQuantity = 0;
    int expectedQuantity = 1;

    assertEquals(initialQuantity, instances.getInstances().size());

    instances.addNewInstance(instance);

    assertEquals(expectedQuantity, instances.getInstances().size());
  }

  @Test
  public void testSetGetInstance_NullObject() {

    Instances instances = new InstancesImpl();
    Instance instance = null;

    int initialQuantity = 0;
    int expectedQuantity = 0;

    assertEquals(initialQuantity, instances.getInstances().size());

    instances.addNewInstance(instance);

    assertEquals(expectedQuantity, instances.getInstances().size());
  }

  @Test
  public void testGetNumberOfInstances() {

    Instances instances = new InstancesImpl();
    Instance firstInstance = new InstanceImpl();
    Instance secondInstance = new InstanceImpl();

    Integer expectedQuantity = 2;

    instances.addNewInstance(firstInstance);
    instances.addNewInstance(secondInstance);

    assertEquals(expectedQuantity, instances.getNumberOfInstances());
  }

  @Test
  public void testGetInstancesByIndex() {

    Instances instances = new InstancesImpl();
    Instance firstInstance = new InstanceImpl();
    Instance secondInstance = new InstanceImpl();

    String output = "Valid";
    int instanceIndex = 1;

    secondInstance.setExpectedOutput(output);

    instances.addNewInstance(firstInstance);
    instances.addNewInstance(secondInstance);

    assertEquals(output, instances.getInstanceByIndex(instanceIndex).getExpectedOutput());
  }

  @Test
  public void testGetInstancesByIndex_InvalidIndex() {

    Instances instances = new InstancesImpl();
    Instance firstInstance = new InstanceImpl();
    Instance secondInstance = new InstanceImpl();

    String output = "Valid";
    int instanceIndex = 5;

    secondInstance.setExpectedOutput(output);

    instances.addNewInstance(firstInstance);
    instances.addNewInstance(secondInstance);

    try {

      instances.getInstanceByIndex(instanceIndex).getExpectedOutput();

      fail("NonExistentInstanceException should be thrown.");
    } catch (NonExistentInstanceException e) {

      assertEquals(InstancesImpl.NON_EXISTENT_INSTANCE, e.getMessage());
    }
  }
}
