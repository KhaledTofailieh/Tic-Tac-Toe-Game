package GameControllers;

import gameMethods.GameType;

import java.util.Scanner;

public class GamePlayingControllerConsol extends GamePlayingController {

    public GameType enter_type(){
        Scanner s=new Scanner(System.in);
        int ty;
        while (true){

            System.out.println("Enter Type: \n1_Human vs Human"+"\n"+"2_Human vs Computer");
            ty=s.nextInt();
            if(ty==1){
                return GameType.HH;
            }else if(ty==2){
                return GameType.HC;
            }else{
                System.out.println("Error! Enter Again");
            }
        }

    }
}
