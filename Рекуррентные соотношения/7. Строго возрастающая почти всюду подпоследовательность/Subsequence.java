import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Subsequence {
    private int n;
    private int len;
    private int[] sequence;
    private int[] subsequence;
    private int break_index;

    Subsequence(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        n = Integer.parseInt(br.readLine());
        sequence = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < n; i++){
            sequence[i] = Integer.parseInt(st.nextToken());
        }
        subsequence = new int[n];
        break_index = -1;
    }

    public int upperBound(int x){
        int start = 0;
        int end = len;
        if(x > subsequence[len - 1]){
            return len;
        }
        if(x < subsequence[0]){
            return 0;
        }
        while (start < end) {
            int mid = (start + end) / 2;
            if (x < subsequence[mid]){
                end = mid;
            }
            else {
                start = mid + 1;
            }
        }
        return start;
    }

    public void solve() throws IOException {
        FileWriter fw = new FileWriter("output.txt");
        if (n == 1) {
            fw.write(1 + "");
            fw.close();
            return;
        } else {
            len = 1;
            subsequence[0] = sequence[0];

            for (int i = 1; i < n; i++) {

                if(subsequence[len - 1] < sequence[i]){
                    subsequence[len] = sequence[i];
                    len++;
                }
                else if(sequence[i] < subsequence[len - 1] && break_index == -1){
                    subsequence[len] = sequence[i];
                    break_index = len;
                    len++;
                }

                else {
                    int ind = upperBound(sequence[i]);
                    if(ind == 0){
                        if(break_index == -1) {
                            subsequence[len] = sequence[i];
                            len++;
                            break_index = 0;
                        }
                        else {
                            subsequence[0] = sequence[i];
                        }

                    } else if (ind <= break_index) {
                        subsequence[ind] = sequence[i];
                        break_index = ind;
                    }
                    else if(break_index == -1){
                        subsequence[ind] = sequence[i];
                    }
                }
            }
            fw.write(len + "");
            fw.close();
        }
    }

    public static void main (String[]args) throws IOException {
        Subsequence sbq = new Subsequence("input.txt");
        sbq.solve();
    }
}
