package movies.spring.data.neo4j.controller;

import movies.spring.data.neo4j.domin2.Company;
import movies.spring.data.neo4j.repositories.CompanyRepository;
import movies.spring.data.neo4j.services.CompanyService;
import movies.spring.data.neo4j.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyRepository companyRepository;

//    @GetMapping("/graph")
//    public Map<String, Object> graph(@RequestParam(value = "regno",required = false) String regno) {
//        return companyService.graph(regno == null ? "123456" : regno);
//    }
    @GetMapping("/graph1")
    public Collection<Company> graph1(@RequestParam(value = "regno",required = false) String regno) {
        return companyRepository.graph1(regno == null ? "123456" : regno);
    }
}
