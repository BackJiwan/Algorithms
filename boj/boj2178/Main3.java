package boj.boj2178;
/* boj 2178 미로탐색
* 1.정답의 최대치(-21억 ~ 21억 : int) : int 사용
* - 미로가 구불구불해서 N*M 크기를 거의 대부분 지나쳐야 하는 경우라면 10000 => 1만이 정답의 최대치
* 2.복잡도 계산
* - 한번의 BFS를 진행하면서 한칸마다 지나는 칸을 dist 값에 갱신한다면 인접리스트 bfs의 탐색속도인 O(정점+간선)
* - 정점의 개수 = N^2, 간선의 개수 = N^2 * 4
* - : 결국 O(N^2) 이므로 1억번 이내, 1초 이내 , 대략 1만번 정도
* 3.문제 이해
* - 1: 이동가능, 0: 이동불가능,
* - (1,1)에서 출발해서 (N,M) 위치로 이동할 수 있는 최소 칸의수 구하기
* 4.풀이 구상
*- bfs를 돌릴건데 map 과 동일한 dis의 x,y를 최초 시작점은 1 부터 시작해서 poll 한 위치 보다 1 더해서 nx, ny dis 배열에 저장한다
* */
import java.io.*;
import java.util.*;

public class Main3 {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N,M;
    static String[] map;
    static boolean[][] visit;
    static int[][] dist;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    static void input(){
        N = sc.nextInt();
        M = sc.nextInt();
        map = new String[N];
        for(int i=0;i<N;i++){
            map[i] = sc.nextLine();
        }
        visit = new boolean[N][M];
        dist = new int[N][M];
    }
    static void bfs(int x,int y){
        Queue<Integer> que = new LinkedList<>();
        que.add(x);
        que.add(y);
        dist[x][y] = 1;
        visit[x][y] = true;
        while(!que.isEmpty()){
            x = que.poll();
            y = que.poll();

            for(int i=0;i<4;i++){
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];

                if(nx<0||ny<0||nx>=N||ny>=M) continue;
                if(visit[nx][ny]) continue;
                // charAt은 int와 비교가 아님을 명시하기
                if(map[nx].charAt(ny)=='0') continue;

                que.add(nx);
                que.add(ny);
                visit[nx][ny] =true;
                dist[nx][ny] = dist[x][y] +1;
            }
        }
    }
    static void process(){
        bfs(0,0);
        System.out.println(dist[N-1][M-1]);
    }
    public static void main(String[] args){
        input();
        process();
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
