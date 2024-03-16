package shinhan_ds.ch06.mini_project1;

public class Board {
    int size;
    String[][] map;
    Board(int size) {
        this.size = size;
        map = new String[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if((0==col)&&(19!=row)){//만약 col이 0 이라면 숫자를 찍어서 몇번쨰 row인지 알려줄 것이다.
                    map[row][col] = String.valueOf(row);
                }else if((19==row)&&(0==col)){
                    map[row][col] = " ";
                }else if(19==row){
                    char ch = (char)(col+64);
                    map[row][col] = String.valueOf(ch);
                }else{
                    map[row][col] = ".";
                }
            }
        }
    }
    public void print() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                System.out.print(" " + map[row][col]);
            }
            System.out.println();
        }
    }
}
