package com.mark.nt.nty.ch1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by lulei on 2016/3/22.
 */
public class TimeServer {

    public static void main(String[] args) throws IOException {
        int port=8888;

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("the time server start at port: " + port);
            Socket socket = null;
            TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50,10000);
            while(true){
                socket = serverSocket.accept();
                singleExecutor.execute(new TimeServerHandler(socket));
//                new Thread(new TimeServerHandler(socket)).start();
            }
        } finally {
            if(serverSocket !=null){
                System.out.println("time server close");
                serverSocket.close();;
                serverSocket = null;
            }
        }

    }
}
