package br.com.algorithms.machine.learning.math.entropy;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class EntropyTests {

  @Test
  public void testEntropyInstantiation() {

    new Entropy();
  }

  @Test
  public void testEntropyCalculation() {

    Map<String, Integer> quantities = new HashMap<String, Integer>();

    quantities.put("Positive", 9);
    quantities.put("Negatives", 5);

    Double entropy = Entropy.calculateEntropy(quantities, 14);

    assertEquals(new Double(0.9402859586706309), entropy);
  }

  @Test
  public void testEntropyCalculationWithADifferentSet() {

    Map<String, Integer> quantities = new HashMap<String, Integer>();

    quantities.put("Positive", 9);
    quantities.put("Negatives", 9);

    Double entropy = Entropy.calculateEntropy(quantities, 18);

    assertEquals(new Double(1), entropy);
  }

  @Test
  public void testEntropyCalculationWithAnOneDimensionalSet() {

    Map<String, Integer> quantities = new HashMap<String, Integer>();

    quantities.put("Positive", 10);

    Double entropy = Entropy.calculateEntropy(quantities, 10);

    assertEquals(new Double(0), entropy);
  }
}
