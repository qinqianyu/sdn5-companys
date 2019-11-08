package movies.spring.data.neo4j.repositories;

import movies.spring.data.neo4j.domin.nodes.Person;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * @author pdtyreus
 * @author Mark Angrish
 */
@Repository
@Transactional(readOnly = true)
public interface PersonRepository extends Neo4jRepository<Person, Long> {

    @Query("MATCH (m:Movie)<-[r:ACTED_IN]-(a:Person) RETURN a,r,m LIMIT {limit}")
    Collection<Person> graph(@Param("limit") int limit);
}