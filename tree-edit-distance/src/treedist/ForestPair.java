package treedist;

public class ForestPair {
	private final Forest forest1, forest2;

	/**
	 * Make a new forest pair.
	 * 
	 * Null is acceptable.
	 * 
	 * @param forest1
	 * @param forest2
	 */
	public ForestPair(Forest forest1, Forest forest2) {
		this.forest1 = forest1;
		this.forest2 = forest2;
	}

	@Override
	public int hashCode() {
		int h1 = this.forest1 == null ? 0 : this.forest1.hashCode();
		int h2 = this.forest2 == null ? 0 : this.forest2.hashCode();
		return Integer.rotateLeft(h1, 8) ^ h2;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		} else if (object instanceof ForestPair) {
			return this.equals((ForestPair) object);
		} else {
			return false;
		}
	}

	public boolean equals(ForestPair pair) {
		return optionalForestEquqlas(this.forest1, pair.forest1)
				&& optionalForestEquqlas(this.forest2, pair.forest2);
	}

	private static boolean optionalForestEquqlas(Forest f1, Forest f2) {
		if (f1 == null && f2 == null) {
			return true;
		} else if (f1 == null || f2 == null) {
			return false;
		} else {
			return f1.equals(f2);
		}
	}
}
