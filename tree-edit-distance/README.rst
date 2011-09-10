Tree Edit Distance Library
==========================

Usage
-----

1. Implement Tree interface.
2. Implement EditScore interface for the Tree.
3. Make a new TreeEditDistance instance with the EditScore instance, and execute calc method.

Example
-------

 Tree tree1 = makeMyTree1();
 Tree tree2 = makeMyTree2();
 EditScore score = new MyEditScore();
 double dist = new TreeEditDistance(score).calc(tree1, tree2);
 System.out.println("distance: " + dist);

See test programs for more information.