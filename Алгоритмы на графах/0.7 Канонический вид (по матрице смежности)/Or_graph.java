import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Or_graph {
    private int n;
    private int[] arr;

    public Or_graph(int n) {
        this.n = n;
        this.arr = new int[n];
    }

    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder();

        for(int i = 0; i < n; i++){
                ans.append(arr[i]);
                ans.append(" ");
        }

        return ans.toString();
    }

    public void fill_array(BufferedReader br) throws IOException{
        StringTokenizer st;
        String val;

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine(), " ");

            for(int j = 0; j < n; j++){
               val = st.nextToken();

               if(val.equals("1")){
                   arr[j] = i + 1;
               }
            }
        }
    }

    public void write_to_file() throws IOException{
        FileWriter fw = new FileWriter("output.txt");
        fw.write(this.toString());
        fw.close();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        Or_graph obj = new Or_graph(n);
        obj.fill_array(br);
        obj.write_to_file();
    }
}
