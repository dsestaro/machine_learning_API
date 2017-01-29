package br.com.algorithms.machine.learning.supervisioned.tree.id3;

import br.com.algorithms.machine.learning.math.entropy.Entropy;
import br.com.algorithms.machine.learning.math.information.gain.InformationGain;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature.Feature;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature.Features;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature.FeaturesImpl;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.Instances;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.exception.feature.EmptyFeaturesException;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.exception.instance.EmptyInstancesException;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.node.Node;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.node.NodeBuilder;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.node.NodeType;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.TreeUtils;
import br.com.algorithms.machine.learning.supervisioned.tree.utils.exception.InvalidFeatureValueException;

import java.util.Map;

public class TreeImpl implements Tree {

  public Node buildDecisionTree(Features features, Instances instances) throws EmptyFeaturesException, EmptyInstancesException, InvalidFeatureValueException {

    validateParameters(features, instances);

    Map<String, Integer> outputQuant = TreeUtils.calculateQuantityOutput(instances);

    if(isOnlyOnePossibleOutput(outputQuant)) {
      return buildLeaf(outputQuant);
    }

    Feature bestFeature = getBestFeature(features, instances, outputQuant);

    Features filteredFeatures = getRemaingFeatures(features, bestFeature);

    Node root = buildFeatureNode(bestFeature);

    Map<String, Instances> instancesByFeatureValue = populateExamples(bestFeature, instances);

    buildRemainTreeNodes(bestFeature, filteredFeatures, root, instancesByFeatureValue);

    return root;
  }

  private void validateParameters(Features features, Instances instances) throws EmptyFeaturesException, EmptyInstancesException {

    if(features == null || features.getNumberOfFeatures() == 0) {

      throw new EmptyFeaturesException();
    }

    if(instances == null || instances.getNumberOfInstances() == 0) {

      throw new EmptyInstancesException();
    }
  }

  protected boolean isOnlyOnePossibleOutput(Map<String, Integer> outputQuant) {

    return outputQuant.size() <= 1;
  }

  private Node buildLeaf(Map<String, Integer> outputQuant) {

    NodeBuilder nodeBuilder = new NodeBuilder();

    nodeBuilder.setNodeType(NodeType.OUTPUT_LEAF_NODE);
    nodeBuilder.setOutput(getLeafOutput(outputQuant));

    return nodeBuilder.buildNode();
  }

  private String getLeafOutput(Map<String, Integer> outputQuant) {

    return outputQuant.keySet().iterator().next();
  }

  protected Feature getBestFeature(Features features, Instances instances, Map<String, Integer> outputQuant) throws InvalidFeatureValueException {

    Double entropy = Entropy.calculateEntropy(outputQuant, instances.getNumberOfInstances());
    Double bestInformationGain = 0.0;
    Feature bestFeature = null;

    for(Feature feature : features.getFeatures()) {

      Map<String, Instances> quantityByFeatureValue = TreeUtils.generateMapOfInstancesByFeatureValue(instances, feature);

      Double featureInformationGain = InformationGain.calculateInformationGain(entropy, feature, quantityByFeatureValue, instances.getNumberOfInstances());

      if(bestInformationGain < featureInformationGain) {
        bestInformationGain = featureInformationGain;
        bestFeature = feature;
      }
    }

    return bestFeature;
  }

  protected Features getRemaingFeatures(Features features, Feature bestFeature) {

    Features filteredList = new FeaturesImpl();

    for(Feature feature : features.getFeatures()) {

      if(!feature.getName().equals(bestFeature.getName())) {

        filteredList.addFeature(feature);
      }
    }

    return filteredList;
  }

  protected Node buildFeatureNode(Feature bestFeature) {

    NodeBuilder nodeBuilder = new NodeBuilder();

    nodeBuilder.setNodeType(NodeType.FEATURE_NODE);
    nodeBuilder.setNodeFeature(bestFeature);

    return nodeBuilder.buildNode();
  }

  protected Map<String, Instances> populateExamples(Feature bestFeature, Instances instances) throws InvalidFeatureValueException {

    Map<String, Instances> instancesByFeatureValue = TreeUtils.generateMapOfInstancesByFeatureValue(instances, bestFeature);

    return  instancesByFeatureValue;
  }

  private void buildRemainTreeNodes(Feature bestFeature, Features filteredFeatures, Node root, Map<String, Instances> instancesByFeatureValue) throws EmptyFeaturesException, EmptyInstancesException, InvalidFeatureValueException {
    for(String featureValue : bestFeature.getValues()) {

      Node childNode = buildDecisionTree(filteredFeatures, instancesByFeatureValue.get(featureValue));

      root.setNewChildNode(featureValue, childNode);
    }
  }
}
