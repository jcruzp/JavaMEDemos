/*
 * Copyright (C) 2014 jcruz
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.jcruz.demos.test;

import com.oracle.httpclient.HttpClient;
import com.oracle.httpclient.HttpClientBuilder;
import com.oracle.httpclient.HttpHeader;
import com.oracle.httpclient.HttpMethod;
import com.oracle.httpclient.HttpRequest;
import com.oracle.httpclient.HttpResponse;
import com.oracle.json.Json;
import com.oracle.json.JsonArray;
import com.oracle.json.JsonException;
import com.oracle.json.JsonObject;
import com.oracle.json.JsonReader;
import com.substanceofcode.utils.StringUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import javax.microedition.midlet.MIDlet;
import net.oauth.j2me.OAuthMessage;
import net.oauth.j2me.Util;

/**
 *
 * @author jcruz
 */
public class TestTwitter extends MIDlet {

    static String consumerKeyStr = "nk1rMBWdjhZj21u6UN09OyknW";
    static String consumerSecretStr = "mEAFzeLrmLxxFRrPFyqj5m2la37K4PsNfdZ5hAMQmumI16JaLs";
    static String accessTokenStr = "1267971140-3PRdd67xMEst4AdOj8XSeTEGYpHdCdpEqTIFvbz";
    static String accessTokenSecretStr = "JNTVLlwQskyXT3mOUfuJNugrcl85WvjxGiFxTyDPgxGEF";

    @Override
    public void startApp() {
        String requestURI = "https://api.twitter.com/1.1/direct_messages/new.json";
        String requestQuery = "screen_name=josecruzpruebas&text=Prueba de Msg 16";

        OAuthMessage req = new OAuthMessage();
        req.setRequestMethod("POST");
        req.setRequestURL(requestURI);
        req.setConsumerKey(consumerKeyStr);
        req.setToken(accessTokenStr);
        req.setTokenSecret(accessTokenSecretStr);

        Hashtable qp = new Hashtable();
        String[] queries = Util.split(requestQuery, "&");
        String[] kv;
        for (int i = 0; i < queries.length; i++) {
            kv = Util.split(queries[i], "=");
            qp.put(kv[0], kv[1]);
        }

        req.setAdditionalProperties(qp);
        req.createSignature("HMAC-SHA1", consumerSecretStr);

        String header = req.convertToAuthorizationHeader();
        System.out.println("Authorization: " + header);

        HttpClient client = HttpClientBuilder.getInstance().build();
        HttpRequest request = client.build(requestURI + '?' + StringUtil.replace(requestQuery, " ", "%20"))
                .setMethod(HttpMethod.POST)
                .setHeader(HttpHeader.AUTHORIZATION, header)
                .build();

        HttpResponse response = request.invoke();

        System.out.println(response.getResponseCode());
        System.out.println(getFollowersInfo());
    }

    private OutputStream os = null;

    /**
     *
     * @return JsonObject with response from feed with channel info
     */
    public JsonArray getFollowersInfo() {
        //String requestURI = "https://api.twitter.com/1.1/followers/ids.json";
        //String requestQuery = "cursor=-1&screen_name=josecruzpruebas&count=5000";
        String requestURI = "https://api.twitter.com/1.1/direct_messages.json";
        String requestQuery = "count=5&include_entities=false&skip_status=true";
        InputStream is = null;
        //JsonObject json = null;
        JsonArray json = null;
        
        OAuthMessage req = new OAuthMessage();
        req.setRequestMethod("GET");
        req.setRequestURL(requestURI);
        req.setConsumerKey(consumerKeyStr);
        req.setToken(accessTokenStr);
        req.setTokenSecret(accessTokenSecretStr);

        if (requestQuery.trim().length() > 0) {
            Hashtable qp = new Hashtable();
            String[] queries = Util.split(requestQuery, "&");
            String[] kv;
            for (int i = 0; i < queries.length; i++) {
                kv = Util.split(queries[i], "=");
                qp.put(kv[0], kv[1]);
            }
            req.setAdditionalProperties(qp);
        }
        
        req.createSignature("HMAC-SHA1", consumerSecretStr);

        String header = req.convertToAuthorizationHeader();
        System.out.println("Authorization: " + header);

        HttpClient client = HttpClientBuilder.getInstance().build();
        HttpRequest request = client.build(requestURI + "?" + StringUtil.replace(requestQuery, " ", "%20"))
                .setMethod(HttpMethod.GET)
                .setHeader(HttpHeader.AUTHORIZATION, header)
                .build();

        HttpResponse response = request.invoke();

        System.out.println(response.getResponseCode());
        is = response.getBodyStream();

        try (JsonReader jsonReader = Json.createReader(is)) {
            json = jsonReader.readArray();
        }

        JsonObject home = json.getJsonObject(0);
        String msg = home.getString("text");
        System.out.println(msg);
        
        if (is != null) {
            try {
                is.close();
            } catch (IOException ex) {
            }
        }

        return json;
    }

    @Override
    public void destroyApp(boolean unconditional) {
    }
}
