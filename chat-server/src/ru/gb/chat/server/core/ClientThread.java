package ru.gb.chat.server.core;

import ru.gb.chat.library.Library;
import ru.gb.jtwo.network.SocketThread;
import ru.gb.jtwo.network.SocketThreadListener;

import java.net.Socket;

public class ClientThread extends SocketThread {

    private String nickname;
    private boolean isAuthorized;
    private boolean isReconnecting;

    public ClientThread(String name, SocketThreadListener listener, Socket socket) {
        super(name, listener, socket);
    }

    public boolean isReconnecting() {
        return isReconnecting;
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    void reconnect() {
        isReconnecting = true;
        shutdown();
    }

    void authAccept(String nickname) {
        isAuthorized = true;
        this.nickname = nickname;
        sendMessage(Library.getAuthAccept(nickname));
    }

    void authFail() {
        sendMessage(Library.getAuthDenied());
    }

    void msgFormatError(String msg) {
        sendMessage(Library.getMsgFormatError(msg));
        shutdown();
    }
}
