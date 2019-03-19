package unionfind;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 399. Evaluate Division
 */
//TODO
public class EvaluateDivision {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String ,String> parent = new HashMap<>();
        Map<String , Double> dist = new HashMap<>();
        double[] ans = new double[queries.length];
        for (int i = 0; i <equations.length; i++) {
            String a = find(parent,dist,equations[i][0]);
            String b = find(parent,dist,equations[i][1]);
            union(parent,dist,a,b,values[i]);
        }
        for (int i = 0; i < queries.length; i++) {
            String a = queries[i][0];
            String b = queries[i][1];
            if (parent.containsKey(a) && parent.containsKey(b)) {
                find(parent,dist,a);
                find(parent,dist,b);
                ans[i] = dist.get(a) / dist.get(b);
            } else {
                ans[i] = -1.0;
            }
        }
        return ans;
    }

    private void union(Map<String, String> parent, Map<String, Double> dist, String a, String b, double value) {
        if (a.compareTo(b) < 0) {
            parent.put(a,b);
            dist.put(a,dist.get(b) / dist.get(a) * value);
        } else {
            parent.put(b,a);
            dist.put(b,dist.get(a) / (dist.get(b) * value));
        }

    }

    private String find(Map<String, String> parent, Map<String, Double> dist, String s) {
        if (!parent.containsKey(s)) {
            parent.put(s,s);
            dist.put(s,1.0);
            return s;
        } else {
            if (parent.get(s).equals(s))
                return s;
            String last = parent.get(s);
            String p = find(parent,dist,last);
            parent.put(s,p);
            dist.put(s,dist.get(s) * dist.get(last));
            return p;
        }

    }
    @Test
    public void test() {
        String[][] equations = new String[][] {{"x1", "x2"}, {"x2", "x3"},{"x3", "x4"}, {"x4", "x5"}};
        double[] val = new double[] {3.0,4.0,5.0,6.0};
        String[][] queries = new String[][] {{"x1", "x5"}, {"x5", "x2"}, {"x2", "x4"}, {"x2", "x2"}, {"x2", "x9"},{"x9", "x9"}};
        Arrays.stream(calcEquation(equations, val, queries)).forEach(System.out::println);
    }
}
