package boj.boj2667;

import java.io.*;
import java.util.*;

public class Main2 {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();



    static class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
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

        String nextLine(){
            String str = "";
            try{
                str = br.readLine();
            }catch(IOException e){
                e.printStackTrace();
            }
            return str;
        }

        int nextInt(){
            return Integer.parseInt(next());
        }
    }

    static int N;
    static String[] map;
    static int[][] dr = {{-1,0},{1,0},{0,1},{0,-1}};
    static ArrayList<Integer> group;
    static boolean[][] visit;
    static int group_cnt;

    static void dfs(int x,int y){
        //일단 dfs 호출횟수만 해도 group_cnt를 늘려줘야함
        //그리고 해당 점을 기점으로 4방 찍고 dfs 재귀 호출즈
        group_cnt++;
        visit[x][y]=true;
        for(int k=0;k<4;k++){
            int nx = x + dr[k][0];
            int ny = y + dr[k][1];
            if(nx<0||ny<0||(nx>=N)||(ny>=N)) continue;
            if(visit[nx][ny]) continue;
            if(map[nx].charAt(ny)=='0') continue;
            //System.out.printf("dfs %d,%d 호출",nx,ny);
            dfs(nx,ny);
        }

    }

    static void input(){
        // 입력받기
        N = sc.nextInt();
        map = new String[N];
        for(int i=0;i<N;i++){
            map[i] = sc.nextLine();
        }
        group = new ArrayList<>();

    }

    static void process(){
        // 격자의 이차원 배열 위에서 이동한다.
        int count=0;
        visit = new boolean[N][N];
        //System.out.println("process 함수 들어오긴함?");
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                //처음으로 집이 시작되는 단지를 만나는지를 검새해야하므로 1이 아니면 continue, visit가 false이면 continue 니까
                //둘다 and 조건 만족하는 경우에만 group_cnt 열고 dfs 호출한뒤 group_cnt를 arrayList에 추가하면 될듯
                //System.out.println(visit[i][j]);
                //System.out.println(map[i].charAt(j));
                //System.out.println("카운트 : "+count++);
                if((!visit[i][j])&&(map[i].charAt(j)=='1')){
                    //System.out.println("여기 안으로 한번은 들어옴?"+i+" "+j);
                    group_cnt = 0;
                    dfs(i,j);
                    //System.out.println("groupt에 추가되는 밸류 : "+group_cnt);
                    group.add(group_cnt);
                }
            }
        }
        sb.append(group.size()).append("\n");
        Collections.sort(group);
        for(int y:group){
            sb.append(y).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args){
        input();
        process();
    }
}
