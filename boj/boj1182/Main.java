package boj.boj1182;
/*boj1182-부분수열의 합 / 240320~
* 1.정답의 최대치(int:-21억~21억)
* - N=20, S=1백만 => 2천만이므로 int 가능
* 2.복잡도 계산(1초:1억)
* - 20개의 자리에 대해서, 1또는0 을 배열, 중복허용,순서중요 => 2^20 => 104만, 1초가능
* 3.문제이해
* - N개의 정수로 이루어진 수열이 있다.
* - 크기가 양수인 부분수열(진부분집합)
* - 수열의 원소를 다 더한 값이 S가 되는 경우의 수를 출력
* 4.구상A
* - 전체 수열을 nums 만큼의 크기를 가지는 select 배열의 해당 인덱스는 nums와 동기화되어있따.
*   결국 select의 각 인덱스에 0또는 1로 마킹하여 해당 자리의 숫자를 뽑을지 말지 고민하는 경우의 수 이다.
*  - rec_func을 이용하여 탐색을 하려는 idx와 그전까지 계산된 value를 넘겨준다.
*   if idx==N+1 이 되면 N까지 골랐다는 의미이므로 넘겨받은 value가 S와 동일한지 확인후 맞다면 ans++
*   else //아직 idx 자리를 고를지 말지 정해야 하는시기 ,idx는 재귀호출의 매개변수를 통해서 ++가 이루어지고 있다.
*       for(int i=0~1) select[idx]=i, rec_func(idx+1,value+nums[i]), select[idx]=0 초기화
*  - main에서 만약 전부다 0인경우 => S가 0인경우에는 ans--를 해주는 필터를 한번 거친뒤 sout(ans)로 정답 출력
* 5.구현A
* 4.구상A
* - 기존에 배열에 1,0을 마킹하고, for 0~1 대신 => 재귀함수를 두개씩 호출하면서 idx요소를 더한 밸류, 안더한 밸류로 분기시켜서 호출
* */

import java.io.*;
import java.util.*;

public class Main {
    static FastReader sc= new FastReader();
    static StringBuilder sb = new StringBuilder();
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
            while(st==null ||!st.hasMoreElements()){
                try{
                    st=new StringTokenizer(br.readLine());
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

    static int N,S,ans;
    static int[] nums;

    static void input(){
        N = sc.nextInt();
        S = sc.nextInt();
        nums = new int[N+1];
        ans=0;
        for(int i=1;i<=N;i++){
            nums[i] = sc.nextInt();
        }

    }
    static void rec_func(int idx,int value){
        if(idx==N+1){
            if(value==S){
                ans++;
            }
        }else{
            //rec_func이 그 자체로 어떠한 정적,로컬 변수에 영향을 주지 않기에 조건문 없이 구분 없이, 그냥 두개 호출해서 해도
            //함수 탈출이후 아래 함수로 다시 들어가도 아무 상관이 없다.
            rec_func(idx+1,value+nums[idx]); //idx요소를 고른경우
            rec_func(idx+1,value); //idx 요소를 고르지 않은 경우
        }
    }

    public static void main(String[] args){
        input();
        rec_func(1,0);
        if(S==0){
            ans--;
        }
        System.out.println(ans);
    }
}
