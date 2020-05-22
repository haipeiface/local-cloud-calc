package demo.service;

import demo.model.LogDO;


import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchPhraseQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.rangeQuery;

@Service
public class LogService {

    private static Logger logger = LoggerFactory.getLogger(LogService.class);

    private static final String PRAMAS_VERSION_PREFIX = "current";

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public long getVersionCountBetween(String version, String from, String to){
        String params = String.format("%s=%s",PRAMAS_VERSION_PREFIX,version);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchPhraseQuery("params", params))
                .withFilter(rangeQuery("creatAt").from(from).to(to)).build();
        long count = elasticsearchTemplate.count(searchQuery, LogDO.class);
        return count;
    }

//    RangeQueryBuilder rangeQueryBuilder =
//            QueryBuilders.rangeQuery("creatAt")
//                    .from("2020-05-17T00:00:00.000Z")
//                    .to("2020-05-18T00:00:00.000Z");
//
//    public String searchByCreatAtDSL(){
//
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(rangeQueryBuilder);
//
//        logger.info(searchSourceBuilder.toString());
//        return searchSourceBuilder.toString();
//    }
//
//    public List<LogDO> searchByCreatAt(){
//
//        SearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withQuery(rangeQueryBuilder)
//                .build();
//
//        return elasticsearchTemplate.queryForList(searchQuery, LogDO.class);
//    }
}
