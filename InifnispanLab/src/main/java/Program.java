import org.infinispan.Cache;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryCreated;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryModified;
import org.infinispan.notifications.cachelistener.event.CacheEntryCreatedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryModifiedEvent;

import java.io.IOException;
import java.util.Scanner;

public class Program extends CacheOperations{

    public static void main(String[] args) throws IOException {
        DefaultCacheManager cacheManager = new DefaultCacheManager();
        cacheManager.defineConfiguration("local", new ConfigurationBuilder().build());
        Cache<Integer, Clinic> cache = cacheManager.getCache("local");

        Clinic clinic1 = new Clinic("Test", "Kielce", "Pediatria", 5);
        Clinic clinic2 = new Clinic("Test2", "Warszawa", "Kariologia", 4);
        Clinic clinic3 = new Clinic("Test3", "Kielce", "Onkologia", 3);

        cache.put(1, clinic1);
        cache.put(2, clinic2);
        cache.put(3, clinic3);

        cache.addListener(new MyListener());

        int choice = 0;
        Scanner myInput = new Scanner( System.in );
        do{
            System.out.println("[1] Add new clinics");
            System.out.println("[2] Get clinics by key");
            System.out.println("[3] Get all clinics");
            System.out.println("[4] Edit clinic");
            System.out.println("[5] Delete by key");
            System.out.println("[6] Delete ALL");
            System.out.println("[7] Get clinics by name");
            System.out.println("[8] Get clinics by location");
            System.out.println("[9] Get clinics average rating by location");
            System.out.println("[10] Quit");
            choice = myInput.nextInt();
            if (choice == 1){
                putCache(cache);
            }
            if (choice == 2) {
                getCache(cache);
            }
            if (choice == 3) {
                getAllCache(cache);
            }
            if (choice == 4){
                editCache(cache);
            }
            if (choice == 5){
                deleteFromCache(cache);
            }
            if (choice == 6){
                cache.clear();
            }
            if (choice == 7){
                getByNameCache(cache);
            }
            if (choice == 8){
                getByLocationCache(cache);
            }
            if (choice == 9){
                getAvgRatingCache(cache);
            }
        }while(choice != 10);
        cacheManager.stop();
    }
}

@Listener
class MyListener {

    @CacheEntryCreated
    public void entryCreated(CacheEntryCreatedEvent<Integer, Clinic> event) {
        if (!event.isPre())
            System.out.printf("Created %s\n", event.getKey());
    }

    @CacheEntryModified
    public void entryModified(CacheEntryModifiedEvent<Integer, Clinic> event) {
        if (event.isPre())
            System.out.printf("About to modify %s\n", event.getKey());
        }

}
