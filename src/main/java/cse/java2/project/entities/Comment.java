package cse.java2.project.entities;

public class Comment {

    private Owner owner;
    private boolean edited;
    private int score;
    private long creation_date;
    private long post_id;
    private long comment_id;
    private String content_license;
    private String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Comment() {
    }

    public Comment(Owner owner, boolean edited, int score, long creation_date, long post_id,
        long comment_id, String content_license) {
        this.owner = owner;
        this.edited = edited;
        this.score = score;
        this.creation_date = creation_date;
        this.post_id = post_id;
        this.comment_id = comment_id;
        this.content_license = content_license;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public boolean isEdited() {
        return edited;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(long creation_date) {
        this.creation_date = creation_date;
    }

    public long getPost_id() {
        return post_id;
    }

    public void setPost_id(long post_id) {
        this.post_id = post_id;
    }

    public long getComment_id() {
        return comment_id;
    }

    public void setComment_id(long comment_id) {
        this.comment_id = comment_id;
    }

    public String getContent_license() {
        return content_license;
    }

    public void setContent_license(String content_license) {
        this.content_license = content_license;
    }
}
