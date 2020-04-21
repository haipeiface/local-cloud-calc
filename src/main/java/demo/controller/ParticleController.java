package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.util.HashMap;

@Controller
public class ParticleController {
    File file = new File(".");

    String absolutePath = file.getAbsolutePath();
    String templatesPath = absolutePath+"\\src\\main\\resources\\templates\\";
    File file2 = new File(templatesPath + ".");
    String fileList[] = file2.list();

    @RequestMapping(value = "/{prefix}")
    public String readHtmlPage(@PathVariable("prefix") String prefix) {
        HashMap map = new HashMap();

        for (String s: fileList) {
            String filePrefix = s.split("_")[0];
            map.put(filePrefix,s);
        }
//        System.out.println(templatesPath);
//        System.out.println(fileList[0]);

        return (String) map.get(prefix);
    }

}
