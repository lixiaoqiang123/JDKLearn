package net;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.*;
import java.net.*;

public class TestGetInetAddress {

    public static void getLocalHost(String[] args) {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String hostAddress = address.getHostAddress();
        System.out.println(hostAddress);
    }

    /**
     * 写一个socket客户端，端口8090，发送"hello world"，要求使用多线程，并实现异常处理
     */

    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 8090;

        try {
            Socket socket = new Socket(serverAddress, serverPort);
            System.out.println("Connected to server at " + serverAddress + ":" + serverPort);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Send "hello world" to server
            out.println("hello world 123");

            // Read response from server
            String response = in.readLine();
            System.out.println("Received response: " + response);

            // Close connections
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Error connecting to server: " + e.getMessage());
        }
    }
}
