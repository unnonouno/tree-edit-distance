package treedist;

import java.util.HashMap;

public class Memoization {
	
	private static class CacheElement {
		public CacheElement(double score, Operation operation) {
			this.score = score;
			this.operation = operation;
		}
		
		public final double score;
		public final Operation operation;
	}
	
	private HashMap<ForestPair, CacheElement> map;
	
	public Memoization() {
		this.map = new HashMap<ForestPair, CacheElement>();
	}
	
	public boolean cached(ForestPair pair) {
		return this.map.containsKey(pair);
	}
	
	public double getScore(ForestPair pair) {
		return this.map.get(pair).score;
	}
	
	public Operation getOperation(ForestPair pair) {
		return this.map.get(pair).operation;
	}
	
	public void set(ForestPair pair, double score, Operation operation) {
		CacheElement e = new CacheElement(score, operation);
		this.map.put(pair, e);
	}
}
