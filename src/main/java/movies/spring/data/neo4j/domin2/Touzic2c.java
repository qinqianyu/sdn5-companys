package movies.spring.data.neo4j.domin2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

@Data
@RelationshipEntity(type = "投资")
public class Touzic2c {
    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnoreProperties({/*"outTouzic2c",*/"inTouzic2c"})
    @StartNode
    private Company startCompany;

    @JsonIgnoreProperties({/*"outTouzic2c",*/"inTouzic2c"})
    @EndNode
    private Company endCompany;
}
