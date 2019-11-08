package movies.spring.data.neo4j.domin.nodes;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Labels;

import java.util.HashSet;
import java.util.Set;

/**
 * 基础节点类型
 */
@Data
public class BaseNode {
    @Id
    @GeneratedValue
    private Long id;

    @Labels
    private Set<String> labels = new HashSet<String>();

    public void addLabel(String name) {
        this.labels.add(name);
    }
}
