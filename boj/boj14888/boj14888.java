package boj.boj14888;

/*
* 1. 수의 개수 : 최대 11개
* 2. 수의 최대값 : 100
* 3. 다루는 최대 크기 : 연산자를 어케 넣어도 항상 -10억보다 크거나 같고 10억보다 작거나 같다는 뜻이다.
* 4. 최대 크기가 10억을 넘지 않기 때문에 int(-20억 ~ 20억)형을 사용해서 결과를 담을수 있다.
* 5. 포인트 : 정해진 수열의 사이사이에 연산자를 중복X, 순서O를 지키며 끼워넣는 경우의 수
* 6. 경우의 수: O(n!/(n-m)!) 이다. -> n개중 m개를 뽑는 순열의 시간 복잡도
* 7. 수학에서 0!은 1이기 때문에 11! /
* 문제풀이 : 재귀함수에서는 k를 1번 숫자부터 하나씩 증가시키면서 다음 연산으로 나아간다. 연산의 결과값을 같이 건네준다.
* 모든 연산자를 다 꺼내서 사용하기 위해서 앞에서부터 하나를 꺼내고 다시 재귀적으로 함수를 호출한다
* 해당 재귀 함수에서는 이미 사용한 연산자들은 소모된 상태이며 해당 재귀함수도 다시 맨앞의 연산자부터 확인한다.
* */

import java.io.*;
import java.util.*;

public class boj14888 {
    static int N,max,min;
    static int[] nums,operands;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        N = Integer.parseInt(br.readLine());
        nums = new int[N+1];
        operands = new int[5];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for(int i=1;i<N+1;i++){
            nums[i] = Integer.parseInt(st1.nextToken());
        }

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for(int i=1;i<5;i++){
            operands[i] = Integer.parseInt(st2.nextToken());
        }

        rec_func(1,nums[1]);
        sb.append(max).append('\n').append(min);
        System.out.println(sb.toString());

    }

    static int calculator(int a,int b,int operand){
        if(operand == 1)
            return a+b;
        else if(operand == 2)
            return a-b;
        else if(operand == 3)
            return a*b;
        else
            return a/b;
    }
    static void rec_func(int k,int value){
        if(k==N){
            min = Math.min(value,min);
            max = Math.max(value,max);
        } else {
            for(int operator=1;operator<5;operator++){
                if(operands[operator]>=1){
                    operands[operator]--;
                    rec_func(k+1,calculator(value,nums[k+1],operator));
                    operands[operator]++;
                }
            }
        }
    }

}