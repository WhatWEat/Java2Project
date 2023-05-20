package cse.java2.project.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import cse.java2.project.Mapper.OwnerMapper;
import cse.java2.project.Mapper.QuestionMapper;
import cse.java2.project.entities.Owner;
import cse.java2.project.entities.Question;
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
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private OwnerMapper ownerMapper;

    @GetMapping("/import")
    public String importData() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/resources/jsons/questions.json")));
        String line;
        StringBuilder response = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            response.append(line).append('\n');
        }
        reader.close();
        JSONArray itemsArray = JSON.parseArray(response.toString());
        HashSet<Long> account_id_map = new HashSet<>();
        HashSet<Long> question_id_map = new HashSet<>();
        List<Question> questions = itemsArray.toJavaList(Question.class);
        for (Question q: questions) {
            Owner owner = q.getOwner();
            if(!account_id_map.contains(owner.getAccount_id())){
                ownerMapper.insert(owner);
                account_id_map.add(owner.getAccount_id());
            }
            if(!question_id_map.contains(q.getQuestion_id())){
                q.setOwner_account_id(owner.getAccount_id());
                questionMapper.insert(q);
                q.getTags().forEach(t -> {
                    questionMapper.insertQuestionTag(q.getQuestion_id(), t);
                });
                question_id_map.add(q.getQuestion_id());
            }

        }
        return "success";
    }
}
