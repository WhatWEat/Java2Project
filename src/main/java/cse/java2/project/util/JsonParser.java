package cse.java2.project.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import cse.java2.project.entities.Question;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class JsonParser {
    public static final String QuestionJsonPath = "src/main/resources/jsons/questions.json";
    public static JSONArray jsonQuestionArray;
    public static List<Question> questions;
    private JsonParser(){};
    static {
        try {
            // 读取JSON文件内容为字符串
            String jsonStr = new String(Files.readAllBytes(Paths.get("src/main/resources/jsons/questions.json")));

            // 将JSON字符串转换为JSONArray对象
            jsonQuestionArray = JSON.parseArray(jsonStr);
            questions = jsonQuestionArray.toJavaList(Question.class);

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

}
