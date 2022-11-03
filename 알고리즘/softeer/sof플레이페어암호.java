import java.util.*;
import java.io.*;


public class Main {
    // 대소문자 구분해야 하나?

    class Point{
        int row;
        int col;
        public Point(int r, int c){
            row = r;
            col = c;
        }
    }
    HashMap<Character, Point> point = new HashMap<>();

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str = br.readLine().toCharArray();
        char[] key = br.readLine().toCharArray();

        char[][] table = getTable(key);
        var list = getList(str,table);

        StringBuilder sb = new StringBuilder();
        for(char[] cArr: list){
            Point c1 = point.get(cArr[0]);
            Point c2 = point.get(cArr[1]);
            if(c1.row == c2.row){
                sb.append(table[c1.row][(c1.col+1)%5]);
                sb.append(table[c2.row][(c2.col+1)%5]);
            }else if(c1.col == c2.col){
                sb.append(table[(c1.row+1)%5][c1.col]);
                sb.append(table[(c2.row+1)%5][c2.col]);
            }else{
                sb.append(table[c1.row][c2.col]);
                sb.append(table[c2.row][c1.col]);
            }
        }
        System.out.println(sb.toString());
    }


    ArrayList<char[]> getList(char[] str, char[][] table){
        ArrayList<char[]> list = new ArrayList<>();
        for(int i = 0; i < str.length;){
            var tmp = new char[2];
            if(i == str.length-1){
                // 마지막 한 글자인 경우
                tmp[0] = str[i];
                tmp[1] = 'X';
                i++;
            }else if(str[i] != str[i+1]){
                tmp[0] = str[i];
                tmp[1] = str[i+1];
                i += 2;
            } else{
                // 같은 글자인 경우
                // XX인 경우
                tmp[0] = str[i];
                if(str[i] == 'X') tmp[1] = 'Q';
                else tmp[1] = 'X';
                i++;
            }
            list.add(tmp);
        }
        return list;
    }

    char[][] getTable(char[] str) {
        var table = new char[5][5];
        var hSet = new HashSet<Character>();
        int index = 0;
        char Alphabet = 'A';
        for(int r = 0; r < 5; r++){
            for(int c = 0; c < 5; c++){
                if(index < str.length){
                    while(true){
                        if(index >= str.length) break;
                        if(hSet.add(str[index])) break;
                        index++;
                    }
                    if(index < str.length) {
                        table[r][c] = str[index];
                        point.put(str[index], new Point(r, c));
                    }
                }
                if(index >= str.length){
                    // 없는 알파벳부터 순서대로
                    while (true){
                        if(Alphabet == 'J') Alphabet++;
                        else if(!hSet.add(Alphabet)) Alphabet++;
                        else break;
                    }
                    table[r][c] = Alphabet;
                    point.put(Alphabet, new Point(r,c));
                }
            }
        }

        return table;
    }


    public static void main(String args[]) throws Exception {
        new Main().solve();
    }
}