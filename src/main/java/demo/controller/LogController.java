package demo.controller;

import demo.model.LogDO;
import demo.repository.LogRepository;
import demo.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LogController {
    @Autowired
    private LogRepository logRepository;

    @Autowired
    private LogService logService;

//    @RequestMapping("/log/{acctId}")
//    @ResponseBody
//    public List<LogDO> getList(@PathVariable(value = "acctId") String acctId) {
//        System.out.println("testing........");
//        List<LogDO> logDOList = logRepository.findByAcctId(acctId);
//        return logDOList;
//    }


    //http://localhost:8011/log/5.3.0?from=2020-05-17T00:00:00.000Z&to=2020-05-18T00:00:00.000Z
    @RequestMapping("/log/{version}")
    @ResponseBody
    public String getVersionCountBetween(@PathVariable(value="version") String version,
                                 @RequestParam(value="from", required=true) String from,
                                 @RequestParam(value="to", required=true) String to) {
        return Long.toString(logService.getVersionCountBetween(version,from,to));
    }

//    @RequestMapping("/log/findByCreatAtDSL")
//    @ResponseBody
//    public String findByCreatAtDSL() {
//        System.out.println("testing........");
//        return logService.searchByCreatAtDSL();
//
//    }
//
//    @RequestMapping("/log/findByCreatAt")
//    @ResponseBody
//    public List<LogDO> findByCreatAt() {
//        System.out.println("testing........");
//        return logService.searchByCreatAt();
//    }
}
