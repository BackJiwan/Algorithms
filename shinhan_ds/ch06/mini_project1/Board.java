package shinhan_ds.ch06.mini_project1;

public class Board {
    int size;
    String[][] map;
    Board(int size) {
        this.size = size;
        map = new String[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if((1==col)&&(20!=row)){//만약 col이 0 이라면 숫자를 찍어서 몇번쨰 row인지 알려줄 것이다.
                    map[row][col] = String.valueOf(row-1);
                }else if((20==row)&&(1==col)){
                    map[row][col] = " ";
                }else if(20==row){
                    char ch = (char)(col+63);
                    map[row][col] = String.valueOf(ch);
                }else{
                    map[row][col] = ".";
                }
            }
        }
    }
    public void print() {
        for (int row = 1; row < size-1; row++) {
            for (int col = 1; col < size-1; col++) {
                System.out.print(" " + map[row][col]);
            }
            System.out.println();
        }
    }
}
