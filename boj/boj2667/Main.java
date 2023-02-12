package boj.boj2667;

/*
* 1.일단 각 좌표를 이차원 배열에 저장한다. int[N][N]
* 2.visited 또한 int[N][N]으로 만들어준다.
* 3.아이디어:기본적으로는 BFS로 넓게 움직이지만 만약 방문한 vertex가 1이라면 DFS로 모든 vertex가 0이될떄까지 cnt를 ++ 하며이동
* 4.DFS가 모두 끝났을때에는 cnt를 ArrayList<Integer>의 새로운 요소로 추가해준다.
* 5.그리고 cnt는 다시 0이 되고 한번 방문한 노드의 visited 표시는 프로그램 내내 유효하다(static)
* 6.for(4번)를 이용하여 4개의 동서남북 움직임을 준다.
* 7.못풀겠어서 강의를 보겠습니다.
*
*
* */

import java.io.*;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N,group_cnt;
    static String[] arr;
    static boolean[][] visited;
    static int[][] move = {{1,0},{0,1},{-1,0},{0,-1}};
    static ArrayList<Integer> group;

    static void input(){
        N = scan.nextInt();
        arr = new String[N];
        for(int i=0;i<N;i++){
            arr[i] = scan.nextLine();
        }
        visited = new boolean[N][N];
    }

    static void dfs(int x,int y){
        group_cnt++; //매회탐색마다 카운트를 증가시킨다.
        visited[x][y] = true;
        for(int k=0;k<4;k++){
            int nx = x + move[k][0];
            int ny = y + move[k][1];
            if(nx<0||ny<0||nx>=N||ny>=N) continue;
            if(arr[nx].charAt(ny)=='0') continue;
            if(visited[nx][ny]) continue;
            dfs(nx,ny);        }
    }

    static void pro(){
        group = new ArrayList<>();
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(!visited[i][j] && arr[i].charAt(j)=='1'){
                    //만약 방문한적 없고, 단지가 존재(1이라면)한다면
                    //여기서 부터 연결된 단지의 크기를 카운트하면서 탐색하기 위해서
                    //dfs를 시작한다.
                    group_cnt =0;
                    dfs(i,j);
                    group.add(group_cnt);
                }
            }
        }
        Collections.sort(group);
        sb.append(group.size()).append('\n');
        for (int cnt: group) sb.append(cnt).append('\n');
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        input();
        pro();
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
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
