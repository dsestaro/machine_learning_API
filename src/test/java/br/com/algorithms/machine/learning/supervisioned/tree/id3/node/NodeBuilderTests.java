package br.com.algorithms.machine.learning.supervisioned.tree.id3.node;

import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature.Feature;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature.FeatureImpl;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.exception.InvalidNodeInformationException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class NodeBuilderTests {

  @Test
  public void testNodeInstantiation_OutputLeafNodeObject() {

    String expectedOutput = "Yes";
    NodeType expectedNodeType = NodeType.OUTPUT_LEAF_NODE;

    NodeBuilder nodeBuilder = new NodeBuilder();

    nodeBuilder.setNodeType(expectedNodeType);
    nodeBuilder.setOutput(expectedOutput);

    Node node = nodeBuilder.buildNode();

    assertEquals(expectedNodeType, node.getNodeType());
    assertEquals(expectedOutput, node.getOutput());
  }

  @Test
  public void testNodeInstantiation_FeatureNodeObject() {

    String featureName = "Outlook";
    NodeType expectedNodeType = NodeType.FEATURE_NODE;

    Feature feature = new FeatureImpl(featureName);

    NodeBuilder nodeBuilder = new NodeBuilder();

    nodeBuilder.setNodeType(expectedNodeType);
    nodeBuilder.setNodeFeature(feature);

    Node node = nodeBuilder.buildNode();

    assertEquals(expectedNodeType, node.getNodeType());
  }

  @Test
  public void testNodeInstantiation_MissingNodeTypeValue() {

    NodeBuilder nodeBuilder = new NodeBuilder();

    try {

      Node node = nodeBuilder.buildNode();

      fail("InvalidNodeInformationException should be thrown.");
    } catch (InvalidNodeInformationException e) {

      assertEquals(NodeBuilder.INVALID_NODE_TYPE, e.getMessage());
    }
  }

  @Test
  public void testNodeInstantiation_MissingOutputValue() {

    NodeBuilder nodeBuilder = new NodeBuilder();
    NodeType nodeType = NodeType.OUTPUT_LEAF_NODE;

    nodeBuilder.setNodeType(nodeType);

    try {

      Node node = nodeBuilder.buildNode();

      fail("InvalidNodeInformationException should be thrown.");
    } catch (InvalidNodeInformationException e) {

      assertEquals(NodeBuilder.INVALID_OUTPUT_VALUE, e.getMessage());
    }
  }

  @Test
  public void testNodeInstantiation_EmptyOutputValue() {

    NodeBuilder nodeBuilder = new NodeBuilder();
    NodeType nodeType = NodeType.OUTPUT_LEAF_NODE;
    String output = "";

    nodeBuilder.setNodeType(nodeType);
    nodeBuilder.setOutput(output);

    try {

      Node node = nodeBuilder.buildNode();

      fail("InvalidNodeInformationException should be thrown.");
    } catch (InvalidNodeInformationException e) {

      assertEquals(NodeBuilder.INVALID_OUTPUT_VALUE, e.getMessage());
    }
  }

  @Test
  public void testNodeInstantiation_MissingFeatureValue() {

    NodeBuilder nodeBuilder = new NodeBuilder();
    NodeType nodeType = NodeType.FEATURE_NODE;

    nodeBuilder.setNodeType(nodeType);

    try {

      Node node = nodeBuilder.buildNode();

      fail("InvalidNodeInformationException should be thrown.");
    } catch (InvalidNodeInformationException e) {

      assertEquals(NodeBuilder.INVALID_FEATURE_VALUE, e.getMessage());
    }
  }
}
