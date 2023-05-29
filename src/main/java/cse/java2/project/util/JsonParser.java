package cse.java2.project.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import cse.java2.project.entities.Answer;
import cse.java2.project.entities.Question;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class JsonParser {
    public static final String QuestionJsonPath = "src/main/resources/jsons/questions.json";
    public static JSONArray jsonQuestionArray;
    public static List<Question> questions;
    public static Map<Long, List<Answer>> ansOfQues;
    public static Map<Long, Question> idOfQue;
    private JsonParser(){};
    static {
        try {
            // 读取JSON文件内容为字符串
            String jsonStr = new String(Files.readAllBytes(Paths.get("src/main/resources/jsons/questions.json")));

            // 将JSON字符串转换为JSONArray对象
            jsonQuestionArray = JSON.parseArray(jsonStr);
            questions = jsonQuestionArray.toJavaList(Question.class);
            idOfQue = new HashMap<>();
            questions.forEach(q -> {
                idOfQue.put(q.getQuestion_id(), q);
            });
            // 读取answer
            jsonStr = new String(Files.readAllBytes(Paths.get("src/main/resources/jsons/answers.json")));
            jsonQuestionArray = JSON.parseArray(jsonStr);
            List<Answer> ans = jsonQuestionArray.toJavaList(Answer.class);
            ansOfQues = new HashMap<>();
            ans.forEach(a -> {
                if(ansOfQues.containsKey(a.getQuestion_id())){
                    ansOfQues.get(a.getQuestion_id()).add(a);
                }
                else{
                    List<Answer> answers = new ArrayList<>();
                    answers.add(a);
                    ansOfQues.put(a.getQuestion_id(), answers);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static float noAnsCount(){
        int totalCount = questions.size();
        AtomicInteger noAnsCnt = new AtomicInteger(0);
        questions.forEach(e -> {
            if(e.getAnswer_count() == 0){
                noAnsCnt.getAndIncrement();
            }
        });
        return (float) noAnsCnt.get()/totalCount;
    }

    public static float avgAnsCount(){
        int totalCount = questions.size();
        AtomicInteger totalAns = new AtomicInteger(0);
        questions.forEach(e -> {
            totalAns.addAndGet(e.getAnswer_count());
        });
        return (float) totalAns.get()/totalCount;
    }

    public static int maxAnsCount(){
        AtomicInteger max = new AtomicInteger(0);
        questions.forEach(e -> {
            max.set(Math.max(max.get(), e.getAnswer_count()));
        });
        return max.get();
    }

    public static float acceptAnsPercent(){
        int totalCount = questions.size();
        AtomicInteger acc = new AtomicInteger(0);
        questions.forEach(e -> {
            acc.getAndIncrement();
        });
        return (float) acc.get() / totalCount;
    }

    public static Map<String, Float> resolutionTimeDis(){
        int lessThanOneDay = 0,
            lessThanTwoDay = 0,
            lessThanAWeek = 0,
            longerThanAWeek = 0;
        Map<String, Float> result = new HashMap<>();
        for (Long l: idOfQue.keySet()) {
            Question q = idOfQue.get(l);
            if(q.isIs_answered()){
                List<Answer> answers = ansOfQues.get(l);
                for (Answer answer : answers) {
                    if(answer.isIs_accepted()){
                        final float days = (float) (answer.getCreation_date() - q.getCreation_date()) / 86400;
                        if(days < 1){
                            lessThanOneDay += 1;
                        }
                        else if(days < 2){
                            lessThanTwoDay += 1;

                        } else if (days < 7) {
                            lessThanAWeek += 1;

                        }
                        else{
                            longerThanAWeek += 1;
                        }
                    }
                }
            }
        }
        int sum = (lessThanOneDay+lessThanTwoDay+lessThanAWeek+longerThanAWeek);
        result.put("lessThanOneDay", (float)lessThanOneDay/sum);
        result.put("lessThanTwoDay", (float)lessThanTwoDay/sum);
        result.put("lessThanAWeek", (float)lessThanAWeek/sum);
        result.put("longerThanAWeek", (float)longerThanAWeek/sum);
        return result;
    }

    public static float ansOverAccept(){
        int over = 0, total = 0;
        for (Long l: idOfQue.keySet()) {
            Question q = idOfQue.get(l);
            if(q.isIs_answered()){
                total++;
                int accVote = 0, maxVote = 0;
                for (Answer answer : ansOfQues.get(l)) {
                    if(answer.isIs_accepted()){
                        accVote = answer.getUp_vote_count();
                    }
                    else {
                        maxVote = Math.max(maxVote, answer.getUp_vote_count());
                    }
                }
                if(maxVote > accVote){
                    over++;
                }
            }
        }
        return (float) over / total;
    }

}
