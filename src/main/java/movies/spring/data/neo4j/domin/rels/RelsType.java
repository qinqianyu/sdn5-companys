package movies.spring.data.neo4j.domin.rels;

/*
所有关系标签常量
这里使用了interface成员变量是常量的特性，比class使用static final String XX="xx"简洁
*/

public interface RelsType {
    String RENYUAN = "人员";
    String TOUZI = "投资";
}
