/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praisetestapp;

import java.io.StringWriter;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author incredulous
 */

@ServerEndpoint(value = "/actions", configurator = ChatConfigurator.class)
public class PraiseTestAppClass {
    static Set<Session> chatroomUsers = Collections.synchronizedSet(new HashSet<Session>());
    @OnOpen
    public void handleOpen(EndpointConfig endpointConfig, Session userSession){
        userSession.getUserProperties().put("username", endpointConfig.getUserProperties().get("username"));
      chatroomUsers.add(userSession);  
    }
    @OnMessage
    public void handleMessage(String message, Session userSession){
        String username = (String) userSession.getUserProperties().get("username");
        if (username != null){
            chatroomUsers.stream().forEach(x ->{
            try {
                x.getBasicRemote().sendText(buildJsonData(username, message));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        }
    }
    @OnClose
    public void handleClose(Session userSession){
        chatroomUsers.remove(userSession);
    }
    @OnError
    public void handleError(Throwable t){}
    
    private String buildJsonData(String username,  String message){
        JsonObject jsonObject = Json.createObjectBuilder().add("message", username+": "+message).build();
        StringWriter stringWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stringWriter)){
            jsonWriter.write(jsonObject);
        }
        return stringWriter.toString();
    }

}
