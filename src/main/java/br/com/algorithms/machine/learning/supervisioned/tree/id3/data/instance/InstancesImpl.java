package br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance;

import br.com.algorithms.machine.learning.supervisioned.tree.id3.exception.instance.NonExistentInstanceException;

import java.util.ArrayList;
import java.util.List;

public class InstancesImpl implements Instances {

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

      throw new NonExistentInstanceException();
    }

    return instance;
  }

  public Integer getNumberOfInstances() {

    return this.instances.size();
  }

  public List<Instance> getInstances() {

    return this.instances;
  }
}
