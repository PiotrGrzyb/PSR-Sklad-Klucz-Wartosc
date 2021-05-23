import org.infinispan.Cache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class CachePut {
    public void putCache(Cache<Integer, Clinic> cache) throws IOException {
        Scanner myInput = new Scanner( System.in );
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        System.out.println("Cache key:");
        int newKey = myInput.nextInt();
        System.out.println("Clinic name:");
        String name =  reader.readLine();
        System.out.println("You entered string " + name);
        System.out.println("Clinic location:");
        String location =  reader.readLine();
        System.out.println("Clinic type:");
        String type =  reader.readLine();
        System.out.println("Clinic rating:");
        int rating = myInput.nextInt();

        cache.put(newKey, new Clinic(name, location, type, rating));
    }
}
