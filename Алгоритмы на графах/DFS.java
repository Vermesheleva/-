import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class DFS {
    private int n;
    private int[][] matr;
    private int[] marks;
    int count;

    DFS(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        this.n = Integer.parseInt(st.nextToken());
        this.matr = new int[n][n];
        this.marks = new int[n];
        this.count = 1;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                matr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

    }
    public void DepthFirstSearch (int start) {
        marks[start] = count;
        count++;

        for (int i = 0; i < n; i++) {
            if (matr[start][i] == 1 && marks[i] == 0) {
                DepthFirstSearch(i);
            }
        }
    }
    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < n; i++){
                ans.append(marks[i]);
                ans.append(" ");
            }
        return ans.toString();
    }
    public void solve() throws IOException{
        FileWriter fw = new FileWriter("output.txt");

        for(int i = 0; i < n; i++){
            if(marks[i] == 0){
                DepthFirstSearch(i);
                if(count == n + 1){
                    break;
                }
            }

        }

        fw.write(this.toString());
        fw.close();
    }
    public static void main(String[] args) throws IOException{
        DFS obj = new DFS("input.txt");
        obj.solve();
    }




    }

