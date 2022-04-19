import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DSU {
    private int[] arr;
    private int n;
    private int k;
    private int ans;
    private int len1;
    private int len2;

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


    public void solution(String filename) throws IOException {
        FileWriter fw = new FileWriter("output.txt");
        BufferedReader br = new BufferedReader(new FileReader(filename));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n + 1];
        ans = n;
        Arrays.fill(arr, -1);
        int e1, e2;
        st = new StringTokenizer(br.readLine(), " ");
        e1 = Integer.parseInt(st.nextToken());
        e2 = Integer.parseInt(st.nextToken());
        arr[e2] = e1;
        arr[e1]--;
        ans--;
        fw.write(ans + "\n");
        for(int i = 1; i < k; i++){
            if(ans == 1){
                fw.write(ans + "");
                if(i != k - 1){
                    fw.write("\n");
                }
            }
            else {
                st = new StringTokenizer(br.readLine(), " ");
                e1 = Integer.parseInt(st.nextToken());
                e2 = Integer.parseInt(st.nextToken());
                union(e1, e2);
                fw.write(ans + "");
                if (i != k - 1) {
                    fw.write("\n");
                }
            }
        }
        fw.close();

    }
    public static void main(String[] args) throws IOException{
        DSU dsu = new DSU();
        dsu.solution("input.txt");
    }
}
