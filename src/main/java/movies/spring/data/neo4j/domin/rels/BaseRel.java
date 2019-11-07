package movies.spring.data.neo4j.domin.rels;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;

/**
 * 基础关系类型
 */
@Data
public class BaseRel {
    @Id
    @GeneratedValue
    private Long id;
}