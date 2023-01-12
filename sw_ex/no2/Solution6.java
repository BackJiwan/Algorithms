package sw_ex.no2;
//시간초과 + 0.12s 정도 + 큐 이용
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
*
* */

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Solution6 {
    static int x,y,len,n,N1,tmp1;
    static int ans;
    static String temp,result,xStr;
    static BigInteger N,TMP1,ANS,X,Y;
    static BigInteger A = new BigInteger("10");
    static StringTokenizer st;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Queue<Integer> q;

        n = Integer.parseInt(br.readLine());
        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine());
            q = new LinkedList<>();
            temp = st.nextToken(); //N을 문자열로 읽어오기
            len = temp.length(); //N의 길이 = 몇자리수인가
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            N1 = temp.charAt(0) - '0'; //N의 맨 앞자리 정수로 뽑기

            if(len == 1){ //한자리수라면
                if(x==0 && N1 < y){
                    sb.append('#').append(i).append(' ').append(-1).append('\n');
                    continue;
                }else if(N1<x){
                    sb.append('#').append(i).append(' ').append(-1).append('\n');
                    continue;
                }
            }
            xStr = "";
            for(int j=0;j<len;j++){ //x로만 이루어진 xxxx를 만든다. (문자열)
                xStr += String.valueOf(x);
            }
            N = new BigInteger(temp);
            X = new BigInteger(String.valueOf(x));
            Y = new BigInteger(String.valueOf(y));
            //N3 = Long.parseLong(temp);
            result = String.valueOf(x);
            ans =0;
            ANS = new BigInteger("0");
            if((N.compareTo(X) != -1) && x!=0){
                q.add(x);
                ANS = X;
            }
            if(N.compareTo(Y) != -1){
                q.add(y);
                ANS = Y;
            }
            while(!q.isEmpty()){
                TMP1 = new BigInteger(String.valueOf(q.peek()));
                tmp1 = q.poll();
                if(tmp1 != 0){
                     TMP1 = TMP1.multiply(A);
                     tmp1 *= 10;
                }
                TMP1 = TMP1.add(X);
                tmp1 += x;
                if(N.compareTo(TMP1) != -1){ //N이 TMP1보다 같거나 크다면(작지 않다면)
                    ANS = TMP1;
                    q.add(tmp1);
                }
                TMP1 = TMP1.subtract(X);
                TMP1 = TMP1.add(Y);
                tmp1 += (y-x);
                if(N.compareTo(TMP1) != -1){ //N이 TMP!보다 같거나 크다면(작지않다면)
                    ANS = TMP1;
                    q.add(tmp1);
                }
            }
            sb.append('#').append(i).append(' ').append(ANS).append('\n');
        }
        System.out.println(sb.toString());
    }

}

