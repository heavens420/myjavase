package com.zlx.es.start;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *  发送http请求查询es
 */
public class HttpQuery {
    public static void main(String[] args) throws IOException {
        query();
    }

    public static void query() throws IOException {
        URL url = new URL("http://81.68.241.197:50010/books/_search?pretty=true");
        HttpURLConnection connection = ((HttpURLConnection) url.openConnection());
        if (connection.getResponseCode() == 200) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            bufferedReader.close();
        }
    }
}
