import java.util.*;
import java.net.*;

public class Dns {
    public static void main(String[] args) {
        String host = "";
        String ip = "";
        Scanner ch = new Scanner(System.in);
        int choice = 0;

        while (true) {
            System.out.println("1. Host to IP Address.\n2. IP Address to Host.");
            System.out.println("\nEnter your choice: ");
            choice = ch.nextInt();

            if (choice == 1) {
                try {
                    System.out.println("Enter the host: ");
                    host = ch.next();
                    InetAddress address = InetAddress.getByName(host);
                    System.out.println("Host Name: " + address.getHostName());
                    System.out.println("IP: " + address.getHostAddress());
                } catch (UnknownHostException e) {
                    System.out.println("Unknown host");
                }
            } else if (choice == 2) {
                try {
                    System.out.println("Enter the IP address: ");
                    ip = ch.next();
                    InetAddress address = InetAddress.getByName(ip);
                    System.out.println("Host Name: " + address.getHostName());
                    System.out.println("IP: " + address.getHostAddress());
                } catch (UnknownHostException e) {
                    System.out.println("Unknown host");
                }
            } else {
                System.out.println("Invalid choice. Please try again.");
                continue; // Restart the loop to ask for another choice
            }

            System.out.println("\nDo you want to perform another operation? (y/n)");
            String cnt = ch.next();
            if (!cnt.equalsIgnoreCase("y")) {
                break; // Exit the loop if the user doesn't want to continue
            }
        }

        System.out.println("Program exited.");
    }
}

