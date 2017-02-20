package br.com.algorithms.machine.learning.utils;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FileUtilsTests {

  @Test
  public void testReadFile() throws IOException, URISyntaxException {

    String path = "datasets/PlayTennis";

    int numberOfInstances = 15;
    int sizeOfInstance = 5;
    int headerPosition = 0;
    int instancePosition = 5;

    List<List<String>> results = FilesUtils.readFile(path);

    assertEquals(numberOfInstances, results.size());
    assertEquals(sizeOfInstance, results.get(headerPosition).size());
    assertEquals(sizeOfInstance, results.get(instancePosition).size());
  }
}
