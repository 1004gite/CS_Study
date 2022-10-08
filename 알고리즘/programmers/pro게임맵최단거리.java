import java.util.*;

class Solution {

    class Point{int r;int c; public Point(int row, int col){ r = row; c = col; }}

    int[] arrR = {0,0,1,-1};
    int[] arrC = {1,-1,0,0};

    int row = 0;
    int col = 0;
    int[][] m;

    public int solution(int[][] maps) {
        m = maps;
        row = maps.length;
        col = maps[0].length;

        Queue<Point> queue = new LinkedList();
        queue.add(new Point(0,0));

        boolean check = false;
        while(true){
            if(queue.isEmpty()) break;
            Point now = queue.poll();
            for(int i = 0; i < 4; i++){
                Point tmp = new Point(now.r+arrR[i], now.c+arrC[i]);
                if(checkPoint(tmp.r, tmp.c)){
                    maps[tmp.r][tmp.c] = maps[now.r][now.c]+1;
                    queue.add(tmp);
                    if(tmp.r == row-1 && tmp.c == col-1){
                        check = true;
                        break;
                    }
                }
            }
            if(check) break;
        }

        if(!check) return -1;

        return maps[row-1][col-1];
    }

    public boolean checkPoint(int r, int c){
        if(r < 0 || c < 0 || r >= row || c >= col) return false;
        if(m[r][c] != 1) return false;
        return true;
    }
}