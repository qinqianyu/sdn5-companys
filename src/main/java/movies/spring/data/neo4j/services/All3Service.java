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

    public Map<String, Object> path(String cerno1,String cerno2) {
        return  parseBaseRel(companyRepository.path(cerno1,cerno2));
    }
    private Map<String, Object> parseBaseRel(Collection<BaseRel> baseRelsResult) {
        //节点列表,也可用其它集合如set
        List<Map> nodes = new ArrayList<>();
        //关系列表
        List<Map> rels = new ArrayList<>();
        for (BaseRel baseRel : baseRelsResult) {
            //判断关系类型并插入列表
            if (baseRel instanceof Renyuan) {
                insertRenyuan(baseRel, nodes, rels);
            } else if (baseRel instanceof Touzi) {
                insertTouzi(baseRel, nodes, rels);
            }
        }
        //返回前台的结果
        Map<String, Object> result = new HashMap<>();
        //将节点和关系放入返回前台的结果
        result.put("nodes", nodes);
        result.put("links", rels);
        return result;
    }


    //将查询结果转化成前台需要的格式
    public Map<String, Object> graph(String regno) {
        return parseBaseRel(companyRepository.graph(regno));
    }

    //插入人员关系的函数
    private void insertRenyuan(BaseRel baseRel, List<Map> nodes, List<Map> rels) {
        Renyuan next = (Renyuan) baseRel;
        Map<String, Object> startNode = CompanyToMap(next.getCompany());
        Map<String, Object> endNode = PersonToMap(next.getPerson());
        //判断节点容器中是否已经包含该节点,否则就插入
        int index = nodes.indexOf(startNode);
        if (index == -1) {
            nodes.add(startNode);
        }
        index = nodes.indexOf(endNode);
        if (index == -1) {
            nodes.add(endNode);
        }
        //插入关系
        rels.add(map3("source", startNode.get("id"), "target", endNode.get("id"), "type", next.getPosition()));
    }

    //插入投资关系的函数
    private void insertTouzi(BaseRel baseRel, List<Map> nodes, List<Map> rels) {
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

    //解析节点的父类指向的子类的类型,并将属性和值映射成map
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

    //将该种类型的节点映射成map
    private Map<String, Object> CompanyToMap(Company company) {
        return map3("name", company.getName(), "id", company.getRegno(), "lable", NodesLabel.Company);
    }

    private Map<String, Object> PersonToMap(Person person) {
        return map3("name", person.getName(), "id", person.getCerno(), "lable", NodesLabel.Person);
    }

    //size为3的map构建函数
    private Map<String, Object> map3(String key1, Object value1, String key2, Object value2, String key3, Object value3) {
        Map<String, Object> result = new HashMap<String, Object>(2);
        result.put(key1, value1);
        result.put(key2, value2);
        result.put(key3, value3);
        return result;
    }
}
