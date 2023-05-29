package cse.java2.project.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import cse.java2.project.Mapper.OwnerMapper;
import cse.java2.project.Mapper.QuestionMapper;
import cse.java2.project.entities.Owner;
import cse.java2.project.entities.Question;
import cse.java2.project.util.JsonParser;
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


    @GetMapping("/NumberOfAnswers/Q1")
    public float noAnsCount(){
        return JsonParser.noAnsCount();
    }

    @GetMapping("/NumberOfAnswers/Q2/avg")
    public float avgAnsCount(){
        return JsonParser.avgAnsCount();
    }

    @GetMapping("/NumberOfAnswers/Q2/max")
    public int maxAnsCount(){
        return JsonParser.maxAnsCount();
    }

    @GetMapping("/NumberOfAnswers/Q3")
    public Map<String, Float> ansDistribution(){
        return JsonParser.ansDistribution();
    }

    @GetMapping("/AcceptedAnswers/Q1")
    public float acceptAnsPercent(){
        return JsonParser.acceptAnsPercent();
    }

    @GetMapping("/AcceptedAnswers/Q2")
    public Map<String, Float> resolutionTimeDis(){
        return JsonParser.resolutionTimeDis();
    }

    @GetMapping("/AcceptedAnswers/Q3")
    public float ansOverAccept(){
        return JsonParser.ansOverAccept();
    }


}
