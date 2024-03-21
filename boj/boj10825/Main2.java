package boj.boj10825;
/*Add:[BOJ_10825][S4]국영수[ms][kb]
* 1.정답의 최대치(-21억~21억:int)
* - 학생수 최대값은 10만이므로 int 충분
* 2.복잡도(1억번:1초)
* - 정렬 = O(NlogN) => 학생수 10만 => 1초 충분
* 3.문제이해
* - 학생 N명의 이름, 국,영,수 점수가 주어진다.
* - 조건대로 정렬하기
* - 국어=내림, 영어=오름, 수학 감소, 이름 사전순 오름차순
* 4.구상A
* - 학생들의 점수를 구조체 클래스 Elem에 담는다. String name과 int 점수들을 가진다.
* - Elem 클래스는 `Comparable<Elem>` 을 구현한다. , compareTo(Elem other)을 재정의 한다.
* - compareTo를 재정의하면서 정렬 조건을 명시한다.
* - 이름을 비교할때에는 for 반복문으로 둘중 짧은 길이의 이름만큼 charAt을 하면서 같지 않을때까지 간다.
* - 입력받을때에는 sc.next를 통해서 이름을 받으면된다.
* - pro()에서는 이미 정렬기준을 재정의하였기 때문에 단지 Arrays.sort(Elem)을 해주고
* - 반복문을 돌면서 이름을 출력해준다.
* 5.구현A
* -
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
    static class Elem implements Comparable<Elem>{
        public String name;
        public int korean,english,math;
        @Override
        public int compareTo(Elem other) { //국어점수 내림차순
            if (korean != other.korean) { //그대로 두면(-1) 오름차순 => 뒤집기(1) 내림차순
                return other.korean - korean; //기본값이 오름차순이라고 봤을때 => 내꺼에서 뒤에껄 빼면 => 음수 => 그대로 두니까 ( 오름차순)
            } else if (english != other.english) { //국어 점수가 같으면 영어점수 오름차순
                return english - other.english;
            } else if (math != other.math) { //수학점수 내림차순
                return other.math - math;
            }
            return name.compareTo(other.name); //스트링의 비교함수는 매개변수쪽(other)이 사전순 뒤쪽(큰수)
            //라면 (음수) 를 리턴해서 그대로둔다. 즉 기본값은 사전순 오름차순이다.
        }
    }
    static int N;
    static Elem[] a;

    static void input(){
        N = sc.nextInt();
        a = new Elem[N]; //0~N-1 까지

        for(int i=0;i<N;i++){
            a[i] = new Elem();
            a[i].name = sc.next();
            a[i].korean = sc.nextInt();
            a[i].english = sc.nextInt();
            a[i].math = sc.nextInt();
        }
    }

    static void pro(){
        //기준을 통해 정렬하기
        Arrays.sort(a);
        //정답 출력하기
        for(int i=0;i<N;i++){
            sb.append(a[i].name).append('\n');
        }
        System.out.println(sb.toString());
    }
    public static void main(String[] args){
        input();
        pro();
    }
}
