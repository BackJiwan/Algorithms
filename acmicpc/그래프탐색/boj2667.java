package acmicpc.그래프탐색;

import java.io.*;
import java.util.*;
public class boj2667 {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();
    static class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        public FastReader(String s)throws FileNotFoundException{
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
    static int N;
    static String[] map;
    static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    static ArrayList<Integer> group;
    static boolean[][] visit;
    static int group_cnt;

    static void input(){
        N = sc.nextInt();
        map = new String[N];
        for(int i=0;i<N;i++){
            map[i] = sc.nextLine();
        }
        group = new ArrayList<>();
    }

    static void process(){
        //static visit 배열을 통해서 방문 여부를 판단 예정 -> 초기화 필요
        visit = new boolean[N][N];

        //기본적으로 2중 반복문을 통해서 최초 그룹 진입 시점을 탐색한다.
        //방문한적 없고 , 1이라면 최초 그룹 이므로 group_cnt를 0으로 한뒤 dfs 로 보낸다.
        //dfs를 다녀왔으면 group_cnt 가 그룹 크기만큼 설정되어 있을 것이기 때문에 group 배열에 add
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){

                if(!visit[i][j] && (map[i].charAt(j))=='1'){
                    group_cnt = 0;
                    dfs(i,j);
                    group.add(group_cnt);
                }

            }
        }

        //이중 반복문 다 돌고 나온거라면 size와 정렬된 그룹 크기를 출력한다.
        sb.append(group.size()).append('\n');
        Collections.sort(group);
        for(int k:group){
            sb.append(k).append('\n');
        }
        System.out.println(sb.toString());
    }

    static void dfs(int x,int y){
        visit[x][y] = true;
        group_cnt++;
        for(int i=0;i<4;i++){
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];

            //nx,ny 가 범위를 벗어나면
            if((nx<0)||(ny<0)||(nx>=N)||(ny>=N)) continue;
            //방문한적이 있다면
            if(visit[nx][ny]) continue;
            //1이 아니라면
            if(map[nx].charAt(ny)!='1') continue;
            //조건 다 통과했으면 다음 dfs 호출해주면 됨
            dfs(nx,ny);

        }
    }

    public static void main(String[] args){
        input();
        process();
    }

}

//    static void process(){
//        //0~n,n 까지를 모두 순차 탐색한다 그러다가 최초로 1을 만난순간에 해당 좌표를 dfs로 보낸다.
//        //dfs가 구동되면 갈수 있는 1에 한해서만 끝까지 탐색하며 매 탐색마다 해당 그룹의 카운트를 늘린다.
//        //그렇게 해서 늘린 카운트를 group.add로 add 해준다.
//        visit = new boolean[N][N];
//
//
//        for(int i=0;i<N;i++){
//            for(int j=0;j<N;j++){
//                if((!visit[i][j])&&(map[i].charAt(j))=='1'){
//                    //방문한적 없고, 1이니까 갈수 있다. 즉 처음만난 1 이므로 새로운 그룹의 시작
//                    group_cnt=0;
//                    dfs(i,j); //dfs 다녀왔으면 gc가 최대치로 갱신되어 있을겨
//                    group.add(group_cnt);
//                }
//            }
//        }
//        Collections.sort(group);
//        sb.append(group.size()).append('\n');
//        for(int K:group){
//            sb.append(K).append('\n');
//        }
//        System.out.println(sb.toString());
//
//    }
//
//    static void dfs(int x,int y){
//        //cnt 증가 조건은 dfs 호출 순간
//        //호출순간마다 visit 체크도 해야함
//        group_cnt++;
//        visit[x][y] = true;
//        for(int i=0;i<4;i++){
//            int nx = x + dir[i][0];
//            int ny = y + dir[i][1];
//            if((nx<0)||(ny<0)||(nx>=N)||(ny>=N)) continue;
//            if(visit[nx][ny]) continue;
//            if(map[nx].charAt(ny)!='1') continue;
//            dfs(nx,ny);
//        }
//    }




