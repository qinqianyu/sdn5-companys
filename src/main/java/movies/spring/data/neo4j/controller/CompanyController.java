package movies.spring.data.neo4j.controller;


import movies.spring.data.neo4j.services.AllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/company")
public class CompanyController {

//    @Autowired
//    private CompanyService companyService;

    @Autowired
    private AllService allService;

//    @GetMapping("/graph")
//    public Map<String, Object> graph(@RequestParam(value = "regno",required = false) String regno) {
//        return companyService.graph(regno == null ? "123456" : regno);
//    }
    @GetMapping("/graph")
    public Map<String, List<Map<String, Object>>> graph1(@RequestParam(value = "regno",required = false) String regno) {
        return allService.graph(regno == null ? "123457" : regno);
    }
}
