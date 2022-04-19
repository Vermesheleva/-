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
    private int[][] requests;

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
        q = Integer.parseInt(st.nextToken());

        arr = new int[n + 1];
        requests = new int[k + 1][k + 1];
        ans = n;
        Arrays.fill(arr, -1);

        for(int i = 1; i < k + 1; i++){
            st = new StringTokenizer(br.readLine(), " ");
            requests[0][i] = Integer.parseInt(st.nextToken());
            requests[1][i] = Integer.parseInt(st.nextToken());
        }

        int e1, e2, ind;

        for(int i = 0; i < q; i++){
            if(ans == 1){
                fw.write(Integer.toString(0));
            }

            else {
                ind = Integer.parseInt(br.readLine());
                e1 = requests[0][ind];
                e2 = requests[1][ind];

                union(e1, e2);

                if(ans > 1){
                    fw.write(Integer.toString(1));
                }
                else{
                    fw.write(Integer.toString(0));
                }
            }
        }
        fw.close();

    }
    public static void main(String[] args) throws IOException{
        RevDSU dsu = new RevDSU();
        dsu.solution("input.txt");
    }
}
