package br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.data.instance;

import java.util.ArrayList;
import java.util.List;

public class InstancesImpl implements Instances {

  private List<Instance> instances;

  public InstancesImpl() {

    this.instances = new ArrayList<Instance>();
  }

  public InstancesImpl addNewInstance(Instance instance) {

    this.instances.add(instance);

    return this;
  }

  public Instance getInstanceByIndex(Integer index) {

    return this.instances.get(index);
  }

  public Integer getNumberOfInstances() {

    return this.instances.size();
  }

  public List<Instance> getInstances() {

    return this.instances;
  }
}
