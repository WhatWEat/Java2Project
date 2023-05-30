package cse.java2.project.controller;

import cse.java2.project.util.JsonParser;
import cse.java2.project.util.Pair;
import cse.java2.project.util.PairParser;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/JavaAPI")
public class JavaAPIController {

  /**
   * @return 升序的出现次数的tags
   */
  @GetMapping("/Q1")
  public List<Pair> frequentlyDiscussed() {
    return PairParser.MapToList(JsonParser.frequentlyDiscussed()).stream()
        .sorted((p1, p2) -> p2.getValue() - p1.getValue()).toList();
  }
}
