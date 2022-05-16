import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class Segment_Tree {
    private int[] p;
    private int n;
    private int m;
    private long[] tree;
    private long[] before;
    private long[] after;
    private long ans;

    Segment_Tree(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));

        this.n = Integer.parseInt(br.readLine());

        this.p = new int[n];
        this.after = new long[n];
        this.before = new long[n];

        this.m = 0;
        for (int i = 0; i < n; i++) {
            p[i] = Integer.parseInt(br.readLine());
            if(p[i] > m){
                m = p[i];
            }
        }
        m++;

        this.tree = new long[4 * m];
        //this.tree2 = new long[4 * m];
    }

    public void add_to_tree(long[] tree, int ind, int v, int left, int right) {
        if (right - left == 1) {
            tree[v] += 1;
            return;
        } else {
            int mid = (left + right) / 2;

            if (ind < mid) {
                add_to_tree(tree, ind, 2 * v, left, mid);
            } else {
                add_to_tree(tree, ind, 2 * v + 1, mid, right);
            }
        }
        tree[v] = tree[2 * v] + tree[2 * v + 1];
    }

    public long find_sum(long[] t, int v, int curr_left, int curr_right, int left, int right) {
        if (left == curr_left && curr_right == right) {
            return t[v];
        }

        if (left > right) {
            return 0;
        }

        int mid = (curr_right + curr_left) / 2;
        if(right <= mid){
            return find_sum(t, v * 2, curr_left, mid, left, right);
        }
        if(mid <= left){
            return find_sum(t, v * 2 + 1, mid, curr_right, left, right);
        }
        return find_sum(t, v * 2, curr_left, mid, left, mid)
                    + find_sum(t,v * 2 + 1, mid, curr_right, mid, right);


    }

    public void solve2() throws IOException {
        FileWriter fw = new FileWriter("output.txt");

        for (int i = 0; i < n; i++) {
            add_to_tree(tree, p[i], 1, 0, m);
            before[i] = i - find_sum(tree, 1, 0, m, 0, p[i]);

            /*add_to_tree(tree2, p[n - i - 1], 1, 0, m);
            after[n - i - 1] = find_sum(tree2, 1, 0, m, 0, p[n - i - 1]);
            System.out.println(after[n - i - 1]);
             */

        }
        for (int i = 0; i < n; i++) {
            after[i] = find_sum(tree, 1, 0, m, 0, p[i]) - (i - before[i]);
            ans += before[i] * after[i];
        }

        fw.write(ans + "");
        fw.close();
    }
}
public class Task implements Runnable {
        public static void main(String[] args) {
            new Thread(null, new Task(), "", 64 * 1024 * 1024).start();
        }

        public void run() {
            try {
                Segment_Tree tree = new Segment_Tree("input.txt");
                tree.solve2();
            } catch (IOException e) {

            }
        }
    }
