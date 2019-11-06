package movies.spring.data.neo4j.repositories;

import movies.spring.data.neo4j.domain.Movie;
import movies.spring.data.neo4j.domin2.Company;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
@Repository
public interface CompanyRepository extends Neo4jRepository<Movie, Long> {

    @Query("MATCH (m:Company{regno:{regno}})-[r]-(a) RETURN m,r,a ")
    Collection<Company> graph(@Param("regno") String regno);

    @Query("MATCH (m:Company{regno:{regno}})-[r:投资]-(a) RETURN m,r,a ")
    Collection<Company> graph1(@Param("regno") String regno);
}
