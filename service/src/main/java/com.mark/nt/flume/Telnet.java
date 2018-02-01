package com.mark.nt.flume;

import java.io.*;
import java.net.Socket;

/**
 * Created by lulei on 2018/2/1.
 */
public class Telnet {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 44444);
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream));
        printWriter.write("hello22\n");
        printWriter.flush();
        printWriter.close();
        socket.close();

    }
}
