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
import java.util.Hashtable;
import javax.microedition.midlet.MIDlet;
import net.oauth.j2me.OAuthMessage;
import net.oauth.j2me.Util;
import net.oauth.j2me.signature.HMACSHA1Signature;

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
      //  try {

//        HttpClient client = HttpClientBuilder.getInstance().build();
//        //?screen_name=josecruzpruebas&text=Version+3&oauth_consumer_key=nk1rMBWdjhZj21u6UN09OyknW&oauth_nonce=-5524422733686571724&oauth_signature=920HAJ%2FxHYV5anpwGOZnQBDiF%2Fc%3D&oauth_signature_method=HMAC-SHA1&oauth_timestamp=1405744585&oauth_token=1267971140-3PRdd67xMEst4AdOj8XSeTEGYpHdCdpEqTIFvbz&oauth_version=1.0")
//        HttpRequest request = client.build("https://api.twitter.com/1.1/direct_messages/new.json?screen_name=joseacruzp&text=Prueba+Msg+1")
//                .setMethod(HttpMethod.POST)
//                .setHeader(HttpHeader.AUTHORIZATION, "OAuth oauth_consumer_key=\"" + consumerKeyStr + "\", oauth_nonce=\"b139a7ddf3b37a97f8b08467914a426e\", oauth_signature=\"Foxa1EEUdZkuHnszqY8S%2FTzIVWQ%3D\", oauth_signature_method=\"HMAC-SHA1\", oauth_timestamp=\"1405835406\", oauth_token=\"" + accessTokenStr + "\", oauth_version=\"1.0\"")
//                .build();
//        HttpResponse response = request.invoke();
//        System.out.println(response.getResponseCode());
        
        HMACSHA1Signature signature= new HMACSHA1Signature();
        signature.setKey(consumerSecretStr + '&' + accessTokenSecretStr);
        signature.setMessage("POST&https%3A%2F%2Fapi.twitter.com%2F1.1%2Fdirect_messages%2Fnew.json&oauth_consumer_key%3Dnk1rMBWdjhZj21u6UN09OyknW%26oauth_nonce%3D1342473805051157753%26oauth_signature_method%3DHMAC-SHA1%26oauth_timestamp%3D1405976539%26oauth_token%3D1267971140-3PRdd67xMEst4AdOj8XSeTEGYpHdCdpEqTIFvbz%26oauth_version%3D1.0%26screen_name%3Djosecruzpruebas%26text%3DMsg%25207");
        System.out.println("**************" + signature.getSignature());
        
        
        String endpoint="https://api.twitter.com/1.1/direct_messages/new.json?screen_name=josecruzpruebas&text=Msg+7";
        OAuthMessage req = new OAuthMessage();
		req.setRequestMethod("POST");
		req.setRequestURL("https://api.twitter.com/1.1/direct_messages/new.json");
        req.setConsumerKey(consumerKeyStr);
        req.setToken(accessTokenStr);
        req.setTokenSecret(accessTokenSecretStr);
        
        Hashtable qp = new Hashtable();
        int i = endpoint.indexOf('?');
		if (i >= 0) {
			endpoint = endpoint.substring(0, i);

			String[] queries = Util.split("screen_name=josecruzpruebas&text=Msg 7", "&");
			String[] kv;
			for (i=0; i<queries.length; i++) {
				kv = Util.split(queries[i], "=");
				qp.put(kv[0], kv[1]);
			}
		}
        
        
		req.setAdditionalProperties(qp);
        req.createSignature("HMAC-SHA1",consumerSecretStr);

		String header = req.convertToAuthorizationHeader();
		System.out.println("Authorization: "+header);
        
        
    }

    @Override
    public void destroyApp(boolean unconditional) {
    }
}
