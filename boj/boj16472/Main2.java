package boj.boj16472;
/*
* 1.정답의 최대치(int:-21억~21억)
* - 26개를 인식가능
* - 문자열의 길이 10만이 26개로 이루어져 있으면
* => 정답의 최대치는 10만 : int 사용
* 2.복잡도 계산(1초 : 1억번)
* -
* 3.문제 이해
* - 고양이는 1~26개 까지의 알파벳을 인식할 수 있다.
* - 문자열의 길이는 1~10만이다.
* - N으로 주어진 인식가능한 알파벳의 주의하여, 인식할 수 있는 문자열의 최대 길이를 구하여라
* 4.풀이 구상
* - 26개의 알파벳의 등장횟수를 기록하는 char[] cnt 배열
* - 투포인터 개념, L=0,R=0 부터 시작해서
* - R을 땡기다가 cnt에 추가,추가, 제거 제거 등등의 로직을 통해서 최종적으로 길이를 구한다.
* */

import java.io.*;
import java.util.*;
public class Main2 {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int N;
    static String str;
    static int[] cnt;
    static int alphabet;
    static void input(){
        N = sc.nextInt();
        str = sc.next(); //String Tokenizer 는 어차피 공백 단위로 끊기 때문에 문자열이 next()로 뽑아도 통으로 들어 있음
        cnt = new int[26];
    }

    static void addChar(int target){
        cnt[target]++;
        if(cnt[target]==1){ //더하고 보니 1이라면 최초 등장이므로
            alphabet++;
        }
    }
    static void subChar(int target){
        cnt[target]--;
        if(cnt[target]==0){ //빼고 보니 0이라면 더이상 없는 알파벳이므로
            alphabet--;
        }
    }

    static void pro(){
        int len = str.length();
        int ans =0 ;
        //alphabet=0;

        for(int L=0,R=0;R<len;R++){
            //R을 계속 증가 시킬 것이다
            //일단 들어왔기 때문에 R을 추가해준다.
            addChar(str.charAt(R)-'a');
            //여기의 while 문을 통해서 알파벳의 개수 조건을 검사한다.
            //반복문을 들어가는 이유는 알파벳의 개수가 조건에 안맞기 때문이므로 조건에 맞게 L을 땡기는 동작을 여기서 해준다.
            while(alphabet>N){
                subChar(str.charAt(L++)-'a');
            }
            ans = Math.max(ans,R-L+1);
        }
        System.out.println(ans);
    }

    public static void main(String[] args){
        input();
        pro();
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
    }
}

/*
* static void pro(){
        int len = str.length(); //문자열의 길이
        int ans=0;

        for(int L=0,R=0;R<len;R++){
            //R을 땡기기 시작함
            //사용한 R을 마킹해줌
            addChar(str.charAt(R)-'a');
            //여기에서는 조건을 확인해봄
            while (alphabet>N){ //L은 주어졌고 R을 최대한 땡겨본다.
                if(alphabet>N){ //알파벳 종류가 최대 인식 개수를 넘긴순간
                    subChar(str.charAt(L++)-'a'); //어차피 이제 L 땡길거니까 사용한 L은 기록에서 지워준다.
                }
            }

            ans = Math.max(ans,R-L+1); //정답 갱신 시도
        }

        System.out.println(ans);
    }
* */
