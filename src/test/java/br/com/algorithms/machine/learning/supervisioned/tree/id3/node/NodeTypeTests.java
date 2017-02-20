package br.com.algorithms.machine.learning.supervisioned.tree.id3.node;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class NodeTypeTests {

  @Test
  public void testIfNodeTypeInstantiatin_FeatureNode() {

    String nodeType = "FEATURE_NODE";

    assertNotNull(NodeType.valueOf(nodeType));
  }

  @Test
  public void testIfNodeTypeInstantiatin_OutputLeadNode() {

    String nodeType = "OUTPUT_LEAF_NODE";

    assertNotNull(NodeType.valueOf(nodeType));
  }
}
