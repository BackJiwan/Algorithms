package boj.boj13144;
/*
* 1.정답의 최대치(int:-21억~21억)
* - N=10만일때, 모든 숫자가 다 다르면,10만+10만(-1)+...1 까지
* - N(N-1)/2 => N^2 이라서 long을 사용해야 한다. 10만 * 10만 / 2 => 50억 = long을 사용한다.
* 2.복잡도 계산(1초: 1억번)
* - 투 포인터를 이용하면 O(N)으로 가능하다.
* 3.문제 이해
* - 최대 10만 길이의 수열 N
* - 수열 내부의 수는 모두 다를 수 있다. (1~10만)
* - 수열에서 연속한(투포인터)
* - 1개 이상의 수로 만든 수열이 같은수가 안나오는 경우의 수
* 4.풀이 구상
* - 입력을 받고
* - L은 1부터 N 까지 for 으로 1씩 증가한다.
* - R도 기존부터 시작하는데 중요한건
*   ** while문을 통해서 R을 늘리는 조건을 R+1이 조건에 맞고, R+1 값이 미사용임을 확인하고 R++를 이용해서 계산에 이용한다. **
* - 방문했다면 방문체크 배열을 증가시킨다.
* - L을 땡기면 방문체크 배열을 감소시켜준다.
* -
* */
import java.io.*;
import java.util.*;
public class Main4 {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();

    static int N;
    static int[] arr;
    static int[] cnt;

    public static void main(String[] args){
        input();
        pro();
    }
    static void pro(){
        long ans = 0;
        //L은 1~N 까지 1씩 증가

        for(int L=1,R=0;L<=N;L++){
            // 먼저 L을 하나 잡았으니까 cnt에 사용한 숫자 체크하기
            //L은 진입하면서 체크하지 않기 때문에 굳이 R=1부터 체크하면서 오는것을 염려하지 않아도 된다.
            while((R+1<=N)&&(cnt[arr[R+1]]==0)){
                //반복문 내부에서는 R+1 <= N , && cnt[R+1]==0 인경우에만
                // R++ 하고 cnt[R]++
                R++; // 우리는 R+1 을 사용할 것이므로 미리 올려준다.
                cnt[arr[R]]++; // 방문했으니까 사용한 숫자를 체크해둔다.
            }

            //여기서 반복문을 나왔다는 것은 L을 땡기기 직전에 R이 최대로 이동한 경우이다.
            //다음 페이즈에서 L을 한칸 땡기기 이전에 ans에 누적 시켜준다.
            ans += (R - L + 1);

            cnt[arr[L]]--; //이제 현재의 L보다 하나 더 땡길거 니까 cnt에서 빼준다.

        }
        // 이곳은 L이 N에 도착한 상황이므로 ans를 출력해주면 된다.
        sb.append(ans).append("\n");
        System.out.print(ans);
        //반복문 출력
    }

    static void input(){
        //입력받기
        N = sc.nextInt();
        arr = new int[N+1];
        cnt = new int[100000+1]; //1~10만 까지의 수 중에서 등장하면
        for(int i=1;i<=N;i++){
            arr[i] = sc.nextInt();
        }
    }

    static class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        public FastReader(String s)throws FileNotFoundException{
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
        long nextLong(){
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
