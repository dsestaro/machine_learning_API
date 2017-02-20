package br.com.algorithms.machine.learning.supervisioned.tree.id3.node;

import br.com.algorithms.machine.learning.exception.parameters.InvalidParameterException;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature.Feature;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature.FeatureImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class NodeTests {

  @Test
  public void testNodeInstantiation() {

    Node node = new NodeImpl();

    assertNotNull(node);
  }

  @Test
  public void testSetGetNodeType() {

    NodeType nodeType = NodeType.FEATURE_NODE;

    NodeImpl node = new NodeImpl();

    node.setNodeType(nodeType);

    assertEquals(nodeType, node.getNodeType());
  }

  @Test
  public void testSetNodeType_NullNodeTye() {

    NodeType nodeType = null;

    NodeImpl node = new NodeImpl();

    try {

      node.setNodeType(nodeType);

      fail("InvalidParameterException should be thrown.");
    } catch (InvalidParameterException e) {

      assertEquals(NodeImpl.INVALID_NODE_TYPE, e.getMessage());
    }

    assertEquals(nodeType, node.getNodeType());
  }

  @Test
  public void testSetGetOutput() {

    String output = "Teste";

    NodeImpl node = new NodeImpl();

    node.setOutput(output);

    assertEquals(output, node.getOutput());
  }

  @Test
  public void testSetGetOutput_NullOutput() {

    String output = null;

    NodeImpl node = new NodeImpl();

    try {

      node.setOutput(output);

      fail("InvalidParameterException should be thrown.");
    } catch (InvalidParameterException e) {

      assertEquals(NodeImpl.INVALID_OUTPUT, e.getMessage());
    }
  }

  @Test
  public void testSetGetOutput_EmptyOutput() {

    String output = "";

    NodeImpl node = new NodeImpl();

    try {

      node.setOutput(output);

      fail("InvalidParameterException should be thrown.");
    } catch (InvalidParameterException e) {

      assertEquals(NodeImpl.INVALID_OUTPUT, e.getMessage());
    }
  }

  @Test
  public void testSetGetFeature() {

    String featureName = "Outlook";

    NodeImpl node = new NodeImpl();

    Feature feature = new FeatureImpl(featureName);

    node.setFeature(feature);

    assertEquals(featureName, node.getFeature().getName());
  }

  @Test
  public void testSetGetNewChildNode() {

    String featureValue = "Weak";
    String output = "Yes";

    NodeImpl childNode = new NodeImpl();
    NodeImpl parentNode = new NodeImpl();

    childNode.setOutput(output);

    parentNode.setNewChildNode(featureValue, childNode);

    assertEquals(output, parentNode.getChildNode(featureValue).getOutput());
  }
}
