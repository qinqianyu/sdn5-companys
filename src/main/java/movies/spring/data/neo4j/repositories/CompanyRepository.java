package movies.spring.data.neo4j.repositories;


import movies.spring.data.neo4j.domin.nodes.Company;
        import movies.spring.data.neo4j.domin.rels.BaseRel;
        import org.springframework.data.neo4j.annotation.Query;
        import org.springframework.data.neo4j.repository.Neo4jRepository;
        import org.springframework.data.repository.query.Param;
        import org.springframework.stereotype.Repository;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.Collection;
@Repository
public interface CompanyRepository extends Neo4jRepository<Company, Long> {
    @Transactional(readOnly = true)
    @Query("MATCH (m:Company{regno:{regno}})-[r*1..4]-(a) RETURN m,r,a ")
    Collection<BaseRel> graph(@Param("regno") String regno);

    @Transactional(readOnly = true)
    @Query("MATCH p=shortestPath((bacon:Person {cerno:'00003'})-[*]-(meg:Person {cerno:'00002'}) ) RETURN p")
    Collection<BaseRel> path(@Param("cerno1") String cerno1,@Param("cerno2") String cerno2);

}