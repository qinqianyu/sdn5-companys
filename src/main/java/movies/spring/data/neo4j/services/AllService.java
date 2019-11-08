package movies.spring.data.neo4j.services;

import movies.spring.data.neo4j.domin.nodes.BaseNode;
import movies.spring.data.neo4j.domin.nodes.Company;
import movies.spring.data.neo4j.domin.nodes.NodesLabel;
import movies.spring.data.neo4j.domin.nodes.Person;
import movies.spring.data.neo4j.domin.rels.Renyuan;
import movies.spring.data.neo4j.domin.rels.Touzi;
import movies.spring.data.neo4j.repositories.RenyuanRepository;
import movies.spring.data.neo4j.repositories.TouziRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class AllService {

    @Autowired
    private TouziRepository touziRepository;

    @Autowired
    private RenyuanRepository renyuanRepository;


    public Map<String, List<Map<String, Object>>> graph(String regno) {
        Collection<Touzi> touziResult = touziRepository.graph(regno);
        Collection<Renyuan> renyuanResult = renyuanRepository.graph(regno);
        HashMap<String, List<Map<String, Object>>> result = new HashMap<String, List<Map<String, Object>>>();
        AddTouzi(touziResult, result);
        AddRenyuan(renyuanResult, result);
        return result;
    }
    public Map<String, List<Map<String, Object>>> graph2(String regno) {
        Collection<Touzi> touziResult = touziRepository.graph(regno);
        touziResult.addAll(touziRepository.graph2(regno));
        Collection<Renyuan> renyuanResult = renyuanRepository.graph(regno);
        renyuanResult.addAll(renyuanRepository.graph2(regno));
        HashMap<String, List<Map<String, Object>>> result = new HashMap<String, List<Map<String, Object>>>();
        AddTouzi(touziResult, result);
        AddRenyuan(renyuanResult, result);
        return result;
    }
    public Map<String, List<Map<String, Object>>> graph3(String regno) {
        Collection<Touzi> touziResult = touziRepository.graph(regno);
        touziResult.addAll(touziRepository.graph2(regno));
        touziResult.addAll(touziRepository.graph3(regno));
        Collection<Renyuan> renyuanResult = renyuanRepository.graph(regno);
        renyuanResult.addAll(renyuanRepository.graph2(regno));
        renyuanResult.addAll(renyuanRepository.graph3(regno));
        HashMap<String, List<Map<String, Object>>> result = new HashMap<String, List<Map<String, Object>>>();
        AddTouzi(touziResult, result);
        AddRenyuan(renyuanResult, result);
        return result;
    }

    private void AddTouzi(Collection<Touzi> touziResult, HashMap<String, List<Map<String, Object>>> result) {
        List<Map<String, Object>> nodes = new ArrayList<>();
        List<Map<String, Object>> rels = new ArrayList<>();
        Iterator<Touzi> touziIterator = touziResult.iterator();
        while (touziIterator.hasNext()) {
            Touzi next = touziIterator.next();
            Map<String, Object> startNode = startNodeToMap(next.getStartNode());
            Map<String, Object> endNode = map3("name", next.getEndNode().getName(), "id", next.getEndNode().getRegno(), "lable", NodesLabel.Company);
            int index = nodes.indexOf(startNode);
            if (index == -1) {
                nodes.add(startNode);
            }
            index = nodes.indexOf(endNode);
            if (index == -1) {
                nodes.add(endNode);
            }
            rels.add(map3("source", startNode.get("id"), "target", endNode.get("id"), "type", "投资"));
        }
        result.put("nodes", nodes);
        result.put("links", rels);
    }

    private void AddRenyuan(Collection<Renyuan> renyuanResult, HashMap<String, List<Map<String, Object>>> result) {
        List<Map<String, Object>> nodes = result.get("nodes");
        List<Map<String, Object>> rels = result.get("links");
        Iterator<Renyuan> renyuanIterator = renyuanResult.iterator();
        while (renyuanIterator.hasNext()) {
            Renyuan next = renyuanIterator.next();
            Map<String, Object> startNode = map3("name", next.getCompany().getName(), "id", next.getCompany().getRegno(), "lable", NodesLabel.Company);
            Map<String, Object> endNode = map3("name", next.getPerson().getName(), "id", next.getPerson().getCerno(), "lable", NodesLabel.Person);
            int index = nodes.indexOf(startNode);
            if (index == -1) {
                nodes.add(startNode);
            }
            index = nodes.indexOf(endNode);
            if (index == -1) {
                nodes.add(endNode);
            }
            rels.add(map3("source", startNode.get("id"), "target", endNode.get("id"), "type", next.getPosition()));
        }
        result.put("nodes", nodes);
        result.put("links", rels);
    }

    private Map<String, Object> startNodeToMap(BaseNode baseNode) {
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

    private Map<String, Object> map3(String key1, Object value1, String key2, Object value2, String key3, Object value3) {
        Map<String, Object> result = new HashMap<String, Object>(2);
        result.put(key1, value1);
        result.put(key2, value2);
        result.put(key3, value3);
        return result;
    }

 /*   private Map<String, Object> map2(String key1, Object value1, String key2, Object value2) {
        Map<String, Object> result = new HashMap<String, Object>(2);
        result.put(key1, value1);
        result.put(key2, value2);
        return result;
    }*/
}
