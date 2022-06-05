package com.zlx.okhttp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import okio.BufferedSink;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Demo2 {

    private static String cookie = "uuid_tt_dd=10_10321240420-1645262066585-809784; ssxmod_itna=euD=0KD5AIxIEmDzaKR2xxUxmqDIxDOlb/UnlUDBk7r4iNDnD8x7YDvmIwb5qHYErPi5HBQ+PoNffjCxRpEjDB3DEx0=5hAYxiiyDCeDIDWeDiDG4GmU4GtDpxG=Dj7I=lBAxYPGWQqDoDYRQ0DitD4qDB+E3DKqGgQMGrQd0f3l6KkuhDo0QDjMrD/8xrZ0je56WNfdito7IinDDHKO9xl0wOTihxBTP0DB6GxBjBzuUL8C9nUTt/mYm4Wr8oSO+P72hx3EDNCYq3Yid=RYyAZYqY4gqHji3LxD3C67+i4rD===; ssxmod_itna2=euD=0KD5AIxIEmDzaKR2xxUxmqDIxDOlb/UnlD8dZ2xGX2eGaBIPo1x8Oi9TzYhtz/DfdWXs8e/My4HekUmAR1YTlf3X+OpaA0TIeDLcOa+sht0gr+tWDqO242VzY/xH9bS+TCp9v3XMCrqVCx0s0uxqOkXPo4XK5iooEn05yTRc7lRx2CbBY3YstfDtDkrQQbKMD=vgFBvGRE0xGW5kU=vOSucCpav7vTcHpgY+gg7pGf+QDd9iz2vWpULZI/8ObPNjx6MTZoFci=rRapnjc8Owd5Z+bwM00kAZA5nw0yEgt9qHGtf86vDff4PY4QYkPKpW0ZGYaaoHlop7XgOYsBqtBen6DviXijt6G5unefY2GOA+ELOeACQ+x6w72brGRq33h7XR0iH=51x2rS3MrPMpGWDG2424Ci5ob=/h5GFbMAF8f6+a4SH4qrxGWsBGKI7R98+Sux4hdbAqWw2+FHFi4I5Nbdn7LqQkFu8Yjuxz0OTKzcMDD08Dij/gxeD+/9GvQDtlP4WhvCGHiDN0=G7HkOFiWD35xKvyu/KNBDynyiQGvDhu/r0pH0FD=zp/F4aMDxD=; UserName=heavens420; UserInfo=11adbbad14bd475ea6bf490df18009ec; UserToken=11adbbad14bd475ea6bf490df18009ec; UserNick=heavens420; AU=AEE; UN=heavens420; BT=1646790148936; p_uid=U010000; Hm_up_6bcd52f51e9b3dce32bec4a3997715ac={\"islogin\":{\"value\":\"1\",\"scope\":1},\"isonline\":{\"value\":\"1\",\"scope\":1},\"isvip\":{\"value\":\"0\",\"scope\":1},\"uid_\":{\"value\":\"heavens420\",\"scope\":1}}; Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac=6525*1*10_10321240420-1645262066585-809784!5744*1*heavens420; c_dl_prid=1648204025883_966542; c_dl_rid=1649665580257_674591; c_dl_fref=https://blog.csdn.net/qq_32053351/article/details/105584301; c_dl_fpage=/download/weixin_38537541/12941962; c_dl_um=distribute.pc_relevant_t0.none-task-blog-2~default~CTRLIST~Rate-1.pc_relevant_paycolumn_v3; firstDie=1; c_first_ref=www.baidu.com; c_segment=8; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1649298867,1649382233,1649641844,1649726148; dc_sid=c950e51905aa192ba96e65b9d561711e; dc_session_id=11_1649749042275.146070; c_utm_term=jackson文档; c_first_page=https://blog.csdn.net/weixin_39608116/article/details/114529462; log_Id_click=217; c_pref=https://blog.csdn.net/weixin_34558565/article/details/113009381?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_baidulandingword~default-0.pc_relevant_antiscanv2&spm=1001.2101.3001.4242.1&utm_relevant_index=3; c_ref=https://blog.csdn.net/weixin_39608116/article/details/114529462; c_utm_medium=distribute.pc_relevant.none-task-blog-2~default~BlogCommendFromBaidu~Rate-2.pc_relevant_antiscanv2; c_utm_relevant_index=4; c_page_id=default; dc_tos=ra7x35; log_Id_pv=499; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1649752387; log_Id_view=1813";
    public static void main(String[] args) throws IOException {
        syncGet();
//        postStreaming();
    }

    /**
     * 同步get请求
     *
     * @throws IOException
     */
    public static void syncGet() throws IOException {
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("https://img-home.csdnimg.cn/data_json/toolbar/toolbar1105.json")
                .header("Content-Type", "application/json; charset=utf-8")
                .header("Cookie", cookie)
                .build();

        try (final Response response = client.newCall(request).execute()) {
            final Headers headers = response.headers();
            headers.forEach(System.out::println);

//            final Map map = (Map) JSONObject.parse(response.body().string());
            ObjectMapper objectMapper = new ObjectMapper();
            final Map<String, String> map = objectMapper.readValue(response.body().string(), new TypeReference<Map<String, String>>() {
            });
            map.forEach((k,v)-> System.out.println(k+"--->"+v));
        }
    }


    /**
     * 异步get请求
     */
    public static void asyncGet() {
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("https://square.github.io/okhttp/search/search_index.json")
                .header("Content-Type", "application/json; charset=utf-8")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (final ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) {
                        throw new IOException("异常");
                    }
                    response.headers().forEach(System.out::println);
                    System.out.println(responseBody.string());
                }
            }
        });
    }

    public static void postStreaming() throws IOException {
        final MediaType MEDIA_TYPE_MARKDOWN
                = MediaType.parse("text/x-markdown; charset=utf-8");

        final OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new RequestBody() {
            @Override
            public MediaType contentType() {
                return MEDIA_TYPE_MARKDOWN;
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                sink.writeUtf8("Numbers\n");
                sink.writeUtf8("-------\n");
                for (int i = 2; i <= 997; i++) {
                    sink.writeUtf8(String.format(" * %s = %s\n", i, factor(i)));
                }
            }

            private String factor(int n) {
                for (int i = 2; i < n; i++) {
                    int x = n / i;
                    if (x * i == n) {
                        return factor(x) + " × " + i;
                    }
                }
                return Integer.toString(n);
            }
        };

        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            System.out.println(response.body().string());
        }

    }

    /**
     * post请求发送 string参数
     */
    public static void postStringParam() {
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("")
                .post(RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"), "hahahah"))
                .build();
        try (final Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("error");
            }
            response.headers().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * post发送图片
     */
    public static void postFile(){
        final MediaType MEDIA_TYPE_MARKDOWN
                = MediaType.parse("text/x-markdown; charset=utf-8");

        final OkHttpClient client = new OkHttpClient();

            File file = new File("README.md");

            Request request = new Request.Builder()
                    .url("https://api.github.com/markdown/raw")
                    .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }

                System.out.println(response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    /**
     * post发送表单参数
     */
    public static void postFormParams(){
        OkHttpClient client = new OkHttpClient();
        final FormBody formBody = new FormBody.Builder()
                .add("a", "q")
                .add("v", "d")
                .build();
        final Request request = new Request.Builder()
                .url("")
                .post(formBody)
                .build();

        try (final Response response = client.newCall(request).execute()){
            System.out.println(response.body());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * post发送多参数
     */
    public static void postMultiPartParams(){
        OkHttpClient client = new OkHttpClient();
        final MultipartBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("1", "f")
                .addFormDataPart("sad", "fs")
                .build();
        final Request request = new Request.Builder()
                .url("")
                .post(multipartBody)
                .build();
    }

    public static void ParseResponseJson(){
        OkHttpClient client = new OkHttpClient();
//        Moshi moshi = new Moshi.Builder().build();
    }
}
