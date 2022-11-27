package boj.boj1759;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* 암호는 서로 다른 L개의 알파벳 소문자로 구성 최소한개의 모음(a,e,i,o,u)과 최소 두 개의 자음으로 구성
* 암호는 증가하는 순서로 배열된다.
* 암호로 사용했을 법한 문자의 종료는 C가지 있다.
* 첫째 줄 : L C
* 두번쨰 줄 : C개의 문자들이 공백으로 구분된다.
* 중복은 없다.
* 사전식으로 가능성 있는 암호 모두 출력하기
* */
public class boj1759 {
    static int L,C;
    static char arr[],candidate[];
    static boolean visited[];

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String s;

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        candidate = new char[C]; //후보군
        visited = new boolean[C]; //가능성있는 암호라고 판단하여 골랐는지를 체크하는 부울 배열

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<C;i++){
            candidate[i] = st.nextToken().charAt(0); //두번쨰줄 입력받아서 배열에 저장
        }
        Arrays.sort(candidate); //사전식으로 정렬 해주기
        backtracking(0,0);

    }

    private static void backtracking(int start,int cnt){
        if(cnt==L) { //4개의 단어를 다 뽑았다면
            int v = 0; //모음
            int cs = 0; //자음
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<C;i++) {
                if(visited[i]) { //방문카운트가 true라면 해당부분은 바로 추가한다.
                    sb.append(candidate[i]);

                    if(candidate[i]=='a'||candidate[i]=='e'||candidate[i]=='i'||candidate[i]=='o'||candidate[i]=='u') {
                        v++; //모음이면 모음 카운트를 증가
                    }else {
                        cs++; //자음이면 자음 카운트를 증가
                    }
                }
            }
            if(v>=1 && cs>=2){
                System.out.println(sb); //자음과 모음이 조건을 만족하면 출력한다.
            }

        }
        //백트래킹
        for(int i=start;i<C;i++) {
            visited[i]=true;
            backtracking(i+1,cnt+1); //카운트롤 올리고 다음 노드를 방문
            //백트래킹을 빠져나온 경우라면 방문 카운트를 다시 false로 바꾸어준다.
            visited[i]=false;
        }
    }
}
