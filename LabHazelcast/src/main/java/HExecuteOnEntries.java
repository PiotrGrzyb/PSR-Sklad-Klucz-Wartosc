import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.Map.Entry;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.map.EntryProcessor;

public class HExecuteOnEntries {

    public static void main( String[] args ) throws UnknownHostException {
        ClientConfig clientConfig = HConfig.getClientConfig();
		final HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);

		IMap<Long, Clinic> clinics = client.getMap("clinics");
		clinics.executeOnEntries(new HEntryProcessor());

		for (Entry<Long, Clinic> e : clinics.entrySet()) {
			System.out.println(e.getKey() + " => " + e.getValue());
		}
	}
}

class HEntryProcessor implements EntryProcessor<Long, Clinic, String>, Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public String process(Entry<Long, Clinic> e) {
		Clinic clinic = e.getValue();
		String name = clinic.getName();
		if (name.equals(name.toLowerCase())) {
			name = name.toUpperCase();
			clinic.setName(name);
		} else{
			name = name.toLowerCase();
			clinic.setName(name);
		}
		
		System.out.println("Processing = " + clinic);
		e.setValue(clinic);
		
		return name;
	}
}
