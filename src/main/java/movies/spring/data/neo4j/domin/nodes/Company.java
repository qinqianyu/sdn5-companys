package movies.spring.data.neo4j.domin.nodes;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.neo4j.ogm.annotation.Labels;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.HashSet;
import java.util.Set;

@NodeEntity(label = NodesLabel.Company)
@Data
@EqualsAndHashCode(callSuper = false)
public class Company extends BaseNode{

    private String regno;
    private String name;


}
