package com.example.demo1.utils;


import com.alibaba.fastjson.JSONObject;
import net.sf.json.JSONException;

import java.io.*;
import java.net.InetAddress;
import java.net.URL;
import java.nio.charset.Charset;

public class GetAddress {
    public static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject parse = JSONObject.parseObject(jsonText);
            return parse;
        } finally {
            is.close();
            // System.out.println("同时 从这里也能看出 即便return了，仍然会执行finally的！");
        }
    }

    public static void main(String[] args) throws IOException, JSONException {
        //这里调用百度的ip定位api服务 详见 http://api.map.baidu.com/lbsapi/cloud/ip-location-api.htm
        String addr = InetAddress.getLocalHost().getHostAddress();//获得本机IP
        System.out.println(addr);
        JSONObject json = readJsonFromUrl("http://api.map.baidu.com/location/ip?ak=F454f8a5efe5e577997931cc01de3974&ip="+addr);
        System.out.println(json.toString());
        System.out.println(((JSONObject) json.get("content")).get("address"));
    }

}
