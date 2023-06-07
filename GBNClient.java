import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
public class GBNClient {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", 3000);
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        int y = din.read();
        int[] arr = new int[y];
        for (int i = 0; i < y; i++) {
            arr[i] = din.read();
//            System.out.println("recieving frame: " + arr[i]);
        }
        arr[4] = -1;
        int temp = 4;
        for (int i = 0; i < y; i++) {
            if (arr[i] == -1) {
                temp = i;
                System.out.println("error in frame: " + arr[i]);
            } else {
                System.out.println("reciving frame:" + arr[i]);
            }
        }
        dout.write(temp);
        for (int i = temp; i < arr.length; i++) {
            arr[i] = din.read();
            System.out.println("resent frame:" + i);
        }
        System.out.println("final frame");
        System.out.println(arr[temp + 2]);
        din.close();
        s.close();
    }
}