package unionfind;

import org.junit.Test;

import java.util.Arrays;

public class RedundantConnection {
    public int[] findRedundantConnection(int[][] edges) {
        if (edges.length < 3) {
            return new int[2];
        }
        int[] roots = new int[2001];
        for (int i = 1;i < roots.length; ++i) {
            roots[i] = i;
        }
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            if (find(roots,a) == find(roots,b)) {
                return edge;
            } else {
                union(roots,a,b);
            }
        }
        return new int[2];
    }

    private int find(int[] roots, int i) {
        if (roots[i] != i) {
            roots[i] = find(roots,roots[i]);
        }
        return roots[i];
    }

    private void union(int[] roots, int a, int b) {
        int pa = find(roots,a);
        int pb = find(roots,b);
        roots[pb] = pa;
    }
    @Test
    public void test() {
        int[][] a = new int[][] {{1,2}, {1,3},{2,3}};
        int[][] b = new int[][]{{1,2},{2,3},{3,4},{1,4},{1,5}};
        Arrays.stream(findRedundantConnection(b)).forEach(System.out::println);
    }

}
