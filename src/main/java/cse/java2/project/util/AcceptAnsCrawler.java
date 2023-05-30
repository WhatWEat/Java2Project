package cse.java2.project.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import cse.java2.project.entities.Answer;
import cse.java2.project.entities.Question;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class AcceptAnsCrawler {

    public static void main(String[] args) throws IOException {
        JSONArray ansQueJson = new JSONArray();
        String key = "PJpa3jAmephrk1Upw7BANg((";
        int pointer = 0;
        System.err.println(JsonParser.questions.size());
        while (pointer < JsonParser.questions.size()) {
            StringBuilder ids = new StringBuilder(
                String.valueOf(JsonParser.questions.get(pointer).getQuestion_id()));
            int currentSize = 0;
            while (pointer < JsonParser.questions.size()
                && currentSize + JsonParser.questions.get(pointer).getAnswer_count() < 100) {
                ids.append(';').append(JsonParser.questions.get(pointer).getQuestion_id());
                currentSize += JsonParser.questions.get(pointer).getAnswer_count();
                pointer++;
            }
            String apiUrl = String.format(
                "https://api.stackexchange.com/2.3/questions/%s/answers?page=1&pagesize=100&order=desc&sort=activity&site=stackoverflow&filter=!nNPvSNdWme&key=%s"
                , ids.toString(), key);
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
                    ansQueJson.addAll(itemsArray);
                } else {
                    System.out.println("Error: " + statusCode);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream("src/main/resources/jsons/answers.json")));
        writer.write(ansQueJson.toString());
        writer.close();
    }
}
