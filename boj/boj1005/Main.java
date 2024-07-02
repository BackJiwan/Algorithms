package boj.boj1005;
/*
* 1.정답의 최대치
* - 10^3 개의 건물이 모두 10^5 시간이 걸리면
* - 일렬로 배열되어 있었을 때에 10^8 이므로 int 가능 100,000,000 = 1억
* 2.복잡도 계산
* - 위상정렬 하면 O(V+E)=O(10^3+10^5) 1초 충분
* 3.문제 이해
* 4.풀이 구상
* - input 받으면서 adj[x] 즉, x에서 갈수 있는 곳들을 인접 리스트로 저장
*   - adj[x].add(y)의 경우라면 indeg[y]를 증가 시키기
* - process
*   -
*
* */
import java.io.*;
import java.util.*;

public class Main {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int Q,N,K;
    static int[] T,T_done,indeg;
    static ArrayList<Integer> [] adj;

    static void input(){
        N = sc.nextInt();
        K = sc.nextInt();
        adj = new ArrayList[N+1];
        T = new int[N+1];
        T_done = new int[N+1];
        indeg = new int[N+1];

        for(int i=1;i<=N;i++){
            adj[i] = new ArrayList<>();
            T[i] = sc.nextInt();
        }

        for(int i=1;i<=K;i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            adj[x].add(y);
            indeg[y]++;
        }
    }

    static void process(){
        Deque<Integer> deque = new LinkedList<Integer>();
        //완탐 한번해서 indeg가 0인 녀석들을 deque에 추가하기
        //indeg가0 이라면 t_done에 t 값을 넣어두기
        for(int i=1;i<=N;i++){
            if(indeg[i]==0){
                deque.add(i);
                T_done[i] = T[i];
            }
        }

        //반복문으로 bfs 하면서 하나씩 poll 시켜서
        //해당 adj가 가진 y 값들을 향상된 for문으로 꺼내서
        //y의 indeg를 감소 시키고
        //t_done[y] = math.max(t_done[y],t_done[x]+t[y])
        //연산을 한번해서 y로 간선이 들어가는 경우마다 y의 최대 시간을 새로고침해준다.
        //그리고 만약 indeg[y]가 0이면 deque에 넣어준다.

        while(!deque.isEmpty()){
            int x = deque.poll();
            for(int y:adj[x]){
                indeg[y]--;
                T_done[y] = Math.max(T_done[y],T_done[x]+T[y]);
                if(indeg[y]==0){
                    deque.add(y);
                }
            }
        }
        int W  = sc.nextInt();
        sb.append(T_done[W]).append('\n');
    }

    public static void main(String[] args){
        Q = sc.nextInt();
        while(Q>0){
            input();
            process();
            Q--;
        }
        System.out.println(sb.toString());
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
}
