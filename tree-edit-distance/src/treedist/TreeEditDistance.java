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
		Memoization memo = new Memoization();
		return calc(t1, t2, memo, null);
	}

	private double calc(Tree t1, Tree t2, Memoization memo, Edit edit) {
		if (t1 == null || t2 == null)
			throw new NullPointerException();
		Forest root1 = new Forest(t1);
		Forest root2 = new Forest(t2);
		double score = calc(memo, root1, root2);
		if (edit != null) {
			this.revertOperation(memo, root1, root2, edit);
		}
		return score;
	}

	public double calc(Tree t1, Tree t2, Edit edit) {
		Memoization memo = new Memoization();
		return calc(t1, t2, memo, edit);
	}

	private void revertOperation(Memoization memo, Forest f1, Forest f2,
			Edit edit) {
		if (f1 == null && f2 == null) {
			return;
		} else {
			ForestPair pair = new ForestPair(f1, f2);
			Operation op = memo.getOperation(pair);
			switch (op) {
			case Deletion:
				if (edit != null)
					edit.setDeletion(f1.head());
				revertOperation(memo, f1.deleteHead(), f2, edit);
				break;
			case Insertion:
				if (edit != null)
					edit.setInsertion(f2.head());
				revertOperation(memo, f1, f2.deleteHead(), edit);
				break;
			case Replacement:
				if (edit != null)
					edit.setReplacement(f1.head(), f2.head());
				revertOperation(memo, f1.getInside(), f2.getInside(),
						edit);
				revertOperation(memo, f1.getOutside(), f2.getOutside(), edit);
				break;
			}
		}
	}

	private double calc(Memoization memo, Forest f1, Forest f2) {
		ForestPair pair = new ForestPair(f1, f2);
		if (memo.cached(pair))
			return memo.getScore(pair);

		double score;
		Operation op;
		if (f1 == null && f2 == null) {
			return 0.0;
		} else if (f1 == null) {
			score = calcInsertScore(memo, f1, f2);
			op = Operation.Insertion;
		} else if (f2 == null) {
			score = calcDeleteScore(memo, f1, f2);
			op = Operation.Deletion;
		} else {
			double replace = calcReplaceScore(memo, f1, f2);
			double delete = calcDeleteScore(memo, f1, f2);
			double insert = calcInsertScore(memo, f1, f2);
			if (replace < delete && replace < insert) {
				score = replace;
				op = Operation.Replacement;
			} else if (delete < insert) {
				score = delete;
				op = Operation.Deletion;
			} else {
				score = insert;
				op = Operation.Insertion;
			}
		}
		memo.set(pair, score, op);
		return score;
	}

	private double calcReplaceScore(Memoization memo, Forest f1, Forest f2) {
		double s1 = calc(memo, f1.getInside(), f2.getInside());
		double s2 = calc(memo, f1.getOutside(), f2.getOutside());
		return s1 + s2 + score.replace(f1.head(), f2.head());
	}

	private double calcDeleteScore(Memoization memo, Forest f1, Forest f2) {
		return calc(memo, f1.deleteHead(), f2) + score.delete(f1.head());
	}

	private double calcInsertScore(Memoization memo, Forest f1, Forest f2) {
		return calc(memo, f1, f2.deleteHead()) + score.insert(f2.head());
	}
}
