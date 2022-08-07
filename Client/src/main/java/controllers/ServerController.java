package controllers;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
//import java.net.MalformedURLException;
//import spiffyUrlManipulator;

public class ServerController implements ResponseHandler<Integer> {
    private final String rootURL = "https://zipcode.rocks:8085";
    private final String idResource = "/ids";
    private static ServerController server = new ServerController();
//    ObjectMapper objMapper = new ObjectMapper();

    CloseableHttpClient httpClient;
    HttpGet httpGet;
    HttpResponse httpResponse;

    public ServerController() {
    }

    public static ServerController getServerInstance() {
//        if (server==null) {
//            server = new ServerController();
//        }
        return server;
    }

//    public static shared() {
//        return server;
//    }

    public String getURL(String url) throws IOException {
        // url -> /ids/
        // send the server a get with url
        // return json from server
/*
        CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse response = client.execute(new HttpGet(url));
//        response.execute(new HttpGet(url));
        String getUrlResponse = EntityUtils.toString(response.getEntity());
        client.close();
*/
        httpClient = HttpClients.createDefault();
        httpGet = new HttpGet(url);
        String reqType = ("Request Type: " +httpGet.getMethod());
        this.httpResponse = httpClient.execute(httpGet);
//        System.out.println(httpResponse.getStatusLine().getStatusCode());
//        HttpResponse httpResponse = httpClient.execute(httpGet);

        return reqType;
    }

    @Override
    public Integer handleResponse(HttpResponse httpResponse) {
        return httpResponse.getStatusLine().getStatusCode();
    }
/*
    public JsonString idPost(Id) {
        // url -> /ids/
        // create json from Id
        // request
        // reply
        // return json
    }
    public JsonString idPut(Id) {
        // url -> /ids/
    }
*/


}

// ServerController.shared.doGet()