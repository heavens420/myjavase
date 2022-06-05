package com.zlx.es.start;

import org.apache.http.HttpHost;
// 这个包是low level client 里面的
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
// 这个包是high level client 里面的 注意不要引入了 low level client
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * high level client
 */
public class HighLevelQuery {
    public static void main(String[] args) throws IOException {
        createIndex1();
//        createIndex2();
//        createIndex3();
    }

    /**
     * 创建es连接 high level client way
     * @return
     */
    public static RestHighLevelClient getConnection(){
//        return new RestHighLevelClient(RestClient.builder(new HttpHost("jing.tk", 50010, "http")));
        return new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.59.177", 8200, "http")));
    }


    /**
     * JSON字符串方式 创建索引
     * @throws IOException
     */
    public static void createIndex1() throws IOException {
        final RestHighLevelClient client = getConnection();

        // 删除已经存在的索引 索引不存在则报错
//        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("ttt");
//        client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);

        // 创建索引
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("ttt");
        // 设置索引信息 mapping setting等
        createIndexRequest.settings(Settings.builder().put("index.number_of_shards", 1).put("index.number_of_replicas", 1));
        //配置字段类型，字段类型可以通过 JSON 字符串、Map 以及 XContentBuilder 三种方式来构建
        // json 字符串方式
//        createIndexRequest.mapping(" {\n" +
//                " \"properties\": {\n" +
//                "         \"name\":{\n" +
//                "           \"type\": \"text\",\n" +
//                "           \"analyzer\": \"ik_max_word\"\n" +
//                "         },\n" +
//                "         \"publish\":{\n" +
//                "           \"type\": \"text\",\n" +
//                "           \"analyzer\": \"ik_max_word\"\n" +
//                "         }\n" +
//                "       }\n" +
//                "}", XContentType.JSON);

        String mapping = "{\n" +
                "      \"properties\" : {\n" +
                "        \"acceptTime\" : {\n" +
                "          \"type\" : \"date\",\n" +
                "\t\t  \"format\": \"yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis\"\n" +
                "        },\n" +
                "        \"area\" : {\n" +
                "          \"type\" : \"text\",\n" +
                "          \"fields\" : {\n" +
                "            \"keyword\" : {\n" +
                "              \"type\" : \"keyword\",\n" +
                "              \"ignore_above\" : 256\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"busiNum\" : {\n" +
                "          \"type\" : \"text\",\n" +
                "          \"fields\" : {\n" +
                "            \"keyword\" : {\n" +
                "              \"type\" : \"keyword\",\n" +
                "              \"ignore_above\" : 256\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"controlId\" : {\n" +
                "          \"type\" : \"text\",\n" +
                "          \"fields\" : {\n" +
                "            \"keyword\" : {\n" +
                "              \"type\" : \"keyword\",\n" +
                "              \"ignore_above\" : 256\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"controlInstId\" : {\n" +
                "          \"type\" : \"text\",\n" +
                "          \"fields\" : {\n" +
                "            \"keyword\" : {\n" +
                "              \"type\" : \"keyword\",\n" +
                "              \"ignore_above\" : 256\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"domain\" : {\n" +
                "          \"type\" : \"text\",\n" +
                "          \"fields\" : {\n" +
                "            \"keyword\" : {\n" +
                "              \"type\" : \"keyword\",\n" +
                "              \"ignore_above\" : 256\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"execDesc\" : {\n" +
                "          \"type\" : \"text\",\n" +
                "          \"fields\" : {\n" +
                "            \"keyword\" : {\n" +
                "              \"type\" : \"keyword\",\n" +
                "              \"ignore_above\" : 256\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"execStatus\" : {\n" +
                "          \"type\" : \"text\",\n" +
                "          \"fields\" : {\n" +
                "            \"keyword\" : {\n" +
                "              \"type\" : \"keyword\",\n" +
                "              \"ignore_above\" : 256\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"execTime\" : {\n" +
                "          \"type\" : \"long\"\n" +
                "        },\n" +
                "        \"faultReason\" : {\n" +
                "          \"type\" : \"text\",\n" +
                "          \"fields\" : {\n" +
                "            \"keyword\" : {\n" +
                "              \"type\" : \"keyword\",\n" +
                "              \"ignore_above\" : 256\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"invokeDesc\" : {\n" +
                "          \"type\" : \"text\",\n" +
                "          \"fields\" : {\n" +
                "            \"keyword\" : {\n" +
                "              \"type\" : \"keyword\",\n" +
                "              \"ignore_above\" : 256\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"invokeStatus\" : {\n" +
                "          \"type\" : \"text\",\n" +
                "          \"fields\" : {\n" +
                "            \"keyword\" : {\n" +
                "              \"type\" : \"keyword\",\n" +
                "              \"ignore_above\" : 256\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"invokeTime\" : {\n" +
                "          \"type\" : \"date\",\n" +
                "\t\t  \"format\": \"yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis\"\n" +
                "        },\n" +
                "        \"logNeName\" : {\n" +
                "          \"type\" : \"text\",\n" +
                "          \"fields\" : {\n" +
                "            \"keyword\" : {\n" +
                "              \"type\" : \"keyword\",\n" +
                "              \"ignore_above\" : 256\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"msgId\" : {\n" +
                "          \"type\" : \"text\",\n" +
                "          \"fields\" : {\n" +
                "            \"keyword\" : {\n" +
                "              \"type\" : \"keyword\",\n" +
                "              \"ignore_above\" : 256\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"neType\" : {\n" +
                "          \"type\" : \"text\",\n" +
                "          \"fields\" : {\n" +
                "            \"keyword\" : {\n" +
                "              \"type\" : \"keyword\",\n" +
                "              \"ignore_above\" : 256\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"omanual\" : {\n" +
                "          \"type\" : \"text\",\n" +
                "          \"fields\" : {\n" +
                "            \"keyword\" : {\n" +
                "              \"type\" : \"keyword\",\n" +
                "              \"ignore_above\" : 256\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"onlineTime\" : {\n" +
                "          \"type\" : \"date\",\n" +
                "          \"format\": \"yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis\"\n" +
                "        },\n" +
                "        \"operateType\" : {\n" +
                "          \"type\" : \"long\"\n" +
                "        },\n" +
                "        \"phyNeId\" : {\n" +
                "          \"type\" : \"text\",\n" +
                "          \"fields\" : {\n" +
                "            \"keyword\" : {\n" +
                "              \"type\" : \"keyword\",\n" +
                "              \"ignore_above\" : 256\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"phyNeName\" : {\n" +
                "          \"type\" : \"text\",\n" +
                "          \"fields\" : {\n" +
                "            \"keyword\" : {\n" +
                "              \"type\" : \"keyword\",\n" +
                "              \"ignore_above\" : 256\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"reqFrom\" : {\n" +
                "          \"type\" : \"text\",\n" +
                "          \"fields\" : {\n" +
                "            \"keyword\" : {\n" +
                "              \"type\" : \"keyword\",\n" +
                "              \"ignore_above\" : 256\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"responseTime\" : {\n" +
                "          \"type\" : \"date\",\n" +
                "\t\t  \"format\": \"yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis\"\n" +
                "        },\n" +
                "        \"serialId\" : {\n" +
                "          \"type\" : \"long\"\n" +
                "        },\n" +
                "        \"servCode\" : {\n" +
                "          \"type\" : \"text\",\n" +
                "          \"fields\" : {\n" +
                "            \"keyword\" : {\n" +
                "              \"type\" : \"keyword\",\n" +
                "              \"ignore_above\" : 256\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"servDesc\" : {\n" +
                "          \"type\" : \"text\",\n" +
                "          \"fields\" : {\n" +
                "            \"keyword\" : {\n" +
                "              \"type\" : \"keyword\",\n" +
                "              \"ignore_above\" : 256\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"servExecTime\" : {\n" +
                "          \"type\" : \"long\"\n" +
                "        },\n" +
                "        \"servName\" : {\n" +
                "          \"type\" : \"text\",\n" +
                "          \"fields\" : {\n" +
                "            \"keyword\" : {\n" +
                "              \"type\" : \"keyword\",\n" +
                "              \"ignore_above\" : 256\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"swsId\" : {\n" +
                "          \"type\" : \"text\",\n" +
                "          \"fields\" : {\n" +
                "            \"keyword\" : {\n" +
                "              \"type\" : \"keyword\",\n" +
                "              \"ignore_above\" : 256\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"vendor\" : {\n" +
                "          \"type\" : \"text\",\n" +
                "          \"fields\" : {\n" +
                "            \"keyword\" : {\n" +
                "              \"type\" : \"keyword\",\n" +
                "              \"ignore_above\" : 256\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"version\" : {\n" +
                "          \"type\" : \"text\",\n" +
                "          \"fields\" : {\n" +
                "            \"keyword\" : {\n" +
                "              \"type\" : \"keyword\",\n" +
                "              \"ignore_above\" : 256\n" +
                "            }\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "  }";
        createIndexRequest.mapping(mapping,XContentType.JSON);
        client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        client.close();
    }

    /**
     * map方式创建索引 太麻烦
     * @throws IOException
     */
    public static void createIndex2() throws IOException {
        final RestHighLevelClient client = getConnection();
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("test2");
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("test2");
        client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);

        createIndexRequest.settings(Settings.builder().put("index.number_of_shards", 5));

        Map<String, String> nameType = new HashMap<>();
        nameType.put("type", "text");
        nameType.put("analyzer", "ik_max_word");

        Map<String, Object> name = new HashMap<>();
        name.put("name", nameType);

        Map<String, String> publishType = new HashMap<>();
        publishType.put("type", "keyword");
        publishType.put("analyzer", "ik_smart");

        Map<String, Object> publish = new HashMap<>();
        publish.put("publish", publishType);

        Map<String, Object> properties = new HashMap<>();
        properties.put("properties", name);

        createIndexRequest.mapping(properties);

        client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        client.close();
    }

    /**
     * 通过XContentBuilder 创建索引
     * @throws IOException
     */
    public static void createIndex3() throws IOException {
        final RestHighLevelClient client = getConnection();

        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("test3");
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("test3");
        client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);

        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        builder.startObject("properties");
        builder.startObject("name");
        builder.startObject("title");
        builder.field("type", "text");
        builder.field("analyzer", "ik_max_word");
        builder.endObject();
        builder.endObject();
        builder.endObject();
        builder.endObject();

        createIndexRequest.mapping(builder);

        client.indices().create(createIndexRequest, RequestOptions.DEFAULT);

        client.close();
    }
}
