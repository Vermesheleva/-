import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Adj_matrix {
    private int n;
    private int m;
    int[][] matrix;

    public Adj_matrix(int n, int m) {
        this.n = n;
        this.m = m;
        matrix = new int[n][n];
    }

    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                ans.append(matrix[i][j]);
                ans.append(" ");
            }
            ans.append("\n");
        }
        return ans.toString();
    }

    public void fill_matrix(BufferedReader br) throws IOException{
        StringTokenizer st;
        int i, j;

        for(int k = 0; k < m; k++){
            st = new StringTokenizer(br.readLine(), " ");
            i = Integer.parseInt(st.nextToken()) - 1;
            j = Integer.parseInt(st.nextToken()) - 1;
            matrix[i][j] = 1;
            matrix[j][i] = 1;
        }
    }

    public void write_to_file() throws IOException{
        FileWriter fw = new FileWriter("output.txt");
        fw.write(this.toString());
        fw.close();
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Adj_matrix obj = new Adj_matrix(n, m);
        obj.fill_matrix(br);
        obj.write_to_file();
    }
}
