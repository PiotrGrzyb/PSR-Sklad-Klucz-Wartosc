import org.infinispan.Cache;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Scanner;

public class CacheOperations {

    public static void putCache(Cache<Integer, Clinic> cache) throws IOException {
        Scanner myInput = new Scanner( System.in );
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        System.out.println("Cache key:");
        int newKey = myInput.nextInt();
        System.out.println("Clinic name:");
        String name =  reader.readLine();
        System.out.println("Clinic location:");
        String location =  reader.readLine();
        System.out.println("Clinic type:");
        String type =  reader.readLine();
        System.out.println("Clinic rating:");
        int rating = myInput.nextInt();

        cache.put(newKey, new Clinic(name, location, type, rating));
    }

    public static void getCache(Cache<Integer, Clinic> cache){
        Scanner myInput = new Scanner( System.in );
        System.out.println("Cache key to get value:");
        int key = myInput.nextInt();
        System.out.printf(key +" => %s\n", cache.get(key));
    }

    public static void getAllCache(Cache<Integer, Clinic> cache){
        Collection<Clinic> clinics = cache.values();
        for (Clinic c : clinics) {
                System.out.println(c);
            }
    }

    public static void editCache(Cache<Integer, Clinic> cache) throws IOException {
        Scanner myInput = new Scanner( System.in );
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        System.out.println("Key of cache to edit:");
        int newKey = myInput.nextInt();
        System.out.println("New clinic name:");
        String name =  reader.readLine();
        System.out.println("New clinic location:");
        String location =  reader.readLine();
        System.out.println("New clinic type:");
        String type =  reader.readLine();
        System.out.println("New clinic rating:");
        int rating = myInput.nextInt();

        cache.put(newKey, new Clinic(name, location, type, rating));
    }

    public static void deleteFromCache(Cache<Integer, Clinic> cache){
        Scanner myInput = new Scanner( System.in );
        System.out.println("Key of cache to delete:");
        int Key = myInput.nextInt();
        cache.evict(Key);
    }

    public static void getByNameCache(Cache<Integer, Clinic> cache) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        System.out.println("Clinic name:");
        String name = reader.readLine();
        Collection<Clinic> clinics = cache.values();
        for (Clinic c : clinics) {
            if (c.getName().equals(name)) {
                System.out.println(c);
            }
        }
    }

    public static void getByLocationCache(Cache<Integer, Clinic> cache) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        System.out.println("Clinic location:");
        String location = reader.readLine();
        Collection<Clinic> clinics = cache.values();
        for (Clinic c : clinics) {
            if (c.getLocation().equals(location)) {
                System.out.println(c);
            }
        }
    }

    public static void getAvgRatingCache(Cache<Integer, Clinic> cache) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        System.out.println("Clinic location:");
        String location = reader.readLine();
        int i = 0;
        double sum = 0;
        Collection<Clinic> clinics = cache.values();
        for (Clinic c : clinics) {
            if (c.getLocation().equals(location)) {
                sum += c.getRating();
                i++;
            }
        }
        sum = sum/i;
        System.out.println("Average rating of clinics: "+sum);
    }
}
