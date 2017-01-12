package br.com.algorithms.machine.learning.supervisioned.tree.id3.node;

import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature.Feature;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature.FeatureImpl;
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

    Feature feature = new FeatureImpl("Outlook");

    this.node.setFeature(feature);

    NodeImpl node = new NodeImpl();
    node.setOutput("Outlook");

    this.node.setNewChildNode("Weak", node);
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

  @Test
  public void testSetFeature() {

    Feature feature = new FeatureImpl("Outlook");

    this.node.setFeature(feature);
  }

  @Test
  public void testGetFeature() {

    assertEquals("Outlook", this.node.getFeature().getName());
  }

  @Test
  public void testSetNewChildNode() {

    NodeImpl node = new NodeImpl();
    node.setOutput("Outlook");

    this.node.setNewChildNode("Weak", node);
  }

  @Test
  public void testGetChildNode() {

    assertEquals("Outlook", this.node.getChildNode("Weak").getOutput());
  }
}
