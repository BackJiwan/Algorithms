package boj.boj15970;


import java.util.*;
import java.io.*;

public class Main {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();
    static class FastReader{
        StringTokenizer st;
        BufferedReader br;

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

        Double nextDouble(){
            return Double.parseDouble(next());
        }

        Long nextLong(){
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

    static int N;
    static ArrayList<Integer>[] arr;

    static void input(){
        N = scan.nextInt();
        arr = new ArrayList[N+1];
        for(int color=1 ;color<=N; color++){
            arr[color] = new ArrayList<Integer>();
        }
        for(int i=1;i<=N;i++){
            int coord, color; //위치,색깔
            coord = scan.nextInt();
            color = scan.nextInt();
            arr[color].add(coord); //색깔 인덱스에 위치를 계속 add 해준다.
        }
    }

    public static void pro(){
        for (int color = 1; color <= N; color++)
            Collections.sort(arr[color]);

        int ans = 0;
        for(int i=1;i<=N;i++){
            if(arr[i].isEmpty()){
                continue;
            }
            int size = arr[i].size(); //i 색깔 어레이에 들어있는 원소의 개수
            ans += arr[i].get(1)-arr[i].get(0); //1번 점은 무조건 2번까지의 거리만 넣는다
            ans += arr[i].get(size-1) - arr[i].get(size-2);
            for(int cnt=1;cnt<size-1;cnt++){
                int left = arr[i].get(cnt)-arr[i].get(cnt-1);
                int right = arr[i].get(cnt+1)-arr[i].get(cnt);
                if(left>right){
                    ans += right;
                }else{
                    ans += left;
                }
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args){
        input();
        pro();
    }
}