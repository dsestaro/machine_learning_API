package br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance;

import br.com.algorithms.machine.learning.supervisioned.tree.id3.exception.instance.NonExistentInstanceException;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.TreeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InstancesImpl implements Instances {

  public static final String NON_EXISTENT_INSTANCE = "The searched instance does not exist.";

  private List<Instance> instances;

  public InstancesImpl() {

    this.instances = new ArrayList<Instance>();
  }

  public InstancesImpl addNewInstance(Instance instance) {

    if(instance != null) {

      this.instances.add(instance);
    }

    return this;
  }

  public Instance getInstanceByIndex(Integer index) {

    Instance instance = null;

    try {

      instance = this.instances.get(index);
    } catch (IndexOutOfBoundsException e) {

      throw new NonExistentInstanceException(NON_EXISTENT_INSTANCE);
    }

    return instance;
  }

  public Integer getNumberOfInstances() {

    return this.instances.size();
  }

  public List<Instance> getInstances() {

    return this.instances;
  }

  public boolean hasOnlyOneOutput() {

    Map<String, Integer> outputQuant = TreeUtils.calculateQuantityOutput(this.instances);

    int numberOfQuantitiesBiggerThanZero = 0;

    for(String outputName : outputQuant.keySet()) {

      if(outputQuant.get(outputName) != null && outputQuant.get(outputName) > 0) {

        numberOfQuantitiesBiggerThanZero++;
      }
    }

    return numberOfQuantitiesBiggerThanZero == 1;
  }
}
