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
import javax.microedition.midlet.MIDlet;


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
        
       
        
        
        HttpClient client = HttpClientBuilder.getInstance().build();
                  //?screen_name=josecruzpruebas&text=Version+3&oauth_consumer_key=nk1rMBWdjhZj21u6UN09OyknW&oauth_nonce=-5524422733686571724&oauth_signature=920HAJ%2FxHYV5anpwGOZnQBDiF%2Fc%3D&oauth_signature_method=HMAC-SHA1&oauth_timestamp=1405744585&oauth_token=1267971140-3PRdd67xMEst4AdOj8XSeTEGYpHdCdpEqTIFvbz&oauth_version=1.0")
        HttpRequest request = client.build("https://api.twitter.com/1.1/direct_messages/new.json?screen_name=joseacruzp&text=Prueba+Msg+1")
                .setMethod(HttpMethod.POST)
                .setHeader(HttpHeader.AUTHORIZATION, "OAuth oauth_consumer_key=\"" + consumerKeyStr+"\", oauth_nonce=\"b139a7ddf3b37a97f8b08467914a426e\", oauth_signature=\"Foxa1EEUdZkuHnszqY8S%2FTzIVWQ%3D\", oauth_signature_method=\"HMAC-SHA1\", oauth_timestamp=\"1405835406\", oauth_token=\""+accessTokenStr+"\", oauth_version=\"1.0\"")
                .build();
        HttpResponse response = request.invoke();
        System.out.println(response.getResponseCode());
            
//            HttpsConnection hc = (HttpsConnection) Connector.open("https://api.twitter.com/1.1/direct_messages/new.json");
//            hc.setRequestMethod(HttpsConnection.POST);
//            hc.setRequestProperty("screen_name", "josecruzpruebas");
//            hc.setRequestProperty("text","Prueba+Msg+1");
////            hc.setRequestProperty("oauth_consumer_key","nk1rMBWdjhZj21u6UN09OyknW");
////            hc.setRequestProperty("oauth_nonce","4124492833574367416");
////            hc.setRequestProperty("oauth_signature","YxeILuO3x07meFU0XTqaGMtUZrE%3D");
////            hc.setRequestProperty("oauth_signature_method","HMAC-SHA1");
////            hc.setRequestProperty("oauth_timestamp","1405834133");
////            hc.setRequestProperty("oauth_token","1267971140-3PRdd67xMEst4AdOj8XSeTEGYpHdCdpEqTIFvbz");
////            hc.setRequestProperty("oauth_version","1.0");
////            
//            
//            
//            
//            
//            
//            
//            
//            hc.openDataOutputStream().flush();
//            System.out.println(hc.getResponseMessage());
//            System.out.println(hc.getResponseCode());
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//            
    }

    @Override
    public void destroyApp(boolean unconditional) {
    }
}
