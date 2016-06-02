package uk.co.buildergenerator;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public final class CollectionInitialisationTypes {
	
	public static Map<Class<?>, String> getDefaultMappings() {
		Map<Class<?>, String> defaults = new LinkedHashMap<Class<?>, String>();
	    defaults.put(Collection.class, "java.util.ArrayList");
	    defaults.put(List.class, "java.util.ArrayList");
	    defaults.put(Set.class, "java.util.HashSet");
	    defaults.put(Queue.class, "java.util.PriorityQueue");
		return defaults; 
	}
	
}
