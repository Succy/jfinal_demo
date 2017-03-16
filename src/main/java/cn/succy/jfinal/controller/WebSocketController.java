package cn.succy.jfinal.controller;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @author Succy
 * @date 2017-03-11 14:27
 **/

@ServerEndpoint("/websocket.ws")
public class WebSocketController {
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("on open");
    }

    @OnClose
    public void  onClose(Session session) {
        System.out.println("onClose");
    }

    @OnMessage
    public void onMessage(String request, Session session) {
        try {
            session.getBasicRemote().sendText(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
