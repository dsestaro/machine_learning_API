package br.com.algorithms.machine.learning.supervisioned.tree.id3.node;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class NodeTypeTests {

  @Test
  public void testIfNodeTypeFeatureNodeExists() {
    assertNotNull(NodeType.valueOf("FEATURE_NODE"));
  }

  @Test
  public void testIfNodeTypeOutputNodeExists() {
    assertNotNull(NodeType.valueOf("OUTPUT_LEAF_NODE"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfAnInvalidNodeExists() {
    assertNull(NodeType.valueOf("INVALID_NODE"));
  }
}
