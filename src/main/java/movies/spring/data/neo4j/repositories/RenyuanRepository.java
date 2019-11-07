package movies.spring.data.neo4j.repositories;

import movies.spring.data.neo4j.domin.rels.Renyuan;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
@Repository

public interface RenyuanRepository extends Neo4jRepository<Renyuan, Long> {

    @Query("MATCH (m:Company)-[r:人员]-(a) RETURN m,r,a ")
    Collection<Renyuan> graph(@Param("regno") String regno);

}
