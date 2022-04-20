import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RevDSU {
    private int[] arr;
    private int n;
    private int k;
    private int q;
    private int ans;
    private int len1;
    private int len2;
    private int pos;
    private int[][] roads;
    private int[] requests;

    public RevDSU(int n, int k, int q) {
        this.n = n;
        this.k = k;
        this.q = q;
        arr = new int[n + 1];
        roads = new int[3][];
        roads[0] = new int[k + 1];
        roads[1] = new int[k + 1];
        requests = new int[q];
        ans = n;
        Arrays.fill(arr, -1);
    }

    int findSet(int elem, int len){
        if(arr[elem] < 0) {
            return elem;
        }
        len++;
        arr[elem] = findSet(arr[elem], len);
        return arr[elem];
    }

    void union(int e1, int e2){
        int parent = findSet(e1, len1);
        int child = findSet(e2, len2);
        if(len2 > len1) {
            int temp = child;
            child = parent;
            parent = temp;
        }
        if(child != parent) {
            int temp = arr[child];
            arr[child] = parent;
            arr[parent] += temp;
            ans--;
        }

        len1 = 0;
        len2 = 0;
    }

    public void restoreRoads(BufferedReader br) throws IOException{
        roads[2] = new int[k + 1];
        for(int i = 0; i < q; i++){
            requests[i] = Integer.parseInt(br.readLine());
            roads[2][requests[i]] = 1;
        }
        int e1, e2;
        for(int i = 1; i < k + 1; i++){
            if(roads[2][i] != 1){
                e1 = roads[0][i];
                e2 = roads[1][i];
                union(e1, e2);
            }
        }
    }


    public void solution(BufferedReader br) throws IOException {
        StringTokenizer st;

        for(int i = 1; i < k + 1; i++){
            st = new StringTokenizer(br.readLine(), " ");
            roads[0][i] = Integer.parseInt(st.nextToken());
            roads[1][i] = Integer.parseInt(st.nextToken());
        }

        if(q < k){
           restoreRoads(br);
           if(ans == 1){
               pos = q;
               return;
           }
        }
        else {
        for(int i = 0; i < q; i++){
                requests[i] = Integer.parseInt(br.readLine());
            }
        }

        int e1, e2, ind;

        for(int i = q - 1; i >= 0; i--){
            if(ans > 1) {
                ind = requests[i];
                e1 = roads[0][ind];
                e2 = roads[1][ind];

                union(e1, e2);

                if(ans == 1){
                   pos = i;
                   break;
                }
            }
        }

    }

    public void writeAnswer() throws IOException{
        FileWriter fw = new FileWriter("output.txt");
        for (int i = 0; i < pos; i++){
            fw.write(Integer.toString(1));
        }
        for (int i = pos; i < q; i++){
            fw.write(Integer.toString(0));
        }
        fw.close();
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        RevDSU dsu = new RevDSU(n, k, q);
        dsu.solution(br);
        dsu.writeAnswer();
    }
}
