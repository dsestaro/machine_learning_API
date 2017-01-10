package br.com.algorithms.machine.learning.math.entropy;

import java.util.Map;

public class Entropy {

  public static Double calculateEntropy(Map<String, Integer> outputQuant, int size) {

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
}
