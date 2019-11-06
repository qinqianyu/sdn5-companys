package movies.spring.data.neo4j.domain;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * @author Mark Angrish
 */
@Data
@NodeEntity
public class Person {

   	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private int born;

	@JsonIgnoreProperties("person")
	@Relationship(type = "ACTED_IN")
	private List<Role> roles;

}