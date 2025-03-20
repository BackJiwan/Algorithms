package pgms.decimalToBinary;
import java.io.*;
import java.util.*;
public class Solution {
    //10진수 입력 -> 2진수로 변환
    public static void main(String[] args) {
        int decimal=27;
        String result = solution(decimal);
        System.out.println("10진수 "+decimal+"의 2진수는 : "+result);
    }

    public static String solution(int decimal){
        Stack<Integer> stack = new Stack<>();
        //10진수를 0보다 큰동안에 2로 나눈 나머지를 stack에 넣는다.
        while(decimal>0){ //
            stack.push(decimal%2);
            decimal = decimal/2;
        }
        //stack 이 빌때까지 꺼내서 StringBuilder에 append 한다.
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}
