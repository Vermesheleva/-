import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Graph {
    private int n;
    private int[] arr;

    public Graph(int n) {
        this.n = n;
        this.arr = new int[n];
    }

    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder();

        for(int i = 0; i < n; i++){
            ans.append(arr[i]);
            ans.append(" ");
        }

        return ans.toString();
    }

    public void fill_array(BufferedReader br) throws IOException {
        StringTokenizer st;
        int l, k;

        for(int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            l = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken()) - 1;

            arr[k] = l;
        }

    }

    public void write_to_file() throws IOException{
        FileWriter fw = new FileWriter("output.txt");
        fw.write(this.toString());
        fw.close();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        Graph obj = new Graph(n);
        obj.fill_array(br);
        obj.write_to_file();
    }
}
