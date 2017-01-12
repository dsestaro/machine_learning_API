package br.com.algorithms.machine.learning.supervisioned.tree.id3.node;

import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature.FeatureImpl;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.exception.InvalidNodeInformationException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NodeBuilderTests {

  @Test
  public void testCreationOfAValidOutputLeafNodeObject() {

    NodeBuilder nodeBuilder = new NodeBuilder();

    nodeBuilder.setNodeType(NodeType.OUTPUT_LEAF_NODE);
    nodeBuilder.setOutput("Yes");

    Node node = nodeBuilder.buildNode();

    assertEquals(NodeType.OUTPUT_LEAF_NODE, node.getNodeType());
    assertEquals("Yes", node.getOutput());
  }

  @Test
  public void testCreationOfAValidFeatureNodeObject() {

    NodeBuilder nodeBuilder = new NodeBuilder();

    nodeBuilder.setNodeType(NodeType.FEATURE_NODE);
    nodeBuilder.setNodeFeature(new FeatureImpl("Outlook"));

    Node node = nodeBuilder.buildNode();

    assertEquals(NodeType.FEATURE_NODE, node.getNodeType());
  }

  @Test(expected = InvalidNodeInformationException.class)
  public void testCreationOfANodeObjectWithMissingNodeTypeValue() {

    NodeBuilder nodeBuilder = new NodeBuilder();

    Node node = nodeBuilder.buildNode();
  }

  @Test(expected = InvalidNodeInformationException.class)
  public void testCreationOfALeafNodeObjectWithMissingOutputValue() {

    NodeBuilder nodeBuilder = new NodeBuilder();

    nodeBuilder.setNodeType(NodeType.OUTPUT_LEAF_NODE);

    Node node = nodeBuilder.buildNode();
  }

  @Test(expected = InvalidNodeInformationException.class)
  public void testCreationOfAFeatureNodeObjectWithMissingOutputValue() {

    NodeBuilder nodeBuilder = new NodeBuilder();

    nodeBuilder.setNodeType(NodeType.FEATURE_NODE);

    Node node = nodeBuilder.buildNode();
  }
}
