package movies.spring.data.neo4j.domin.nodes;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@Data
@EqualsAndHashCode(callSuper = false)
public class Company extends BaseNode{

    private String regno;
    private String name;

/*
    @Relationship(type = "人员")
    private Set<People> renyuans;

    @JsonIgnoreProperties("company")
    @Relationship(type = "投资", direction = Relationship.INCOMING)
    private Set<Touzip2c> touzip2c;

    @JsonIgnoreProperties("startCompany")
    @Relationship(type = "投资")
    private Set<Touzic2c> outTouzic2c;

    @JsonIgnoreProperties("endCompany")
    @Relationship(type = "投资", direction = Relationship.INCOMING)
    private Set<Touzic2c> inTouzic2c;*/

}
