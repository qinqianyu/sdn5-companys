package movies.spring.data.neo4j.domin2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

@Data
@RelationshipEntity(type = "人员")
public class Renyuan {
    @Id
    @GeneratedValue
    private Long id;
    @JsonIgnoreProperties({"outTouzic2c","inTouzic2c","touzip2c"})
    @StartNode
    private Company company;
    @JsonIgnoreProperties({"renyuan"})
    @EndNode
    private People people;

    private String position;
}
