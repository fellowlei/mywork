package com.mark.frame.util.debug;

import java.io.*;

/**
 * Created by lulei on 2018/1/18.
 * tail util
 */
public class TailUtil {

    public static boolean stop = false;
    public static void tail(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
        String line = null;
        while (!stop) {
            while ((line = br.readLine()) != null) {
                System.out.println("line" + line);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("end");
        br.close();
    }


    public static void tail2(String filePath) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(new File(filePath));
        fileInputStream.skip(1); // skip n byte
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream,"UTF-8"));
        String line = null;
        while (!stop) {
            while ((line = br.readLine()) != null) {
                System.out.println("line: " + line);
            }
            Thread.sleep(1000);
        }
        System.out.println("end");
        br.close();
    }

    public static void tail3(String filePath) throws Exception {
        File file = new File(filePath);
        RandomAccessFile randomAccessFile = new RandomAccessFile(file,"r");
        randomAccessFile.seek(1);
        String line = null;
        while(!stop){
            while((line = randomAccessFile.readLine()) != null){
                System.out.println(line);
            }
            Thread.sleep(1000);
        }

        randomAccessFile.close();
    }

    public static void tail4(String filePath) throws IOException {
        Process process = Runtime.getRuntime().exec("tail -f " + filePath);
        InputStream inputStream = process.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        while((line = br.readLine()) != null){
            System.out.println(line);
        }
        inputStream.close();
    }



    public static void main(String[] args) throws Exception {
        TailUtil.tail3("d:/demo/tail.txt");
    }
}
