package br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance;

import java.util.List;
import java.util.Map;

public interface Instances {

  InstancesImpl addNewInstance(Instance instance);

  Instance getInstanceByIndex(Integer index);

  Integer getNumberOfInstances();

  List<Instance> getInstances();

  boolean hasOnlyOneOutput();
}
