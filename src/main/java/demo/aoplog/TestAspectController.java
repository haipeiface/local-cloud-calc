package demo.aoplog;

import demo.model.CalcParam;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author peifs
 */
@Controller
public class TestAspectController {
    @Loggable(descp = "用户个人资料", include = "")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Integer> addPost(@RequestBody CalcParam calcParam) {
        int res = calcParam.getA() + calcParam.getB();
        return ResponseEntity.ok(res);
    }
}
