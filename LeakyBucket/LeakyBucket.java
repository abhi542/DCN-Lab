package LeakyBucket;

import java.util.Scanner;

public class LeakyBucket {

    public static void main(String[] args) throws InterruptedException {
        int n, incoming, outgoing, store = 0, bucketsize;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Bucket size, Outgoing rate, number of Inputs and Incoming Size");
        bucketsize = sc.nextInt(); 
        outgoing = sc.nextInt();  
        n = sc.nextInt();          
        incoming = sc.nextInt();  

        while (n != 0) {
            System.out.println("Incoming size is " + incoming);
            if (incoming <= (bucketsize - store))
            {
                store += incoming;
                System.out.println("Bucket buffer size is" + store + "out of" + bucketsize);
            } 
            else 
            {
                System.out.println("Packet loss: " + (incoming - (bucketsize - store)));
                store = bucketsize;
                System.out.println("Bucket buffer size is " + store + " out of" + bucketsize);
            }
            
            store -= outgoing;
            System.out.println("After outgoing: " + store + "  out of " + bucketsize + " in buffer");
            n--;
            Thread.sleep(3000);
        }
        sc.close();
    }
}
