package shinhan_ds.ch06.mini_project1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Omok {
    static Board boardMain;//오목판의 상태를 프로그램 전역으로 하나만 유지하기 위해 static 선언

    //check의 이차원 배열은 boardMain과 동일한 위치를 의미하며, 내부의 값은 연속으로 발견된 값을 기록한다. (1~5)
    static int[][][] check = new int[22][22][4]; //4가지의 방향중 한방향으로 이동하며 연속을 찾기 위한 배열

    //dx,dy를 조합하여 좌->우, 상->하로 포인터를 이동시키며 가능한 4가지의 direction을 사용한다.
    static int[] dx = {1,1,0,-1};
    static int[] dy = {0,1,1,1};
    static boolean flag; //오목 게임이 종료되는 순간을 나타내기 위한 boolean 값, 전역 선언

    public static void main(String[] args) {
        Player user = new Player("사용자", "O");
        Player computer = new Player("컴퓨터", "X");
        boardMain = new Board(22);
        play(boardMain, user, computer);
    }

    private static void play(Board board, Player user, Player computer) {
        boardMain.print();
        flag = true;
        Player curPlayer = user;
        while(flag){
            //돌놓기(플레이어)
            //오목인지 검사
            if(curPlayer==user){
                setStone(user);
                boardMain.print();
                curPlayer=computer;
                System.out.print(checkOmok());
            }else{
                setStone(computer);
                boardMain.print();
                curPlayer=user;
                System.out.print(checkOmok());
            }

        }

    }
    public static String checkOmok(){
        for(int row=1;row<=19;row++){
            for(int col=2;col<=20;col++){
                if(!".".equals(boardMain.map[row][col])){ //.이 아닌 경우에만
                    for(int d=0;d<4;d++){
                        if(check[row][col][d]==0 && finder(row,col,d,boardMain.map[row][col])==5){
                            if (boardMain.map[row][col].equals("O")){
                                flag = false;
                                return "사용자가 승리하였습니다.";
                            }else if(boardMain.map[row][col].equals("X")){
                                flag = false;
                                return "컴퓨터가 승리하였습니다.";
                            }else{
                                return "";
                            }
                        }
                    }
                }
            }
        }
        return "";
    }
    public static int finder(int row,int col,int d,String player){
        int nextRow = row + dx[d];
        int nextCol = col + dy[d];

        if(boardMain.map[nextRow][nextCol].equals(player)){
            return check[nextRow][nextCol][d] = finder(nextRow,nextCol,d,player) + 1;
        }
        return 1;
    }
    private static void setStone(Player who){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        //사용자인지,컴퓨터인지 Player 타입으로 판별
        String stone = who.stone;
        int col=0,row=0;
        //사용자면 사용자>, 컴퓨터면 컴퓨터> 로 출력
        String player = who.name;
        System.out.print(player+">");

        //System.in 으로 입력받고
        try{
            st = new StringTokenizer(br.readLine()," ");
            col = st.nextToken().charAt(0)-63;
            row = Integer.parseInt(st.nextToken())+1;
        }catch(IOException e){
            e.printStackTrace();
        }
        //해당 입력에서 col,row로 분리해서 해당 위치에 플레이어 타입의 오목을 둔다.
        boardMain.map[row][col] = stone;
        //돌을 둘때 범위 안벗어나도록 검사정도는 해야 할듯
    }
}
