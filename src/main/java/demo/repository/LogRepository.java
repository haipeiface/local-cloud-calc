package demo.repository;

import demo.model.LogDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LogRepository extends ElasticsearchRepository<LogDO,String>{

    List<LogDO> findByAcctId(String acctId);

}
