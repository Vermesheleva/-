import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Domino{
    private int n;
    private int m;
    private List[] list;
    private boolean ans;
    private Queue<Integer> queue;
    private int[] is_visited;

    public Domino(int m) {
        this.n = 7;
        this.m = m;
        this.list = new List [n];
        for(int i = 0; i < n; i++){
            list[i] = new ArrayList<Integer>();
        }
        this.queue = new LinkedList<>();
        this.is_visited = new int[7];
    }

    public void BreadthFirstSearch(int start){
        queue.add(start);
        is_visited[start] = 1;
        int p;

        while(!queue.isEmpty()){
            p = queue.poll();

            for(int j = 0; j < list[p].size(); j++){
                int elem = (int) list[p].get(j);
                if(is_visited[elem] == 0){
                    is_visited[elem] = 1;
                    queue.add(elem);
                }
            }
        }


    }

    public void fill_lists(BufferedReader br) throws IOException {
        StringTokenizer st;
        int l, k;

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            l = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            if(l != k){
                list[l].add(k);
                list[k].add(l);
            }
        }
    }
    public void solve(){
        if(m == 2){
            ans = false;
            return;
        }

        ans = true;
        int start = 0;
        for(int i = 0; i < n; i++){
            if(list[i].size() != 0){
                start = i;
                break;
            }
        }

        this.BreadthFirstSearch(start);
        for(int i = 0; i < n; i++){
            if(is_visited[i] == 0 && list[i].size() != 0){
                ans = false;
                return;
            }
        }

        for(int i = 0; i < n; i++){
            if(list[i].size() % 2 != 0 && list[i].size() != 0){
                ans = false;
                break;
            }
        }
    }

    public void writeAns() throws IOException{
        FileWriter fw = new FileWriter("output.txt");

        if(ans){
            fw.write("Yes");
        }
        else{
            fw.write("No");
        }

        fw.close();
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());

        Domino obj = new Domino(m);
        obj.fill_lists(br);
        obj.solve();
        obj.writeAns();

    }
}
