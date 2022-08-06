package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class IdController {
    private HashMap<String, Id> allIds;
//    Id myId;
    Id newId;
    ObjectMapper mapper = new ObjectMapper();
    ServerController sc = ServerController.getServerInstance();
    ArrayList<Id> idList = new ArrayList<>();
    int count =0;


    public ArrayList<Id> getIds() throws IOException {
        sc.getURL("http://zipcode.rocks:8085/ids");
        Integer status = sc.handleResponse(sc.httpResponse);    //http response code, successful or failed connection
        HttpEntity httpEntity = sc.httpResponse.getEntity();    //page content from http get request

        System.out.println("status: " +status);
//        if (status >=200 && status <300) {  //status code 200-299 successful?
//        }
        ArrayList<Object> list;
        list = mapper.readValue(EntityUtils.toString(httpEntity), new TypeReference<ArrayList<Object>>() {});

        count=list.size();
        System.out.println("count: "+count);
        for (Object obj : list) {
            String objStr = obj.toString();

            String id = objStr.substring(objStr.indexOf("id=")+3, objStr.indexOf(","));
            String name = objStr.substring(objStr.indexOf("name=")+5, objStr.lastIndexOf(","));
            String github = objStr.substring(objStr.indexOf("github=")+7, objStr.length()-1);

//            System.out.println("#"+list.indexOf(obj));
//            System.out.println(objStr);
//            System.out.println("id -> "+id);
//            System.out.println("name -> "+name);
//            System.out.println("github -> "+github);

//            System.out.println("");
            newId = new Id(id,name,github);
            idList.add(newId);
        }
        return idList;
    }

    public Id postId(Id id) throws IOException {
        // create json from id
        // call server, get json result Or error
        // result json to Id obj
        HttpPost httpPost = new HttpPost("http://zipcode.rocks:8085/ids");
        Id addId = new Id("12345abcdefg3333","didntThinkIWouldGetThisFar", "TamyahD");
        StringEntity jsonId = new StringEntity(mapper.writeValueAsString(addId), ContentType.APPLICATION_JSON);

        httpPost.setEntity(jsonId);
        CloseableHttpResponse response = sc.httpClient.execute(httpPost);

        if (response.getStatusLine().getStatusCode() != 200) {
            System.out.println("Id not added to server: " + response.getStatusLine().getStatusCode());
        }
        return id;
    }

//    post message
//    /ids/githubName/messages
    public Id putId(Id id) {
        return null;
    }

}