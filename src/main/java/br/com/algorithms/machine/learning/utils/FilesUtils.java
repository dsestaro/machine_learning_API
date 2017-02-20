package br.com.algorithms.machine.learning.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FilesUtils {

  private static final String SEPARATOR = ",";

  public static List<List<String>> readFile(String path) throws IOException, URISyntaxException {

    List<List<String>> results = new ArrayList<List<String>>();

    Stream<String> stream = Files.lines(Paths.get(ClassLoader.getSystemResource(path).toURI()));

    stream.forEach(item -> {

      List<String> instance = Arrays.asList(item.split(SEPARATOR));

      results.add(instance);
    });

    return results;
  }
}
