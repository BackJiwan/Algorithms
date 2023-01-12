package sw_ex.no1;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* 1. 관광도시들이 R행 * C열 까지 있다. (이차원배열...?) (시작은 1.1부터)
* 2. 각 도시에는 A~Z중 하나의 알파벳을 가지는 기념품이 있으며 같은 알파벳은 같은 기념품이다.
* 3. 최대한 많은 기념품, 가장 적은 비용, 이동한 위치에서 구매, 다음 경로는 동서 남북중 하나
* */
public class Main {
    static int r,c;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    static int result;
    static boolean[] check;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            map = new int[r][c];
            for(int j=0;j<r;j++){
                String str = br.readLine();
                for(int k=0;k<c;k++){
                    map[j][k] = str.charAt(k)-'A';
                }
            }
            check = new boolean[26];
            Arrays.fill(check,false);
            rec_func(0,0,1);
            sb.append('#').append(i).append(' ').append(result).append('\n');
            result = 0;
        }
        System.out.println(sb.toString());
    }
    public static void rec_func(int x,int y,int value){
        check[map[x][y]] = true;
        result = Math.max(result,value);

        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx >=0 && ny >=0 && nx<r && ny<c){
                if(check[map[nx][ny]] == false){
                    check[map[nx][ny]] = true;
                    rec_func(nx,ny,value+1);
                    check[map[nx][ny]] = false;
                }
            }
        }
    }
}
