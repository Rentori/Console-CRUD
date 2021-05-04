package main.java.com.company.crud.model;

import java.util.List;

public class Post {
    private Long id;
    private String content;
    private String created;
    private String updated;
    private String postStatus;
    private List<Label> labels;

    public Post(String content, List<Label> labels) {
        this.content = content;
        this.labels = labels;
    }

    public Post(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Post(Long id, String content, String created, String updated, String postStatus, List<Label> labels) {
        this.id = id;
        this.content = content;
        this.created = created;
        this.updated = updated;
        this.postStatus = postStatus;
        this.labels = labels;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public String getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(String postStatus) {
        this.postStatus = postStatus;
    }

    public void getInfo() {
        System.out.print("Post ID: " + this.getId() + "\n" +
                "Content: " + this.getContent() + "\n" +
                "Created: " + this.getCreated() + "\n" +
                "Updated: " + this.getUpdated() + "\n" +
                "Status: " + this.getPostStatus() + "\n" +
                "Labels: ");
        this.getLabels().forEach(n -> System.out.print(n.getId() + " "));
        System.out.println("\n\n");
    }
}
