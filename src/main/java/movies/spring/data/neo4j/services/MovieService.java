package movies.spring.data.neo4j.services;

import java.util.*;

import movies.spring.data.neo4j.domain.Movie;
import movies.spring.data.neo4j.domain.Role;
import movies.spring.data.neo4j.repositories.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieService {

    private final static Logger LOG = LoggerFactory.getLogger(MovieService.class);

	private final MovieRepository movieRepository;
	public MovieService(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	private Map<String, Object> toD3Format(Collection<Movie> movies) {
		List<Map<String, Object>> nodes = new ArrayList<>();
		List<Map<String, Object>> rels = new ArrayList<>();
		int i = 0;
		Iterator<Movie> result = movies.iterator();
		while (result.hasNext()) {
			Movie movie = result.next();
			int target = i;
			nodes.add(map2("title", movie.getTitle(), "id", target));
			i++;
			for (Role role : movie.getRoles()) {
				Map<String, Object> actor = map2("title", role.getPerson().getName(), "label", "actor");
				int source = nodes.indexOf(actor);
				if (source == -1) {
					source = i++;
					nodes.add(map2("title", role.getPerson().getName(), "id", source));
				}
				rels.add(map3("source", source, "target", target,"type",role.getRoles().toString()));

			}
		}
		return map2("nodes", nodes, "links", rels);
	}
	private Map<String, Object> map2(String key1, Object value1, String key2, Object value2) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put(key1, value1);
		result.put(key2, value2);
		return result;
	}
	private Map<String, Object> map3(String key1, Object value1, String key2, Object value2,String key3, Object value3) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put(key1, value1);
		result.put(key2, value2);
		result.put(key3, value3);
		return result;
	}

    @Transactional(readOnly = true)
    public Movie findByTitle(String title) {
        Movie result = movieRepository.findByTitle(title);
        return result;
    }

    @Transactional(readOnly = true)
    public Collection<Movie> findByTitleLike(String title) {
        Collection<Movie> result = movieRepository.findByTitleLike(title);
        return result;
    }

	@Transactional(readOnly = true)
	public Map<String, Object>  graph(int limit) {
		Collection<Movie> result = movieRepository.graph(limit);
		return toD3Format(result);
	}

	@Transactional(readOnly = true)
	public Set<Movie> searchAllNode() {
		return movieRepository.searchAllNode();
	}

}
