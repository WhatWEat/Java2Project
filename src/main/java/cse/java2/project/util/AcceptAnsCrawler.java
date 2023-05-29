package cse.java2.project.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import cse.java2.project.entities.Answer;
import cse.java2.project.entities.Question;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class AcceptAnsCrawler {
    public static void main(String[] args) throws IOException {
        Map<Long, List<Answer>> ansOfQue = new HashMap<>();
        JSONObject ansQueJson = new JSONObject();
        String key = "A71YmgTD8Wao7nN2aakPpg((";
        for(Question q: JsonParser.questions){
            String apiUrl = String.format(
                "https://api.stackexchange.com/2.3/questions/%d/answers?order=desc&sort=activity&site=stackoverflow&filter=!nOedRLgcx)&key=%s"
                , q.getQuestion_id(),key);
            System.out.printf("\n\n\n%d\n\n\n", q.getQuestion_id());
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(apiUrl);

            try {
                CloseableHttpResponse response = httpClient.execute(request);
                int statusCode = response.getStatusLine().getStatusCode();

                if (statusCode == 200) {
                    HttpEntity entity = response.getEntity();
                    String jsonResponse = EntityUtils.toString(entity);
//                    System.out.println(jsonResponse);
                    JSONArray itemsArray = JSON.parseObject(jsonResponse).getJSONArray("items");
                    ansQueJson.put(String.valueOf(q.getQuestion_id()), itemsArray);
                } else {
                    System.out.println("Error: " + statusCode);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ansOfQue = ansQueJson.toJavaObject(ansOfQue.getClass());
        System.out.println(ansOfQue);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream("src/main/resources/jsons/answers.json")));
        writer.write(ansQueJson.toString());
        writer.close();


    }
}
