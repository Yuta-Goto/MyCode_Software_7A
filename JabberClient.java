import java.io.*;
import java.net.*;

public class JabberClient{
    private static int SERVER_PORT;
    public static void main(String[] args) throws IOException{
        InetAddress addr = InetAddress.getByName("10.230.79.92");//IPアドレスへの変換
        System.out.println("addr = " + addr);
        Socket socket = new Socket(addr, SERVER_PORT);//変更：自分のPORTを参照
        try{
            System.out.println("socket =" + socket);
            BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(
                        socket.getInputStream()));//データ受信用バッファの設定
            PrintWriter out =
                new PrintWriter(
                    new BufferedWriter(
                        new OutputStreamWriter(
                            socket.getOutputStream())),true);//送信バッファ設定
            for(int i=0;i<10;i++){
                out.println("howdy "+i);//データ送信
                String str = in.readLine();//データ受信
                System.out.println(str);
            }
            System.out.println("END");
        }finally{
            System.out.println("closing...");
            socket.close();
        }
    }
}