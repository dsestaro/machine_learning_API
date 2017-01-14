package br.com.algorithms.machine.learning.math.information.gain;

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

    FeatureImpl feature = new FeatureImpl("Wind");
    feature.addNewValue("Strong");
    feature.addNewValue("Weak");

    Instances instancesWeak = new InstancesImpl();
    Instances instancesStrong = new InstancesImpl();

    Instance instance1 = new InstanceImpl();
    instance1.setExpectedOutput("No");
    instance1.setNewFeature("Wind", "Weak");
    instancesWeak.addNewInstance(instance1);

    Instance instance2 = new InstanceImpl();
    instance2.setExpectedOutput("No");
    instance2.setNewFeature("Wind", "Strong");
    instancesStrong.addNewInstance(instance2);

    Instance instance3 = new InstanceImpl();
    instance3.setExpectedOutput("Yes");
    instance3.setNewFeature("Wind", "Weak");
    instancesWeak.addNewInstance(instance3);

    Instance instance4 = new InstanceImpl();
    instance4.setExpectedOutput("Yes");
    instance4.setNewFeature("Wind", "Weak");
    instancesWeak.addNewInstance(instance4);

    Instance instance5 = new InstanceImpl();
    instance5.setExpectedOutput("Yes");
    instance5.setNewFeature("Wind", "Weak");
    instancesWeak.addNewInstance(instance5);

    Instance instance6 = new InstanceImpl();
    instance6.setExpectedOutput("No");
    instance6.setNewFeature("Wind", "Strong");
    instancesStrong.addNewInstance(instance6);

    Instance instance7 = new InstanceImpl();
    instance7.setExpectedOutput("Yes");
    instance7.setNewFeature("Wind", "Strong");
    instancesStrong.addNewInstance(instance7);

    Instance instance8 = new InstanceImpl();
    instance8.setExpectedOutput("No");
    instance8.setNewFeature("Wind", "Weak");
    instancesWeak.addNewInstance(instance8);

    Instance instance9 = new InstanceImpl();
    instance9.setExpectedOutput("Yes");
    instance9.setNewFeature("Wind", "Weak");
    instancesWeak.addNewInstance(instance9);

    Instance instance10 = new InstanceImpl();
    instance10.setExpectedOutput("Yes");
    instance10.setNewFeature("Wind", "Weak");
    instancesWeak.addNewInstance(instance10);

    Instance instance11 = new InstanceImpl();
    instance11.setExpectedOutput("Yes");
    instance11.setNewFeature("Wind", "Strong");
    instancesStrong.addNewInstance(instance11);

    Instance instance12 = new InstanceImpl();
    instance12.setExpectedOutput("Yes");
    instance12.setNewFeature("Wind", "Strong");
    instancesStrong.addNewInstance(instance12);

    Instance instance13 = new InstanceImpl();
    instance13.setExpectedOutput("Yes");
    instance13.setNewFeature("Wind", "Weak");
    instancesWeak.addNewInstance(instance13);

    Instance instance14 = new InstanceImpl();
    instance14.setExpectedOutput("No");
    instance14.setNewFeature("Wind", "Strong");
    instancesStrong.addNewInstance(instance14);

    Map<String, Instances> quantityMap = new HashMap<String, Instances>();
    quantityMap.put("Strong", instancesStrong);
    quantityMap.put("Weak", instancesWeak);

    Double informationGain = InformationGain.calculateInformationGain(entropy, feature, quantityMap, 14);

    assertEquals(new Double(0.0478410717376383), informationGain);
  }
}
