package unionfind;

import org.junit.Test;

public class FriendCircles {

    int count = 0;
    public int findCircleNum(int[][] M) {
        if (M == null || M[0].length == 0) {
            return 0;
        }

        int n = M.length;
        count = n;
        int[] roots = new int[n];
        for (int i = 0; i < n; i++) {
            roots[i] = i;
        }
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                if (M[i][j] == 1) {
                    union(roots,i,j);
                }
            }

        }
        return count;
    }

    private void union(int[] roots, int i, int j) {
        int pa = find(roots,i);
        int pb = find(roots,j);
        if (pa == pb) {
            return;
        } else {
            if (pa < pb) {
                roots[pb] = pa;
            } else {
                roots[pa] = pb;
            }
            count --;
        }
    }

    private int find(int[] roots, int i) {
        if(roots[i] != i) {
            roots[i] = find(roots,roots[i]);
        }
        return roots[i];
    }


    @Test
    public void test() {
        int[][] M = {{1,0,0,1},{0,1,1,0},{0,1,1,1},{1,0,1,1}};
        int[][] n = new int[][] {{1,1,0},{1,1,0},{0,0,1}};
        System.out.println(findCircleNum(M));
    }
}
