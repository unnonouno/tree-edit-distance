package treedist;

public interface EditScore {
	public double replace(int node1, int node2);
	
	public double delete(int node1);
	
	public double insert(int node2);
}
