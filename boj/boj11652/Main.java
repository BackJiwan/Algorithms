package boj.boj11652;
/*
 * 1. 정답의 최대치 : -2^62~ 2^62 => Long 사용
 * 2. 복잡도 파악하기 : 카드를 정렬 O(N logN) + 순차적으로 연속된 수의 빈도 계산 O(N) => O(N logN) =
 * 3-1. 입력받은 Long 카드 배열을 Arrays.sort(arr)로 정렬
 * 3-2. 처음 입력받으면 currentCount=1, modeCount의 초기값보다 current가 크다면(같거나의 경우 갱신X=작은것 출력위해)
 * 3-3. modeCount=currentCount로 변경하면서 해당 숫자로 Mode를 갱신 Mode의 초기값 = 배열의 첫번째 숫자
 * 3-4. 이러한 갱신의 조건은 하나 앞의 배열 원소와 지금 원소의 값이 같은지 다른지를 통해서 판단한다.
 * 3-5. 대신 이러한 로직은 첫번째 시도에서는 앞의 배열을 참고하지 못하니 if idx==0인 경우를 특이케이스로 처리
 * 3-6. 탈출 조건은 idx가 N보다는 항상 작도록
 * */
import java.util.*;
import java.io.*;

public class Main {
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

        Double nextDouble(){
            return Double.parseDouble(next());
        }

        Long nextLong(){
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
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N;
    static long[] arr;
    static int currentCount,modeCount;
    static Long mode;

    static void input(){
        N = scan.nextInt();
        arr = new long[N]; //0~ N-1 까지 N개의 원소를 가짐
        for(int i=0;i<N;i++){
            arr[i] = scan.nextLong();
        }
    }

    static void pro(){
        Arrays.sort(arr); // 정렬
        currentCount = 1;
        modeCount = 1;
        mode = arr[0]; // 배열의 첫번째 요소 정보를 다 담아준다
        for(int i=1;i<N;i++){
            if (arr[i-1] == arr[i]){ //앞선 숫자와 동일하다면,
                currentCount++;
            }else{ //앞선 숫자와 다르다면 여기부터 카운트 시작
                currentCount = 1;
            }
            if (currentCount > modeCount){ //현재 카운트하는 숫자가 기록보다 크다면
                modeCount = currentCount; //modeCount 갱신
                mode = arr[i]; //mode 숫자 갱신
            }
        }
        System.out.println(mode);
    }


    public static void main(String args[]){
        input();
        pro();
    }
}
