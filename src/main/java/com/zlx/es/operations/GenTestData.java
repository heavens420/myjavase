package com.zlx.es.operations;

import com.zlx.es.domain.RequestRecord;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.lang.reflect.Field;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 造数据
 */
@Slf4j
public class GenTestData {
    public static void main(String[] args) throws IOException {
//        genData();
//        getData();
        genData2();
    }


    public static RestHighLevelClient getConnection(){
//        return new RestHighLevelClient(RestClient.builder(new HttpHost("jing.tk", 50010, "http")));
        return new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.59.177", 8200, "http")));
    }

    public static void genData() throws IOException {
        final RestHighLevelClient client = getConnection();
        BulkRequest request = new BulkRequest();
        for (int i = 0; i < 300000; i++) {
            IndexRequest doc = new IndexRequest();
            doc.id(String.valueOf(i+1)).index("ttt");
            Map<String, Object> data = new HashMap<>();
            data.put("serialId", i%2);
            data.put("servName", "doamin" + i%2);
            data.put("invokeTime", "20" + (i % 34+10) + "-" + (i % 3 + 10) + "-" + (i % 8 + 10));
            data.put("execStatus", i%2);
            data.put("invokeStatus", i%2);
            data.put("invokeStatus", i%2);
            data.put("invokeStatus", i%2);
            data.put("invokeStatus", i%2);
            data.put("invokeStatus", i%2);
            doc.source(data);
            request.add(doc);

            if (i % 1000 == 0) {
                final BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
                request = new BulkRequest();
            }
        }

        client.close();
    }

    public static void genData2() throws IOException {
        final RestHighLevelClient client = getConnection();
        Map<String, Object> data = new HashMap<>();

        final Field[] declaredFields = RequestRecord.class.getDeclaredFields();

        IndexRequest doc = new IndexRequest("ttt");
        for (Field declaredField : declaredFields) {
//            System.out.println(declaredField.getName());
//            System.out.println(declaredField.getType().getSimpleName());
            String type = declaredField.getType().getSimpleName();
            String fieldName = declaredField.getName();
            if (type.equals(String.class.getSimpleName())) {
                data.put(fieldName, "haha");
            }
            if (type.equals(Long.class.getSimpleName())) {
                data.put(fieldName, Instant.now().toEpochMilli()-165*24*3600*1000L);
            }
            if (type.equals(Date.class.getSimpleName())) {
                data.put(fieldName, "2020-09-08 12:23:34");
            }
            if (type.equals(Integer.class.getSimpleName())) {
                data.put(fieldName,321);
            }
            doc.source(data);
        }

        BulkRequest request = new BulkRequest();
        for (int i = 0; i < 1000000; i++) {
            request.add(doc);
            if (i % 10000 == 0) {
                final BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
                request = new BulkRequest();
            }
        }

        client.close();
    }

    public static void getData() throws IOException {
        final RestHighLevelClient client = getConnection();
        SearchRequest request = new SearchRequest();
        request.indices("ttt");
        request.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()).size(10003));
        final SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        final SearchHits hits = response.getHits();
        log.info("命中数据:{}", hits.getTotalHits());
        client.close();

    }
}
