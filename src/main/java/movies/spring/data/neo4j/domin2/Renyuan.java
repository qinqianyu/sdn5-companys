package movies.spring.data.neo4j.domin2;

import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "人员")
public class Renyuan {
    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Company company;

    @EndNode
    private People people;

    private String position;
}
