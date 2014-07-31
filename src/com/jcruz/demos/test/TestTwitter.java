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
import com.oracle.json.JsonObject;
import com.oracle.json.JsonReader;
import com.jcruz.utils.StringUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import javax.microedition.midlet.MIDlet;
import com.jcruz.utils.oauth.OAuthMessage;
import com.jcruz.utils.Util;

/**
 *
 * @author jcruz
 */
public class TestTwitter extends MIDlet {

    static String consumerKeyStr = "iXMe961nayGZH28Bwhh8s6oWK";
    static String consumerSecretStr = "e3atmSHzqpQF5ih2d053fgFTrLobJbbW72kVKP0j8o3mapDbkc";
    static String accessTokenStr = "2678087965-oqw0l3w8UJWphCHhafvTsp2hCAqgfju27loI6vA";
    static String accessTokenSecretStr = "FAC4OG3tjAJ3YJfIZhkdFmaaj7ebHM4OIsy3yJH6dpn72";

    @Override
    public void startApp() {

        //TestPost();
        //System.out.println(getFollowersInfo());
        TestStream();
    }

    private void TestPost() {
        String requestURI = "https://api.twitter.com/1.1/direct_messages/new.json";
        String requestQuery = "screen_name=josecruzpruebas&text=Prueba de Msg 17";

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

    private void CreateFriend(String screen_name) {
        String requestURI = "https://api.twitter.com/1.1/friendships/create.json";
        String requestQuery = "screen_name=" + screen_name;

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
    }

    public void TestStream() {
        String requestURI = "https://userstream.twitter.com/1.1/user.json";
        String requestQuery = "stall_warnings=false";
        InputStream is = null;
        //JsonObject json = null;
        JsonObject json = null;

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
        JsonReader jsonReader = null;
        for (int msgRead = 0; msgRead < 100; msgRead++) {
            is = response.getBodyStream();

            jsonReader = Json.createReader(is);
            json = jsonReader.readObject();

            if (json != null) {
                //TwitterJsonObjectType.Type event = TwitterJsonObjectType.determine(json);
//                switch (event) {
//                    case SENDER:
//                        onSender(json, listeners);
//                        break;
//                    case STATUS:
//                        onStatus(json, listeners);
//                        break;
//                    case DIRECT_MESSAGE:
//                        onDirectMessage(json, listeners);
//                        break;
//                    case DELETE:
//                        onDelete(json, listeners);
//                        break;
//                    case LIMIT:
//                        onLimit(json, listeners);
//                        break;
//                    case STALL_WARNING:
//                        onStallWarning(json, listeners);
//                        break;
//                    case SCRUB_GEO:
//                        onScrubGeo(json, listeners);
//                        break;
//                    case FRIENDS:
//                        onFriends(json, listeners);
//                        break;
//                    case FAVORITE:
//                        onFavorite(json.getJSONObject("source"), json.getJSONObject("target"), json.getJSONObject("target_object"), listeners);
//                        break;
//                    case UNFAVORITE:
//                        onUnfavorite(json.getJSONObject("source"), json.getJSONObject("target"), json.getJSONObject("target_object"), listeners);
//                        break;
//                    case FOLLOW:
//                        onFollow(json.getJSONObject("source"), json.getJSONObject("target"), listeners);
//                        break;
//                    case UNFOLLOW:
//                        onUnfollow(json.getJSONObject("source"), json.getJSONObject("target"), listeners);
//                        break;
//                    case USER_LIST_MEMBER_ADDED:
//                        onUserListMemberAddition(json.getJSONObject("target"), json.getJSONObject("source"), json.getJSONObject("target_object"), listeners);
//                        break;
//                    case USER_LIST_MEMBER_DELETED:
//                        onUserListMemberDeletion(json.getJSONObject("target"), json.getJSONObject("source"), json.getJSONObject("target_object"), listeners);
//                        break;
//                    case USER_LIST_SUBSCRIBED:
//                        onUserListSubscription(json.getJSONObject("source"), json.getJSONObject("target"), json.getJSONObject("target_object"), listeners);
//                        break;
//                    case USER_LIST_UNSUBSCRIBED:
//                        onUserListUnsubscription(json.getJSONObject("source"), json.getJSONObject("target"), json.getJSONObject("target_object"), listeners);
//                        break;
//                    case USER_LIST_CREATED:
//                        onUserListCreation(json.getJSONObject("source"), json.getJSONObject("target_object"), listeners);
//                        break;
//                    case USER_LIST_UPDATED:
//                        onUserListUpdated(json.getJSONObject("source"), json.getJSONObject("target_object"), listeners);
//                        break;
//                    case USER_LIST_DESTROYED:
//                        onUserListDestroyed(json.getJSONObject("source"), json.getJSONObject("target_object"), listeners);
//                        break;
//                    case USER_UPDATE:
//                        onUserUpdate(json.getJSONObject("source"), json.getJSONObject("target"), listeners);
//                        break;
//                    case BLOCK:
//                        onBlock(json.getJSONObject("source"), json.getJSONObject("target"), listeners);
//                        break;
//                    case UNBLOCK:
//                        onUnblock(json.getJSONObject("source"), json.getJSONObject("target"), listeners);
//                        break;
//                    case DISCONNECTION:
//                        onDisconnectionNotice(line, listeners);
//                        break;
//                    case UNKNOWN:
//                    default:
//                        logger.warn("Received unknown event:", CONF.getHttpClientConfiguration().isPrettyDebugEnabled() ? json.toString(1) : json.toString());
//                }
                System.out.println(json);

                if (json.containsKey("event")) {
                    if (json.getString("event").equals("follow")) {
                        if (!json.getJsonObject("source").getString("screen_name").equals("jrobotpi")) {
                            CreateFriend(json.getJsonObject("source").getString("screen_name"));
                        }
                    }
                }

                if (json.getJsonObject("direct_message") != null) {
                    System.out.print("De:" + json.getJsonObject("direct_message").getJsonObject("sender").getString("name"));
                    System.out.println(" Msg:" + json.getJsonObject("direct_message").getString("text"));
                    if (json.getJsonObject("direct_message").getString("text").equals("Close"))
                        break;
                }

            }
        }

        if (is != null) {
            try {
                jsonReader.close();
                is.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        destroyApp(false);
    }

    @Override
    public void destroyApp(boolean unconditional) {
        notifyDestroyed();
    }
}
