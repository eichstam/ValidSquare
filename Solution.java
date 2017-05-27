/**
Given the coordinates of four points in 2D space, return whether the four points could construct a square.

The coordinate (x,y) of a point is represented by an integer array with two integers.

Example:

Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
Output: True

Note:

    All the input integers are in the range [-10000, 10000].
    A valid square has four equal sides with positive length and four equal angles (90-degree angles).
    Input points have no order.
*/

public class Solution {
  public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
    boolean rc = false;

    if(validCorner(p1, p2, p3, p4)
        && validCorner(p2, p3, p4, p1)
        && validCorner(p3, p4, p1, p2)
        && validCorner(p4, p1, p2, p3)) {
      rc = true;
    }
    return rc;
  }

  // check if we have two points with equal distance and
  // those points form a 90 degree angle with p1
  public boolean validCorner(int[] p1, int[] p2, int[] p3, int[] p4) {
    boolean rc = false;
    // check if two points have same distance to p1
    double d2 = distance(p1, p2);
    double d3 = distance(p1, p3);
    double d4 = distance(p1, p4);

    if(d2 <= 0 || d3 <= 0 || d4 <= 0) return false;
    if(d2 == d3) {
      if(Math.round(distance(p2, p3)) == Math.round(Math.sqrt(d2 * d2 + d3 * d3))) {rc = true;}
    } else if(d3 == d4) {
      if(Math.round(distance(p3, p4)) == Math.round(Math.sqrt(d3 * d3 + d4 * d4))) {rc = true;}      
    } else if(d2 == d4) {
      if(Math.round(distance(p2, p4)) == Math.round(Math.sqrt(d2 * d2 + d4 * d4))) {rc = true;}           
    }
    return rc;
  }

  // distance between two points
  public double distance(int[] p1, int[] p2) {
    int x = Math.abs(p1[0] - p2[0]);
    int y = Math.abs(p1[1] - p2[1]);
    return Math.sqrt(x*x + y*y);
  }
  
  public static void main(String[] args) {
    Solution s = new Solution();
    int[] a = {0, 3};
    int[] b = {4, 0};
    //System.out.println(s.distance(a, b));

    int tests[][][] =  {{{0,0}, {1,1}, {1,0}, {0,1}},
                        {{1,0}, {0,1}, {-1,0}, {0,-1}},
                        {{1134,-2539}, {492,-1255}, {-792,-1897}, {-150,-3181}},
                        {{0,0}, {0,0}, {0,0}, {0,0}}};
    boolean rc = false;
    for(int i = 0; i < tests.length; i++) {
      rc = s.validSquare(tests[i][0], tests[i][1], tests[i][2], tests[i][3]);
      System.out.println("Test=" + i + " rc=" + rc);
    }
  }
}
