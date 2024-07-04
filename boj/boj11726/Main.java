package boj.boj11726;
/*
* 문제 이해:
* - 세로는 항상 2이고 가로는 n으로 주어지는 직사각형을 1*2의 가로or세로 직사각형으로 타일을 채우는 방법의 수를 구하기
* n=1 : |
* n=2 : || ,
*       =
* n=3 : |||, =|,
*       |=
* n=4 : ||||, =||, |=|
*       ==, ||=
* 여기도 보면
* 끝이 | 세로로 끝나거나 = 가로 두개짜리로 끝나거나 인데
* 끝이 세로 한칸인 경우 1개 직전의 개수들에 모두 | 이거 하나만 더해주면 되는 경우의 수이고
* 끝이 = 가로 두칸인 경우 2개 직전의 경우의 수와 동일하다.
* - 점화식 : D[i] = D[i-1] + D[i-2]
* */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[] D = new int[1001];

        D[1] = 1;
        D[2] = 2;
        for(int i=3;i<1001;i++){
            D[i] = (D[i-1]+D[i-2]) % 10007;
        }

        System.out.println(D[n]);
    }
}
