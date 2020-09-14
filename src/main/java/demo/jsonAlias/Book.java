package demo.jsonAlias;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {
    @JsonProperty("bookName")
    private String name;

    @JsonProperty("bookCategory")
    @JsonAlias({"bkcat", "mybkcat"})
    private String category;

    public Book(){}
    public Book(String name, String category) {
        this.name = name;
        this.category = category;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
}
