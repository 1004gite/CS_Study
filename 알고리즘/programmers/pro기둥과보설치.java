import java.util.*;

class Solution {

    // 기둥은 바닥,보의 끝, 다른기둥 위에 올 수 있다.
    // 보는 한쪽 끝이 기둥과 연결되거나, 양쪽 끝이 다른 보와 연결되어야 한다.
    // 입력은 x,y 좌표로 들어온다.

    Info[][] board;
    int n;

    class Info{
        boolean row = false; // 보
        boolean col = false; // 기둥
    }

    public int[][] solution(int n, int[][] build_frame) {

        this.n = n;
        board = new Info[n+1][n+1];
        for(int x = 0; x <= n; x++){
            for(int y = 0; y <= n; y++){
                board[x][y] = new Info();
            }
        }

        for(int i = 0; i < build_frame.length; i++){
            int[] now = build_frame[i];
            int x = now[0];
            int y = now[1];
            boolean build = (now[3] == 1);
            boolean row = (now[2] == 1);
            if(row && build){
                board[x][y].row = true;
                if(!checkAll(x,y)) board[x][y].row = false;
            }
            else if(row && !build){
                board[x][y].row = false;
                if(!checkAll(x,y)) board[x][y].row = true;
            }
            else if(!row && build){
                board[x][y].col = true;
                if(!checkAll(x,y)) board[x][y].col = false;
            }
            else if(!row && !build){
                board[x][y].col = false;
                if(!checkAll(x,y)) board[x][y].col = true;
            }
        }


        ArrayList<int[]> answer = new ArrayList<int[]>();
        for(int x = 0; x <= n; x++){
            for(int y = 0; y <= n; y++){
                if(board[x][y].col) answer.add(new int[] {x,y,0});
                if(board[x][y].row) answer.add(new int[] {x,y,1});
            }
        }

        int[][] result = new int[answer.size()][3];
        for(int i = 0; i < answer.size(); i++){
            result[i] = answer.get(i);
        }
        return result;
    }

    boolean checkAll(int a, int b){
        for(int x = a-1; x <= a+1; x++){
            if( x < 0 || x > n ) continue;
            for(int y = b-1; y <= b+1; y++){
                if(y < 0 || y > n) continue;
                if(board[x][y].row && !checkRow(x,y)) return false;
                if(board[x][y].col && !checkCol(x,y)) return false;
            }
        }

        return true;
    }

    boolean checkRow(int x, int y){
        return (
                (checkRangeCol(x,y-1) && board[x][y-1].col) || //아래 기둥이 있음
                        (checkRangeCol(x+1,y-1) && board[x+1][y-1].col) || // 오른쪽 아래 기둥이 있음
                        ((checkRangeRow(x-1,y) && checkRangeRow(x+1,y) && board[x+1][y].row && board[x-1][y].row)) // 양쪽에 보가 있음
        );
    }

    boolean checkCol(int x, int y){
        // 위로 설치한다.
        // 바닥이다.
        // 아래에 기둥이 있다.
        // 보가 있다.
        // 왼쪽에 보가 있다.
        if(y == 0) return true;
        if(checkRangeCol(x,y-1) && board[x][y-1].col) return true;
        if(board[x][y].row) return true;
        if(checkRangeRow(x-1,y) && board[x-1][y].row) return true;
        return false;
    }

    // 보랑 기둥이랑 판단 기준으로 다르게 해야함
    boolean checkRangeRow(int x,int y){
        // 보는 가로로 n-1까지 올 수 있음
        // 보는 세로로 n까지 가능함
        return !(x < 0 || x >= n || y < 0 || y > n);
    }
    boolean checkRangeCol(int x, int y){
        // 기둥은 가로로 n까지 올 수 있음
        // 기둥은 세로로 n-1까지 올 수 있음
        return !(x < 0 || x > n || y < 0 || y >= n);
    }
}