package br.com.algorithms.machine.learning.supervisioned.tree.id3;

import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature.Features;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.Instances;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.node.Node;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.exception.InvalidFeatureValueException;

public interface Tree {

  Node buildDecisionTree(Features features, Instances instances) throws InvalidFeatureValueException;
}
