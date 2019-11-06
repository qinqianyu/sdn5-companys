package movies.spring.data.neo4j.controller;

import movies.spring.data.neo4j.domain.Person;
import movies.spring.data.neo4j.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private  PersonRepository personRepository;
    @GetMapping("/graph")
    public Collection<Person> graph(@RequestParam(value = "limit",required = false) Integer limit) {
        Collection<Person> graph = personRepository.graph(limit == null ? 10 : limit);
        return graph;
    }

}
