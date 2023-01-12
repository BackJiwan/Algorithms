package sw_ex.no2;
//시간초과 + 0.13초 정도 + 1씩 빼기 사용
/*
*
13
16 1 3
2 6 9
5 0 8
422223324 2 4
1008 1 2
1002 0 1
4008 0 2
4008 1 2
4008 1 5
100400 1 9
242424 2 4
242420 2 4
24323 2 4

* */

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Solution2 {
    static int x,y,len,n;
    static Integer N1;
    static String temp;
    static boolean check;
    static BigInteger A;
    static BigInteger B = new BigInteger("1");

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine());
            temp = st.nextToken();
            len = temp.length();
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            N1 = temp.charAt(0) - '0'; //N은 주어진 temp의 가장 앞의 수
            if(x==0 && N1 < y){
                sb.append('#').append(i).append(' ').append(-1).append('\n');
                continue;
            }
            if(N1<x){
                sb.append('#').append(i).append(' ').append(-1).append('\n');
                continue;
            }
            len = temp.length();
            rec_func2();
            sb.append('#').append(i).append(' ').append(temp).append('\n');
        }
        System.out.println(sb.toString());
    }
    static void rec_func2(){
        while(true) {
            len = temp.length();
            check = true;
            for(int i = 0; i < len; i++) {
                if((temp.charAt(i) - '0')!= x && (temp.charAt(i) - '0') != y) {
                    check = false;
                    break;
                }
            }
            if(check == true) break;
            else {
                A = new BigInteger(temp); //지금부터 정수 temp 는 A이다.
                temp = String.valueOf(A.subtract(B));
            }
        }
    }
}
