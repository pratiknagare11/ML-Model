import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SRClient {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", 3333);
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        int y = din.read();
        int[] arr = new int[y];
        for (int i = 0; i < y; i++) {
            arr[i] = din.read();
//            System.out.println("recieving frame: " + arr[i]);
        }
        arr[4] = -1;
        int temp = 0;
        for (int i = 0; i < y; i++) {
            if (arr[i] == -1) {
                temp = i;
                System.out.println("error in frame: " + arr[i]);
            } else {
                System.out.println("receiving frame:" + arr[i]);
            }
        }
        dout.write(temp);
        arr[temp] = din.read();
        System.out.println("resent frame:" + temp);
        System.out.println("final frame");
        System.out.println(arr[temp]);
        din.close();
        s.close();
    }
}