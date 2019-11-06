package movies.spring.data.neo4j.domin2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

@Data
@RelationshipEntity(type = "投资")
public class Touzip2c {
    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnoreProperties({"renyuan"})
    @StartNode
    private People people;
    @JsonIgnoreProperties({"outTouzic2c","inTouzic2c","touzip2c"})
    @EndNode
    private Company company;
}
