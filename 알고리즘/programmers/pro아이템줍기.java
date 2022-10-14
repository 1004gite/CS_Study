import java.util.*;

/*
1. 본인이 들어있는 사각형의 모서리를 우선해서 이동한다.
2. 만약 다른 사각형과 만나게 된다면 다른 사각형으로 옮겨간다.
3. 다른 사각형으로 옮겨갈 때 이전 사각형의 안쪽으로 들어가지 않아야 한다.
*/

class Solution {

    class P{
        int x;
        int y;
        int count;

        public P(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {

        int[] arrX = {0,0,1,-1};
        int[] arrY = {1,-1,0,0};

        int desX = itemX*2;
        int desY = itemY*2;

        // 모든 좌표를 2배로 본다
        for(int[] rec: rectangle){
            rec[0] *= 2;
            rec[1] *= 2;
            rec[2] *= 2;
            rec[3] *= 2;
        }
        boolean[][] board = new boolean[102][102];
        setBoard(board,rectangle);

        boolean[][] visited = new boolean[102][102];
        Queue<P> queue = new LinkedList<>();
        queue.add(new P(characterX*2,characterY*2,0));
        while(!queue.isEmpty()){
            P now = queue.remove();

            for(int i = 0; i < 4; i++){
                int tmpX = now.x + arrX[i];
                int tmpY = now.y + arrY[i];
                if(visited[tmpX][tmpY]) continue;
                if(!board[tmpX][tmpY]) continue;
                visited[tmpX][tmpY] = true;

                if(tmpX == desX && tmpY == desY) return (now.count+1)/2;
                queue.add(new P(tmpX, tmpY, now.count+1));
            }
        }

        return -1;
    }

    public void setBoard(boolean[][] board, int[][] rectangle){
        // board에 rectangle들의 모서리만 true로 바꾼다.
        for( int[] rec: rectangle ){
            for(int x = rec[0]; x <= rec[2]; x++){
                if(!isInside(rectangle,x,rec[1])) board[x][rec[1]] = true;
                if(!isInside(rectangle,x,rec[3])) board[x][rec[3]] = true;
            }
            for(int y = rec[1]; y <= rec[3]; y++){
                if(!isInside(rectangle,rec[0],y)) board[rec[0]][y] = true;
                if(!isInside(rectangle,rec[2],y)) board[rec[2]][y] = true;
            }
        }
    }

    public boolean isInside(int[][] rectangle, int x, int y) {
        // 어떤 점이 rec안에 있는지 판단하는 함수
        for(int[] rec: rectangle) {
            if( rec[0] < x && x < rec[2] && rec[1] < y && y < rec[3] ) return true;
        }

        return false;
    }
}