package cse.java2.project.controller;

import cse.java2.project.util.JsonParser;
import cse.java2.project.util.Pair;
import cse.java2.project.util.PairParser;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Users")
public class UsersController {
    /**
     *
     * @return 降序排列，被观看最多的
     */
    @GetMapping("/Q1")
    public List<Pair> participantsDis(){
        return PairParser.MapToList(JsonParser.participantsDis());
    }
    /**
     *
     * @return 按照活跃度降序排列的用户，name是用户名，value是活跃度
     */
    @GetMapping("/Q2")
    public List<Pair> activityEvaluation(){
        return PairParser.MapToList(JsonParser.activityEvaluation()).stream().sorted((e1, e2) -> e2.getValue() - e1.getValue()).toList();
    }
}
