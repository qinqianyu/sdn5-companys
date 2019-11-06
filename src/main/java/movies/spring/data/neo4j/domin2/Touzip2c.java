package movies.spring.data.neo4j.domin2;

import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "投资")
public class Touzip2c {
    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private People people;

    @EndNode
    private Company company;
}
