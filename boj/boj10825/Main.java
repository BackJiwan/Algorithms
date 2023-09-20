package boj.boj10825;

import java.util.*;
import java.io.*;

public class Main {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N;
    static Elem[] arr;

    static void input(){
        N = scan.nextInt();
        arr = new Elem[N];
        for (int i=0;i<N;i++){
            arr[i] = new Elem();
            arr[i].name = scan.next();
            arr[i].korean = scan.nextInt();
            arr[i].english = scan.nextInt();
            arr[i].math = scan.nextInt();
        }
    }

    static void pro(){
        Arrays.sort(arr);
        for(int i=0;i<N;i++){
            sb.append(arr[i].name).append('\n');
        }
        System.out.println(sb.toString());
    }
    public static void main(String[] args){
        input();
        pro();
    }

    static class Elem implements Comparable<Elem>{
        public String name;
        public int korean,english,math;

        @Override
        public int compareTo(Elem other){
            if(korean != other.korean){ //국어점수가 같지 않다면 국어로 분류한다.
                return other.korean - korean; //국어 : 내림차순
            } else if (english != other.english){
                return english - other.english; //영어 : 오름차순
            } else if (math != other.math){
                return other.math - math; //수학 : 내림차순
            } else{ // 사전순으로 앞에오는 문자열이 더 작은수, 증가하눈순 = 작은수가 앞에오도록, 오름차순
                return name.compareTo(other.name);
            }
        }
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
            }catch (IOException e){
                e.printStackTrace();
            }
            return str;
        }
    }
}
