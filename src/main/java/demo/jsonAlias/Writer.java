package demo.jsonAlias;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Writer {
    @JsonProperty("writerId")
    private Integer id;

    @JsonProperty("writerName")
    @JsonAlias({"wname", "mywname"})
    private String name;

    @JsonProperty("writerBook")
    private Book book;

    public Writer(){}
    public Writer(Integer id, String name, Book book){
        this.id = id;
        this.name = name;
        this.book = book;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }
}
