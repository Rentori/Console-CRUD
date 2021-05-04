package main.java.com.company.crud.model;

import java.util.List;

public class Writer {
    private Long id;
    private String firstName;
    private String lastName;
    List<Post> posts;

    public Writer(String firstName, String lastName, List<Post> posts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.posts = posts;
    }

    public Writer(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Writer(Long id, String firstName, String lastName, List<Post> posts) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.posts = posts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void getInfo() {
        System.out.print("Writer ID: " + this.getId() + "\n" +
                "First name: " + this.getFirstName() + "\n" +
                "Last name: " + this.getLastName() + "\n" +
                "Posts: ");
        this.getPosts().forEach(n -> System.out.print(n.getId() + " "));
        System.out.println("\n\n");
    }
}
