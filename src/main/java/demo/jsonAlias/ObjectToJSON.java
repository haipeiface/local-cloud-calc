package demo.jsonAlias;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectToJSON {
    public static void main(String[] args) throws JsonProcessingException {
        //输出
        ObjectMapper mapper = new ObjectMapper();
        Book book = new Book("Learning Java", "Java");
        Writer writer = new Writer(110, "Mohit", book);
        String jsonWriter = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(writer);
        System.out.println(jsonWriter);

    }
}
