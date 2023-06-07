import java.net.*;
import java.io.*;
public class SRServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(3333);
        Socket s = ss.accept();
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        int[] arr = { 10, 20, 30, 40, 50, 60, 70 };
        int k = arr.length;
        dout.write(k);
        for (int i = 0; i < arr.length; i++) {
            System.out.println("sending frame:" + arr[i]);
            dout.write(arr[i]);
        }
        int rd = din.read();
        System.out.println("ERROR frame recieved aft ack " + (rd + 1));
        dout.write(arr[rd]);
        din.close();
        s.close();
        ss.close();
    }
}