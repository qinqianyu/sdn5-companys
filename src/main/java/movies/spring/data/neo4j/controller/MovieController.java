package movies.spring.data.neo4j.controller;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import movies.spring.data.neo4j.domain.Movie;
import movies.spring.data.neo4j.services.MovieService;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mark Angrish
 */
@RestController
@RequestMapping("/movie")
public class MovieController {

	private final MovieService movieService;
	
	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

    @GetMapping("/onebyname")
    public Movie findByTitle(@RequestParam String title) {
        return movieService.findByTitle(title);
    }

    @GetMapping("/allbyname")
    public Collection<Movie> findByTitleLike(@RequestParam String title) {
        return movieService.findByTitleLike(title);
    }
/*
 * 此REST API接口，向外提供了节点、关系组成的图数据
 */
    @GetMapping("/graph")
	public Map<String, Object> graph(@RequestParam(value = "limit",required = false) Integer limit) {
		return movieService.graph(limit == null ? 10 : limit);
	}
    @RequestMapping("/all")
    @ResponseBody
    public Set<Movie> searchAllNode() {
//		logger.debug("access all");
        Set<Movie> nodes =movieService.searchAllNode();

        if (nodes != null) {
            return nodes;
        }
//		logger.info("error,nodes is null");
        return null;
    }
}
