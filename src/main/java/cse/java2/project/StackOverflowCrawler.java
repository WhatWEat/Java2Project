package cse.java2.project;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import cse.java2.project.entities.Question;

public class StackOverflowCrawler {

    public static void main(String[] args) {

        String apiUrl = "https://api.stackexchange.com/2.3/questions?order=desc&sort=activity&site=stackoverflow";

        try {
            JSONArray mergeArray = new JSONArray();
            for (int i = 0; i < 40; i++) {
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {

                    BufferedReader reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(connection.getInputStream())));
                    String line;
                    StringBuilder response = new StringBuilder();

                    while ((line = reader.readLine()) != null) {
                        response.append(line).append('\n');
                    }
                    reader.close();

                    JSONArray itemsArray = JSON.parseObject(response.toString()).getJSONArray("items");
                    mergeArray.addAll(itemsArray);
                } else {
                    System.out.println("HTTP request failed. Response Code: " + responseCode);
                }
            }
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/main/resources/jsons/questions.json")));
                writer.write(mergeArray.toString());
                writer.close();
                List<Question> questions = mergeArray.toJavaList(Question.class);
                System.out.println(questions);





        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

