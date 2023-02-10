package boj.boj16472;

/*
* 1.N=int, 문자열의 길이 len =int,
* 2.가장 쉬운 방법:
*   왼쪽 시작 L 결정 -> O(N) = 10만
*   오른쪽 끝 R을 L부터 한칸씩 이동 -> O(N) = 10만
*   R+1이 조건을 만족하는지 아닌지를 판단하는 식으로 사용 -> O(N)
*   조건:한칸 옮겨볼때마다 해당문자가 1~R-1 까지에서 사용되었는지 확인하고 F라면 cnt++
*   cnt의 초기값은 0, L이 설정되면서 cnt++,
*   R+1의 조건 : cnt
* 3.문제풀이:
*   N 입력받기
*   문자열을 String arr에 저장
*   pro()에서 L=0,R=L로 시작, charAt(i)로 arr의 요소에 접근
*   반복문은 L=0부터 len-1 까지 이동,
*   하나씩 잡을 때마다 add 메소드로 보내고 존재한다면 False,아니면 True를 리턴하면서 26까지의 cnt배열의 해당 자리 증가
*   만약 add가 True였으면 temp++ False였다면 temp는 그대로, 이동성공하면 max++를 통해 최대길이를 임시저장
*   그러다가 R+1을 보냈을때에 temp값이 N을 초과해버리면 break하면서 max값을 기존의 ans와 비교하여 크다면 대체해준다.
*   이렇게 하나의 L에 관해 R이 이동을 멈췄다면 거기까지는 R의 최대 길이이기 때문에 L을 하나 빼주고 cnt배열에서 L을 빼는 메서드
* 4.못풀겠네요...
*   어려웠던점: 알파벳이 중복이 가능해서... 사용된 알파벳의 개수만 찾는것이 안떠올랐고 또한 L을 땡기면서 사용한 cnt에서 해당
*   알파벳의 자리를 -- 하는것은 대충 해보겠는데 이럴경우 사용된 알파벳의 종류 변수에서 그냥 뺄수도 없고 안뺄수도 없어서 어려웠네요
* 5.강의 이후 다시 문제풀이 시작
* 6.문제의 포인트는 kind를 업데이트하는 조건상정하기 + kind가 N을 초과하는 경우를 if문을 사용하지 않고 while문을 사용하여
*   혹시나 여러개의 동일한 알파벳을 지속적으로 제거해야 할 경우에도 한번만 삭제하지 않고 조건을 만족할때까지 L을 땡기도록
*   while문을 사용해야 하는것
* */

import java.util.*;
import java.io.*;

public class Main {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N,kind;
    static String A;
    static int[] arr;
    static int[] cnt;


    public static void main(String[] args){
        input();
        pro();
    }

    static void add(char c){
        if(cnt[c-'a']==0){ //만약 전달받은 알파벳이 한번도 나온적이 없다면
            kind++;
        }
        cnt[c-'a']++;
    }

    static void remove(char c){
        if(cnt[c-'a']==1){//만약 이번 빼기연산으로 더이상 사용된적이 없게 된다면
            kind--;
        }
        cnt[c-'a']--;
    }

    static void pro(){
        int ans=0;
        int len = A.length();
        for(int L=0,R=0;R<len;R++){
            add(A.charAt(R)); //먼저 R을 움직이면서 이동할때마다 add하면서 cnt와 kind를 조작
            while(kind>N){//kind의 수가 N을 초과하게 되는 순간
                remove(A.charAt(L++)); //L에 사용된 알파벳을 cnt에서 제거하고 L을 한칸 앞으로 땡긴다.
            }
            ans = Math.max(ans,R-L+1);
        }
        System.out.println(ans);
    }

    static void input(){
        N = scan.nextInt();
        A = scan.nextLine();
        cnt = new int[26]; //0~25까지 알파벳을 저장
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
        long nextLong(){
            return Long.parseLong(next());
        }
        double nextDouble(){
            return Double.parseDouble(next());
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
}
