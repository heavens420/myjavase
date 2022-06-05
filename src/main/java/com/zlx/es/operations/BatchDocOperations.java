package com.zlx.es.operations;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.Arrays;

/**
 * 批量操作文档
 */
@Slf4j
public class BatchDocOperations {
    public static void main(String[] args) throws IOException {
        batchAddDoc();
//        batchDelete();
    }

    public static RestHighLevelClient getConnection(){
        return new RestHighLevelClient(RestClient.builder(new HttpHost("jing.tk", 50010, "http")));
    }

    /**
     * 批量添加
     * @throws IOException
     */
    public static void batchAddDoc() throws IOException {
        final RestHighLevelClient client = getConnection();
        BulkRequest request = new BulkRequest();
        String doc1 = """
                {
                  "title":"标题1",
                  "name":"张三",
                  "age":10
                }
                """;
        String doc2 = """
                {
                  "title":"标题2",
                  "name":"李四",
                  "age":201
                }
                """;
        request.add(new IndexRequest("sss").id("3").source(doc1, XContentType.JSON));
        request.add(new IndexRequest("sss").id("4").source(doc2, XContentType.JSON));
        request.add(new IndexRequest("sss").id("5").source(XContentType.JSON, "title", "new Title", "name", "测试", "age", 30));

        final BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
        System.out.println(request.getShouldStoreResult());
        log.info("执行时间:{}",response.getTook());
        log.info("执行结果2:{}", Arrays.stream(response.getItems()).toList().toString());
        client.close();
    }

    /**
     * 批量删除
     * @throws IOException
     */
    public static void batchDelete() throws IOException {
        final RestHighLevelClient client = getConnection();

        BulkRequest request = new BulkRequest();
        request.add(new DeleteRequest("sss").id("3"));
        request.add(new DeleteRequest("sss").id("4"));

        final BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);

        log.info("执行时间：{}", response.getTook());
        client.close();
    }
}
