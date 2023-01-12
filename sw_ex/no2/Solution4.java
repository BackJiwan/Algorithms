package sw_ex.no2;
//이것도 큐 사용 + 런타임에러
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

public class Solution4 {
    static int x,y,len,n,N1;
    static long ans,N3;
    static String temp,result,xStr,yStr;
    static BigInteger RESULT,N,S,XSTR;
    static StringTokenizer st;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Queue<Long> q;

        n = Integer.parseInt(br.readLine());
        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine());
            q = new LinkedList<Long>();
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
            for(int j=0;j<len;j++){
                xStr += String.valueOf(x);
            }
            N = new BigInteger(temp);
            N3 = Long.parseLong(temp);
            result = String.valueOf(x);
            ans =0;
            if(N3>=x && x!=0){
                q.add((long)x);
                ans = x;
            }
            if(N3>=y){
                q.add((long)y);
                ans = y;
            }
            while(!q.isEmpty()){
                long tmp1 = q.peek();
                q.poll();
                tmp1 *= 10;
                tmp1 += x;
                if(tmp1 <= N3){
                    ans = tmp1;
                    q.add(tmp1);
                }
                tmp1 += (y-x);
                if(tmp1 <= N3){
                    ans = tmp1;
                    q.add(tmp1);
                }
            }

//            if(x==0){
//                dfs(String.valueOf(y));
//            } else{
//                dfs(String.valueOf(x));
//                dfs(String.valueOf(y));
//            }
            sb.append('#').append(i).append(' ').append(ans).append('\n');
        }
        System.out.println(sb.toString());
    }

    static void dfs(String s){
        if(s.charAt(0)=='0'&& s.length()>1){ //처음에 0이 올경우 00000... 이렇게 길어지지 않도록 끊어준다.
            return;
        }
        if(s.length()>len){
            return;
        }

        S = new BigInteger(s);
        RESULT = new BigInteger(result);
        XSTR = new BigInteger(xStr);

        if(XSTR.compareTo(N)==1){
            yStr ="";
            for(int j=1;j<len;j++){
                yStr += String.valueOf(y);
            }
            result = yStr;
            return;
        }

        if (S.compareTo(N) == 1) { //S>N 이 참이라면
            return;
        } else if (S.compareTo(RESULT) == 1) { //S>RESULT 가 참이라면
            result = s;
        }
        dfs(s + x);
        dfs(s + y);
    }
}

