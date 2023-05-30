package cse.java2.project.controller;

import cse.java2.project.util.JsonParser;
import cse.java2.project.util.Pair;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/AcceptedAnswers")
public class AcceptedAnsController {

  /**
   * @return [0]是有isAnswered的数量，[1]是总共的问题数量
   */
  @GetMapping("/Q1")
  public List<Integer> acceptAnsCnt() {
    return JsonParser.acceptAnsCnt();
  }

  /**
   * @return value是时间间距，name是时常陈述
   */
  @GetMapping("/Q2")
  public List<Pair> resolutionTimeDis() {
    return JsonParser.resolutionTimeDis();
  }

  /**
   * @return [0]是有over的数量，[1]是总共被回答的问题数量
   */
  @GetMapping("/Q3")
  public List<Integer> ansOverAccept() {
    return JsonParser.ansOverAccept();
  }

}
