//package boj.boj2251;
///*
///*
//* 1. 강의 본 뒤에 푼 풀이
//* 2. 각 물통들의 사이즈와 상태를 가지는 구조체 클래스 필요 State
//* 3. 물통의 초기 상태를 Queue에 추가하여 BFS탐색한다.
//* 4. 방문을 기록하는 배열visit은 가능한 모든 경우의 수를 담는다. 200^3
//* 5. 각 물병끼리의 물교환은 -> 완전탐색 (3*3 - 3)
//* 6. 완탐과정중에 물병끼리의 물 교환을 담당하는 move() 메소드는 기존의
//*   물병상태배열과 비교하여 리턴값으로 변화된 이후의 State를 반환해야 하므로 구조체 클래스 내부에 구현
//*   리턴값 = State
//* */
//
//
//import java.util.*;
//import java.io.*;
//
//
//class State{
//
//    int[] X; //물통에 담긴 물의 상태를 가지는 배열(a,b,c)
//    State(int[] _X){ //생성자는 매개변수르 물통의 상태를 받는다.
//        X = new int[3];
//        for(int i=0;i<3;i++){
//            X[i] = _X[i];
//        }
//    }
//
//    State move(int from,int to,int[] Limit){
//        //from 물통에서 to 물통으로 옮긴다.
//        int[] nX = new int[]{X[0],X[1],X[2]}; //
//        if(X[from]+X[to] <= Limit[to]){ //from+to가 사이즈보다 같거나 작다면 모두 부을수있다.
//            nX[to] = nX[from] + nX[to];
//            nX[from] = 0; //from물병은 모두 비워진다.
//        } else{ //to는 가득차고 from은 약간 남는다.
//            nX[from] -= Limit[to] - nX[to];
//            nX[to] = Limit[to];
//        }
//        return new State(nX);
//    }
//}
//
//
//public class Main {
//    static FastReader scan = new FastReader();
//    static StringBuilder sb = new StringBuilder();
//
//    static int[] Limit;
//    static boolean[] possible;
//    static boolean[][][] visit;
//
//    static void input(){
//        Limit = new int[3];
//        for(int i=0;i<3;i++){
//            Limit[i] = scan.nextInt();
//        }
//        visit = new boolean[205][205][205]; //방문한 정점을 표시
//        possible = new boolean[205]; //가능한 경우를 표시 => 정답출력에 이용
//    }
//
//    static void bfs(int a,int b,int c){
//        Queue<State> que = new LinkedList<>();
//        visit[a][b][c] = true;
//        que.add(new State(new int[]{a,b,c})); //물통의 초기 상태를 a,b,c로 받는다.
//
//        while(!que.isEmpty()){
//            State st = que.poll(); //첫 상태를 꺼내서 st에 참조
//            if(st.X[0]==0) possible[st.X[2]]=true; //a가 0인경우 C의 물양을 정답에 추가한다.
//            for(int from=0;from<3;from++){
//                for(int to=0;to<3;to++){
//                    if(from==to) continue; //같은 물병끼리의 교환은 반려
//                    State nxt = st.move(from,to,Limit); //from -> to의 물 교환
//
//                    if(!visit[nxt.X[0]][nxt.X[1]][nxt.X[2]]){//만약 물교환이후의 상태가 방문한 적 없다면
//                        visit[nxt.X[0]][nxt.X[1]][nxt.X[2]] = true; //방문체크
//                        que.add(nxt); //bfs를 위해서 큐에 State추가
//                    }
//
//                }
//            }
//
//        }
//    }
//
//    static void pro(){
//        bfs(0,0,Limit[2]);
//        for(int i=0;i<=200;i++){
//            if(possible[i]){
//                sb.append(i).append(' ');
//            }
//        }
//        System.out.println(sb);
//    }
//
//    public static void main(String[] args){
//        input();
//        pro();
//    }
//
//    static class FastReader{
//        BufferedReader br;
//        StringTokenizer st;
//
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
//    }
//}
