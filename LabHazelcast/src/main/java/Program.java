import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.Predicates;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Program{
    final private static Random r = new Random(System.currentTimeMillis());

    public static void getClinics() throws UnknownHostException {
        ClientConfig clientConfig = HConfig.getClientConfig();
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        IMap<Long, Clinic> clinics = client.getMap("clinics");
        System.out.println("All clinics: ");
        for (Map.Entry<Long, Clinic> e : clinics.entrySet()) {
            System.out.println(e.getKey() + " => " + e.getValue());
        }
    }
    public static void putClinic(String name, String location, String type, int rating) throws UnknownHostException {
        Config config = HConfig.getConfig();
        HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);
        Map<Long, Clinic> clinics = instance.getMap("clinics");
        Long key1 = (long) Math.abs(r.nextInt());
        Clinic clinic1 = new Clinic(name, location, type, rating);
        System.out.println("PUT " + key1 + " => " + clinic1);
        clinics.put(key1, clinic1);
    }

    public static void removeClinics() throws UnknownHostException {
        ClientConfig clientConfig = HConfig.getClientConfig();
        HazelcastInstance client = HazelcastClient.newHazelcastClient( clientConfig );
        IMap<Long, Clinic> clinics = client.getMap( "clinics" );
        clinics.evictAll();
    }

    public static void searchByType(String type) throws UnknownHostException {
        ClientConfig clientConfig = HConfig.getClientConfig();
        final HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        IMap<Long, Clinic> clinics = client.getMap("clinics");

        Predicate<?,?> typePredicate = Predicates.equal( "type", type );
        Collection<Clinic> typy = clinics.values(Predicates.and(typePredicate));
        for (Clinic s : typy) {
            System.out.println(s);
        }
    }

    public static void searchByLocation(String location) throws UnknownHostException {
        ClientConfig clientConfig = HConfig.getClientConfig();
        final HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        IMap<Long, Clinic> clinics = client.getMap("clinics");

        Predicate<?,?> typePredicate = Predicates.equal( "location", location );
        Collection<Clinic> lokacje = clinics.values(Predicates.and(typePredicate));
        for (Clinic s : lokacje) {
            System.out.println(s);
        }
    }

    public static void searchByKey(int key) throws UnknownHostException {
        ClientConfig clientConfig = HConfig.getClientConfig();
        final HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        IMap<Long, Clinic> clinics = client.getMap("clinics");

        for (Map.Entry<Long, Clinic> s : clinics) {
            if (s.getKey() == key) {
                System.out.println(s.getKey() + " => " + s.getValue());
            }
        }
    }

    public static void editClinic(int key) throws IOException {
        Scanner myInput = new Scanner( System.in );
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        ClientConfig clientConfig = HConfig.getClientConfig();
        final HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        IMap<Long, Clinic> clinics = client.getMap("clinics");
        for (Map.Entry<Long, Clinic> s : clinics) {
            if(s.getKey() == key){
                System.out.println("New clinic name:");
                String name = clinics.getName();
                name =  reader.readLine();
                System.out.println("New clinic location:");
                String location =  reader.readLine();
                System.out.println("New clinic type:");
                String type =  reader.readLine();
                System.out.println("New clinic rating:");
                int rating = myInput.nextInt();

                Clinic clinic = s.getValue();
                clinic.setName(name);
                clinic.setLocation(location);
                clinic.setType(type);
                clinic.setRating(rating);

                s.setValue(clinic);
            }

        }
    }

    public static void main(String[] args) throws IOException {
        int choice = 0;

        Scanner myInput = new Scanner( System.in );
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        do{
            System.out.println("[1] Add new clinics");
            System.out.println("[2] Get clinics");
            System.out.println("[3] Delete clinics");
            System.out.println("[4] Search clinics by type");
            System.out.println("[5] Search clinics by location");
            System.out.println("[6] Edit clinics");
            System.out.println("[7] Search by key");
            System.out.println("[8] Quit");
            choice = myInput.nextInt();
            if (choice == 1){
                System.out.println("Clinic name:");
                String name =  reader.readLine();
                System.out.println("You entered string " + name);
                System.out.println("Clinic location:");
                String location =  reader.readLine();
                System.out.println("Clinic type:");
                String type =  reader.readLine();
                System.out.println("Clinic rating:");
                int rating = myInput.nextInt();
                putClinic(name,location,type,rating);
            }
            if (choice == 2)
                getClinics();
            if (choice == 3)
                removeClinics();
            if (choice == 4){
                System.out.println("Clinic type:");
                String type =  reader.readLine();
                searchByType(type);
            }
            if (choice == 5){
                System.out.println("Clinic location:");
                String location =  reader.readLine();
                searchByLocation(location);
            }
            if (choice == 6){
                System.out.println("Clinic key:");
                int key = myInput.nextInt();
                editClinic(key);
            }
            if (choice == 7){
                System.out.println("Clinic key:");
                int key = myInput.nextInt();
                searchByKey(key);
            }
        }while(choice != 8);


    }
}

