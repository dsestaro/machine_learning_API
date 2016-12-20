package br.com.algorithms.machine.learning.supervisioned.tree.utils.tree;

import br.com.algorithms.machine.learning.math.entropy.Entropy;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.data.feature.Feature;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.data.feature.Features;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.data.instance.Instance;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.data.instance.Instances;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.exception.EmptyFeaturesException;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.exception.EmptyInstancesException;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.node.Node;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.node.NodeImpl;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.node.NodeType;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeImpl implements Tree {

  public Node buildDecisionTree(Features features, Instances instances) throws EmptyFeaturesException, EmptyInstancesException {

    validateParameters(features, instances);

    Map<String, Integer> outputQuant = calculateQuantityOutput(instances);

    if(isOnlyOnePossibleOutput(outputQuant)) {
      return buildLeaf(outputQuant);
    }

    Feature feature = getBestFeature(features.getFeatures(), instances.getInstances(), outputQuant);

    return null;
  }

  private void validateParameters(Features features, Instances instances) throws EmptyFeaturesException, EmptyInstancesException {

    if(features == null || features.getNumberOfFeatures() == 0) {

      throw new EmptyFeaturesException();
    }

    if(instances == null || instances.getNumberOfInstances() == 0) {

      throw new EmptyInstancesException();
    }
  }

  protected Map<String, Integer> calculateQuantityOutput(Instances instances) {

    Map<String, Integer> outputQuant = new HashMap<String, Integer>();

    for(Instance instance : instances.getInstances()) {

      populateOutputMap(outputQuant, instance.getExpectedOutput());
    }

    return outputQuant;
  }

  private void populateOutputMap(Map<String, Integer> outputQuant, String output) {

    if(outputQuant.containsKey(output)) {

      outputQuant.put(output, outputQuant.get(output) + 1);
    } else {

      outputQuant.put(output, 1);
    }
  }

  protected boolean isOnlyOnePossibleOutput(Map<String, Integer> outputQuant) {

    if(outputQuant.size() > 1) {

      return false;
    } else {

      return true;
    }
  }

  private Node buildLeaf(Map<String, Integer> outputQuant) {

    Node node = new NodeImpl();

    node.setNodeType(NodeType.OUTPUT_LEAF_NODE);
    node.setOutput(getLeafOutput(outputQuant));

    return node;
  }

  private String getLeafOutput(Map<String, Integer> outputQuant) {

    return outputQuant.keySet().iterator().next();
  }

  protected Feature getBestFeature(List<Feature> features, List<Instance> instances, Map<String, Integer> outputQuant) {

    Double collectionEntropy = Entropy.calculateEntropy(outputQuant, instances.size());

    for(Feature feature : features) {

      Map<String, Map<String, Integer>> featureValueDist = getFeatureValueDistribution(feature, instances, outputQuant);
    }

    return null;
  }

  private Map<String,Map<String,Integer>> getFeatureValueDistribution(Feature feature, List<Instance> instances, Map<String, Integer> outputQuant) {

    return null;
  }
}
