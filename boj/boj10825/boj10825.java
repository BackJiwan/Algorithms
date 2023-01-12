package boj.boj10825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
* 1. 학생수(1~10만)
* 2.
* */
public class boj10825 {
    static Elem[] a;
    static int N;

    static class Elem implements Comparable<Elem>{
        public String name;
        public int korean,english,math;

        @Override
        public int compareTo(Elem other){
            if(other.korean != korean)
                return other.korean - korean;
            else if(other.english != english)
                return english - other.english;
            else if(other.math != math)
                return other.math - math;
            else
                return name.compareTo(other.name);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        a = new Elem[N];

        for(int i=0;i<N;i++){
            a[i] = new Elem();
            st = new StringTokenizer(br.readLine());
            a[i].name = st.nextToken();
            a[i].korean = Integer.parseInt(st.nextToken());
            a[i].english = Integer.parseInt(st.nextToken());
            a[i].math = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(a);
        for(int i=0;i<N;i++){
            sb.append(a[i].name).append('\n');
        }
        System.out.println(sb.toString());
    }
}
