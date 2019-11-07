/*
package movies.spring.data.neo4j.services;

import movies.spring.data.neo4j.domain.Movie;
import movies.spring.data.neo4j.domain.Role;
import movies.spring.data.neo4j.domin2.Company;
import movies.spring.data.neo4j.domin2.People;
import movies.spring.data.neo4j.domin2.Renyuan;
import movies.spring.data.neo4j.domin2.Touzip2c;
import movies.spring.data.neo4j.repositories.CompanyRepository;
import movies.spring.data.neo4j.repositories.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CompanyService {
    private final static Logger LOG = LoggerFactory.getLogger(MovieService.class);

    private final CompanyRepository companyRepository;
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    private Map<String, Object> toD3Format(Collection<Company> companies) {
        List<Map<String, Object>> nodes = new ArrayList<>();
        List<Map<String, Object>> rels = new ArrayList<>();
        int i = 0;
        Iterator<Company> result = companies.iterator();
        while (result.hasNext()) {
            Company company = result.next();
            int target = i;
            nodes.add(map2("name", company.getName(),"id" ,company.getRegno()));
            i++;
            for (Touzip2c touzip2c : company.getTouzip2c()) {
                Map<String, Object> people = map2("name", touzip2c.getPeople().getName(), "id", touzip2c.getPeople().getCerno());
                int source = nodes.indexOf(people);
                if (source == -1) {
                    source = i++;
                    nodes.add(people);
                }
                rels.add(map3("source", touzip2c.getPeople().getCerno(), "target", company.getRegno(),"type","投资"));
            }
            for (People peopletmp : company.getRenyuans()) {
                Map<String, Object> people = map2("name", peopletmp.getName(), "id", peopletmp.getCerno());
                int source = nodes.indexOf(people);
                if (source == -1) {
                    source = i++;
                    nodes.add(people);
                }
                rels.add(map3("source",  company.getRegno(), "target", peopletmp.getCerno(),"type",peopletmp.getRenyuan()));
            }
        }
        return map2("nodes", nodes, "links", rels);
    }
    private Map<String, Object> map2(String key1, Object value1, String key2, Object value2) {
        Map<String, Object> result = new HashMap<String, Object>(2);
        result.put(key1, value1);
        result.put(key2, value2);
        return result;
    }
    private Map<String, Object> map3(String key1, Object value1, String key2, Object value2,String key3, Object value3) {
        Map<String, Object> result = new HashMap<String, Object>(2);
        result.put(key1, value1);
        result.put(key2, value2);
        result.put(key3, value3);
        return result;
    }

    @Transactional(readOnly = true)
    public Map<String, Object> graph(String regno) {
        Collection<Company> result = companyRepository.graph(regno);
        return toD3Format(result);
    }
}
*/
