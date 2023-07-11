//package com.zlx.es.start;
//
//import lombok.SneakyThrows;
//import org.apache.http.HttpHost;
//import org.apache.http.auth.AuthScope;
//import org.apache.http.auth.UsernamePasswordCredentials;
//import org.apache.http.impl.client.BasicCredentialsProvider;
//import org.elasticsearch.client.*;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.concurrent.TimeUnit;
//
///**
// * low level client query
// */
//public class LowLevelQuery {
//    public static void main(String[] args) throws IOException {
////        query1();
////        query2();
////        query3();
//        query4();
//    }
//
//    /**
//     * 创建es连接
//     * @return
//     */
//    public static RestClientBuilder getConnection(){
//        final BasicCredentialsProvider basicCredentialsProvider = new BasicCredentialsProvider();
//        basicCredentialsProvider.setCredentials(AuthScope.ANY,new UsernamePasswordCredentials("caikong", "caikong_Elastic#1275"));
//
//        return RestClient.builder(new HttpHost("133.0.120.193",9200,"http"))
//                .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder
//                        .setDefaultCredentialsProvider(basicCredentialsProvider))
//                .setRequestConfigCallback(builder -> builder
//                        .setSocketTimeout(180*1000)
//                        .setConnectionRequestTimeout(180*1000)
//                        .setConnectTimeout(180*1000));
//    }
//
//    /**
//     * 同步查询
//     * @throws IOException
//     */
//    public static void query1() throws IOException {
//        // 1 获取连接
//        final RestClientBuilder builder = getConnection();
//        // 设置请求头 如认证等相关信息
////        builder.setDefaultHeaders(new Header[]{new BasicHeader("key", "value")});
//        final RestClient client = builder.build();
//
//        // 构建请求
//        final Request request = new Request("GET", "/books/_search");
//
//        // 添加请求参数
//        request.addParameter("pretty","true");
//
//        // 发起请求 阻塞式
//        final Response response = client.performRequest(request);
//
//        // 解析返回结果
//        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
//            String line = null;
//            while ((line = bufferedReader.readLine())!= null) {
//                System.out.println(line);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            client.close();
//        }
//    }
//
//    /**
//     * 异步查询
//     * @throws IOException
//     */
//    public static void query2()  throws IOException{
//        // 获取连接
//        final RestClientBuilder builder = getConnection();
//        // 创建客户端
//        final RestClient client = builder.build();
//
//        // 构建请求
//        final Request request = new Request("GET", "/books/_search");
//
//        // 添加参数
//        request.addParameter("pretty", "true");
//
//        // 发起异步查询请求
//        client.performRequestAsync(request, new ResponseListener() {
//            @SneakyThrows
//            @Override
//            public void onSuccess(Response response) {
//                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
//                    String line = null;
//                    while ((line = bufferedReader.readLine()) != null) {
//                        System.out.println(line);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }finally {
//                    client.close();
//                }
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//                System.out.println("查询失败");
//            }
//        });
//    }
//
//    /**
//     * 携带请求体查询： 自定义查询json
//     */
//    public static void query3() {
//        final RestClientBuilder builder = getConnection();
//        final RestClient client = builder.build();
//
//        final Request request = new Request("GET", "/books/_search");
//
//        request.addParameter("pretty", "true");
//
//        request.setJsonEntity("{\n" +
//                "  \"query\": {\n" +
//                "    \"terms\": {\n" +
//                "      \"name\": [\n" +
//                "        \"高等\",\n" +
//                "        \"线性代数\"\n" +
//                "      ]\n" +
//                "    }\n" +
//                "  }\n" +
//                "}");
//        client.performRequestAsync(request, new ResponseListener() {
//            @SneakyThrows
//            @Override
//            public void onSuccess(Response response) {
//                try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
//                    String line = null;
//                    while ((line = bufferedReader.readLine()) != null) {
////                        System.out.println(response.getEntity().getContent().toString());
//                        System.out.println(line);
//                    }
//                }finally {
//                    client.close();
//                }
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//                System.out.println("查询失败");
//            }
//        });
//    }
//
//    public static void query4() throws IOException {
//        final RestClientBuilder builder = getConnection();
//        final RestClient client = builder.build();
//
//        Request request = new Request("GET", "/ck_request_record/_search");
//        request.addParameter("pretty", "true");
//
//        String queryJson = """
//                {
//                  "from": 0,
//                	"size": 10000,
//                	"query": {
//                		"bool": {
//                			"must": [
//                				{
//                					"range": {
//                						"invokeTime": {
//                							"from": "2008-05-05 10:25:01",
//                							"to": "2022-06-30 10:25:01",
//                							"include_lower": true,
//                							"include_upper": true,
//                							"boost": 1.0
//                						}
//                					}
//                				}
//                			],
//                			"adjust_pure_negative": true,
//                			"boost": 1.0
//                		}
//                	},
//                	"version": true,
//                	"aggregations": {
//                		"groupByDomain": {
//                			"terms": {
//                				"field": "domain.keyword",
//                				"size": 100000,
//                				"min_doc_count": 1,
//                				"shard_min_doc_count": 0,
//                				"show_term_doc_count_error": false,
//                				"order": [
//                					{
//                						"_count": "desc"
//                					},
//                					{
//                						"_key": "asc"
//                					}
//                				]
//                			},
//                			"aggregations": {
//                				"subInvokeTime": {
//                					"date_histogram": {
//                						"field": "invokeTime",
//                						"format": "yyy-MM-dd HH:mm:ss",
//                						"interval": "1d",
//                						"offset": 0,
//                						"order": {
//                							"_key": "asc"
//                						},
//                						"keyed": false,
//                						"min_doc_count": 0,
//                						"extended_bounds": {
//                							"min": "2008-05-05 10:25:01",
//                							"max": "2022-06-30 10:25:01"
//                						}
//                					},
//                					"aggregations": {
//                						"avgExecTime": {
//                							"avg": {
//                								"field": "execTime"
//                							}
//                						},
//                						"exceedLimitSum": {
//                							"filter": {
//                								"range": {
//                									"execTime": {
//                										"from": 2000,
//                										"to": null,
//                										"include_lower": true,
//                										"include_upper": true,
//                										"boost": 1.0
//                									}
//                								}
//                							}
//                						},
//                						"subInvokeStatus": {
//                							"terms": {
//                								"field": "invokeStatus.keyword",
//                								"size": 10000,
//                								"min_doc_count": 1,
//                								"shard_min_doc_count": 0,
//                								"show_term_doc_count_error": false,
//                								"order": [
//                									{
//                										"_count": "desc"
//                									},
//                									{
//                										"_key": "asc"
//                									}
//                								]
//                							}
//                						},
//                						"subExecStatus": {
//                							"terms": {
//                								"field": "execStatus.keyword",
//                								"size": 10000,
//                								"min_doc_count": 1,
//                								"shard_min_doc_count": 0,
//                								"show_term_doc_count_error": false,
//                								"order": [
//                									{
//                										"_count": "desc"
//                									},
//                									{
//                										"_key": "asc"
//                									}
//                								]
//                							}
//                						}
//                					}
//                				}
//                			}
//                		}
//                	}
//                }
//                """;
//        request.setJsonEntity(queryJson);
//
//        final Response response = client.performRequest(request);
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//        String line = null;
//        while ((line = bufferedReader.readLine()) != null) {
////                        System.out.println(response.getEntity().getContent().toString());
//            System.out.println(line);
//        }
//
//        client.close();
//        bufferedReader.close();
//    }
//}
