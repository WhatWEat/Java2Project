package cse.java2.project.controller;

import cse.java2.project.util.JsonParser;
import cse.java2.project.util.Pair;
import cse.java2.project.util.PairParser;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Tags")
public class TagsController {

    /**
     * @return 升序的出现次数的tags
     */
    @GetMapping("/Q1")
    public List<Pair> appearMostWithJava() {
        return PairParser.MapToList(JsonParser.appearMostWithJava()).stream()
            .sorted((p1, p2) -> p1.getValue() - p2.getValue()).toList();
    }

    /**
     * @return 降序排列，被点赞最多的
     */
    @GetMapping("/Q2")
    public List<Pair> MostUpCom() {
        return JsonParser.MostUpCom();
    }

    /**
     * @return 降序排列，被观看最多的
     */
    @GetMapping("/Q3")
    public List<Pair> MostViewCom() {
        return JsonParser.MostViewCom();
    }

}
