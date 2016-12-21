package br.com.algorithms.machine.learning.supervisioned.tree.utils.tree;

import br.com.algorithms.machine.learning.math.entropy.Entropy;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.data.feature.Feature;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.data.feature.Features;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.data.feature.value.matrix.FeatureValueDistributionMatrix;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.data.feature.value.matrix.FeatureValueDistributionMatrixImpl;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.data.instance.Instance;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.data.instance.Instances;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.exception.EmptyFeaturesException;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.exception.EmptyInstancesException;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.exception.InvalidFeatureInDistributionMatrixException;
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

    Feature feature = getBestFeature(features, instances, outputQuant);

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

    return outputQuant.size() <= 1;
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

  protected Feature getBestFeature(Features features, Instances instances, Map<String, Integer> outputQuant) {

    Double collectionEntropy = Entropy.calculateEntropy(outputQuant, instances.getNumberOfInstances());

    FeatureValueDistributionMatrix featureValueDist = getFeatureValueDistributionMatrix(features, instances, outputQuant);

    return null;
  }

  protected FeatureValueDistributionMatrix getFeatureValueDistributionMatrix(Features features, Instances instances, Map<String, Integer> outputQuant) {

    FeatureValueDistributionMatrix distributionMatrix = new FeatureValueDistributionMatrixImpl();

    for(Feature feature : features.getFeatures()) {

      distributionMatrix.setNewFeature(feature.getName());

      updateFeatureValueMatrix(instances, distributionMatrix, feature);
    }

    return distributionMatrix;
  }

  private void updateFeatureValueMatrix(Instances instances, FeatureValueDistributionMatrix distributionMatrix, Feature feature) {
    for(Instance instance : instances.getInstances()) {

      try {

        distributionMatrix.addFeatureValueQuantity(feature.getName(), instance.getFeatureValue(feature.getName()));
      } catch (InvalidFeatureInDistributionMatrixException e) {

        e.printStackTrace();
      }
    }
  }
}
