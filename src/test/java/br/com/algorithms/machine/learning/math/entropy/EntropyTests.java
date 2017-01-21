package br.com.algorithms.machine.learning.math.entropy;

import br.com.algorithms.machine.learning.math.entropy.exception.InvalidEntropyParametersException;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EntropyTests {

  @Test
  public void testEntropyInstantiation() {

    Entropy entropy = new Entropy();

    assertNotNull(entropy);
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
  public void testEntropyCalculation_DifferentSet() {

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
  public void testEntropyCalculation_OneDimensionalSet() {

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
  public void testEntropyCalculation_EmptySet() {

    int totalNumberOfOutputs = 0;

    Double expectedEntropy = 0.0;

    Map<String, Integer> quantities = new HashMap<String, Integer>();

    Double entropy = Entropy.calculateEntropy(quantities, totalNumberOfOutputs);

    assertEquals(expectedEntropy, entropy);
  }

  @Test
  public void testEntropyCalculation_NullSet() {

    int totalNumberOfOutputs = 0;

    Double expectedEntropy = 0.0;

    Map<String, Integer> quantities = null;

    try {

      Double entropy = Entropy.calculateEntropy(quantities, totalNumberOfOutputs);
    } catch (InvalidEntropyParametersException e) {

      assertEquals(Entropy.INVALID_MAP, e.getMessage());
    }
  }

  @Test
  public void testEntropyCalculation_ZeroNumberOfOutputsWithVAluesOnMap() {

    String nameOfFirstOutput = "Positive";

    int numberOfFirstOutput = 0;

    int totalNumberOfOutputs = numberOfFirstOutput;

    Double expectedEntropy = 0.0;

    Map<String, Integer> quantities = new HashMap<String, Integer>();

    quantities.put(nameOfFirstOutput, numberOfFirstOutput);

    try {

      Double entropy = Entropy.calculateEntropy(quantities, totalNumberOfOutputs);
    } catch (InvalidEntropyParametersException e) {

      assertEquals(Entropy.INVALID_QUANTITY, e.getMessage());
    }
  }
}
