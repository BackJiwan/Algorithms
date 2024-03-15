package boj.boj15649;
/* 완전탐색문제(적은 범위) , 재귀함수사용(부분별 유지,재귀탈출조건 있음),
 * 1.정답의 최대치(int=앞뒤로 약21억 까지)
 * - N이 최대값 이므로 최대 8, int 사용
* 2. 복잡도 계산하기 (1초=1억번)
* - 완전탐색 방법
* - 중복을 허용하지 않기 때문에 한번 사용한 숫자를 기록해두는 배열이 필요하다
* - 그러나 사용한 숫자를 기록하지 말고 사용된 숫자에 해당하는 인덱스를 flag 체크해두고 쓸때마다 그 인덱스만 0인지 1인지 체크
* 3.구현
* input()
* - N과 M 입력받기 (static)**
* - 정답으로 사용할 배열 1개 static (재귀니까) int selected[M+1] *
* - 사용된 숫자인지 체크할 배열 int flag[N+1] * (해당 배열의 인덱스가 사용된 숫자를 나타내고 내부값이 1이면 사용됨, 아니면 0)
* rec_func(int k)
* - k는 현재 몇번째 자리 숫자를 채워야 하는지를 의미하는 매개변수
* - if k==M+1 이라면 이미 1~M 자리까지 채웠으므로 반복문으로 selected 다 털어서 sb에 append
* - else 라면 for(1~N) 까지 증가시키면서 재귀함수 호출 - 반복인자는 cand
*   반복문 들어오면 일단 내가 사용하려는 cand가 사용됬는지 if(flag[cand]==1) 인지 확인하고 1 인경우라면
*   재귀 과정에서 이미 사용된 숫자라는 의미이기 때문에 continue를 통해 반복문 초입으로 보낸다.
*   뱐복문 내부의 if를 지나면, flag[camd]=1,selected[k]=cand, k+1 인자와 함께 rec_func 재귀 호출이 있고
*   재귀 호출 탈출 이후 라인에서는 사용한 flag[cand]=0,selected[k]=0 초기화를 해준다.
*
* */
import java.io.*;
import java.util.StringTokenizer;

public class Main2 {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static class FastReader{
        BufferedReader br;
        StringTokenizer st;
        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        public FastReader(String s) throws FileNotFoundException { //다형성
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
        double nextDouble(){
            return Double.parseDouble(next());
        }
        long nextLong(){
            return Long.parseLong(next());
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
    static int[] selected;
    static int[] flag;

    public static void input(){
        N = scan.nextInt();
        M = scan.nextInt();
        selected = new int[M+1];
        flag = new int[N+1];
    }
    public static void rec_func(int k){
        if(k==M+1){ //k까지 다 채웠으므로 더이상 호출 x, sb에 추가, 반복문 사용
            for(int i=1;i<M+1;i++){
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
        }else{
            for(int cand=1;cand<N+1;cand++){ //selected에 넣을 숫자를 증가시키는 반복문
                if(flag[cand]==1){ //현재 넣으려는 cand가 사용된 적이 있다 continue
                    continue;
                }else{ //cand가 사용된적 없으므로 ,selected의 k인덱스에 cand 저장,flag의 cand 인덱스에 1저장
                    selected[k] = cand;
                    flag[cand] = 1;
                    rec_func(k+1);
                    selected[k] = 0;
                    flag[cand] = 0;
                }
            }
        }
    }

    public static void main(String[] args){
        input();
        rec_func(1);
        System.out.print(sb);
    }
}
