package cse.java2.project.entities;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.StringTokenizer;

public class Question {
    @TableField(exist = false)
    private List<String> tags;
    @TableField(exist = false)
    private Owner owner;
    private boolean is_answered;
    private int view_count;
    private int answer_count;
    private int score;
    private long last_activity_date;
    private long creation_date;
    private long last_edit_date;
    private long question_id;
    private String content_license;
    private String link;
    private String title;

    @JSONField(serialize = false)
    private long owner_account_id;

    public long getOwner_account_id() {
        return owner_account_id;
    }

    public void setOwner_account_id(long owner_account_id) {
        this.owner_account_id = owner_account_id;
    }

    @JsonCreator
    public Question(){

    }

//    public Question(@JsonProperty("field_name")List<String> tags,
//                    Owner owner,
//                    boolean is_answered, int view_count,
//                    int answer_count, int score, long last_activity_date, long creation_date,
//                    long last_edit_date, long question_id, String content_license, String link,
//                    String title) {
//        this.tags = tags;
////        this.owner = owner;
//        this.is_answered = is_answered;
//        this.view_count = view_count;
//        this.answer_count = answer_count;
//        this.score = score;
//        this.last_activity_date = last_activity_date;
//        this.creation_date = creation_date;
//        this.last_edit_date = last_edit_date;
//        this.question_id = question_id;
//        this.content_license = content_license;
//        this.link = link;
//        this.title = title;
//    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public boolean isIs_answered() {
        return is_answered;
    }

    public void setIs_answered(boolean is_answered) {
        this.is_answered = is_answered;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public int getAnswer_count() {
        return answer_count;
    }

    public void setAnswer_count(int answer_count) {
        this.answer_count = answer_count;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getLast_activity_date() {
        return last_activity_date;
    }

    public void setLast_activity_date(long last_activity_date) {
        this.last_activity_date = last_activity_date;
    }

    public long getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(long creation_date) {
        this.creation_date = creation_date;
    }

    public long getLast_edit_date() {
        return last_edit_date;
    }

    public void setLast_edit_date(long last_edit_date) {
        this.last_edit_date = last_edit_date;
    }

    public long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(long question_id) {
        this.question_id = question_id;
    }

    public String getContent_license() {
        return content_license;
    }

    public void setContent_license(String content_license) {
        this.content_license = content_license;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Question{" +
            "tags=" + tags +
            ", owner=" + owner +
            ", is_answered=" + is_answered +
            ", view_count=" + view_count +
            ", answer_count=" + answer_count +
            ", score=" + score +
            ", last_activity_date=" + last_activity_date +
            ", creation_date=" + creation_date +
            ", last_edit_date=" + last_edit_date +
            ", question_id=" + question_id +
            ", content_license='" + content_license + '\'' +
            ", link='" + link + '\'' +
            ", title='" + title + '\'' +
            '}';
    }
}
