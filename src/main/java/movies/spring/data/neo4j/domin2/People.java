package movies.spring.data.neo4j.domin2;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@Data
public class People {
    @Id
    @GeneratedValue
    private Long id;

    private String cerno;
    private String name;
}
