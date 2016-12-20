package br.com.algorithms.machine.learning.supervisioned.tree.utils.tree;

import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.data.feature.Features;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.data.instance.Instances;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.exception.EmptyFeaturesException;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.exception.EmptyInstancesException;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.node.Node;

public interface Tree {

  Node buildDecisionTree(Features features, Instances instances) throws EmptyFeaturesException, EmptyInstancesException;
}
