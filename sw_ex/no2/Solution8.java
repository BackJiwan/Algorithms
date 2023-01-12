package sw_ex.no2;
//솔루션3에 기반
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

public class Solution8 {
    static int x,y,len,n,N1;
    static String temp,result,xStr,yStr;
    static BigInteger RESULT,N,S,XSTR,X,Y;
    static StringTokenizer st;
    static BigInteger A = new BigInteger("1");
    static BigInteger B = new BigInteger("0");
    static BigInteger C = new BigInteger("10");
    static BigInteger TEMP ;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine());
            temp = st.nextToken(); //N을 문자열로 읽어오기
            len = temp.length(); //N의 길이 = 몇자리수인가
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            X = new BigInteger(String.valueOf(x));
            Y = new BigInteger(String.valueOf(y));
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

            N = new BigInteger(temp); //N을 빅인티저로
            result = String.valueOf(x); //result는 일단 x로 초기화
            RESULT = new BigInteger(result);
            XSTR = new BigInteger(xStr);

            if(XSTR.compareTo(N)==1){
                yStr ="";
                for(int j=1;j<len;j++){
                    yStr += String.valueOf(y);
                }
                result = yStr;
                RESULT = new BigInteger(result);
                sb.append('#').append(i).append(' ').append(RESULT).append('\n');
                continue;
            }
            TEMP = N;
            loop:{
                for(;;N=N.subtract(A)){
                    TEMP = N;
                    while(true){
                        if(TEMP.compareTo(B)==0){
                            sb.append('#').append(i).append(' ').append(N).append('\n');
                            break loop;
                        }
                        if(((TEMP.remainder(C)).compareTo(X)==0) || ((TEMP.remainder(C)).compareTo(Y)==0)){
                            TEMP = TEMP.divide(C);
                        } else{
                            break;
                        }
                    }
                }
            }
        }
        System.out.println(sb.toString());
    }
}

