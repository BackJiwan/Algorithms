package pgms.Product_and_sum_of_elements;

class Solution {
public int solution(int[] num_list) {
        int all_pro = 1;
        int sum_pow = 0;
        for(int i=0;i<num_list.length;i++){
        all_pro *= num_list[i];
        sum_pow += num_list[i];
        }
        sum_pow *= sum_pow;
        return (all_pro > sum_pow) ? 0 : 1;
        }
}
