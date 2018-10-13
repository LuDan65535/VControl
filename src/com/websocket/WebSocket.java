package com.websocket;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.utils.RcvMsg;

import static com.Tools.Tool_MsgProcessor.messageParser;
/**
 * 功能说明：websocket处理类
 */

@ServerEndpoint("/webSocketByTomcat/{username}")
public class WebSocket {

    private static int onlineCount = 0;
    private static Map<String, WebSocket> clients = new ConcurrentHashMap<String, WebSocket>();
    private Session session;
    private String username;

    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) throws IOException {

        this.username = username;
        this.session = session;

        addOnlineCount();
        clients.put(username, this);
        System.out.println("已连接");

    }

    @OnClose
    public void onClose() throws IOException {
        clients.remove(username);
        subOnlineCount();
    }

    @OnMessage
    public String onMessage(String message) throws IOException {
        /**
        JSONObject jsonTo = JSONObject.parseObject(message);
        System.out.println(jsonTo.getString("to") +":"+ jsonTo.getString("msg"));

        if (!jsonTo.getString("to").toLowerCase().equals("all")){
            sendMessageTo(jsonTo.getString("msg"), jsonTo.getString("to"));
        }else{
            sendMessageAll(jsonTo.getString("msg"));
        }
         **/
        //System.out.println(message);
        JSONObject msg = JSON.parseObject(message);
        JSONObject content = JSON.parseObject(msg.get("content").toString());
        //System.out.println(msg);System.out.println(content.get("username"));System.out.println(content.get("password"));
        String result = messageParser(msg,content);
        System.out.println(result);
        return result;
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessageTo(String message, String To) throws IOException {
        // session.getBasicRemote().sendText(message);
        //session.getAsyncRemote().sendText(message);
        for (WebSocket item : clients.values()) {
            if (item.username.equals(To) )
                item.session.getAsyncRemote().sendText(message);
        }
    }

    public void sendMessageAll(String message) throws IOException {
        for (WebSocket item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }



    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocket.onlineCount--;
    }

    public static synchronized Map<String, WebSocket> getClients() {
        return clients;
    }
}