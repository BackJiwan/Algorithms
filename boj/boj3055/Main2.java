package boj.boj3055;

import java.io.*;
import java.util.*;
public class Main2 {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int R,C;
    static String[] a;
    static boolean[][] visit;
    static int[][] dist_hedgehog;
    static int[][] dist_water;
    static int[][] dir = {{0,-1},{0,1},{1,0},{-1,0}};

    static void input(){
        R = sc.nextInt();
        C = sc.nextInt();
        a = new String[R];
        for(int i=0;i<R;i++){
            a[i] = sc.nextLine();
        }
        visit = new boolean[R][C];
        dist_hedgehog = new int[R][C];
        dist_water = new int[R][C];
    }

    static void bfs_water(){
        Queue<Integer> que = new LinkedList<>();

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                visit[i][j] = false;
                dist_water[i][j] = -1;
                if(a[i].charAt(j)=='*'){
                    que.add(i);
                    que.add(j);
                    visit[i][j] = true;
                    dist_water[i][j] = 0;
                }
            }
        }

        while(!que.isEmpty()){
            int x = que.poll();
            int y = que.poll();

            for(int k=0;k<4;k++){
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];

                if(nx<0||ny<0||nx>=R||ny>=C) continue;
                if(visit[nx][ny]) continue;
                if(a[nx].charAt(ny)!='.') continue;

                que.add(nx);
                que.add(ny);
                visit[nx][ny] = true;
                dist_water[nx][ny] = dist_water[x][y]+1;
            }
        }
    }

    static void bfs_hedgehog(){
        Queue<Integer> que = new LinkedList<>();

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                visit[i][j] = false;
                dist_hedgehog[i][j] = -1;
                if(a[i].charAt(j)=='S'){
                    que.add(i);
                    que.add(j);
                    visit[i][j] = true;
                    dist_hedgehog[i][j] = 0;
                }
            }
        }

        while(!que.isEmpty()){
            int x = que.poll();
            int y = que.poll();

            for(int k=0;k<4;k++){
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];

                if(nx<0||ny<0||nx>=R||ny>=C) continue;
                if((a[nx].charAt(ny)!='.')&&(a[nx].charAt(ny)!='D')) continue;
                if(visit[nx][ny]) continue;
                if((dist_water[nx][ny]!=(-1))&&(dist_water[nx][ny]<=dist_hedgehog[x][y]+1)) continue;

                que.add(nx);
                que.add(ny);
                visit[nx][ny] = true;
                dist_hedgehog[nx][ny] = dist_hedgehog[x][y] + 1;

            }
        }
    }


    static void process(){
        //물길 뚫기
        bfs_water();

        //고슴도치 이동시키기
        bfs_hedgehog();

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(a[i].charAt(j)=='D'){
                    if (dist_hedgehog[i][j]==(-1)){
                        sb.append("KAKTUS");
                    }else{
                        sb.append(dist_hedgehog[i][j]);
                    }
                }
            }
        }

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
