package cse.java2.project.entities;

public class Answer {
    private Owner owner;
    private boolean is_accepted;
    private long community_owned_date;
    private int up_vote_count;
    private int score;
    private long last_activity_date;
    private long last_edit_date;
    private long creation_date;
    private long answer_id;
    private long question_id;
    private String content_license;

    private String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getUp_vote_count() {
        return up_vote_count;
    }

    public void setUp_vote_count(int up_vote_count) {
        this.up_vote_count = up_vote_count;
    }

    public Answer(Owner owner, boolean is_accepted, long community_owned_date, int up_vote_count,
                  int score, long last_activity_date, long last_edit_date, long creation_date,
                  long answer_id, long question_id, String content_license) {
        this.owner = owner;
        this.is_accepted = is_accepted;
        this.community_owned_date = community_owned_date;
        this.up_vote_count = up_vote_count;
        this.score = score;
        this.last_activity_date = last_activity_date;
        this.last_edit_date = last_edit_date;
        this.creation_date = creation_date;
        this.answer_id = answer_id;
        this.question_id = question_id;
        this.content_license = content_license;
    }

    public Answer(){

    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public boolean isIs_accepted() {
        return is_accepted;
    }

    public void setIs_accepted(boolean is_accepted) {
        this.is_accepted = is_accepted;
    }

    public long getCommunity_owned_date() {
        return community_owned_date;
    }

    public void setCommunity_owned_date(long community_owned_date) {
        this.community_owned_date = community_owned_date;
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

    public long getLast_edit_date() {
        return last_edit_date;
    }

    public void setLast_edit_date(long last_edit_date) {
        this.last_edit_date = last_edit_date;
    }

    public long getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(long creation_date) {
        this.creation_date = creation_date;
    }

    public long getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(long answer_id) {
        this.answer_id = answer_id;
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

    @Override
    public String toString() {
        return "Answer{" +
            "owner=" + owner +
            ", is_accepted=" + is_accepted +
            ", community_owned_date=" + community_owned_date +
            ", score=" + score +
            ", last_activity_date=" + last_activity_date +
            ", last_edit_date=" + last_edit_date +
            ", creation_date=" + creation_date +
            ", answer_id=" + answer_id +
            ", question_id=" + question_id +
            ", content_license='" + content_license + '\'' +
            '}';
    }
}
