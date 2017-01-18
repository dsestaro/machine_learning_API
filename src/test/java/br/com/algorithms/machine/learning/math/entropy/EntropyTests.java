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

    String nameOfFirstOutput = "Positive";
    String nameOfSecondOutput = "Negative";

    int numberOfFirstOutput = 9;
    int numberOfSecondOutput = 5;

    int totalNumberOfOutputs = numberOfFirstOutput + numberOfSecondOutput;

    Double expectedEntropy = 0.9402859586706309;

    Map<String, Integer> quantities = new HashMap<String, Integer>();

    quantities.put(nameOfFirstOutput, numberOfFirstOutput);
    quantities.put(nameOfSecondOutput, numberOfSecondOutput);

    Double entropy = Entropy.calculateEntropy(quantities, totalNumberOfOutputs);

    assertEquals(expectedEntropy, entropy);
  }

  @Test
  public void testEntropyCalculationWithADifferentSet() {

    String nameOfFirstOutput = "Positive";
    String nameOfSecondOutput = "Negative";

    int numberOfFirstOutput = 9;
    int numberOfSecondOutput = 9;

    int totalNumberOfOutputs = numberOfFirstOutput + numberOfSecondOutput;

    Double expectedEntropy = 1.0;

    Map<String, Integer> quantities = new HashMap<String, Integer>();

    quantities.put(nameOfFirstOutput, numberOfFirstOutput);
    quantities.put(nameOfSecondOutput, numberOfSecondOutput);

    Double entropy = Entropy.calculateEntropy(quantities, totalNumberOfOutputs);

    assertEquals(expectedEntropy, entropy);
  }

  @Test
  public void testEntropyCalculationWithAnOneDimensionalSet() {

    String nameOfFirstOutput = "Positive";

    int numberOfFirstOutput = 10;

    int totalNumberOfOutputs = numberOfFirstOutput;

    Double expectedEntropy = 0.0;

    Map<String, Integer> quantities = new HashMap<String, Integer>();

    quantities.put(nameOfFirstOutput, numberOfFirstOutput);

    Double entropy = Entropy.calculateEntropy(quantities, totalNumberOfOutputs);

    assertEquals(expectedEntropy, entropy);
  }

  @Test
  public void testEntropyCalculationWithAnEmptySet() {

    int totalNumberOfOutputs = 0;

    Double expectedEntropy = 0.0;

    Map<String, Integer> quantities = new HashMap<String, Integer>();

    Double entropy = Entropy.calculateEntropy(quantities, totalNumberOfOutputs);

    assertEquals(expectedEntropy, entropy);
  }
}
