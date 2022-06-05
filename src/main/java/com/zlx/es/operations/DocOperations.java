package com.zlx.es.operations;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 索引添加文档
 */
@Slf4j
public class DocOperations {
    public static void main(String[] args) throws IOException {
//        addContent1();
        addContent2();
    }

    /**
     * 创建连接
     * @return
     */
    public static RestHighLevelClient getConnection(){
        return new RestHighLevelClient(RestClient.builder(new HttpHost("jing.tk", 50010, "http")));
    }

    /**
     * json方式添加文档
     * @throws IOException
     */
    public static void addContent1() throws IOException {
        final RestHighLevelClient client = getConnection();

        IndexRequest request = new IndexRequest("sss");

        // 指定id 同 put /test2/1
        // 指定id 重复创建的时候 会认为是更新
        request.id("1");

        String json = "{\n" +
                "  \"title\":\"标题5\",\n" +
                "  \"name\":\"张三\",\n" +
                "  \"age\":20\n" +
                "}";

        request.source(json, XContentType.JSON);
        final IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        if (response.getResult() == DocWriteResponse.Result.CREATED) {
            log.info("文档添加成功");
        }
        if (response.getResult() == DocWriteResponse.Result.UPDATED) {
            log.info("文档更新成功");
        }
        final ReplicationResponse.ShardInfo shardInfo = response.getShardInfo();
        // 不相等 存在分片出错
        if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
            log.info("存在分片出错");
        }
        // 打印出错分片信息
        if (shardInfo.getFailed() > 0) {
            for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
                log.info("出错原因:{}",failure);
            }
        }

        System.out.println("-----------------");
        log.info("执行结果：{}",response.getResult());
        log.info("文档id:{}",response.getId());
        log.info("分片id:{}",response.getShardId());
        client.close();
    }

    /**
     * 通过map的方式 创建文档
     * @throws IOException
     */
    public static void addContent2() throws IOException {
        final RestHighLevelClient client = getConnection();
        IndexRequest indexRequest = new IndexRequest();
        indexRequest.index("sss");
        indexRequest.id("2");

        // 强制默认添加文档 而不是更新文档 当id存在则会报错
        indexRequest.opType(DocWriteRequest.OpType.CREATE);

        Map<String, String> map = new HashMap<>();
        map.put("name", "zs");
        map.put("age", "20");
        map.put("addr", "北京");

        indexRequest.source(map);
        client.indexAsync(indexRequest, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
            @SneakyThrows
            @Override
            public void onResponse(IndexResponse indexResponse) {
                log.info("文档添加成功");
                log.info("文档id:{}",indexResponse.getId());
                client.close();
            }

            @Override
            public void onFailure(Exception e) {
                log.info("文档添加失败");
            }
        });
    }

    /**
     * Xcontent 方式类似创建索引 太麻烦 略
     */
    public static void addContent3(){

    }
}
