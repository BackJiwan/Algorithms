package boj.boj1753;
/*
* 1.정답의 최대치(-21억~21억,int): int 충분
* - 모든 정점(2*10^4)이 일렬로 늘어서 있고,
* - 그 사이의 모든 간선이 가중치 최대 10이라면
* - 정답의 최대치는 2*10^4 * 10 = 2 * 10^5 = 20만
* - int 충분하다.
* 2.복잡도 계산(1억번,1초):
* - 다익스트라 알고리즘 사용시
* - O(E logV) = 30만 * log20만 => 얼추 150만
* 3.문제 이해:
* - 방향 그래프, 주어진 시작점, 모든 정점으로 최단 경로
* - 모든 간선 가중치는 10이하 자연수(음의 가중치 없음 -> dijkstra)
* 4.풀이 구상:
* */
import java.io.*;
import java.util.*;
public class Main {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int V,E,K;
    static int[] dist;
    static ArrayList<Edge> [] edges;

    static class Edge{
        int to;
        int weight;

        public Edge(int _to,int _weight){
            this.to = _to;
            this.weight = _weight;
        }
    }

    static class Info{
        int idx;
        int dist;

        public Info(int _idx,int _dist){
            this.idx = _idx;
            this.dist = _dist;
        }
    }

    static void input(){
        V = sc.nextInt(); //정점의 개수
        E = sc.nextInt(); //간선의 개수
        K = sc.nextInt(); //시작 정점의 번호
        dist = new int[V+1];
        edges = new ArrayList [V+1];

        for(int i=1;i<=V;i++) edges[i] = new ArrayList<>();

        for(int i=1;i<=E;i++){
            int from = sc.nextInt();
            int to = sc.nextInt();
            int weight = sc.nextInt();
            edges[from].add(new Edge(to,weight));
        }

    }

    static void dijkstra(int start,int init_dist){
        //dist를 모두 최대 값으로 초기화
        for(int i=1;i<=V;i++) dist[i] = Integer.MAX_VALUE;

        //우선순위큐, 최소힙 , Info객체의 dist를 기준으로 오름차순으로 저장
        PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));

        //시작점을 최소힙에 추가, 시작점 -> 시작점의 거리 0으로 기록
        pq.add(new Info(start,init_dist));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Info info = pq.poll();

            // 힙에서 꺼냈는데 거기까지 가는 거리적혀있는게, dist에 적혀있는것과 다르다면 옛날에 넣어둔거라 쓸일 없음
            if(info.dist != dist[info.idx]) continue;

            //쓸만한 정보라면 해당 idx까지 dist 정보를 갱신할꺼라서 해당 idx에서 갈수 있는 점들을 하나씩 꺼내서 갱신시도
            for(Edge e : edges[info.idx]){
                //info.idx에서 갈수 있는 점 e의 기존거리 dist보다 idx + 가중치가 더 같거나 길면 굳이 갱신 필요 없음
                if(dist[e.to] <= dist[info.idx]+e.weight) continue;

                //갱신가능하니까 갱신하고, 갱신된 e.to 와 e.to까지 가는길정보를 info로 최소힙에 넣어준다.
                dist[e.to] = dist[info.idx]+e.weight;
                pq.add(new Info(e.to,dist[e.to]));
            }
        }

    }

    static void process(){
        dijkstra(K,0);

        for(int i=1;i<=V;i++){
            if(dist[i]==Integer.MAX_VALUE){
                sb.append("INF").append('\n');
            }else{
                sb.append(dist[i]).append('\n');
            }
        }

        System.out.println(sb.toString());
    }

    public static void main(String[] args){
        //System.out.println(Math.log(200000));
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
            while(st==null ||!st.hasMoreElements()){
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
