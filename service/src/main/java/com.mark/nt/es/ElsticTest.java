package com.mark.nt.es;

import com.mark.frame.FastJsonUtils;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lulei on 2016/9/30.
 */
public class ElsticTest {
    public static void main(String[] args) throws UnknownHostException {
        TransportClient client = TransportClient.builder().build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByAddress("192.168.154.96".getBytes()), 9300));


        Map<String, Object> json = new HashMap<String, Object>();
        json.put("user","kimchy");
        json.put("postDate",new Date());
        json.put("message","trying out Elasticsearch");
        IndexResponse response = client.prepareIndex("twitter", "tweet", "1")
                .setSource(FastJsonUtils.toJson(json))
                .get();
// on shutdown

        String _index = response.getIndex();
// Type name
        String _type = response.getType();
// Document ID (generated or not)
        String _id = response.getId();
// Version (if it's the first time you index this document, you will get: 1)
        long _version = response.getVersion();
// isCreated() is true if the document is a new one, false if it has been updated
        boolean created = response.isCreated();

        System.out.println(_index + "," + _type + "," + "," + _id + "," + _version + "," + created);
        client.close();
    }



}
