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

}
