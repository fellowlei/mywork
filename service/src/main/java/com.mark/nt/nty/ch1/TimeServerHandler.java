package com.mark.nt.nty.ch1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * Created by lulei on 2016/3/22.
 */
public class TimeServerHandler implements Runnable {
    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String currentTime = null;
            String body = null;
            while (true) {
                body = in.readLine();
                if (body == null) break;
                System.out.println("time server receive order: " + body);
                currentTime = "query time order".equals(body) ? new Date(System.currentTimeMillis()).toString() : "bad order";
                out.println(currentTime);
            }
        } catch (IOException e) {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                if (out != null) {
                    out.close();
                    out = null;
                }

                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    socket = null;
                }

            }
        }
    }
}
