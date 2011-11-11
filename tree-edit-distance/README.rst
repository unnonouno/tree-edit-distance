Tree Edit Distance Library
==========================

This library is an implementation of Zhang and Shasha's algorithm [Zhang89]_ for calculating tree edit distance.


Usage
-----

1. Implement Tree interface.
2. Implement EditScore interface for the Tree.
3. Make a new TreeEditDistance instance with the EditScore instance, and execute calc method.


Example
-------

::

 Tree tree1 = makeMyTree1();
 Tree tree2 = makeMyTree2();
 EditScore score = new MyEditScore();
 double dist = new TreeEditDistance(score).calc(tree1, tree2);
 System.out.println("distance: " + dist);

See test programs for more information.

License
-------

This library is distributed under the new BSD license. See LICENSE file.

Reference
---------

.. [Zhang89]
   Kaizhong Zhang, Dennis Shashs.
   **Simple fast algorithms for the editing distance between trees and related problems.**
   *SIAM Journal on Computing, vol. 18, issue 6, 1989.*
