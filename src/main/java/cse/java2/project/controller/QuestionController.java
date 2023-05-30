package cse.java2.project.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import cse.java2.project.Mapper.OwnerMapper;
import cse.java2.project.Mapper.QuestionMapper;
import cse.java2.project.entities.Owner;
import cse.java2.project.entities.Question;
import cse.java2.project.util.JsonParser;
import cse.java2.project.util.Pair;
import cse.java2.project.util.PairParser;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.swing.plaf.PanelUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {
    /**
     *
     * @return [0]是没答案的数量，[1]是总共问题的数量
     */
    @GetMapping("/NumberOfAnswers/Q1")

    public List<Integer> noAnsCount(){
        return JsonParser.noAnsCount();
    }
    /**
     *
     * @return [0]总共的答案数量，[1]是总共问题的数量
     */
    @GetMapping("/NumberOfAnswers/Q2/avg")
    public List<Integer>  avgAnsCount(){
        return JsonParser.avgAnsCount();
    }
    /**
     *
     * @return 最多回答的数量
     */
    @GetMapping("/NumberOfAnswers/Q2/max")
    public int maxAnsCount(){
        return JsonParser.maxAnsCount();
    }

    /**
     *
     * @return 回答数量分布
     */
    @GetMapping("/NumberOfAnswers/Q3")
    public List<Pair> ansDistribution(){
        return PairParser.MapToList(JsonParser.ansDistribution());
    }

    /**
     *
     * @return [0]是有isAnswered的数量，[1]是总共的问题数量
     */
    @GetMapping("/AcceptedAnswers/Q1")
    public List<Integer> acceptAnsCnt(){
        return JsonParser.acceptAnsCnt();
    }
    /**
     *
     * @return value是时间间距，name是时常陈述
     */
    @GetMapping("/AcceptedAnswers/Q2")
    public List<Pair> resolutionTimeDis(){
        return JsonParser.resolutionTimeDis();
    }
    /**
     *
     * @return [0]是有over的数量，[1]是总共被回答的问题数量
     */
    @GetMapping("/AcceptedAnswers/Q3")
    public List<Integer> ansOverAccept(){
        return JsonParser.ansOverAccept();
    }

    /**
     *
     * @return 升序的出现次数的tags
     */
    @GetMapping("/Tags/Q1")
    public List<Pair> appearMostWithJava(){
        return PairParser.MapToList(JsonParser.appearMostWithJava()).stream().sorted((p1, p2) -> p1.getValue()-p2.getValue()).toList();
    }

    /**
     *
     * @return 降序排列，被点赞最多的
     */
    @GetMapping("/Tags/Q2")
    public List<Pair> MostUpCom(){
        return JsonParser.MostUpCom();
    }
    /**
     *
     * @return 降序排列，被观看最多的
     */
    @GetMapping("/Tags/Q3")
    public List<Pair> MostViewCom(){
        return JsonParser.MostViewCom();
    }

    /**
     *
     * @return 降序排列，被观看最多的
     */
    @GetMapping("/Users/Q1")
    public List<Pair> participantsDis(){
        return PairParser.MapToList(JsonParser.participantsDis());
    }

}
