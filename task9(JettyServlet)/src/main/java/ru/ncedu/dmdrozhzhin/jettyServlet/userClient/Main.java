package ru.ncedu.dmdrozhzhin.jettyServlet.userClient;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
     /*
      PostMethod post = new PostMethod("http://localhost:8080");
        NameValuePair[] nameValuePairs = {

                new NameValuePair("type", userClient.getAction()),
                new NameValuePair("login", userClient.getLogin()),
                new NameValuePair("password", userClient.getPass()),
                new NameValuePair("balance",userClient.getBalance())
        };
        post.setRequestBody(data2);
        //HttpClient client = new HttpClient();
        //client.executeMethod(post);*/

        UserClient userClient = new UserClient();
        userClient.start();
        System.out.println("login " + userClient.getLogin() + "\n pass " + userClient.getPass()
                + "\n action " + userClient.getAction() + "\n balance " + userClient.getBalance());

        String responce = new String();
        List<NameValuePair> nameValuePairs =
                new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("type", userClient.getAction()));
        nameValuePairs.add(new BasicNameValuePair("login", userClient.getLogin()));
        nameValuePairs.add(new BasicNameValuePair("password", userClient.getPass()));
        nameValuePairs.add(new BasicNameValuePair("balance", userClient.getBalance()));

        HttpEntity requestEntity = new UrlEncodedFormEntity(nameValuePairs);

        HttpPost httpPost = new HttpPost("http://localhost:8080");
        httpPost.setEntity(requestEntity);
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity responceEntity = httpResponse.getEntity();

        if (requestEntity != null) {
            responce = EntityUtils.toString(responceEntity);
            System.out.println(responce);
        } else {
            System.out.println("Not answer");
        }

        ((DefaultHttpClient) httpClient).close();
    }
}
