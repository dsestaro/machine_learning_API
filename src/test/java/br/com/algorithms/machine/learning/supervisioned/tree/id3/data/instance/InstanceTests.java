package br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance;

import br.com.algorithms.machine.learning.supervisioned.tree.id3.exception.InvalidInstanceInformationException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InstanceTests {

  private Instance instance;

  @Before
  public void instantiateInstance() {

    this.instance = new InstanceImpl();

    this.instance.setExpectedOutput("Output1");
    this.instance.setNewFeature("FeatureName", "FeatureValue");
  }

  @Test
  public void testFeatureInstantiation() {

    new InstanceImpl();
  }

  @Test
  public void testDataInsertion() {

    this.instance.setExpectedOutput("Output");
    this.instance.setNewFeature("NewFeature", "FeatureValue");
  }

  @Test(expected = InvalidInstanceInformationException.class)
  public void testDataInsertionOfNullFeatureName() {

    this.instance.setNewFeature(null, "FeatureValue");
  }

  @Test(expected = InvalidInstanceInformationException.class)
  public void testDataInsertionOfEmptyFeatureName() {

    this.instance.setNewFeature("", "FeatureValue");
  }

  @Test(expected = InvalidInstanceInformationException.class)
  public void testDataInsertionOfSameValueTwice() {

    this.instance.setNewFeature("NewFeature", "FeatureValue");
    this.instance.setNewFeature("NewFeature", "FeatureValue");
  }

  @Test
  public void testDataRetrieve() {

    assertEquals("Output1", this.instance.getExpectedOutput());
    assertEquals("FeatureValue", this.instance.getFeatureValue("FeatureName"));
  }
}
