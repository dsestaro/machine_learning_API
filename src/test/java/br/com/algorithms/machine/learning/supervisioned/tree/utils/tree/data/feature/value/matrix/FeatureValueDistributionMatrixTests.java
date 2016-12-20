package br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.data.feature.value.matrix;

import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.exception.InvalidFeatureInDistributionMatrixException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class FeatureValueDistributionMatrixTests {

  private FeatureValueDistributionMatrix featureValueDistributionMatrix;

  @Before
  public void instantiateFeatureValueDistributionMatrix() {

    this.featureValueDistributionMatrix = new FeatureValueDistributionMatrixImpl();

    this.featureValueDistributionMatrix.setNewFeature("Feature");
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

    assertNotNull(this.featureValueDistributionMatrix.setNewFeatureValue("Feature", "Value1"));
  }

  @Test(expected = InvalidFeatureInDistributionMatrixException.class)
  public void testSettingFeatureValueInAnNonExistingFeature() throws InvalidFeatureInDistributionMatrixException {

    this.featureValueDistributionMatrix.setNewFeatureValue("HHHHjjjj", "Value1");
  }
}
