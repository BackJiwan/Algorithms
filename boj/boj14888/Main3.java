package boj.boj14888;
/* 240318 ~
* 1.정답의 최대치(`21억~21억,int) : 10억으로 문제에서 제한 => int 사용
* 2.시간복잡도(1초=1억번) : 2초
* - 최대 N=11일때, 10개의 연산자를 뽑아서 중복없이 순서 있는 순여 => 10! 362만
* 3.문제이해
* 4.구상A
* -
* 5.구현A
* - input()
*   N입력받기, max,min 초기화, nums[]입력받기 및 초기화, operators[] 입력받기및 초기화, 연산자를 담는 order[N] 초기화
* - rec_func(int k, int value)
*   k번째 연산자를 뽑는의미,
*   _if (k==N) N번쨰 연산자를 이번에 뽑는다 => 직전에 N-1 번째 연산자를 뽑았다 => N-1번째 연산자를 뽑을때 N번째 숫자와 계산을 완료했다.
*       이므로 매개변수로 받은 value가 최종 결과물이기에 min,max를 갱신한다.
*   _else k번째 연산자를 뽑는 행위를 위해 for(cand=1~4)반복을 통해서 연산자 배열에서 하나씩 꺼내고
*       - if(연산자[cand]>=1) 경우에만 연산자[cand]--,order[k]=cand,rec_func(k+1,calc(cand,value,nums[k+1]))
*       하고 다시 연산자[cand]++ 로 초기화 시켜준다.
* - calc(int operator,int left,int right)
*   if조건문을 통해서 1,2,3,4의 경우마다 왼쪽 오른쪽 매개변수의 연산 결과를 리턴한다.
* */

import java.io.*;
import java.util.*;

public class Main3 {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();
    static class FastReader{
        BufferedReader br;
        StringTokenizer st;
        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        public FastReader(String s)throws IOException{
            br = new BufferedReader(new FileReader(new File(s)));
        }
        String next(){
            while(st==null ||!st.hasMoreElements()){
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
        String nextLine(){
            String str ="";
            try{
                str = br.readLine();
            }catch(IOException e){
                e.printStackTrace();
            }
            return str;
        }
    }

    static int N,min,max;
    static int[] nums;
    static int[] operators;
    static void input(){
        N = sc.nextInt();
        nums = new int[N+1];
        operators = new int[5];
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        for(int i=1;i<=N;i++){
            nums[i] = sc.nextInt();
        }
        for(int i=1;i<=4;i++){
            operators[i] = sc.nextInt();
        }
    }

    static void rec_func(int k,int value){
        if(k==N){ //총 N-1번째 연산자를 골라야 하는데 , 매개변수로 N번째 연산자와 그 왼쪽까지의 덧셈
            min = Math.min(min,value);
            max = Math.max(max,value);
        }else{
            for(int i=1;i<=4;i++){
                if(operators[i]>=1){ //연산자가 있는 경우에만
                    operators[i]--; //연산자 하나빼고 //여기서 value는 k번째 연산자 왼쪽까지의 덧셈이므로 k번째
                    int new_value = calc(i,value,nums[k+1]);
                    rec_func(k+1,new_value);
                    operators[i]++;
                }
            }
        }
    }

    static int calc(int oper,int left,int right){
        if(oper ==1){
            return left + right;
        }else if(oper ==2){
            return left - right;
        }else if(oper ==3){
            return left * right;
        }else {
            return left / right;
        }
    }

    public static void main(String[] args){
        input();
        rec_func(1,nums[1]);
        sb.append(max).append('\n').append(min);
        System.out.println(sb.toString());
    }
}
