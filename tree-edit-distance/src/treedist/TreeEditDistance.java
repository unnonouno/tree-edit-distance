package treedist;

public class TreeEditDistance implements TreeDistance {

	private final EditScore score;

	public TreeEditDistance(EditScore score) {
		if (score == null)
			throw new NullPointerException();
		this.score = score;
	}

	@Override
	public double calc(Tree t1, Tree t2) {
		if (t1 == null || t2 == null)
			throw new NullPointerException();

		return calc(new SubForest(t1), new SubForest(t2));
	}

	private double calc(SubForest f1, SubForest f2) {
		if (f1 == null && f2 == null) {
			return 0.0;
		} else if (f1 == null) {
			return calcInsertScore(f1, f2);
		} else if (f2 == null) {
			return calcDeleteScore(f1, f2);
		} else {
			double replace = calcReplaceScore(f1, f2);
			double delete = calcDeleteScore(f1, f2);
			double insert = calcInsertScore(f1, f2);
			return Math.min(Math.min(replace, delete), insert);
		}
	}

	private double calcReplaceScore(SubForest f1, SubForest f2) {
		double s1 = calc(f1.getHeadChild(), f2.getHeadChild());
		double s2 = calc(f1.getSibling(), f2.getSibling());
		return s1 + s2 + score.replace(f1.head(), f2.head());
	}

	private double calcDeleteScore(SubForest f1, SubForest f2) {
		return calc(f1.deleteHead(), f2) + score.delete(f1.head());
	}

	private double calcInsertScore(SubForest f1, SubForest f2) {
		return calc(f1, f2.deleteHead()) + score.insert(f2.head());
	}
}
