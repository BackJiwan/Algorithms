//package boj.boj2178;
//
///*
//* 1.시작 위치는 (1,1) 도착 위치는 (N,M) 고정
//* 2.배열의 크기가 최대일때에는 N * M = 10,000 = 1만
//* 3.칸을 저장하는 N크기의 String을 저장하는 배열 map
//* 4.시작점에서부터 BFS탐색을 시작한다.
//* */
//
//import java.util.*;
//import java.io.*;
//
//public class Main {
//    static FastReader scan = new FastReader();
//    static StringBuilder sb = new StringBuilder();
//
//    static int N,M,ans;
//    static String[] map;
//    static boolean[][] visit;
//    static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
//    static ArrayList<Integer>[] adj;
//    static int[][] dist;
//
//    public static void main(String[] args){
//        input();
//        pro();
//    }
//
//    static void input(){
//        N = scan.nextInt();
//        M = scan.nextInt();
//        map = new String[N];
//        for(int i=0;i<N;i++){
//            map[i] = scan.nextLine();
//        }
//        visit = new boolean[N][M];
//        dist = new int[N][M];
//    }
//
//    static void pro(){
//        bfs(0,0); //시작점은 0,0이다.
//        System.out.println(dist[N-1][M-1]); // 도착점까지의 최소 이동 간선수 출력
//    }
//
//    static void bfs(int x,int y){
//        for(int i=0;i<N;i++){
//            for(int j=0;j<M;j++){
//                dist[i][j] = -1; //dist 배열을 -1로 초기화
//            }
//        }
//        Queue<Integer> que = new LinkedList<>();
//        que.add(x);
//        que.add(y); //순서대로 두쌍씩 꺼내기만 하면 x와 y를 하나씩 저장해도 괜찮다.
//        dist[x][y] = 1; //일단 시작점부터 1을 카운트한다.
//        visit[x][y] = true; //방문체크
//
//        while(!que.isEmpty()){ //bfs시작
//            x = que.poll();
//            y = que.poll();
//            for(int k=0;k<4;k++){
//                int nx = x + dir[k][0];
//                int ny = y + dir[k][1]; //다음 x,y 좌표를 동서남북으로 한번씩 대입한다.
//                if(nx<0||ny<0||nx>=N|ny>=M) continue; //지도를 벗어나는지
//                if(map[nx].charAt(ny) =='0') continue; //방문 불가능한 정점인지
//                if(visit[nx][ny]) continue; //방문한적이 있는지
//                que.add(nx); //조건을 만족하는 방문한적 없는 정점이라면 큐에 추가한다.
//                que.add(ny);
//                visit[nx][ny] = true;
//                dist[nx][ny] = dist[x][y]+1; //다음칸으로 방문하면서 기존보다 거리는 1 늘어난것을 저장해준다.
//            }
//        }
//    }
//
//
//    static class FastReader{
//        BufferedReader br;
//        StringTokenizer st;
//        public FastReader(){
//            br = new BufferedReader(new InputStreamReader(System.in));
//        }
//        public FastReader(String s) throws FileNotFoundException{
//            br = new BufferedReader(new FileReader(new File(s)));
//        }
//        String next(){
//            while(st==null || !st.hasMoreElements()){
//                try{
//                    st = new StringTokenizer(br.readLine());
//                }catch(IOException e){
//                    e.printStackTrace();
//                }
//            }
//            return st.nextToken();
//        }
//        int nextInt(){
//            return Integer.parseInt(next());
//        }
//        long nextLong(){
//            return Long.parseLong(next());
//        }
//        String nextLine(){
//            String str = "";
//            try{
//                str = br.readLine();
//            }catch(IOException e){
//                e.printStackTrace();
//            }
//            return str;
//        }
//
//    }
//}
