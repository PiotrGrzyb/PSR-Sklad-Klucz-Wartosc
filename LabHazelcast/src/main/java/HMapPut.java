import java.net.UnknownHostException;
import java.util.Map;
import java.util.Random;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class HMapPut {

	final private static Random r = new Random(System.currentTimeMillis());

	public static void main(String[] args) throws UnknownHostException {
		Config config = HConfig.getConfig();
		HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);
		Map<Long, Clinic> clinics = instance.getMap("clinics");
		Long key1 = (long) Math.abs(r.nextInt());
		Clinic clinic1 = new Clinic("Kalinka", "Kielce", "Pediatria", 5);
		System.out.println("PUT " + key1 + " => " + clinic1);
		clinics.put(key1, clinic1);
	}
}
