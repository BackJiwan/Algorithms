package this_cote.p7_8;
/*
* N = 최대 100만
* M = 최대 20억 이니까 long
* */
import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static long M;
    static int arr[];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st= new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int start = 0;
        int end = arr[N-1];
        int result = 0;

        while(start <= end){
            long total = 0;
            int mid = (start + end) / 2;
            for (int i=0;i<N;i++){
                if(arr[i]>mid) total += arr[i] - mid;
            }
            if(total < M){
                end = mid -1;
            }
            else{
                result = mid;
                start = mid +1;
            }
        }
        System.out.println(result);
    }
}
