package acmicpc.그래프탐색;

import java.io.*;
import java.util.*;
public class boj1260 {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N,M,V;
    static boolean[] visit;
    static ArrayList<Integer>[] adj;

    static void input(){
        N = sc.nextInt();
        M = sc.nextInt();
        V = sc.nextInt();

        visit = new boolean[N+1];
        adj = new ArrayList[N+1];
        for(int i=0;i<=N;i++) adj[i] = new ArrayList<>();

        for(int i=1;i<=M;i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            adj[x].add(y);
            adj[y].add(x);
        }
    }

//    static void dfs(int start){
//        //재귀
//        visit[start] = true;
//        sb.append(start).append(' ');
//
//        for(int K :adj[start]){
//            if(visit[K]) continue;
//            dfs(K);
//        }
//    }
//
//    static void bfs(int start){
//        Deque<Integer> que = new LinkedList<>();
//        que.add(start);
//        visit[start] = true;
//        sb.append(start).append(' ');
//
//        while(!que.isEmpty()){
//            int temp = que.poll();
//            for(int K:adj[temp]){
//                if(visit[K]) continue;
//                que.add(K);
//                visit[K] = true;
//                sb.append(K).append(' ');
//            }
//        }
//    }
//
//    static void process(){
//        //작은점부터 가야하니까 정렬
//        for(int i=1;i<=N;i++){
//            Collections.sort(adj[i]);
//        }
//        //dfs (v) 호출
//        dfs(V);
//        sb.append('\n');
//        //다시 visit 초기화 시키고
//        for(int i=1;i<=N;i++){
//            visit[i] = false;
//        }
//        //bfs(v) 호출
//        bfs(V);
//        System.out.println(sb.toString());
//    }

    static void dfs(int start){
        visit[start] = true;
        sb.append(start).append(' ');

        for(int k:adj[start]){
            if(visit[k]) continue;
            dfs(k);
        }
    }

    static void bfs(int start){
        Deque<Integer> deque = new LinkedList<>();
        deque.add(start);
        sb.append(start).append(' ');
        visit[start] = true;

        while(!deque.isEmpty()){
            int temp = deque.poll();
            for(int k:adj[temp]){
                if(visit[k]) continue;
                deque.add(k);
                sb.append(k).append(' ');
                visit[k] = true;
            }
        }
    }


    static void process(){
        //작은것 부터니까 정렬 때리기
        for(int i=1;i<=N;i++){
            Collections.sort(adj[i]);
        }
        //dfs 먼저 시도
        dfs(V);
        //사이에 공백추가하기
        sb.append('\n');
        //dfs 하고났으니까 visit 초기화 해주기
        for(int i=0;i<=N;i++){
            visit[i] = false;
        }
        //bfs 돌리기
        bfs(V);
        System.out.println(sb.toString());
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
            while(st==null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
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

        long nexgLong(){
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











































//    static FastReader sc = new FastReader();
//    static StringBuilder sb = new StringBuilder();
//
//    static int N,M,V;
//    static ArrayList<Integer>[] adj;
//    static boolean[] visit;
//
//    static void input(){
//        N = sc.nextInt();
//        M = sc.nextInt();
//        V = sc.nextInt();
//        adj = new ArrayList[N+1];
//        visit = new boolean[N+1];
//        for(int i=0;i<=N;i++) adj[i] = new ArrayList<>();
//
//
//        for(int i=1;i<=M;i++){
//            int x = sc.nextInt();
//            int y = sc.nextInt();
//            adj[x].add(y);
//            adj[y].add(x);
//        }
//
//        //작은곳부터 방문이니 정렬해주기
//        for(int i=1;i<=N;i++){
//            Collections.sort(adj[i]);
//        }
//    }
//
//    static void dfs(int start){
//        //dfs 시작하자마자 현재 점에 true 체크를 해주고
//        //바로 sb.append
//        //그러니까 만약 부모 노드부터 시작한다치면
//        //부모, 작은점1,작은점1-1
//
//
//        visit[start] = true;
//        sb.append(start).append(' ');
//
//        for(int k:adj[start]){
//            //start에 연결된 간선들을 하나씩 꺼낸다.
//            //만약 방문한적이 있으면 스킵하고, 그게 아니면 재귀호출
//            if(visit[k]) continue;
//            dfs(k);
//
//        }
//    }
//
//    static void bfs(int start){
//        Queue<Integer> deque = new LinkedList<>();
//        deque.add(start);
//        visit[start] = true;
//        while(!deque.isEmpty()){
//            int temp = deque.poll();
//            sb.append(temp).append(' ');
//            for(int k:adj[temp]){
//                if(visit[k]) continue;
//                deque.add(k);
//                visit[k] = true;
//            }
//        }
//    }
//
//    static void process(){
//        dfs(V);
//        sb.append('\n');
//        for(int i=0;i<=N;i++){
//            visit[i] = false;
//        }
//        bfs(V);
//        System.out.println(sb.toString());
//    }
//
//
//    public static void main(String[] args){
//        input();
//        process();
//    }
//
//    static class FastReader{
//        BufferedReader br;
//        StringTokenizer st;
//
//        public FastReader(){
//            br = new BufferedReader(new InputStreamReader(System.in));
//        }
//
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
//
//        int nextInt(){
//            return Integer.parseInt(next());
//        }
//
//    }
//}
