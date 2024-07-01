package boj.boj1068;

import java.io.*;
import java.util.*;
public class Main2 {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N,root,erased;
    static ArrayList<Integer> [] child;
    static int[] leaf;

    static void input(){
        N = sc.nextInt();
        child = new ArrayList[N];
        leaf = new int[N];

        for(int i=0;i<N;i++) child[i] = new ArrayList<>();

        for(int i=0;i<N;i++){
            int temp = sc.nextInt();
            if(temp==(-1)){
                root = i; //만약 부모가 -1이면 해당 i 노드가 root이다.
            }else{
                child[temp].add(i); //temp의 자식노드 i를 추가한다.
            }
        }
        erased = sc.nextInt();
    }

    static void dfs(int x,int par){
        //탈출조건 : x의 자식이 없는 경우 leaf[x] ++
        //즉 x 자체가 단말 노드이기 때문
        if(child[x].isEmpty()) {
            leaf[x]++;
        }

        //child[x]를 하나씩 꺼내서 y로 보고
        //만약 y가 부모이면 continue
        //그게아니라면 dfs(y,x)로 호출하고
        //leaf[x] += leaf[y]

        for(int y: child[x]){
            if(y==par) continue;
            dfs(y,x);
            leaf[x] += leaf[y];
        }
    }

    static void process(){
        for(int i=0;i<N;i++){
            if(child[i].contains(erased)){ //만약 i 노드의 자식중에 지우려는 노드 erased를 가지고 있다면
                child[i].remove(child[i].indexOf(erased)); // i노드의 자식중 erased 노드의 인덱스를 제거한다.
            }
        }
        if(root!=erased) dfs(root,-1); //해당 노드와 해당 노드의 부모를 제공한다.
        //최종적으로는 leaf[root]에 root 하위의 모든 서브트리들의 단말노드의 개수가 있을 것이다.

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
    }
}
