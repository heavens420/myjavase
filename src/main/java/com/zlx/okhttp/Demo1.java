package com.zlx.okhttp;

import okhttp3.*;
import okio.BufferedSink;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Demo1 {
    public static void main(String[] args) throws IOException, InterruptedException {
//        syncGet();
        asyncGet();
//        syncPost();
//        asyncPost2();
//        asyncPost3();
//        asyncPost4();
    }


    public static void syncGet() throws IOException, InterruptedException {
        // 请求地址
        String url = "https://www.baidu.com";
        // 创建客户端对象
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();
//        OkHttpClient client = new OkHttpClient();
        // 创建请求对象
        Request request = new Request.Builder().url(url).build();

        // 创建调用对象
        Call call = client.newCall(request);
        // 创建返回结果对象
        Response response = call.execute();
        // 打印请求返回
        System.out.println(Objects.requireNonNull(response.body()).string());
    }

    public static void asyncGet() throws InterruptedException {
        String url = "https://www.baidu.com";
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();
        Request request = new Request.Builder().url(url)
                .header("Content-Type","application/json;charset=utf-8")
                .build();

        Call call = client.newCall(request);

        // 创建异步回调对象
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("请求失败");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                System.out.println(response.headers());
                System.out.println("----------------------");
                System.out.println(response.body().string());
            }
        });
        System.out.println(Thread.currentThread().getName());
        // 主线程等待主线程结束再打印主线程最后的内容 --> 死锁
//        Thread.currentThread().join();
        System.out.println("请求结束");
        System.out.println(request.headers());
    }


    /**
     * post 提交字符串
     *
     * @throws IOException
     * @throws InterruptedException
     */
    public static void asyncPost1() throws IOException, InterruptedException {
        MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");
        String requestBody = "nihao";
        String url = "https://api.github.com/markdown/raw";
        RequestBody body;
        // 3和4写法不同
//        Request request = new Request.Builder().url(url).post(RequestBody.create(mediaType,requestBody)).build();
        Request request = new Request.Builder().url(url).post(RequestBody.create(requestBody, mediaType)).build();

        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("请求失败");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Headers headers = response.headers();
                headers.forEach(header -> {
                    System.out.println(header.getFirst() + ":" + header.getSecond());
                });
                System.out.println("-----------------------------------------------");
                System.out.println(response.body().string());
            }
        });
        Thread.currentThread().join(12);
        System.out.println("over");
    }

    /**
     * post 提交流数据
     */
    public static void asyncPost2() {
        String url = "https://api.github.com/markdown/raw";
        RequestBody requestBody = new RequestBody() {
            @Nullable
            @Override
            public MediaType contentType() {
                return MediaType.parse("text/x-markdown; charset=utf-8");
            }

            @Override
            public void writeTo(@NotNull BufferedSink bufferedSink) throws IOException {
                bufferedSink.writeUtf8("nihaoazheshijie");
            }
        };

        Request request = new Request.Builder().url(url).post(requestBody).build();

        OkHttpClient client = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("请求失败");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Headers headers = response.headers();
                headers.forEach(header -> {
                    System.out.println(header.getFirst() + ":" + header.getSecond());
                });
                System.out.println("------------------------------------");
                System.out.println(response.body().string());
            }
        });
    }


    /**
     * post 提交文件
     */
    public static void asyncPost3() {
        String url = "https://api.github.com/markdown/raw";
        MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        File file = new File("C:\\workspace\\java\\myproject\\myjavase\\src\\main\\java\\com\\zlx\\thread\\T1.java");

        Request request = new Request.Builder().url(url).post(RequestBody.create(file, mediaType)).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("请求失败");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Headers headers = response.headers();
                headers.forEach(header -> {
                    System.out.println(header.getFirst() + ":" + header.getSecond());
                });
                System.out.println("--------------------------------------------");
                System.out.println(response.body().string());
            }
        });
    }

    /**
     * post 发送表单请求
     */
    public static void asyncPost4() {
        String url = "http://192.168.81.198:31211/security-core/sys/login";
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("username", "zhangsan")
                .add("password", "hahahah")
                .add("key", "123")
                .build();

        Request request = new Request.Builder().url(url)
                .header("Content-Type","application/json;charset=utf-8")
                .post(requestBody).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("请求失败");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                System.out.println("111" + response.message());
                Headers headers = response.headers();
                headers.forEach(header -> {
                    System.out.println(header.getFirst() + ":" + header.getSecond());
                });
                System.out.println("----------------------------------------------");
                System.out.println(response.body().string());
            }
        });
    }

    /**
     * post 发送同步表单请求
     * @throws IOException
     */
    public static void syncPost() throws IOException {
        String url = "https://www.baidu.com";
        RequestBody requestBody = new FormBody.Builder()
                .add("name","zs")
                .add("passwd","sjs")
                .build();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        final Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    /**
     * post 发送复杂请求
     */
    private static void postMultipartBody() {
        final String IMGUR_CLIENT_ID = "...";
        final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

        OkHttpClient client = new OkHttpClient();


        // Use the imgur image upload API as documented at https://api.imgur.com/endpoints/image
        MultipartBody body = new MultipartBody.Builder("AaB03x")
                .setType(MultipartBody.FORM)
                .addPart(
                        Headers.of("Content-Disposition", "form-data; name=\"title\""),
                        RequestBody.create("Square Logo",null))
                .addPart(
                        Headers.of("Content-Disposition", "form-data; name=\"image\""),
                        RequestBody.create( new File("website/static/logo-square.png"),MEDIA_TYPE_PNG))
                .build();

        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
                .url("https://api.imgur.com/3/image")
                .post(body)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());

            }
        });
    }
}
