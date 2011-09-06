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

		return calc(new Memoization(), new SubForest(t1), new SubForest(t2));
	}

	private double calc(Memoization memo, SubForest f1, SubForest f2) {
		ForestPair pair = new ForestPair(f1, f2);
		if (memo.cached(pair))
			return memo.getScore(pair);

		double score;
		if (f1 == null && f2 == null) {
			return 0.0;
		} else if (f1 == null) {
			score = calcInsertScore(memo, f1, f2);
		} else if (f2 == null) {
			score = calcDeleteScore(memo, f1, f2);
		} else {
			double replace = calcReplaceScore(memo, f1, f2);
			double delete = calcDeleteScore(memo, f1, f2);
			double insert = calcInsertScore(memo, f1, f2);
			score = Math.min(Math.min(replace, delete), insert);
		}
		memo.set(pair, score);
		return score;
	}

	private double calcReplaceScore(Memoization memo, SubForest f1, SubForest f2) {
		double s1 = calc(memo, f1.getHeadChild(), f2.getHeadChild());
		double s2 = calc(memo, f1.getSibling(), f2.getSibling());
		return s1 + s2 + score.replace(f1.head(), f2.head());
	}

	private double calcDeleteScore(Memoization memo, SubForest f1, SubForest f2) {
		return calc(memo, f1.deleteHead(), f2) + score.delete(f1.head());
	}

	private double calcInsertScore(Memoization memo, SubForest f1, SubForest f2) {
		return calc(memo, f1, f2.deleteHead()) + score.insert(f2.head());
	}
}
