package acmicpc.스택;

import java.io.*;
import java.util.*;
public class boj11899 {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static String str;
    static int ans;
    static void input(){
        str = sc.nextLine();
    }

    static void process(){
        //입력 str을 length() 까지 charAt으로 뜯어서 ( 이면 스택에 push
        // ) 이면 pop 하는데 만약 스택이 empty이면 ans ++ 하면 필요한 괄호의 개수를 알 수 있다.
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<str.length();i++){
            char temp = str.charAt(i);
            if(temp == '('){
                stack.push(temp);
            }else if(temp == ')'){
                if(stack.isEmpty()){
                    ans++; //괄호가 필요한 경우들
                }else{
                    stack.pop(); //스택에 있는 사용된 왼쪽 괄호를 빼줌
                }
            }

        }
        ans += stack.size(); //다 돌고도 왼쪽 괄호들이 아직 남아 있으면 그 수만큼 오른쪽 괄호들이 필욯다.
        System.out.println(ans);
    }

    public static void main(String[] args){
        input();
        process();
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
