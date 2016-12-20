package br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.data.instance;

import java.util.List;

public interface Instances {

  InstancesImpl addNewInstance(Instance instance);

  Instance getInstanceByIndex(Integer index);

  Integer getNumberOfInstances();

  List<Instance> getInstances();
}
