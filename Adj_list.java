import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Adj_list {
    int n;
    int m;
    List[] list;

    public Adj_list(int n, int m) {
        this.n = n;
        this.m = m;
        this.list = new List [n];
        for(int i = 0; i < n; i++){
            list[i] = new ArrayList<Integer>();
        }
    }



    public void fill_lists(BufferedReader br) throws IOException{
        StringTokenizer st;
        int l, k;

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            l = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            list[l - 1].add(k);
            list[k - 1].add(l);
        }
    }

    public void write_answer() throws IOException{
        FileWriter fw = new FileWriter("output.txt");

        for(int i = 0; i < n; i++){
            fw.write(list[i].size() + " ");

            for(int j = 0; j < list[i].size(); j++){
                fw.write(list[i].get(j) + " ");
            }

            if(i != n - 1){
                fw.write("\n");
            }
        }
        fw.close();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Adj_list obj = new Adj_list(n, m);
        obj.fill_lists(br);
        obj.write_answer();
    }
}
