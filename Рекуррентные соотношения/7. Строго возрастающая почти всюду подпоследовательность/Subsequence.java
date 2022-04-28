import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Subsequence {
    private int n;
    private int[] arr;
    private int[] len_up;
    private int[] len_down;
    private int[] subseq;
    private int[] subseq2;
    private int up_len;
    private int down_len;
    private int max_len;

    Subsequence(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        len_up = new int[n];
        len_down = new int[n];
        subseq = new int[n];
        subseq2 = new int[n];
        max_len = 0;
    }

    public int upperBound(int x){
        int start = 0;
        int end = up_len;
        if(x > subseq[end - 1]){
            return up_len;
        }
        if(x < subseq[start]){
            return 0;
        }
        while (start < end) {
            int mid = (start + end) / 2;
            if (x < subseq[mid]){
                end = mid;
            }
            else {
                start = mid + 1;
            }
        }
        return start;
    }

   public int lowerBound(int x){
        int start = 0;
        int end = down_len;
       if(x < subseq2[end - 1]){
           return down_len;
       }
       if(x > subseq2[start]){
           return 0;
       }
        while (start < end) {
            int mid = (start + end) / 2;
            if (x > subseq2[mid]){
                end = mid;
            }
            else {
                start = mid + 1;
            }
        }
        return start;
    }

    public void solve()throws IOException {
        FileWriter fw = new FileWriter("output.txt");
        if (n == 1) {
            fw.write(1 + "");
            fw.close();
            return;
        } else {
            up_len = 1;
            subseq[0] = arr[0];
            len_up[0] = 1;

            down_len = 1;
            subseq2[0] = arr[n - 1];
            len_down[n - 1] = 1;

            for (int i = 1; i < n; i++) {
                if (arr[i] > subseq[up_len - 1]) {
                    subseq[up_len] = arr[i];
                    up_len++;
                } else {
                    int ind = upperBound(arr[i]);
                    if (ind == up_len && subseq[up_len - 1] != arr[i]) {
                        subseq[up_len] = arr[i];
                        up_len++;
                    } else if (ind - 1 < 0 || subseq[ind - 1] != arr[i]) {
                        subseq[ind] = arr[i];
                    }
                }
                len_up[i] = up_len;

                if(arr[n - i - 1] < subseq2[down_len - 1]){
                    subseq2[down_len] = arr[n - i - 1];
                    down_len++;
                }
                else{
                    int ind = lowerBound(arr[n - 1 - i]);
                    if (ind == down_len && subseq2[down_len - 1] != arr[i]) {
                        subseq2[down_len] = arr[n - 1 - i];
                        down_len++;
                    } else if (ind - 1 < 0 || subseq2[ind - 1] != arr[n - 1 - i]) {
                        subseq2[ind] = arr[n - 1 - i];
                    }
                }
                len_down[n - 1 - i] = down_len;

            }

            for (int i = 0; i < n; i++) {
                if(len_down[i] + len_up[i] > max_len){
                    max_len = len_down[i] + len_up[i];
                }
            }
            if(max_len > n){
                max_len = n;
            }
            if(up_len + down_len == 2){
                max_len = 1;
            }
            fw.write(max_len + "");
            fw.close();
        }
    }

    public static void main (String[]args) throws IOException {
        Subsequence sbq = new Subsequence("input.txt");
        sbq.solve();
    }

}
