package cse.java2.project.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.zip.GZIPInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import cse.java2.project.entities.Question;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class StackOverflowCrawler {

    public static void main(String[] args) {

        try {
            JSONArray mergeArray = new JSONArray();
            String key = "A71YmgTD8Wao7nN2aakPpg((";
            for (int i = 0; i < 10; i++) {
                String apiUrl = String.format(
                    "https://api.stackexchange.com/2.3/questions?page=%d"
                        + "&pagesize=100&order=desc&sort=activity&site=stackoverflow&filter=!T3Audpctoqz)l6kJC0&key=%s"
                    , i + 1, key);

                CloseableHttpClient httpClient = HttpClientBuilder.create().build();
                HttpGet request = new HttpGet(apiUrl);

                try {
                    CloseableHttpResponse response = httpClient.execute(request);
                    int statusCode = response.getStatusLine().getStatusCode();

                    if (statusCode == 200) {
                        HttpEntity entity = response.getEntity();
                        String jsonResponse = EntityUtils.toString(entity);
                        System.out.println(jsonResponse);
                        JSONArray itemsArray = JSON.parseObject(jsonResponse).getJSONArray("items");
                        mergeArray.addAll(itemsArray);
                    } else {
                        System.out.println("Error: " + statusCode);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("src/main/resources/jsons/questions.json")));
            writer.write(mergeArray.toString());
            writer.close();
            List<Question> questions = mergeArray.toJavaList(Question.class);
            System.out.println(questions);


        } catch (IOException e) {
            e.printStackTrace();
        }
//        catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }
}

