import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class HashTable {
    private int m;
    private int n;
    private int c;
    private int[] table;

    public void fillTable(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        m = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        table = new int[m];
        Arrays.fill(table, -1);

        int elem;
        int pos;
        int j;
        for(int i = 0; i < n; i++){
            elem = Integer.parseInt(br.readLine());
            pos = elem % m;
            j = 0;
            while(table[pos] != -1 && table[pos] != elem){
                pos = ((elem % m) + c*j) % m;
                j++;
            }
            if(table[pos] == -1){
                table[pos] = elem;
            }
        }
    }

    public void writeTable() throws IOException{
        FileWriter fw = new FileWriter("output.txt");
        for(int el : table){
            fw.write(el + " ");
        }
        fw.close();
    }


    public static void main(String[] args) throws IOException{
        HashTable table = new HashTable();
        table.fillTable("input.txt");
        table.writeTable();
    }
}
