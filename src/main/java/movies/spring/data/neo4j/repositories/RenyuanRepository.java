package movies.spring.data.neo4j.repositories;

import movies.spring.data.neo4j.domin.rels.Renyuan;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface RenyuanRepository extends Neo4jRepository<Renyuan, Long> {

    @Transactional(readOnly = true)
    @Query("MATCH (m:Company{regno:{regno}})-[r:人员]-(a) RETURN m,r,a ")
    Collection<Renyuan> graph(@Param("regno") String regno);

    @Transactional(readOnly = true)
    @Query("MATCH (a:Company{regno:{regno}})-[]-(b)-[r:人员]-(c) RETURN b,r,c ")
    Collection<Renyuan> graph2(@Param("regno") String regno);

    @Transactional(readOnly = true)
    @Query("MATCH (a:Company{regno:{regno}})-[]-(b)-[]-(c)-[r:人员]-(d) RETURN c,r,d ")
    Collection<Renyuan> graph3(@Param("regno") String regno);

    Optional<Renyuan> findById(Long aLong, int depth);
}
