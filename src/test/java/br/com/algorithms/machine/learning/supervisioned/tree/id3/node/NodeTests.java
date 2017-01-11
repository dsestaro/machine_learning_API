package br.com.algorithms.machine.learning.supervisioned.tree.id3.node;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NodeTests {

  private NodeImpl node;

  @Before
  public void instantiateNode() {

    this.node = new NodeImpl();

    this.node.setNodeType(NodeType.FEATURE_NODE);
    this.node.setOutput("Teste");
  }

  @Test
  public void testSetNodeType() {

    this.node.setNodeType(NodeType.FEATURE_NODE);
  }

  @Test
  public void testGetNodeType() {

    assertEquals(NodeType.FEATURE_NODE, this.node.getNodeType());
  }

  @Test
  public void testSetOutput() {

    this.node.setOutput("Teste");
  }

  @Test
  public void testGetOutput() {

    assertEquals("Teste", this.node.getOutput());
  }
}
