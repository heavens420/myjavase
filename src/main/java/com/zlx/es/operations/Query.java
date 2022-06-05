package com.zlx.es.operations;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;

import java.io.IOException;

/**
 * 文档查询
 */
@Slf4j
public class Query {

    public static void main(String[] args) throws IOException {
//        getById();
//        exists();
//        deleteContent();

//        query111();
//        query1();
//        query2();
        query3();
//        queryAll();
//        query4();
//        query5();
    }

    /**
     * 创建连接
     *
     * @return
     */
    public static RestHighLevelClient getConnection() {
        return new RestHighLevelClient(RestClient.builder(new HttpHost("jing.tk", 50010, "http")));
    }

    /**
     * 根据文档id查询
     *
     * @throws IOException
     */
    public static void getById() throws IOException {
        final RestHighLevelClient client = getConnection();
        GetRequest request = new GetRequest();
        request.index("books").id("1");
        final GetResponse response = client.get(request, RequestOptions.DEFAULT);
        // 文档存在
        if (response.isExists()) {
            log.info("查询结果:{}", response.getSourceAsString());
        } else {
            log.info("结果为空");
        }
        client.close();
    }

    /**
     * 不获取文档判断文档是否存在
     *
     * @throws IOException
     */
    public static void exists() throws IOException {
        final RestHighLevelClient client = getConnection();
        GetRequest request = new GetRequest("sss", "2");
        // 不获取文档 判断文档是否存在
        request.fetchSourceContext(new FetchSourceContext(false));
        final boolean exists = client.exists(request, RequestOptions.DEFAULT);
        log.info("文档是否存在：{}", exists);
        client.close();
    }

    /**
     * 删除文档
     *
     * @throws IOException
     */
    public static void deleteContent() throws IOException {
        final RestHighLevelClient client = getConnection();
        DeleteRequest request = new DeleteRequest("sss", "1");
        final DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        if (response.getResult() == DocWriteResponse.Result.DELETED) {
            log.info("删除成功");
        } else {
            log.info("删除失败");
        }
        // 后面的一些操作同创建文档

        client.close();
    }

    /**
     * 跟新文档
     *
     * @throws IOException
     */
    public static void updateContent1() throws IOException {
        final RestHighLevelClient client = getConnection();
        UpdateRequest request = new UpdateRequest("sss", "2");

        final UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
        if (response.getResult() == DocWriteResponse.Result.UPDATED) {
            log.info("更新成功");
        } else {
            log.info("更新失败");
        }
        client.close();
    }

    /**
     * 查询全部
     *
     * @throws IOException
     */
    public static void queryAll() throws IOException {
        final RestHighLevelClient client = getConnection();
        SearchRequest request = new SearchRequest();
        request.indices("sss");

        SearchSourceBuilder builder = new SearchSourceBuilder();
        // 高亮查询
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<h1>");
        highlightBuilder.postTags("</h1>");
        highlightBuilder.field("name");
        builder.highlighter(highlightBuilder);
        builder.query(QueryBuilders.matchAllQuery());

        request.source(builder);
        final SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        log.info("查询json:{}",builder);
        log.info("time:{}",response.getTook());
        log.info("total:{}",response.getHits().getTotalHits());
        for (SearchHit hit : response.getHits()) {
            log.info("------->{}", hit.getSourceAsString());
        }
        client.close();
    }

    /**
     * match term等 普通查询
     * @throws IOException
     */
    public static void query1() throws IOException {
        final RestHighLevelClient client = getConnection();
        SearchRequest request = new SearchRequest();
        request.indices("books");
        String[] includes = {"name", "author"};
        String[] excludes = {"_id", "name"};
        final SearchSourceBuilder sourceBuilder = new SearchSourceBuilder()
                .query(QueryBuilders.matchQuery("name", "大学数学"))
                .from(0)
                .size(50)
                // 只显示 或 不显示 部分字段  如果二者冲突 以排除数组为准
                .fetchSource(includes,excludes);

        request.source(sourceBuilder);
        final SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        log.info("time:{}",response.getTook());
        log.info("total:{}",response.getHits().getTotalHits());
        for (SearchHit hit : response.getHits()) {
            log.info("------->{}", hit.getSourceAsString());
        }
        client.close();
    }

    /**
     * bool 查询
     * @throws IOException
     */
    public static void query2() throws IOException {
        final RestHighLevelClient client = getConnection();
        SearchRequest request = new SearchRequest("books");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        final BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchQuery("name", "大学教材"));
        boolQueryBuilder.must(QueryBuilders.boolQuery().filter(QueryBuilders.rangeQuery("price").lte(20).gte(10)));

        builder.query(boolQueryBuilder);
        request.source(builder);
        final SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        log.info("time:{}",response.getTook());
        log.info("total:{}",response.getHits().getTotalHits());
        for (SearchHit hit : response.getHits()) {
            log.info("------->{}", hit.getSourceAsString());
        }
        client.close();
    }


    /**
     * 纠错查询
     * @throws IOException
     */
    public static void query3() throws IOException {
        final RestHighLevelClient client = getConnection();
        SearchRequest request = new SearchRequest("books");
        SearchSourceBuilder builder = new SearchSourceBuilder();

        // 纠错查询
        final FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery("name", "javv").fuzziness(Fuzziness.ONE);
        final SearchSourceBuilder sourceBuilder = builder.query(fuzzyQueryBuilder);

        request.source(sourceBuilder);
        log.info("search json---->:{}", sourceBuilder);
        final SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        log.info("time:{}",response.getTook());
        log.info("total:{}",response.getHits().getTotalHits());
        for (SearchHit hit : response.getHits()) {
            log.info("------->{}", hit.getSourceAsString());
        }
        client.close();
    }

    /**
     * 聚合查询
     * @throws IOException
     */
    public static void query4() throws IOException {
        final RestHighLevelClient client = getConnection();
        SearchRequest request = new SearchRequest("books");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        AggregationBuilder aggregationBuilder = AggregationBuilders.max("maxPrice").field("price");
        builder.aggregation(aggregationBuilder);

        request.source(builder);
        final SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        log.info("search json---->:{}", builder);
        log.info("time:{}",response.getTook());
        log.info("total:{}",response.getHits().getTotalHits());
        for (SearchHit hit : response.getHits()) {
            log.info("------->{}", hit.getSourceAsString());
        }
        log.info("aggs:{}", JSONObject.toJSONString(response.getAggregations()));
        log.info("maxPrice:{}",response.getAggregations().asMap().get("value"));
        client.close();
    }

    /**
     * 分组聚合
     * @throws IOException
     */
    public static void query5() throws IOException {
        final RestHighLevelClient client = getConnection();
        SearchRequest request = new SearchRequest("books");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        AggregationBuilder aggregationBuilder = AggregationBuilders.terms("groupBy").field("author");
        builder.aggregation(aggregationBuilder);
        request.source(builder.size(0));
        final SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        log.info("search json---->:{}", builder);
        log.info("time:{}",response.getTook());
        log.info("total:{}",response.getHits().getTotalHits());
        for (SearchHit hit : response.getHits()) {
            log.info("------->{}", hit.getSourceAsString());
        }
        log.info("aggs:{}", JSONObject.toJSONString(response.getAggregations()));
//        log.info("maxPrice:{}",response.getAggregations().asMap());
        client.close();
    }

}
