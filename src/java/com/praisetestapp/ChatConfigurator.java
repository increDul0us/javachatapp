/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praisetestapp;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.*;

/**
 *
 * @author incredulous
 */

public class ChatConfigurator extends ServerEndpointConfig.Configurator{
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response){
        sec.getUserProperties().put("username", (String)((HttpSession) request.getHttpSession()).getAttribute("username"));
    }
    
}
