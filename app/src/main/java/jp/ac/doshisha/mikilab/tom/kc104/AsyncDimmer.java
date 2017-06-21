package jp.ac.doshisha.mikilab.tom.kc104;


import android.os.AsyncTask;

import java.io.*;
import java.net.Socket;

/**
 * Created by RyotoTomioka on 2017/06/15.
 */

public class AsyncDimmer extends AsyncTask<String, Integer, String>{

    static private String PORT;
    static private String IP_ADDRESS;

    private Socket socket;

    public static void setIpAddress(String ip) {
        IP_ADDRESS = ip;
    }

    public static String getIpAddress() {
        return IP_ADDRESS;
    }

    public static String getPORT() {
        return PORT;
    }

    public static void setPORT(String PORT) {
        AsyncDimmer.PORT = PORT;
    }

    @Override
    protected String doInBackground(String... str) {
        try {
            socket = new Socket(IP_ADDRESS, Integer.parseInt(PORT));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(str[0].equals("all")) {
            int s1, s2;
            s1 = Integer.parseInt(str[1]);
            s2 = Integer.parseInt(str[2]);
            sendAll(s1, s2);
        }
        return null;
    }

    private void sendAll(int s1, int s2) {
        try {
            // コマンド生成
            String cmd = "MANUAL_SIG-ALL\r\n";
            cmd += String.valueOf(s1) + ",";
            cmd += String.valueOf(s2) + ",";

            // サーバーに数値を送信
            OutputStreamWriter ow = new OutputStreamWriter(socket.getOutputStream());
            BufferedWriter bw = new BufferedWriter(ow);
            ow.write(cmd);
            ow.flush();

            // close
            bw.close();
            ow.close();
        }
        catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
