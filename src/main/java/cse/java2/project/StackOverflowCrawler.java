package cse.java2.project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StackOverflowCrawler {

    public static <MyClass> void main(String[] args) {

        String apiUrl = "https://api.stackexchange.com/2.3/questions?order=desc&sort=activity&site=stackoverflow";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println(connection.getContentEncoding());

                BufferedReader reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(connection.getInputStream())));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line).append('\n');
                }
                reader.close();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/main/resources/jsons/questions.json")));
                writer.write(response.toString());
                writer.close();


            } else {
                System.out.println("HTTP request failed. Response Code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Question {
    private String title;
    private List<String> tags;
    private String link;

    public Question(String title, List<String> tags, String link) {
        this.title = title;
        this.tags = tags;
        this.link = link;
    }

    // Getters and setters

    // Optional: Override toString() method for better output representation
}
