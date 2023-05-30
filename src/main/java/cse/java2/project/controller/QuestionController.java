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
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.swing.plaf.PanelUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/NumberOfAnswers")
public class QuestionController {
    /**
     *
     * @return [0]是没答案的数量，[1]是总共问题的数量
     */
    @GetMapping("/Q1")

    public List<Integer> noAnsCount(){
        return JsonParser.noAnsCount();
    }
    /**
     *
     * @return [0]总共的答案数量，[1]是总共问题的数量
     */
    @GetMapping("/Q2/avg")
    public List<Integer>  avgAnsCount(){
        return JsonParser.avgAnsCount();
    }
    /**
     *
     * @return 最多回答的数量
     */
    @GetMapping("/Q2/max")
    public int maxAnsCount(){
        return JsonParser.maxAnsCount();
    }

    /**
     *
     * @return 回答数量分布
     */
    @GetMapping("/Q3")
    public List<Pair> ansDistribution(){
        return PairParser.MapToList(JsonParser.ansDistribution()).stream().sorted(Comparator.comparingInt(e -> Integer.parseInt(e.getName()))).toList();
    }

}
