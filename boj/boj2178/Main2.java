package boj.boj2178;

/*
* 1.N,M 입력받기
* 2.bfs로 탐색을 (0,0) 부터 시작
* 3.순서대로 x,y를 넣어주고 쌍으로 꺼내주면 Integer Queue 사용해도 가능
* 4.범위내,방문하지않은,0이 아닌 경우 라면 다음 정점(동,서,남,북)으로 이동 (nx,ny) 하면서
*   dist[nx][ny] = dist[x][y] + 1 를 통해서 간선의 수를 카운트한다.
* */

import java.util.*;
import java.io.*;

public class Main2 {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N,M;
    static String[] map;
    static boolean[][] visited;
    static int[][] dist;
    static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};

    public static void main(String[] args){
        input();
        pro();
    }

    static void input(){
        N = scan.nextInt();
        M = scan.nextInt();
        map = new String[N];
        for(int i=0;i<N;i++){ //map은 chatAt으로 y좌표에 접근한다.
            map[i] = scan.nextLine();
        }
        visited = new boolean[N][M];
        dist = new int[N][M];
    }

    static void pro(){
        //시작점은 0,0이다.
        bfs(0,0);
        //도착점에서의 dist에 담긴 값이 최단 이동횟수이다.
        System.out.println(dist[N-1][M-1]);
    }

    static void bfs(int x,int y) {
        //dist배열 -1로 초기화
        //1
        for(int i=0;i<N;i++){
            for (int j=0;j<M;j++){
                dist[i][j] = -1;
            }
        }
        Queue<Integer> queue = new LinkedList<>(); //시작위치를 큐에 넣는다.
        queue.add(x);
        queue.add(y);
        dist[x][y] = 1; //시작점의 dist는 1부터 시작한다. //행위마다 dist와 visited를 조작해야 한다.
        visited[x][y] = true;

        //BFS
        while(!queue.isEmpty()){
            x = queue.poll();
            y = queue.poll();
            for(int k=0;k<4;k++){
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];
                if(nx<0 || ny<0 || nx>=N || ny>=M) continue; //맵을 벗어난다면
                if(map[nx].charAt(ny) =='0') continue; //갈수 없는 칸이면
                if(visited[nx][ny]) continue; //이미 방문했다면
                queue.add(nx);
                queue.add(ny);
                visited[nx][ny] = true;
                dist[nx][ny] = dist[x][y] + 1;
            }
        }

    }

    static class FastReader{
        BufferedReader br;
        StringTokenizer st;
        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        public FastReader(String s) throws FileNotFoundException{
            br = new BufferedReader(new FileReader(new File(s)));
        }
        String next(){
            while(st==null || !st.hasMoreElements()){
                try{
                    st = new StringTokenizer(br.readLine());
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt(){
            return Integer.parseInt(next());
        }
        long nextLong(){
            return Long.parseLong(next());
        }
        String nextLine(){
            String str = "";
            try{
                str = br.readLine();
            }catch(IOException e){
                e.printStackTrace();
            }
            return str;
        }
    }
}
