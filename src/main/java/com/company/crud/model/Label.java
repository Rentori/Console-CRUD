package main.java.com.company.crud.model;

public class Label {
    private Long id;
    private String name;

    public Label(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
