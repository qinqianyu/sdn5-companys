package movies.spring.data.neo4j.domin.rels;

import lombok.Data;
import lombok.EqualsAndHashCode;
import movies.spring.data.neo4j.domin.nodes.Company;
import movies.spring.data.neo4j.domin.nodes.Person;
import org.neo4j.ogm.annotation.*;

@Data
@EqualsAndHashCode(callSuper = false)
@RelationshipEntity(type = RelsType.RENYUAN)
public class Renyuan extends BaseRel {

    @StartNode
    private Company company;

    @EndNode
    private Person person;

    @Property
    private String position;
}
