package br.com.algorithms.machine.learning.supervisioned.tree.id3.node;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NodeBuilderTests {

  @Test
  public void testCreationOfANodeObject() {

    NodeBuilder nodeBuilder = new NodeBuilder();

    nodeBuilder.setNodeType(NodeType.FEATURE_NODE);
    nodeBuilder.setOutput("Yes");

    Node node = nodeBuilder.buildNode();

    assertEquals(NodeType.FEATURE_NODE, node.getNodeType());
    assertEquals("Yes", node.getOutput());
  }
}
