package com.myself.com.myself.boot.demo.contoller;

import com.myself.com.myself.boot.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    @Qualifier("testService")
    TestService testService;


    @RequestMapping(value = "testAPI", method = {RequestMethod.GET, RequestMethod.PUT})
    public String testAPI() {
        return "This is test api.";
    }

    @RequestMapping(value = "getTableSize", method = {RequestMethod.GET, RequestMethod.POST})
    public Integer getTableSize() {
        return testService.getTabelSize();
    }

    @RequestMapping(value = "testParam", method = {RequestMethod.GET, RequestMethod.PUT})
    public Map<String, String> testAPIWithParam(@RequestParam(name = "cparam", required = false) String param,
                                                @RequestParam(name = "bparam", defaultValue = "dparam") String param2) {
        Map<String, String> map = new HashMap<>();
        map.put("param", param);
        map.put("param2", param2);
        map.put("test", testService.getTestString());

        return map;
    }
}
