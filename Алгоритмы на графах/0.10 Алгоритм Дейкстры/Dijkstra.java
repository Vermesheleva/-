import java.io.*;
import java.util.*;

class Edge{
    private int v;
    private int c;

    public Edge(int v, int c) {
        this.v = v;
        this.c = c;
    }

    public int getV() {
        return v;
    }

    public int getC() {
        return c;
    }
}

class Step implements Comparable<Step>{
    private int v;
    private long len;

    public Step(int v, long len){
        this.v = v;
        this.len = len;
    }

    public int getV() {
        return v;
    }

    public long getLen() {
        return len;
    }

    @Override
    public int compareTo(Step obj){
        return this.len > obj.len ? 1 : this.len == obj.len ? 0 : -1;
    }
}

public class Dijkstra {
    private int n;
    private int m;
    private List[] list;
    private long[] dist;
    private int[] is_visited;
    private Queue<Step> queue;

    public Dijkstra(int n, int m) {
        this.n = n;
        this.m = m;
        this.list = new List [n];
        for(int i = 0; i < n; i++){
            list[i] = new ArrayList<Edge>();
        }
        this.is_visited = new int[n + 1];
        this.dist = new long[n + 1];
        //Arrays.fill(this.dist, Long.MAX_VALUE);
        this.queue = new PriorityQueue<>();

    }

    public void fill_lists(BufferedReader br) throws IOException {
        StreamTokenizer st = new StreamTokenizer(br);
        int l, k;
        int c;

        for(int i = 0; i < m; i++){
            st.nextToken();
            l = (int) st.nval;
            st.nextToken();
            k = (int) st.nval;
            st.nextToken();
            c = (int) st.nval;

            if (k != l) {
                list[l - 1].add(new Edge(k, c));
                list[k - 1].add(new Edge(l, c));
            }

        }
    }

    public void solve(int start) throws IOException{
        FileWriter fw = new FileWriter("output.txt");

        queue.add(new Step(start, 0));

        Step st;
        while(!queue.isEmpty()){
            st = queue.poll();

            if(is_visited[st.getV()] == 0) {
                is_visited[st.getV()] = 1;
                dist[st.getV()] = st.getLen();

                for (int j = 0; j < list[st.getV() - 1].size(); j++) {
                    Edge elem = (Edge) list[st.getV() - 1].get(j);

                    if (is_visited[elem.getV()] == 0) {
                        queue.add(new Step(elem.getV(), elem.getC() + st.getLen()));
                    }
                }
            }
        }

        fw.write(dist[n] + "");
        fw.close();

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Dijkstra obj = new Dijkstra(n, m);
        obj.fill_lists(br);
        obj.solve(1);
    }


}
