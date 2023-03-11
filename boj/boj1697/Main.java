package boj.boj1697;

/*
* 1.수빈이의 점 : N, 동생 : K, +1, -1 ,*2 의 3가지 간선을 가진다.
* 2.수빈이가 동생을 찾을수 있는 가장 빠른시간은?
* 3.가능한 최대 : 수빈이 제일 크고, 동생이 가장 적으면 -1만 반복해야 한다.
*   10만번의 연산 => int 가능
* 4.가장 빠른 시간 = 가장 적은 간선수 = BFS의 dist 이용
* */
import java.io.*;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N, K;
    static int[] dist;
    static boolean[] visited;

    public static void main(String[] args) {
        input();
        pro();
    }

    static void input() {
        N = scan.nextInt();
        K = scan.nextInt();
        visited = new boolean[100002];
        dist = new int[100002];
    }

    static void pro() {
        bfs();
        //dist[K]에는 출발점에서 K까지 가는 최단 이동횟수가 저장되어 있다.
        System.out.println(dist[K]);
    }

    static void bfs() {
        Queue<Integer> Q = new LinkedList<>();
        Q.add(N);
        visited[N] = true;
        dist[N] = 0;

        // BFS
        while (!Q.isEmpty()) {
            int x = Q.poll();
            //뒤로가는 간선은 0보다 커야한다
            if (x - 1 >= 0 && !visited[x - 1]) {
                visited[x - 1] = true;
                dist[x - 1] = dist[x] + 1;
                Q.add(x - 1);
            }
            //앞으로 1칸 가는 간선의 조건
            if (x + 1 <= 100000 && !visited[x + 1]) {
                visited[x + 1] = true;
                dist[x + 1] = dist[x] + 1;
                Q.add(x + 1);
            }
            //앞으로 2배로 가는 간선의 조건
            if (x * 2 <= 100000 && !visited[x * 2]) {
                visited[x * 2] = true;
                dist[x * 2] = dist[x] + 1;
                Q.add(x * 2);
            }
        }
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
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
