package br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InstanceTests {

  private Instance instance;

  @Before
  public void instantiateInstance() {

    String output = "Output1";
    String featureName = "FeatureName";
    String featureValue = "FeatureValue";

    this.instance = new InstanceImpl();

    this.instance.setExpectedOutput(output);
    this.instance.setNewFeature(featureName, featureValue);
  }

  @Test
  public void testFeatureInstantiation() {

    new InstanceImpl();
  }

  @Test
  public void testDataInsertion() {

    String output = "Output1";
    String featureName = "FeatureName";
    String featureValue = "FeatureValue";

    this.instance.setExpectedOutput(output);
    this.instance.setNewFeature(featureName, featureValue);
  }

  @Test
  public void testDataRetrieve() {

    String output = "Output1";
    String featureName = "FeatureName";
    String featureValue = "FeatureValue";

    assertEquals(output, this.instance.getExpectedOutput());
    assertEquals(featureValue, this.instance.getFeatureValue(featureName));
  }
}
