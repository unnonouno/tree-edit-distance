package treedist;

import java.util.HashMap;

public class Memoization {
	private HashMap<ForestPair, Double> map;
	
	public Memoization() {
		this.map = new HashMap<ForestPair, Double>();
	}
	
	public boolean cached(ForestPair pair) {
		return this.map.containsKey(pair);
	}
	
	public double getScore(ForestPair pair) {
		return this.map.get(pair);
	}
	
	public void set(ForestPair pair, double score) {
		this.map.put(pair, score);
	}
}
