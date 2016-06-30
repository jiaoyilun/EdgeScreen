package com.fisher.utils;

import android.util.Log;

import com.fisher.po.TrackData;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2016/6/15/.
 */
public class NetUtil {
    private static String TAG = "NetUtil";

    public static TrackData loadRouteInfo(String com, String nu) {
        TrackData data = null;
        String content = "";
        try {
            String addr = String.format(Constants.URL_KD, com, nu);
            Log.d(TAG, addr);
            URL url = new URL(addr);
            URLConnection con = url.openConnection();
            con.setAllowUserInteraction(false);
            InputStream urlStream = url.openStream();
            String type = con.guessContentTypeFromStream(urlStream);
            String charSet = null;
            if (type == null)
                type = con.getContentType();

            if (type == null || type.trim().length() == 0 || type.trim().indexOf("text/html") < 0)
                return data;

            if (type.indexOf("charset=") > 0)
                charSet = type.substring(type.indexOf("charset=") + 8);

            byte b[] = new byte[10000];
            int numRead = urlStream.read(b);
            content = new String(b, 0, numRead);
            while (numRead != -1) {
                numRead = urlStream.read(b);
                if (numRead != -1) {
                    String newContent = new String(b, 0, numRead, charSet);
                    content += newContent;
                }
            }
            Log.d(TAG, content);
            urlStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        data = gson.fromJson(content, TrackData.class);
        return data;
    }

    public static TrackData getTestData(){
        String data = "{'nu':'3944490863','comcontact':'400-6789-000','companytype':'zhaijisong','com':'zhaijisong','condition':'F00','status':'1','codenumber':'3944490863','state':'3','data':[{'time':'2016-05-21 12:50:00','location':'','context':'客户已签收'},{'time':'2016-05-21 10:14:40','location':'','context':'离开 [河南_配送区部_南阳分拨站_白河南A加盟商] 派送中，递送员[薛甫]，电话[15738092568]'},{'time':'2016-05-21 08:33:57','location':'','context':'离开 [河南_配送区部_南阳分拨站] 发往 [河南_配送区部_南阳分拨站_白河南A加盟商]'},{'time':'2016-05-21 07:52:15','location':'','context':'到达 [河南_配送区部_南阳分拨站]'},{'time':'2016-05-21 01:01:07','location':'','context':'离开 [郑州运转中心] 发往 [河南_配送区部_南阳分拨站]'},{'time':'2016-05-20 13:11:46','location':'','context':'到达 [郑州运转中心]'},{'time':'2016-05-20 08:31:51','location':'','context':'到达 [山东_配送区部_潍坊营业所_河滩镇加盟商]'},{'time':'2016-05-20 03:13:54','location':'','context':'离开 [山东_潍坊运转中心] 发往 [郑州运转中心]'},{'time':'2016-05-19 20:15:20','location':'','context':'已取件，到达 [山东_潍坊运转中心]'}],'message':'ok','ischeck':'1','comurl':'com.fisher.http://www.zjs.com.cn'}";
        return new Gson().fromJson(data,TrackData.class);
    }


}

