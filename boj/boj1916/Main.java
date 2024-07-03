package boj.boj1916;
/*
* 1.정답의 최대치(-21억~ 21억 : int범위)
* - 가장 오래걸린다면 10^3 개의 도시 처음부터 끝까지 10^5의 비용으로 가는경우
* - 10^8 = 1억이므로 int로 충분
* 2.복잡도 계산(1초 : 1억번 연산)
* - bfs = O(E+V)
* - 다익스트라 = O(E logV) = 10만 * log1000 = 100만
* 3.문제 이해
* - N(1~10^3)개의 도시, M(10^5)개의 버스
* - 버스정보 ( 출발도시,도착도시,비용)
* - 이차원 배열에 가중치를 저장하는 식으로 하면 될듯
* - 버스비용(10^5)
* - 최소비용 구하기
* 4.풀이 구상
* */

import java.io.*;
import java.util.*;
public class Main {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N,M;
    static ArrayList<Edge>[] edges;
    static int start,end;
    static int[] dist;
    //목적지(to), 가중치(weight)를 가지는 Edge 클래스
    static class Edge{
        int to;
        int weight;

        public Edge(int _to,int _weight){
            this.to = _to;
            this.weight = _weight;
        }
    }

    //최소힙(우선순위큐)에 넣기위한 Info 클래스
    //최소힙에 들어가면서 dist가 짧은 순서대로 idx를 정렬하기 위함이므로
    //현재 노드의 idx와 거기 까지 가기위한 dist를 가진다.
    static class Info{
        int idx;
        int dist;


        public Info(int _idx,int _dist){
            this.idx = _idx;
            this.dist = _dist;
        }
    }

    //다익스트라 함수
    //bfs와 비슷하게 동작할예정
    //dijkstra(int start,int dist)
    //시작위치와 해당 시작위치의 최초 dist를 매개변수로 함수가 호출된다.
    //다익스트라가 모두 돌고나면 dist[N+1] 의 각 idx에는 start에서 탐색한 최단거리가 담겨 있음
    static void dijkstra(int start,int init){
        //전체 dist를 무한대 표시해주기
        for(int i=1;i<=N;i++) dist[i] = Integer.MAX_VALUE;

        //일단 최초 start와 dist를 info로 만들어서 자료구조에 넣고
        //dist[start] = 0 기록하기
        //info(idx,dist)의 의미 : 시작점 start 부터 idx 까지 가는데에 dist의 거리가 필요하다는 의미
        PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
        pq.add(new Info(start,0));
        dist[start] = 0;

        //while 돌면서 heap에 자료 없을 때까지 반복
        //info에 꺼내기

        //만약 옛날 info라서 의미 없으면 continue (dist[info.idx] != info.dist)
        //그게 아니라면 Edge e :edges[info.idx] = 현재 꺼낸 정점에서 갈수 있는 점들을 edge로 모두 조회한다.
        //스킵조건 : dist[e.to] <= dist[info.idx] + e.weight 이면 스킵
        // -> 기존에 e.to 까지 가는 알던 길dist 보다, 현재 꺼낸 idx를 거쳐서 가는게 더 오래걸리면 굳이 새로고침 안해도됨
        //스킵조건 안걸렸으면 여기 idx 거쳐가는게 더 짧은거니까
        //dist[e.to] = dist[info.idx] + e.weight; 로 넣어주고
        //어차피 꺼낼때에 최단거리로 꺼낼꺼니까 넣을때는 그냥 pq 방금 사용한 new info(e.to , dist[e.to])를 넣어준다.

        while(!pq.isEmpty()){
            Info info = pq.poll();

            if(dist[info.idx] != info.dist) continue;

            for(Edge e : edges[info.idx]){
                if(dist[e.to] <= dist[info.idx]+e.weight) continue;

                dist[e.to] = dist[info.idx]+e.weight;
                pq.add(new Info(e.to,dist[e.to]));
            }
        }
    }

    static void process(){
        dijkstra(start,0);

        System.out.println(dist[end]);
    }

    static void input(){
        N = sc.nextInt();
        M = sc.nextInt();
        edges = new ArrayList [N+1]; //edges는 도시의 개수만큼 배열의 인덱스 = 도시번호, 줄줄이 어레이 = to,weight를 가진다.
        dist = new int[N+1];
        for(int i=0;i<=N;i++) edges[i] = new ArrayList<>();
        for(int i=0;i<M;i++){
            int from = sc.nextInt();
            int to = sc.nextInt();
            int weight = sc.nextInt();
            edges[from].add(new Edge(to,weight));
        }
        start = sc.nextInt();
        end = sc.nextInt();
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
            while(st==null||!st.hasMoreElements()){
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
