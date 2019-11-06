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
public class Company {
    @Id
    @GeneratedValue
    private Long id;

    private String regno;
    private String name;

    @Relationship(type = "人员")
    private Set<People> renyuans;

    @JsonIgnoreProperties("company")
    @Relationship(type = "投资", direction = Relationship.INCOMING)
    private Set<Touzip2c> touzip2c;


}
