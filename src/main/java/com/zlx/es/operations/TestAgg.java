package com.zlx.es.operations;

import org.apache.http.HttpHost;
import org.apache.lucene.queryparser.xml.builders.BooleanQueryBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ValueCountAggregationBuilder;
import org.elasticsearch.search.query.QuerySearchRequest;

import java.util.ArrayList;
import java.util.List;

public class TestAgg {
    public static void main(String[] args) {

    }

    /**
     * 创建连接
     *
     * @return
     */
    public static RestHighLevelClient getConnection() {
        return new RestHighLevelClient(RestClient.builder(new HttpHost("jing.tk", 50010, "http")));
    }

    public static void testAgg() {
        final RestHighLevelClient client = getConnection();
        BoolQueryBuilder request = QueryBuilders.boolQuery();
        TermsAggregationBuilder terms = AggregationBuilders
                .terms("groupByServName").field("servName" + ".keyword").size(10)
                //平均
                .subAggregation(AggregationBuilders.avg("avgExecTime").field("execTime"))
                //最大
                .subAggregation(AggregationBuilders.max("maxExecTime").field("execTime"))
                //最小
                .subAggregation(AggregationBuilders.min("minExecTime").field("execTime"))
                //子查询,成功失败状态分组
                .subAggregation(AggregationBuilders.terms("subInvokeStatus").field("invokeStatus.keyword").size(10000))
                .subAggregation(AggregationBuilders.terms("subExecStatus").field("execStatus.keyword").size(10000));
        ValueCountAggregationBuilder valCount = AggregationBuilders.count("recordSum").field("serialId");
        List<AbstractAggregationBuilder> list = new ArrayList<>();
        list.add(valCount);
        list.add(terms);
    }

//    public Aggregations aggSearch(QueryBuilder queryBuilder, List<AbstractAggregationBuilder> aggregationBuilder, Class<?> clazz) {
//        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
//        nativeSearchQueryBuilder.withQuery(queryBuilder);
//        if (aggregationBuilder != null && !aggregationBuilder.isEmpty()) {
//            aggregationBuilder.forEach((build) -> {
//                nativeSearchQueryBuilder.addAggregation(build);
//            });
//        }
//
//        NativeSearchQuery searchQuery = nativeSearchQueryBuilder.build();
//        Aggregations recordAggregations = this.elasticsearchRestTemplate.queryForPage(searchQuery, clazz).getAggregations();
//        return recordAggregations;
//    }

}
