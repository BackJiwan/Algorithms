package acmicpc.정렬.boj10825_2;

import acmicpc.정렬.boj10825.boj10825;

import java.io.*;
import java.util.*;

public class Main {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
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


    static class Elem implements Comparable<Elem>{

        String name;
        int korean;
        int english;
        int math;

        public Elem(String _name,int _korean,int _english, int _math){
            this.name = _name;
            this.korean = _korean;
            this.english = _english;
            this.math = _math;
        }

        @Override
        public int compareTo(Elem other){
            if(this.korean != other.korean){ //국어점수가 다르면 들어온다.
                return -(this.korean- other.korean); //내림차순일때 내국어-놈국어 하면 양수니까 반전되버림, 고로 음수치환해줘서 내림차순 유지
            }else if(this.english != other.english){
                return (this.english - other.english); // 오름차순일때 내영어-놈영어 하면 음수니까 유지됨 ok
            }else if(this.math != other.math) {
                return -(this.math - other.math); //내림차순인데 내수학-놈수학하면 양수니까 반전되버림, 고로 음수치환해주면 내림 유지
            }else{ //국어같아,영어같아,수학같아 그러면 이름 사전순..?
                return this.name.compareTo(other.name);
            }
        }

    }

    static void process(){
        Arrays.sort(arr);
        for(int i=0;i<N;i++){
            sb.append(arr[i].name).append('\n');
        }
        System.out.println(sb.toString());

    }

    static int N;
    static Elem[] arr;
    static void input(){
        N = sc.nextInt();
        arr = new Elem[N];
        for(int i=0;i<N;i++){ //N번 반복한다는 의미
            String name = sc.next();
            int korean = sc.nextInt();
            int english = sc.nextInt();
            int math = sc.nextInt();
            arr[i] = new Elem(name,korean,english,math);
        }
    }

    public static void main(String[] args){
        input();
        process();
    }
}
