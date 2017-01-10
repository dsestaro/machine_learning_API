package br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance;

import java.util.List;

public interface Instances {

  InstancesImpl addNewInstance(Instance instance);

  Instance getInstanceByIndex(Integer index);

  Integer getNumberOfInstances();

  List<Instance> getInstances();
}
