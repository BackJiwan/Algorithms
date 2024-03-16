package shinhan_ds.ch06.mini_project1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Omok {
    static Board boardMain;//board의 형태는 오목 플레이 내내 유지시키기 위해서 static 선언


    public static void main(String[] args) {
        Player user = new Player("사용자", "O");
        Player computer = new Player("컴퓨터", "X");
        boardMain = new Board(20);
        play(boardMain, user, computer);
    }

    private static void play(Board board, Player user, Player computer) {
        board.print();
        int cnt=0;
        while(cnt<10){
            //돌놓기(플레이어)
            //오목인지 검사
            //
            setStone(user);
            setStone(computer);
            cnt++;
        }

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
            col = st.nextToken().charAt(0)-65;
            row = Integer.parseInt(st.nextToken());
        }catch(IOException e){
            e.printStackTrace();
        }
        //해당 입력에서 col,row로 분리해서 해당 위치에 플레이어 타입의 오목을 둔다.
        boardMain.map[row][col] = stone;
        //돌을 둘때 범위 안벗어나도록 검사정도는 해야 할듯
    }
}
