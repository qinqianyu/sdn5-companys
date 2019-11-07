package movies.spring.data.neo4j.repositories;

import movies.spring.data.neo4j.domin.nodes.Company;
import movies.spring.data.neo4j.domin.rels.Touzi;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface TouziRepository extends Neo4jRepository<Touzi, Long> {

    @Query("MATCH (m)-[r:投资]-(a) RETURN r")
    Collection<Touzi> graph(@Param("regno") String regno);
}
