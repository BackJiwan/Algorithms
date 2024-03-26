package boj.boj15970;

import java.util.ArrayList;

/*
* 1.정답의 최대치(int:-21억~21억)
* - N은 5천까지, 위치는 10만까지, 색깔은 1~N 사이 범위
* 2.복잡도 계산(1초:1억번)
* - 2중 for 반복문으로 N^2
* 3.문제이해
* - 음수가 아닌 정수들, N개의 위치에 하나씩 점들이 주어진다.
* - 각 점은 N개의 색깔중 하나를 가진다.(1~N의 색 표현)
* - p와 q는 같은 색을 가지는 점이고, 가장 가까운 두 점들 사이의 거리가 화살표의 길이가 된다.
* - p->q가 가장 짧을때, q에서는 p가 아닌 다른 점이 더 가까울 수도 있다.
* - 모든 점에서 "시작" 하는 화살표들의 길이 합을 출력 하시오.
* 4.구상A
* - 점들의 개수 N이 주어진다.
* - N개의 점은 (위치,색깔) 의 구조로 주어진다.
* - 이것을 Elem(idx,color)의 형태로 저장하고 Comparable을 구현, comporeTo를 재정의해서, 색깔별로 오름차순 정렬, 색이 같으면
* - 위치별로 오름차순 정렬 시켜준다, -> 그런데 그러면 색깔별로 녀석들을 순회하며 조회할 수있다.
* - 차라리 이 차원 배열에서 앞 인덱스인 위치를 1~N까지 순회하면서 내부 배열을 정렬한뒤 내부 배열을 for 돌면서
* - 만약 1번 배열이면 바로 뒷 요소와의 차이, 마지막 요소이면 바로 앞 요소와의 차이를 저장 하도록 한뒤,그 외에는 앞뒤 중 짧은 길이를 저장하도록
* 5.구상B
* - (위치,색) 으로 들어온 입력을 [색][위치]로 처음부터 입력 받는다. 그러면 앞의 배열은 [1~5000] 색 볌위 이고 뒷 배열은 [1~10만] 까지의 위치가 기록 된다.
* - 배열의 기준이 색인것은 당연, 2차원 배열로 추가하기에는 사전에 길이를 지정하기 힘들기 때문에 => ArrayList로 저장
* - a[color].add(coord) 를 통해서 a[color]를 열면 Integer 타입의 ArrayList가 있다.
* - 이것을 sort 하면 a[color]에는 정렬된 ArrayList에 위치가 0부터 들어 있다. 인덱스는 0부터 size()-1 까지 add를 통해서 추가하고 get으로 가져온다.
* - 색을 순회하는 반복문 내부에서, 색을 하나 잡았으면 for(inti=0<a.size()) 반복문을 통해 요소를 하나씩 꺼내고 꺼낸 요소의 len=Math.min(왼 화살표, 오른화살표)
* - 에 담고 result += len을 해준다.
* */
import java.io.*;
import java.util.*;

public class Main2 {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

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
            while (st == null || !st.hasMoreElements()){
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

    static int N;
    static ArrayList<Integer>[] a;

    static void input(){
        N = sc.nextInt();
        a = new ArrayList[N+1]; //1~N 까지의 색을 모두 커버하기 위함이다.
        for(int color=1;color<=N;color++){ //각 색에 위치를 저장할 ArrayList를 생성해서 넣어준다.
            a[color] = new ArrayList<Integer>();
        }
        for(int i=1;i<=N;i++){
            int coord,color;
            coord = sc.nextInt();
            color = sc.nextInt();
            // TODO : color인 색의 점이 coord에 놓여 있다.
            a[color].add(coord);
        }
    }

    static int toLeft(int color,int idx){
        //TODO
        //색깔이 color인 점의 idx 번째에 있는 점이 왼쪽으로 화살표를 그린다면
        //화살표의 길이를 return 하는 함수. 왼쪽에 점이 없다면 무한대를 return.
        if (idx==0){
            return Integer.MAX_VALUE;
        }else{
            return a[color].get(idx)-a[color].get(idx-1);
        }
    }

    static int toRight(int color, int idx){
        //TODO
        //색깔이 color인 점의 idx 번째에 있는 점이 오른쪽으로 화살표를 그린다면
        //화살표의 길이를 return 하는 함수. 오른쪽에 점이 없다면 무한대를 return
        if(idx==a[color].size()-1){
            return Integer.MAX_VALUE;
        }else{
            return a[color].get(idx+1)-a[color].get(idx);
        }
    }

    static void pro(){
        //TODO :  색깔별로 정렬하기
        for(int i=1;i<=N;i++){
            Collections.sort(a[i]); //각 색별로 열어서 정렬하고 넘어가기
        }
        //이제 a[i] i색의 위치들은 앞에서부터 정렬되어 있으므로 가까운 화살표는 왼쪽 또는 오른쪽에 있다.
        int ans = 0;
        for(int color = 1;color <=N;color++){
            //TODO : 색깔 별로, 각 점마다 가장 가까운 점 찾아주기
            for(int i=0;i<a[color].size();i++){
                ans += Math.min(toLeft(color,i),toRight(color,i));
            }

        }
        System.out.println(ans);
        //정답 출력하기
    }
    public static void main(String[] args){
        input();
        pro();
    }
}
