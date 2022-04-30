import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Fenwick_Tree {
    private int[] p;
    private int n;
    private int[] tree;
    private int[] before;
    private int[] after;
    private int ans;

    Fenwick_Tree(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));

        this.n = Integer.parseInt(br.readLine());

        this.p = new int[n];
        this.after = new int[n];
        this.before = new int[n];
        this.tree = new int[300001];

        for(int i = 0; i < n; i++){
            p[i] = Integer.parseInt(br.readLine());
        }
    }

    private void solve() throws IOException{
        FileWriter fw = new FileWriter("output.txt");
        if(n < 3){
            fw.write("0");
            fw.close();
            return;
        }

        before[1] = 0;
        tree[p[0]]++;

        for(int i = 1; i < n; i++){
            tree[p[i]]++;

            int sum = 0;
            for(int j = 1; j < p[i] + 1; j++){
               sum += tree[j];
            }
            before[i] = i + 1 - sum;
        }

        Arrays.fill(tree, 0);

        after[n - 1] = 0;
        tree[p[n - 1]]++;

        for(int i = n - 2; i >= 0; i--){
            tree[p[i]]++;

            for(int j = 1; j < p[i]; j++){
                after[i] += tree[j];
            }
        }

        for(int i = 0; i < n; i++){
            ans += before[i] * after[i];
        }

        fw.write(ans + "");
        fw.close();
    }

    public static void main(String[] args) throws IOException{
        Fenwick_Tree tree = new Fenwick_Tree("input.txt");
        tree.solve();
    }
}
