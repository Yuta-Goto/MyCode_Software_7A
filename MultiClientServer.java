import java.io.*;
import java.net.*;

public class MultiClientServer {
    private static int SERVER_PORT = 8080;//ポート番号設定
    public static void main(String[] args)
    throws IOException{
        ServerSocket s = new ServerSocket(SERVER_PORT);//ソケット作成
        System.out.println("Started: " + s);
        try{
            Socket socket = s.accept();//コネクション設定要求を待つ　(要求が来るまで待って、来たら次に行く
            try{
                System.out.println("Connectoin accepted: " + socket);
                BufferedReader in = 
                    new BufferedReader(
                        new InputStreamReader(
                            socket.getInputStream()));//データ受信用バッファの設定
                PrintWriter out = 
                    new PrintWriter(
                        new BufferedWriter(
                            new OutputStreamWriter(
                                socket.getOutputStream())),true);//送信バッファの設定
                while(true){
                    String str = in.readLine();//データ受信
                    if(str.equals("END")) break;//終了条件
                    System.out.println("Echoing: ");
                    out.println(str);//受信したデータをそのまま送信
                }
            } finally {//(例外の発生有無に寄らず実行される)
                System.out.println("closing...");
                socket.close();
            }
        }finally{
            s.close();
        }
    }
}