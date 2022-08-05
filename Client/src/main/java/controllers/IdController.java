package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.util.EntityUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class IdController {
    private HashMap<String, Id> allIds;
    Id myId;
    Id newId;
    ObjectMapper mapper = new ObjectMapper();
    ServerController sc = ServerController.getServerInstance();


    public ArrayList<Id> getIds() throws IOException {
        ArrayList<Id> idList = new ArrayList<>();
        sc.getURL("http://zipcode.rocks:8085/ids");
        Integer status = sc.handleResponse(sc.httpResponse);    //http response code, successful or failed connection
        HttpEntity httpEntity = sc.httpResponse.getEntity();    //page content from http get request
        int count =0;

        System.out.println("status: " +status);
//        if (status >=200 && status <300) {  //status code 200-299 successful?
//        }
        ArrayList<Object> list;
        list = mapper.readValue(EntityUtils.toString(httpEntity), new TypeReference<ArrayList<Object>>() {});

        count=list.size();
        System.out.println("count: "+count);
        for (Object obj : list) {
//            System.out.println("#"+list.indexOf(obj));
            String objStr = obj.toString();
//            System.out.println(objStr);

            String id = objStr.substring(objStr.indexOf("id=")+3, objStr.indexOf(","));
//            System.out.println("id -> "+id);

            String name = objStr.substring(objStr.indexOf("name=")+5, objStr.lastIndexOf(","));
//            System.out.println("name -> "+name);

            String github = objStr.substring(objStr.indexOf("github=")+7, objStr.length()-1);
//            System.out.println("github -> "+github);

//            System.out.println("");
            newId = new Id(id,name,github);
            idList.add(newId);
        }
        return idList;
    }

    public Id postId(Id id) {
        // create json from id
        // call server, get json result Or error
        // result json to Id obj

        return null;
    }

    public Id putId(Id id) {
        return null;
    }

}