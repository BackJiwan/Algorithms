package sw_ex.no2;

/*
*
8
16 1 3
2 6 9
5 0 8
422223324 2 4
1008 1 2
4008 0 2
4008 1 2
4008 1 5
* */

import java.io.*;
import java.util.*;

public class Solution {
    static int x,y,len,sol2,small2,n;
    static Integer N;
    static String temp,str2,str3;
    static Integer[] a;
    static ArrayList<Integer> result;
    static ArrayList<Integer> small;
    static ArrayList<Integer> sol;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            len = (int)(Math.log10(N)+1);
            sol2 = 0;
            small2 = 0;
            small = new ArrayList<>();
            sol = new ArrayList<>();
            for(int j=0;j<len;j++){
                small.add(x);
            }
            str2 = "";
            for(Integer k:small){
                str2 += k;
            }
            small2 = Integer.parseInt(str2);

            if(x==0 && N < y){
                sb.append('#').append(i).append(' ').append(-1).append('\n');
                continue;
            }
            if(N<x){
                sb.append('#').append(i).append(' ').append(-1).append('\n');
                continue;
            }
            if(N < small2){
                for(int j=1;j<len;j++){
                    sol.add(y);
                }
                str3 = "";
                for(Integer k:sol){
                    str3 += k;
                }
                sol2 = Integer.parseInt(str3);
                sb.append('#').append(i).append(' ').append(sol2).append('\n');
                continue;
            }
            temp = Long.toString(N);
            a = new Integer[len+2];
            result = new ArrayList<>();
            for (int j = 0; j < temp.length(); j++){
                a[j] = temp.charAt(j) - '0';
            }
            rec_func(0);
            sb.append('#').append(i).append(' ');
            if(result.get(0)==0){
                result.remove(0);
            }
            for(Integer k:result){
                sb.append(k);
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }
    static void rec_func(int idx){
        if(idx==len){
            return;
        } else if(a[idx]<y && x<a[idx]){
            result.add(x);
            for(int j=0;j<len-idx-1;j++){
                result.add(y);
            }
            return;
        }else if(a[idx]<y && x==a[idx]){
            result.add(x);
            rec_func(idx+1);
            return;
        } else if(a[idx] < x && idx==0){
            for(int j=0;j<len-1;j++){
                result.add(y);
            }
            return;
        } else if(a[idx] < x && idx != 0){
            result.add(y);
            for(int j=0;j<len-idx-1;j++){
                result.add(y);
            }
            return;
        } else if(a[idx] >= y){
            result.add(y);
            rec_func(idx+1);
            return;
        }else{
            return;
        }
    }
}
