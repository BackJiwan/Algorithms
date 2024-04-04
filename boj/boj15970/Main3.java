package boj.boj15970;
/*boj15970-화살표 그리기
* 1.정답의 최대치
* 2.복잡도 계산
* 3.문제이해
* - 점들의 위치x,색깔y가 (x,y) 로 주어진다.
* - 점들의 개수는 N개이며, 색깔y는 N개 이하이다.
* - 점들의 개수 N개는 5000이하이다.
* - 화살표는 같은 색의 점끼리 거리차중 최소 값이다.
* 4.구상A
* - 점은 성분 2개를 가진다. 위치, 색깔
* - 점을 Elem 구조 클래스로 만들고 색별로 정렬, 색이 같으면 거리순 오름차순 정렬을 한다.
* - 정렬된 elem에서 왼쪽까지 거리, 오른쪽 까지의 거리중 min 값을 sum에 갱신한다.
* - 왼쪽 거리 함수는 만약에 idx가 1인 경우에는 MAX_VALUE를 리턴, 오른쪽 거리함수는 idx가 N인 경우에 MIN_VALUE를 리턴한다.
* 5.구현A
* - input() - 입력받기
* - pro() - 메인 프로세스
* - Elem{} - 점 구조체
* - toLeft() - 왼쪽 점과의 거리(동일색)
* - toRight() - 오른쪽 점과의 거리(동일색)
* 6.잘못생각한점?
* - Elem에 idx와 color를 유지하지 말고 Elem 자체의 배열 인덱스를 무엇으로 활용하지...?
* - 차라리 ArrayList로 만들고 구조체 자체의 idx가 색깔이고 add로 위치값을 계속 추가하는게 맞겠다.
* -> 가변 배열이 필요함
* */
import java.io.*;
import java.util.*;

public class Main3 {
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
            while(st==null||!st.hasMoreElements()){
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
            String str ="";
            try{
                str = br.readLine();
            }catch(IOException e){
                e.printStackTrace();
            }
            return str;
        }
    }
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

//    static class Elem implements Comparable<Elem>{
//        //Elem은 점의 위치와 색을 가진다. 색이 같은 경우 위치 기준 오름차순 한다.
//        int idx;
//        int color;
//
//        @Override
//        public int compareTo(Elem other){
//            if(this.color == other.color){ //return이 음수면 위치유지, 2-10= -8 유지 => 오름차순
//                return this.idx - other.idx;
//            }
//            return 0;
//        }
//    }

    static int N;
    static ArrayList<Integer>[] arr;
    static int sum;
    //static Elem[] arr;

    static void input(){
        N = sc.nextInt();
        //arr[i] 를 열면 i 색 점들의 위치들이 add 되어 있다.
        arr = new ArrayList[N+1];
        for(int i=1;i<=N;i++){
            arr[i] = new ArrayList<>();
        }
        sum =0;
        for(int i=1;i<=N;i++){ //어쩃든 N번 입력받으면 끝난다.
            int idx,color;
            idx=sc.nextInt();
            color= sc.nextInt();
            arr[color].add(idx); //해당색에 위치를 계속 추가한다.
        }
    }
    static void pro(){
        //배열: Arrays.sort, 리스트 : Collections.sort
        //Arrays.sort(arr,1,N+1);
        //0번 색부터 마지막 색까지 열어보아야 한다.
        for(int i=1;i<=N;i++){ //색깔의 범위 = 1~N
            Collections.sort(arr[i]); // 각 색의 위치들을 정렬해준다.
        }
        for(int i=1;i<=N;i++){ //이제 색을 하나씩 열어서 거리를 sum 할것이다.
            for(int j=0;j<arr[i].size();j++){ //열린색의 요소들을 하나씩 꺼내서 왼쪽길이, 오른쪽 길이중 최소값을 정답에 갱신한다.
                sum += Math.min(toLeft(i,j),toRight(i,j));
            }
        }
        System.out.println(sum);
    }
    static int toLeft(int color,int idx){
        if(idx==0){ //해당 색의 첫번째 점 이라면
            return Integer.MAX_VALUE;
        }else{
            return arr[color].get(idx)-arr[color].get(idx-1);
        }
    }
    static int toRight(int color,int idx){
        if(idx==arr[color].size()-1){ //해당 색의 마지막 위치 점 이라면
            return Integer.MAX_VALUE;
        }else{
            return arr[color].get(idx+1)-arr[color].get(idx);
        }
    }
    public static void main(String[] args){
        input();
        pro();
    }
}
