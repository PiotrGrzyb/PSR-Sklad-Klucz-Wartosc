import java.net.UnknownHostException;
import java.util.Collection;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.Predicates;

public class HPredicate {

    public static void main( String[] args ) throws UnknownHostException {
        ClientConfig clientConfig = HConfig.getClientConfig();
		final HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
		IMap<Long, Clinic> clinics = client.getMap("clinics");

		Predicate<?,?> namePredicate = Predicates.equal( "name", "Kalinka" );
		Predicate<?,?> birthyearPredicate = Predicates.lessThan("rating", 6);

		Collection<Clinic> kalinka = clinics.values(Predicates.and(namePredicate, birthyearPredicate));
		for (Clinic s : kalinka) {
			System.out.println(s);
		}
	}
}
