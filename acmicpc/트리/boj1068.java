package acmicpc.트리;
//리프 노드의 개수를 출력하기
//주어진 노드를 지웠을 때에 리프 노드의 개수
//리프 노드
//노드를 지운다의 의미 = 관계를 끊어내고 나서 세면 된다.
import java.io.*;
import java.util.*;
public class boj1068 {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N,root,erased;
    static int[] leaf;
    static ArrayList<Integer>[] child;



    static void input(){
        N =sc.nextInt();
        leaf = new int[N];
        child = new ArrayList[N];

        for(int i=0;i<N;i++) child[i] = new ArrayList<>();
        for(int i=0;i<N;i++){
            int par = sc.nextInt();

            if(par == -1){
                root = i;
                continue;
            }

            child[par].add(i); //현재 인덱스 i의 부모가 par 이므로 par의 자식으로 i를 추가해줌
        }
        erased = sc.nextInt();
    }

    static void dfs(int x,int par){
        //dfs를 돌면서 child[x]가 비어있을 경우에만 leaf[x]를 ++ 하고 리턴한다.
        if(child[x].isEmpty()){
            leaf[x]++;
        }else{
            for(int y:child[x]){
                if(y==par) continue;
                dfs(y,x);
                leaf[x] += leaf[y];
            }
        }

        //그렇지 않은 경우라면 child[x]의 요소인 x의 자식들을 하나씩 꺼내본다.
        //만약 x의 부모가 자식 배열에 들어 있다면 con 한다.
        //그렇지 않다면 dfs를 추가호출해주고 다 돌고 왔을 경우 현재 leaf[x] 에 자식의 leaf[y] 값을 더해준다.
    }


    static void process(){
        //erased를 지우기 위한 작업
        for(int i=0;i<N;i++){
            if(child[i].contains(erased)){ //만약 현재 i의 자식중 지워야 하는 erased를 가지고 있다면
                //해당 인덱스의 요소만 remove 시켜주어야 한다.
                child[i].remove(child[i].indexOf(erased));
            }
        }

        //erased 가 root 가 아닐때에만 dfs를 돌린다.
        if(erased != root) dfs(root,-1);

        //다 돌았으면 leaf[root]에 정답이 있겠지
        System.out.println(leaf[root]);
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

        public FastReader(String s) throws FileNotFoundException {
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
    }
}
