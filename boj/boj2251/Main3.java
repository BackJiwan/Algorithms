package boj.boj2251;
/*
* Limit[] 배열에 각 물통의 최대 값을 저장한다.
* bfs(0,0,Limit[2])로 bfs 탐색을 시작한다.
* while 큐가 비어있기 전까지 반복하면서 from0~3 (to 0~3) 을 반복하면서 소스-타겟 관계를 계속 반복해준다.
* 한번 move가 되고 나서는 첫번째 물통이 0 인경우마다 2번째 물통의 물의양을 possible 배열에 기록해준다.
* */
import java.io.*;
import java.util.*;
class State3{
    int[] X;

    public State3(int[] tX){
        X = new int[3];
        for(int i=0;i<3;i++){
            X[i] = tX[i];
        }
    }

    State3 move (int from,int to,int[] Limit){
        int[] nX = new int[] {X[0],X[1],X[2]};
        if(nX[to]+nX[from]>=Limit[to]){ //두 물병을 더하면 타겟물통이 넘친다면
            nX[from] -= (Limit[to]-nX[to]);
            nX[to] = Limit[to];
        }else{
            nX[to] += nX[from];
            nX[from] = 0;
        }
        return new State3(nX);
    }

}
public class Main3 {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();
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

    static int[] Limit;
    static boolean[][][] visit;
    static boolean[] possible;

    static void input(){
        Limit = new int[3];
        for(int i=0;i<3;i++){
            Limit[i] = sc.nextInt();
        }
        visit = new boolean[205][205][205];
        possible = new boolean[205];
    }

    static void bfs(int a,int b,int c){
        Queue<State3> que = new LinkedList<>();
        que.add(new State3(new int[] {a,b,c}));
        visit[a][b][c] = true;
        while(!que.isEmpty()){
            State3 state = que.poll();
            if(state.X[0]==0) possible[state.X[2]]=true;
            for(int from=0;from<3;from++){
                for(int to=0;to<3;to++){
                    if(from==to) continue;
                    State3 state2 = state.move(from,to,Limit);
                    if(!visit[state2.X[0]][state2.X[1]][state2.X[2]]){
                        visit[state2.X[0]][state2.X[1]][state2.X[2]] = true;
                        que.add(state2);
                    }
                }
            }
        }

    }
    static void process(){


        bfs(0,0,Limit[2]);

        for(int j=0;j<201;j++){
            if(possible[j]) {
                sb.append(j).append(" ");
            }
        }

        System.out.println(sb.toString());
    }

    public static void main(String[] args){
        input();
        process();
    }
}
