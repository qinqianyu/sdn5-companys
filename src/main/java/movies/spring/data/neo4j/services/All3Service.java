package movies.spring.data.neo4j.services;

import movies.spring.data.neo4j.domin.nodes.BaseNode;
import movies.spring.data.neo4j.domin.nodes.Company;
import movies.spring.data.neo4j.domin.nodes.NodesLabel;
import movies.spring.data.neo4j.domin.nodes.Person;
import movies.spring.data.neo4j.domin.rels.BaseRel;
import movies.spring.data.neo4j.domin.rels.RelsType;
import movies.spring.data.neo4j.domin.rels.Renyuan;
import movies.spring.data.neo4j.domin.rels.Touzi;
import movies.spring.data.neo4j.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class All3Service {
    @Autowired
    private CompanyRepository companyRepository;
    public Map<String, Object> graph(String regno) {
        List<Map<String, Object>> nodes = new ArrayList<>();
        List<Map<String, Object>> rels = new ArrayList<>();
        Collection<BaseRel> baseRelsResult = companyRepository.graph(regno);
        Iterator<BaseRel> iterator = baseRelsResult.iterator();
        while (iterator.hasNext()) {
            BaseRel baseRel = iterator.next();
            if (baseRel instanceof Renyuan) {
                Renyuan next = (Renyuan) baseRel;
                Map<String, Object> startNode = CompanyToMap(next.getCompany());
                Map<String, Object> endNode = PersonToMap(next.getPerson());
                int index = nodes.indexOf(startNode);
                if (index == -1) {
                    nodes.add(startNode);
                }
                index = nodes.indexOf(endNode);
                if (index == -1) {
                    nodes.add(endNode);
                }
                rels.add(map3("source", startNode.get("id"), "target", endNode.get("id"), "type", next.getPosition()));
            } else if (baseRel instanceof Touzi) {
                Touzi next = (Touzi) baseRel;
                Map<String, Object> startNode = baseNodeToMap(next.getStartNode());
                Map<String, Object> endNode = CompanyToMap(next.getEndNode());
                int index = nodes.indexOf(startNode);
                if (index == -1) {
                    nodes.add(startNode);
                }
                index = nodes.indexOf(endNode);
                if (index == -1) {
                    nodes.add(endNode);
                }
                rels.add(map3("source", startNode.get("id"), "target", endNode.get("id"), "type", RelsType.TOUZI));
            }
        }
        HashMap<String, Object> result = new HashMap<>();
        result.put("nodes", nodes);
        result.put("links", rels);
        return result;
    }

    private Map<String, Object> baseNodeToMap(BaseNode baseNode) {
        if (baseNode instanceof Person) {
            Person person = (Person) baseNode;
            return map3("name", person.getName(), "id", person.getCerno(), "lable", NodesLabel.Person);
        }
        if (baseNode instanceof Company) {
            Company company = (Company) baseNode;
            return map3("name", company.getName(), "id", company.getRegno(), "lable", NodesLabel.Company);
        }
        return null;
    }

    private Map<String, Object> CompanyToMap(Company company) {
        return map3("name", company.getName(), "id", company.getRegno(), "lable", NodesLabel.Company);
    }

    private Map<String, Object> PersonToMap(Person person) {
        return map3("name", person.getName(), "id", person.getCerno(), "lable", NodesLabel.Person);
    }

    private Map<String, Object> map3(String key1, Object value1, String key2, Object value2, String key3, Object value3) {
        Map<String, Object> result = new HashMap<String, Object>(2);
        result.put(key1, value1);
        result.put(key2, value2);
        result.put(key3, value3);
        return result;
    }
}
