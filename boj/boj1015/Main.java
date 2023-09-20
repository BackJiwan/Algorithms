package boj.boj1015;
/*
* 1. 정답의 최대치 : 출력되는 배열의 개수는 최대 50까지(N의 최대치),
* 배열의 원소가 될수 있는 최대치는 1000 이므로 int 로 충분하다.
* 2. 복잡도 계산하기 : 배열 A를 내림차순 정렬하여 B를 만들고 A[0]번째 원소를 하나씩 골라서
* B의 몇번째 위치에 있는지 발견하면 해당 위치의 b_idx를 P[i] 번째에 기록해주는 식으로 하면 N^2이다.
* 즉 최대의 경우 2500 번의 연산을 수행하므로 1초이내에 충분이 가능하다
* 3. 위의 N^2 연산에서 놓친부분, 애초에 A배열과 B배열에 idx를 적어두면 된다.
* 4. 방법 : Arr는 Elem
* */
import java.util.*;
import java.io.*;

public class Main {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static class Elements implements Comparable<Elements>{
        public int num,idx;

        @Override
        public int compareTo(Elements other){
            return num - other.num;
        }
    }

    static int N;
    static Elements[] Arr;
    static int[] Prr;

    static void input(){
        N = scan.nextInt();
        Arr = new Elements[N];
        Prr = new int[N];
        for(int i=0;i<N;i++){
            Arr[i] = new Elements();
            Arr[i].num = scan.nextInt();
            Arr[i].idx = i;
        }
    }

    static void pro(){
        Arrays.sort(Arr);
        for(int i=0;i<N;i++){
            Prr[Arr[i].idx] = i;
        }
        for(int i=0;i<N;i++){
            sb.append(Prr[i]).append(' ');
        }
    }

    public static void main(String[] args){
        input();
        pro();
        System.out.println(sb.toString());
    }

    static class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws IOException{
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
}
