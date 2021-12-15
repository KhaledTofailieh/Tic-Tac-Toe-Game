package gameMethods;

import GameControllers.GamePlayingController;
import GameControllers.GamePlayingControllerConsol;
import Players.ComputerPlayer;
import Players.HumanPlayer;
import Players.Player;
import States.StateGraph;
import gameStructure.PlayPoint;
import java.util.Scanner;

public class Main {
    public static void main(String []args){
        GamePlayingController controller=new GamePlayingControllerConsol();
        PlayPoint played ;
        Player[] players;
        int i,j,x=-1,y=-1,next=0,depth=1;
        Scanner s=new Scanner(System.in);
        played=new PlayPoint(-1,-1,-1,-1,"");
        GameType type=((GamePlayingControllerConsol)controller).enter_type();

        if(type==GameType.HH){
          players=controller.init_HumanHuman();
        }else{
            do{
                System.out.println("Difficulty Level:");
                depth=s.nextInt();
            }while (depth<=0||depth>40);
            players=controller.init_HumanComputer(depth);

        }

        Game g=new Game(type,players);



        while (true){
            played.setName(g.getPlayers()[g.getCurrent()].getXO());
         if((g.getPlayers()[g.getCurrent()])instanceof HumanPlayer){

            g.getPlayers()[g.getCurrent()].print();
            if(g.getPlayers()[g.getCurrent()].getMiniX()==-1 && (g.getPlayers()[g.getCurrent()].getMiniY()==-1)){

               do{
                   System.out.println("Enter X:");
                   x=s.nextInt();
                   System.out.println("Enter Y:");
                   y=s.nextInt();
                   ((HumanPlayer)g.getPlayers()[g.getCurrent()]).set_minis(x,y);
                   played.setX(x);played.setY(y);

               }while (!g.getController().isAvailable_forPlay(g.getGrid(),new PlayPoint(played.getX(),played.getY(),-1,-1,played.getName())));
            }

            System.out.println(g.getPlayers()[g.getCurrent()].getMiniX()+"  "+g.getPlayers()[g.getCurrent()].getMiniY());
            do{

                System.out.println("Please Play!");
                System.out.println("Enter I:");
                i=s.nextInt();
                System.out.println("Enter J:");
                j=s.nextInt();
                next=controller.getNextPlayer(g.getCurrent());

               played.setX(g.getPlayers()[g.getCurrent()].getMiniX());
               played.setY(g.getPlayers()[g.getCurrent()].getMiniY());
                played.setI(i);played.setJ(j);
            }   while (!g.getController().isAvailable_cell(g.getGrid(),played));

            (g.getPlayers()[g.getCurrent()]).play(played);

        }

        else{//Computer Player

             if(g.getController().isAvailable_forPlay(g.getGrid(),new PlayPoint(played.getI(),played.getJ(),-1,-1,played.getName())))
             {
             played.setName(g.getPlayers()[0].getXO());
             ((ComputerPlayer)g.getPlayers()[g.getCurrent()]).setGame(g);
                 PlayPoint p = null;
             try {
                 p = g.getPlayers()[g.getCurrent()].play(played);
             }catch (Exception e){
                 System.out.println("llllllllllll");
                 g.getPlayers()[g.getCurrent()].play(played);
             }

              //  p.setName(played.getName());
                played=new PlayPoint(p);

                next = controller.getNextPlayer(g.getCurrent());
            }else {
                 played.setName(g.getPlayers()[0].getXO());
              //  ArrayList<StateGraph> list=((ComputerPlayer)g.getPlayers()[g.getCurrent()]).getall(played);
                 played= ((ComputerPlayer)g.getPlayers()[g.getCurrent()]).plays(played,depth);
                // ((ComputerPlayer)g.getPlayers()[g.getCurrent()]).play(p);
                System.err.println("The computer can`t play");
                next = controller.getNextPlayer(g.getCurrent());
            }
        }

            g.setCurrent(next);
            g.getGrid().print_win_grid();
            g.play(played);

        if(g.getController().isAvailable_forPlay(g.getGrid(),new PlayPoint(played.getI(),played.getJ(),-1,-1,played.getName())))
        {
            g.getPlayers()[next].setMiniX(played.getI());
            g.getPlayers()[next].setMiniY(played.getJ());

        }
        else {
            g.getPlayers()[next].setMiniX(-1);
            g.getPlayers()[next].setMiniY(-1);
        }

    }
    }
}
