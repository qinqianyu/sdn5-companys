package movies.spring.data.neo4j.controller;


import movies.spring.data.neo4j.domin.rels.Touzi;
import movies.spring.data.neo4j.repositories.TouziRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/touzi")
public class TouziController {

    @Autowired
    private TouziRepository touziRepository;


    @GetMapping("/test")
    public Collection<Touzi> graph(@RequestParam(value = "regno",required = false) String regno) {
        Collection<Touzi> graph = touziRepository.graph(regno == null ? "123457" : regno);
        System.out.println(graph.size());
        return graph;
    }
}
