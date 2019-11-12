package movies.spring.data.neo4j.controller;


import movies.spring.data.neo4j.domin.rels.BaseRel;
import movies.spring.data.neo4j.repositories.CompanyRepository;
import movies.spring.data.neo4j.services.All3Service;
import movies.spring.data.neo4j.services.AllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private AllService allService;
    @Autowired
    private All3Service all3Service;
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/graph")
    public Map<String, List<Map<String, Object>>> graph(@RequestParam(value = "id",required = false) String regno) {
        return allService.graph(regno == null ? "123457" : regno);
    }
    @GetMapping("/graph2")
    public Map<String, List<Map<String, Object>>> graph2(@RequestParam(value = "regno",required = false) String regno) {
        return allService.graph2(regno == null ? "123457" : regno);
    }
    @GetMapping("/graph3")
    public Map<String, List<Map<String, Object>>> graph3(@RequestParam(value = "regno",required = false) String regno) {
        return allService.graph3(regno == null ? "123457" : regno);
    }

    @GetMapping("/test")
    public Collection<BaseRel> test(@RequestParam(value = "id", required = false) String regno) {

        return companyRepository.graph(regno == null ? "123457" : regno);
    }

    @GetMapping("/test2")
    public Map<String, Object> test2(@RequestParam(value = "id", required = false) String regno) {
        return all3Service.graph(regno == null ? "123457" : regno);
    }
}
