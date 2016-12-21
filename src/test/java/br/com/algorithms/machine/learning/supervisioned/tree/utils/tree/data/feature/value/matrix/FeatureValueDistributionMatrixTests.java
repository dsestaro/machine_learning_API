package br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.data.feature.value.matrix;

import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.exception.InvalidFeatureInDistributionMatrixException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class FeatureValueDistributionMatrixTests {

  private FeatureValueDistributionMatrixImpl featureValueDistributionMatrix;

  @Before
  public void instantiateFeatureValueDistributionMatrix() throws InvalidFeatureInDistributionMatrixException {

    this.featureValueDistributionMatrix = new FeatureValueDistributionMatrixImpl();

    this.featureValueDistributionMatrix.setNewFeature("Feature");

    this.featureValueDistributionMatrix.addFeatureValueQuantity("Feature", "Value2");
    this.featureValueDistributionMatrix.addFeatureValueQuantity("Feature", "Value2");
  }

  @Test
  public void testFeatureValueDistributionMatrixInstantiation() {

    new FeatureValueDistributionMatrixImpl();
  }

  @Test
  public void testSettingNewFeatureInTheMatrix() {

    this.featureValueDistributionMatrix.setNewFeature("Feature");
  }

  @Test
  public void testGettingFeatureInTheMatrix() throws InvalidFeatureInDistributionMatrixException {

    assertNotNull(this.featureValueDistributionMatrix.getFeatureDistribution("Feature"));
  }

  @Test(expected = InvalidFeatureInDistributionMatrixException.class)
  public void testGettingNonExistingFeatureInTheMatrix() throws InvalidFeatureInDistributionMatrixException {

    this.featureValueDistributionMatrix.getFeatureDistribution("HHHHjjjj");
  }

  @Test
  public void testSettingFeatureValueInAnExistingFeature() throws InvalidFeatureInDistributionMatrixException {

    this.featureValueDistributionMatrix.setNewFeatureValue("Feature", "Value1");
  }

  @Test(expected = InvalidFeatureInDistributionMatrixException.class)
  public void testSettingFeatureValueInAnNonExistingFeature() throws InvalidFeatureInDistributionMatrixException {

    this.featureValueDistributionMatrix.setNewFeatureValue("HHHHjjjj", "Value1");
  }

  @Test
  public void testAddingANewFeatureValueToTheMatrix() throws InvalidFeatureInDistributionMatrixException {

    this.featureValueDistributionMatrix.addFeatureValueQuantity("Feature", "Value2");
  }

  @Test
  public void testGettingTheQuantityByExistingFeatureValue() throws InvalidFeatureInDistributionMatrixException {

    assertEquals(new Integer(2), this.featureValueDistributionMatrix.getQuantityByFeatureValue("Feature", "Value2"));
  }

  @Test
  public void testGettingTheQuantityByNewFeatureValue() throws InvalidFeatureInDistributionMatrixException {

    assertEquals(new Integer(0), this.featureValueDistributionMatrix.getQuantityByFeatureValue("Feature", "Error"));
  }
}
