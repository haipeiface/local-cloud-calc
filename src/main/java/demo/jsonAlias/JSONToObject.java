package demo.jsonAlias;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONToObject {
    public static void main(String[] args) throws Exception {
        //输入
        String jsonData =
                "{"
                        +"\"writerId\" : 111,"
                        +"\"mywname\" : \"Mahesh\","
                        +"\"writerBook\" : {"
                        +"\"bookName\" : \"Learning Spring\","
                        +"\"bkcat\" : \"Spring\""
                        +"}"
                        +"}";
        ObjectMapper mapper = new ObjectMapper();
        Writer writer = mapper.readValue(jsonData, Writer.class);
        System.out.println(writer.getId()+", "+ writer.getName());
        Book book = writer.getBook();
        System.out.println(book.getName()+", "+ book.getCategory());
    }
}
