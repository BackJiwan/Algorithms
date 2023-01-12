package this_cote.p7_5;
/*
* 부품이 N개 있다.
* 각 부품은 정수 형태의 고유한 번호가 있다.(배열에 저장)
* 손님이 M개 종류의 견적서를 요청한다.
* M개의 정수가 배열로 요청된다.
*1. 첫줄에 정수 N은 최대 100만이므로 int 가능
* 2. 둘째줄의 공백 구분 N개의 정수는 1보다 크고 100만이하이므로 int[]에 저장
* 3. 셋째 줄의 정수 M은 최대 10만이다.
* 4. 넷째줄의 공백 구분 M개의 정수는 최대 10만이하이므로 int[]에 저장
* */
import java.util.*;
import java.io.*;


public class Main {
    static int N,M;
    static int[] arr1,arr2;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr1 = new int[N];
        for(int i=0;i<N;i++){
            arr1[i]=Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr1);

        st= new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        arr2 = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++){
            arr2[i]=Integer.parseInt(st.nextToken());
        }

        for(int k:arr2){
            int result = binary_search(k,0,N-1);
            if(result == -1){
                sb.append("no"+" ");
            } else{
                sb.append("yes"+" ");
            }
        }
        System.out.println(sb.toString());
    }

    static int binary_search(int key,int low,int high) {
        int mid;
        if(low <= high){
            mid = (low + high) / 2;

            if(key == arr1[mid]){
                return mid;
            } else if(key < arr1[mid]){
                return binary_search(key,low,mid-1);
            } else{
                return binary_search(key,mid+1,high);
            }
        }
        return -1;
    }
}
