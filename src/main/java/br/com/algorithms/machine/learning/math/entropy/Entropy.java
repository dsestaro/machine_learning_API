package br.com.algorithms.machine.learning.math.entropy;

import br.com.algorithms.machine.learning.math.entropy.exception.InvalidEntropyParametersException;

import java.util.Map;

public class Entropy {

  public static String INVALID_MAP = "The map of quantities can not be null.";
  public static String INVALID_QUANTITY = "The quantity of instances should be greate than 0.";

  protected Entropy() {

  }

  public static Double calculateEntropy(Map<String, Integer> outputQuant, int size) {

    validateParameters(outputQuant, size);

    Double entropy = 0.0;

    for(String key : outputQuant.keySet()) {

      double percentage = calcPercentage(outputQuant.get(key), size);

      entropy += (-1 * percentage) * log(percentage, 2);
    }

    return entropy;
  }

  private static double log(double x, int base) {

    return (Math.log(x) / Math.log(base));
  }

  private static double calcPercentage(double quant, double total) {

    return quant / total;
  }

  private static void validateParameters(Map<String, Integer> outputQuant, int size) {

    if(outputQuant == null) {

      throw new InvalidEntropyParametersException(INVALID_MAP);
    }

    if(size < 1) {

      throw new InvalidEntropyParametersException(INVALID_QUANTITY);
    }
  }
}
