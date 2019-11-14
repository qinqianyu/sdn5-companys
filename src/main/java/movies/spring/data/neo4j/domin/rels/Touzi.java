package movies.spring.data.neo4j.domin.rels;

        import lombok.Data;
        import lombok.EqualsAndHashCode;
        import movies.spring.data.neo4j.domin.nodes.BaseNode;
        import movies.spring.data.neo4j.domin.nodes.Company;
        import org.neo4j.ogm.annotation.EndNode;
        import org.neo4j.ogm.annotation.RelationshipEntity;
        import org.neo4j.ogm.annotation.StartNode;

@Data
@EqualsAndHashCode(callSuper = false)
@RelationshipEntity(type = RelsType.TOUZI)
public class Touzi<S extends BaseNode> extends BaseRel {

    @StartNode
    private S startNode;

    @EndNode
    private Company endNode;
}
