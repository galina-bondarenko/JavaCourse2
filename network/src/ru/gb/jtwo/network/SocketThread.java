package ru.gb.jtwo.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

public class SocketThread extends Thread {

    private final SocketThreadListener listener;
    private final Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private final AtomicBoolean running = new AtomicBoolean(true);

    public SocketThread(String name, SocketThreadListener listener, Socket socket) {
        super(name);
        this.listener = listener;
        this.socket = socket;
        start();
    }

    @Override
    public void run() {
        try {
            listener.onSocketStart(this, socket);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            listener.onSocketReady(this, socket);
            while (running.get()) {
                String msg = in.readUTF();
                listener.onReceiveString(this, socket, msg);
            }
        } catch (IOException exception) {
            listener.onSocketException(this, exception);
        } finally {
            close();
        }
    }

    public boolean sendMessage(String msg) {
        try {
            out.writeUTF(msg);
            out.flush();
            return true;
        } catch (IOException exception) {
            listener.onSocketException(this, exception);
            shutdown();
            return false;
        }
    }

    public void shutdown() {
        System.out.println("Shutting down client");
        running.set(false);
        interrupt();
    }

    private void close() {
        try {
            in.close();
        } catch (IOException exception) {
            listener.onSocketException(this, exception);
        }
        try {
            socket.close();
        } catch (IOException exception) {
            listener.onSocketException(this, exception);
        }
        listener.onSocketStop(this);
    }

}
