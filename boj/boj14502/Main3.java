package boj.boj14502;
/* boj 14502(골드4) - 연구소
* 1.정답의 최대치(-21억~21억 int)
* - 가능한 안전구역이 최대 크기라면 N*2 => 최대 64이다.
* 2.복잡도 계산(1억번 : 1초)
* - 3개의 벽을 놓을수 있는 모든 경우를 탐색 => 64개중 3개를 중복X,순서X 고르기 중복X 이므로 64!/(64-3)!
*   퍼뮤테이션의 의미는 64개중 중복없이, 순서가 있으니까 3개를 뽑고 싶으면 64 * 63 * 62 이렇게 구해지는 것임 식은 위와 같고
*   그러나 이번 경우는 중복없고, 순서도 없으니까, 콤비네이션이고 퍼뮤테이션의 경우에서 3!으로 나누어 주어야함
*   그러면 64*63*62 / 3*2 => 4.1만
* - 벽을 세운 경우마다 바이러스를 퍼트려 봐야 한다. 이것을 bfs, 인접리스트로 구현하면
*   시간복잡도는 O(정점+간선) 개수만큼이다. 점점의 개수는 64, 간선의 개수는 무방향 그래프이니까 2*64 => O(N^2)
* - 결론 : 4.1만 * 64 = 266만 으로 1억초 이내라서 1초이내로 가능하다.
* 3.문제 이해
* - N과 M이 주어지며, N*M의 지도에서 빈칸중, 벽을 3개 세워서 가장큰 안전영역을 구하자
* - N,M의 최대 크기는 8이다. 빈공간의 최대 크기는 64개보다는 작으며 이중에서 3개를 중복 없이, 순서없이 고르는 경우이므로 ,
*
* 4.풀이 구상
* - 가능한 벽을 3개 모두 세운다. 벽을 세우는건 dfs를 이용한다.
* - 벽 3개가 세워진 경우마다 bfs를 호출한다.
* - bfs에서는 2중 for문을 통해서 바이러스를 찾을때마다 큐에 넣기, 방문체크하기 등의 작업을 실시한다.
*   그리고 while bfs를 진행해준뒤에 , 탐색이 종료되면 안전영역의 넓이를 계산화고 정답을 갱신한다.
* -
* */

import java.io.*;
import java.util.*;

public class Main3 {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();

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
        double nextDouble(){
            return Double.parseDouble(next());
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

    static int N,M,B,ans;
    static int[][] map, blank;
    static boolean[][] visit;
    static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};

    static void input(){
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N+1][M+1];
        for(int i=1;i<=N;i++){
            for(int j=1;j<=M;j++){
                map[i][j] = sc.nextInt();
            }
        }
        visit = new boolean[N+1][M+1];
        blank = new int[N*M+1][2];
    }

    static void bfs(){
        Queue<Integer> que = new LinkedList<>();

        //모든 바이러스를 탐색하며 큐에 넣어준다. 동시에 visit 배열도 false로 초기화하면서 바이러스를 만난 곳은 true로 체크해준다.
        for(int i=1;i<=N;i++){
            for(int j=1;j<=M;j++){
                visit[i][j] = false;
                if(map[i][j]==2){
                    visit[i][j] = true;
                    que.add(i);
                    que.add(j);
                }
            }
        }
        //bfs while 반복문을 이용한 탐색을 하며 갈수 있는 곳을 방문하면, 방문체크, 큐에 넣기
        while(!que.isEmpty()){
            int x = que.poll();
            int y = que.poll();
            for(int i=0;i<4;i++){
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];

                if(nx<1 || ny<1 || nx>N || ny>M) continue;
                if(visit[nx][ny]) continue;
                if(map[nx][ny] != 0 ) continue;

                visit[nx][ny] = true;
                que.add(nx);
                que.add(ny);
            }
        }
        // 탐색이 종료되었다면 이제 방문한적 없는 안전 영역의 넓이를 계산하고 ans에 갱신한다.
        int temp=0;
        for(int i=1;i<=N;i++){
            for(int j=1;j<=M;j++){
                if(map[i][j]==0 && !visit[i][j]){
                    temp++;
                    //System.out.println(temp);
                }
            }
        }
        ans = Math.max(ans,temp);
    }

    static void dfs(int idx,int selected_cnt){
        if(selected_cnt==3){
            //3개의 벽을 모두 세운 경우
            bfs();
            return;
        }

        if(idx>B) return; //더 이상 세울수 있는 벽이 없는 상태

        map[blank[idx][0]][blank[idx][1]] = 1;
        dfs(idx+1,selected_cnt+1);

        map[blank[idx][0]][blank[idx][1]] = 0;
        dfs(idx+1,selected_cnt);

        //dfs 재귀를 이용해서 3개의 벽을 세우기 위해서 벽을 세우거나, 안세우거나 한다.
    }

    static void process(){
        //벽을 세울 수 있는 위치를 모두 모아서 B++로 개수를 기록하고 B위치의 0,1 로 좌표를 기록해두기
        for(int i=1;i<=N;i++){
            for(int j=1;j<=M;j++){
                if(map[i][j]==0){
                    //벽을 세울 수 있는 위치
                    B++; //B의 값을 증가 시킨뒤 저장(1부터 저장된다는 의미이다.)
                    blank[B][0] = i;
                    blank[B][1] = j;
                }
            }
        }

        dfs(1,0); //1번 벽의 위치에 세울지말지, 기존까지 선택된 벽의 개수 =0
        //벽을 3개 세우는 모든 방법을 확인하기

        System.out.println(ans);
    }

    public static void main(String[] args){
        //System.out.println(((64*63*62)/(3*2))*64);
        input();
        process();
    }

}
