package com.zlx.es.operations;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

import java.io.IOException;

public class IndexOperations {
    public static void main(String[] args) throws IOException {
//        createIndex();
//        deleteIndex();
        queryIndex();
    }

    public static RestHighLevelClient getConnection(){
        return new RestHighLevelClient(RestClient.builder(new HttpHost("81.68.241.197", 50010, "http")));
    }

    /**
     * 创建索引
     * @throws IOException
     */
    public static void createIndex() throws IOException {
        final RestHighLevelClient client = getConnection();
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("sss");
        final CreateIndexResponse response = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        System.out.println("-------------------------------");
        System.out.println(response.toString());
        client.close();
    }

    /**
     * 删除索引
     * @throws IOException
     */
    public static void deleteIndex() throws IOException {
        final RestHighLevelClient client = getConnection();
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("sss");
        client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        client.close();
    }

    /**
     * 查询索引
     * @throws IOException
     */
    public static void queryIndex() throws IOException {
        final RestHighLevelClient client = getConnection();
        GetIndexRequest getIndexRequest = new GetIndexRequest("sss");
        final GetIndexResponse response = client.indices().get(getIndexRequest, RequestOptions.DEFAULT);
        System.out.println("-------------------------------");
        response.getDataStreams().forEach((k,v)-> System.out.println(k+"---->"+v));
        client.close();
    }
}
