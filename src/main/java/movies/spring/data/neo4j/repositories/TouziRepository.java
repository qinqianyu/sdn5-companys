package movies.spring.data.neo4j.repositories;

import movies.spring.data.neo4j.domin.rels.Touzi;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Repository
public interface TouziRepository extends Neo4jRepository<Touzi, Long> {

    @Transactional(readOnly = true)
    @Query("MATCH (m:Company{regno:{regno}})-[r:投资]-(a) RETURN m,r,a")
    Collection<Touzi> graph(@Param("regno") String regno);

    @Transactional(readOnly = true)
    @Query("MATCH (a:Company{regno:{regno}})-[]-(b)-[r:投资]-(c) RETURN b,r,c ")
    Collection<Touzi> graph2(@Param("regno") String regno);

    @Transactional(readOnly = true)
    @Query("MATCH (a:Company{regno:{regno}})-[]-(b)-[]-(c)-[r:投资]-(d) RETURN c,r,d ")
    Collection<Touzi> graph3(@Param("regno") String regno);
}
