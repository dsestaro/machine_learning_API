package br.com.algorithms.machine.learning.math.information.gain;

import br.com.algorithms.machine.learning.math.information.gain.exception.InvalidQuantityMapByFeatureValueInitializationException;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature.Feature;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature.FeatureImpl;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.Instance;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.InstanceImpl;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.Instances;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.InstancesImpl;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class InformationGainTests {

  @Test
  public void testInformationGainInstantiation() {

    new InformationGain();
  }

  @Test
  public void testCalculateInformationGain() {

    Double entropy = 0.94;
    Double expectedInformationGain = 0.0478410717376383;

    int numberOfInstances = 14;

    String featureName = "Wind";
    String featureFirstValue = "Strong";
    String featureSecondValue = "Weak";

    String negativeOutput = "No";
    String positiveOutput = "Yes";

    FeatureImpl feature = new FeatureImpl(featureName);
    feature.addNewValue(featureFirstValue);
    feature.addNewValue(featureSecondValue);

    Instances instancesWeak = new InstancesImpl();
    Instances instancesStrong = new InstancesImpl();

    Instance instance1 = new InstanceImpl();
    instance1.setExpectedOutput(negativeOutput);
    instance1.setNewFeature(featureName, featureFirstValue);
    instancesWeak.addNewInstance(instance1);

    Instance instance2 = new InstanceImpl();
    instance2.setExpectedOutput(negativeOutput);
    instance2.setNewFeature(featureName, featureSecondValue);
    instancesStrong.addNewInstance(instance2);

    Instance instance3 = new InstanceImpl();
    instance3.setExpectedOutput(positiveOutput);
    instance3.setNewFeature(featureName, featureSecondValue);
    instancesWeak.addNewInstance(instance3);

    Instance instance4 = new InstanceImpl();
    instance4.setExpectedOutput(positiveOutput);
    instance4.setNewFeature(featureName, featureSecondValue);
    instancesWeak.addNewInstance(instance4);

    Instance instance5 = new InstanceImpl();
    instance5.setExpectedOutput(positiveOutput);
    instance5.setNewFeature(featureName, featureSecondValue);
    instancesWeak.addNewInstance(instance5);

    Instance instance6 = new InstanceImpl();
    instance6.setExpectedOutput(negativeOutput);
    instance6.setNewFeature(featureName, featureFirstValue);
    instancesStrong.addNewInstance(instance6);

    Instance instance7 = new InstanceImpl();
    instance7.setExpectedOutput(positiveOutput);
    instance7.setNewFeature(featureName, featureFirstValue);
    instancesStrong.addNewInstance(instance7);

    Instance instance8 = new InstanceImpl();
    instance8.setExpectedOutput(negativeOutput);
    instance8.setNewFeature(featureName, featureSecondValue);
    instancesWeak.addNewInstance(instance8);

    Instance instance9 = new InstanceImpl();
    instance9.setExpectedOutput(positiveOutput);
    instance9.setNewFeature(featureName, featureSecondValue);
    instancesWeak.addNewInstance(instance9);

    Instance instance10 = new InstanceImpl();
    instance10.setExpectedOutput(positiveOutput);
    instance10.setNewFeature(featureName, featureSecondValue);
    instancesWeak.addNewInstance(instance10);

    Instance instance11 = new InstanceImpl();
    instance11.setExpectedOutput(positiveOutput);
    instance11.setNewFeature(featureName, featureFirstValue);
    instancesStrong.addNewInstance(instance11);

    Instance instance12 = new InstanceImpl();
    instance12.setExpectedOutput(positiveOutput);
    instance12.setNewFeature(featureName, featureFirstValue);
    instancesStrong.addNewInstance(instance12);

    Instance instance13 = new InstanceImpl();
    instance13.setExpectedOutput(positiveOutput);
    instance13.setNewFeature(featureName, featureSecondValue);
    instancesWeak.addNewInstance(instance13);

    Instance instance14 = new InstanceImpl();
    instance14.setExpectedOutput(negativeOutput);
    instance14.setNewFeature(featureName, featureFirstValue);
    instancesStrong.addNewInstance(instance14);

    Map<String, Instances> quantityMap = new HashMap<String, Instances>();
    quantityMap.put(featureFirstValue, instancesStrong);
    quantityMap.put(featureSecondValue, instancesWeak);

    Double informationGain = InformationGain.calculateInformationGain(entropy, feature, quantityMap, numberOfInstances);

    assertEquals(expectedInformationGain, informationGain);
  }

  @Test
  public void testCalculateInformationGainWithNoInstances() {

    Double entropy = 0.94;
    Double expectedInformationGain = 0.94;

    int numberOfInstances = 14;

    String featureName = "Wind";
    String featureFirstValue = "Strong";
    String featureSecondValue = "Weak";

    FeatureImpl feature = new FeatureImpl(featureName);
    feature.addNewValue(featureFirstValue);
    feature.addNewValue(featureSecondValue);

    Map<String, Instances> quantityMap = new HashMap<String, Instances>();
    quantityMap.put(featureFirstValue, new InstancesImpl());
    quantityMap.put(featureSecondValue, new InstancesImpl());

    Double informationGain = InformationGain.calculateInformationGain(entropy, feature, quantityMap, numberOfInstances);

    assertEquals(expectedInformationGain, informationGain);
  }

  @Test(expected = InvalidQuantityMapByFeatureValueInitializationException.class)
  public void testCalculateInformationGainWithInvalidFeature() {

    Double entropy = 0.94;

    int numberOfInstances = 14;

    String featureName = "Wind";
    String featureFirstValue = "Strong";
    String featureSecondValue = "Weak";
    String featureInvalidValue = "Invalid";

    String negativeOutput = "No";

    FeatureImpl feature = new FeatureImpl(featureName);
    feature.addNewValue(featureFirstValue);
    feature.addNewValue(featureSecondValue);

    Instances instances = new InstancesImpl();

    Instance instance = new InstanceImpl();
    instance.setExpectedOutput(negativeOutput);
    instance.setNewFeature(featureName, featureInvalidValue);
    instances.addNewInstance(instance);

    Map<String, Instances> quantityMap = new HashMap<String, Instances>();
    quantityMap.put(featureSecondValue, instances);

    InformationGain.calculateInformationGain(entropy, feature, quantityMap, numberOfInstances);
  }
}
