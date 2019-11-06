package movies.spring.data.neo4j.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Systemcontroller {
    @RequestMapping("/{id}")
    public String findpage(@PathVariable("id") String page){
        return page;
    }
//    @RequestMapping("/login")
//    public String login(Student student){
//        return "login";
//    }
}
