package boj.boj15651;

import java.io.*;
import java.util.StringTokenizer;

/* 완전탐색 : 모든 경우 탐색, 재귀함수를 자주씀
 * 1. 정답의 최대치
 * 최대값이 N이므로 7까지이다.
 * 2. 복잡도 계산
 * 중복을 허용하므로 이중 for문으로 M번 N을 고르면 된다.
 * N=7,M=7 일때에 약 82만 , 1초안에 충분히 가능하다.
 * 3. 구현
 * input()
 * -N과 M을 입력받는다.
 * - M크기의 int 배열을 초기화한다.
 * rec_func(cnt,)
 * - 몇번째 숫자를 고르는지 cnt를 통해서 전달한다.
 * - 재귀함수이며 cnt를 확인해서 다 골랐다면 sb에 추가한다.
 *
* */
public class Main2 {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        public FastReader(String s)throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next(){
            while(st==null||!st.hasMoreElements()){ //만약 st가 비어있을 경우에만 새로운 줄을 읽어들인다.
                try{
                    st = new StringTokenizer(br.readLine());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt(){
            return Integer.parseInt(next());
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
    static int N,M;
    static int[] selected; //한줄에 고른 숫자들을 담는 배열, 추후
    public static void input(){
        N = scan.nextInt();
        M = scan.nextInt();
        selected = new int[M+1]; //1~M까지 인덱스 사용할 예정
    }
    public static void rec_func(int cnt){
        if(cnt==M+1){//이 순간에 M개의 자리수를 모두 채웠다는 의미이다.
            for(int i=1;i<=M;i++){
                sb.append(selected[i]).append(' ');
            }
            sb.append('\n');
        }else{
            for(int cand=1;cand<=N;cand++) {
                selected[cnt] = cand;
                rec_func(cnt + 1);
                selected[cnt] = 0;
            }
        }
    }

    public static void main(String[] args) {
        input();
        rec_func(1);
        System.out.println(sb.toString());
    }

}
