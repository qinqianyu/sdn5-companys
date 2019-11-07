package movies.spring.data.neo4j.domin.nodes;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@Data
@EqualsAndHashCode(callSuper = false)
public class Person extends BaseNode {


    private String cerno;
    private String name;

   /* @Relationship(type = "投资")
    private Set<Company> companies;

    @JsonIgnoreProperties("people")
    @Relationship(type = "人员", direction = Relationship.INCOMING)
    private Set<Renyuan> renyuan;*/

}
