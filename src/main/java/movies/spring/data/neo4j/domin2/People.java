package movies.spring.data.neo4j.domin2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

@NodeEntity
@Data
public class People {
    @Id
    @GeneratedValue
    private Long id;

    private String cerno;
    private String name;

    @Relationship(type = "投资")
    private Set<Company> companies;

    @JsonIgnoreProperties("people")
    @Relationship(type = "人员", direction = Relationship.INCOMING)
    private Set<Renyuan> renyuan;

}
