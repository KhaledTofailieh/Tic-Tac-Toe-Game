package Players;

import States.StateGraph;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import gameMethods.Game;
import gameStructure.Grid;
import gameStructure.PlayPoint;
import gameStructure.WinGrid;

import java.util.ArrayList;

public class ComputerPlayer extends Player {


    ArrayList<StateGraph> states;
    StateGraph parent;
    Game game;
    int depthLevel;

    public void setGame(Game game) {
        this.game = game;
    }

    public ComputerPlayer(String name, String XO,int depth) {
        super(name,XO);
        this.depthLevel=depth;
        states=new ArrayList<>();
    }
   private String getInvers(String ch){
        if(ch.equals("X"))
            return  "O";
        return "X";

   }
    public ArrayList<StateGraph> generate_all_states(StateGraph state){
        Grid temp_grid=new Grid(state.getGrid());
        ArrayList<StateGraph> temp_graph=new ArrayList<>();

        parent=new StateGraph(temp_grid);
        if(state.getPlayed().getI()==-1&&state.getPlayed().getJ()==-1){
          //Nothing
        }else{
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    temp_grid=new Grid(state.getGrid());
                    PlayPoint pp=new PlayPoint(state.getPlayed().getI(),state.getPlayed().getJ(),i,j,getInvers(state.getPlayed().getName()));
                    if(game.getController().isAvailable_forPlay(temp_grid,pp)){
                        if(game.getController().isAvailable_cell(temp_grid,pp))
                        {
                          temp_grid=game.play(pp,temp_grid);
                          StateGraph temp_state=new StateGraph(temp_grid,pp);
                          temp_graph.add(temp_state);
                        }
                    }
                }
            }

        }
        return states=temp_graph;
    }
    public void print_states(){
        for (StateGraph st:states) {
           st.print();
           System.out.println("----------------------"+st.getEval());
        }
    }
    public StateGraph MaxMin(StateGraph graph,int depth){

            if(depth==0){
//            PlayPoint temp=graph.getPlayed();
//            graph.setPlayed(parent.getPlayed());
//            graph.getPlayed().setName(temp.getName());
            graph.evaluate();
            graph.setMax(graph.getEval());
            return graph;
        }
        else{
           // graph.getPlayed().setName(getInvers(graph.getPlayed().getName()));
//            graph.getPlayed().setY(parent.getPlayed().getJ());
        //    graph.getPlayed().setX(parent.getPlayed().getI());
            ArrayList<StateGraph> states=generate_all_states(graph);
            this.states=states;
           // print_states();

            for (StateGraph state:states) {
//                state.getPlayed().setX(graph.getPlayed().getI());
               // state.getPlayed().setY(graph.getPlayed().getJ());
           //     state.getPlayed().setName(getInvers(graph.getPlayed().getName()));
                int d=depth-1;
                if(game.getController().isAvailable_forPlay(state.getGrid(),state.getPlayed()))
                MinMax(state,d);
                else
                   plays(state.getPlayed(),d);


            }
            StateGraph stateGraph=Max(states);
            graph.setMax(stateGraph.getMin());
            graph.setEval(stateGraph.getMax());
            return stateGraph;
        }
    }

    public StateGraph MinMax(StateGraph graph,int dephtcurren){
      //  System.out.println("::::"+dephtcurren);
           if(dephtcurren==0){
//            PlayPoint temp=graph.getPlayed();
//            graph.setPlayed(parent.getPlayed());
//            graph.getPlayed().setName(temp.getName());
               graph.evaluate();

            graph.setMin(graph.getEval());
            return graph;
        }

        else{//graph.getPlayed().setName(getInvers(graph.getPlayed().getName()));
//            graph.getPlayed().setY(parent.getPlayed().getJ());
//            graph.getPlayed().setX(parent.getPlayed().getI());

            ArrayList<StateGraph> states=generate_all_states(graph);

            for (StateGraph state:states) {
//                state.getPlayed().setX(graph.getPlayed().getI());
//                state.getPlayed().setY(graph.getPlayed().getJ());
//               state.getPlayed().setName(getInvers(graph.getPlayed().getName()));
                int d=dephtcurren-1;
                if(game.getController().isAvailable_forPlay(state.getGrid(),state.getPlayed()))
                  MaxMin(state,d);
                else {
                    plays(state.getPlayed(), d);
                  //  StateGraph stateGraph =new StateGraph();
                }

        }   StateGraph stateGraph=Min(states);
            graph.setMin(stateGraph.getMax());
            graph.setEval(stateGraph.getMin());
            return stateGraph;
        }
    }
    public StateGraph Min(ArrayList<StateGraph > states){
       int  min =1000;
       StateGraph temp=null;
        for (StateGraph statet : states){
            if(min>statet.getMax()) {
                min = statet.getMax();
                temp=statet;

            }
        }
        return temp;
    }
    public StateGraph  Max(ArrayList<StateGraph> states){
        int  max =-1000;
        StateGraph temp=null;
        for (StateGraph statet : states){
            if(max<statet.getMin()) {
                max = statet.getMin();
                temp = statet;
            }

        }
        return  temp;
    }

    public PlayPoint  play(PlayPoint played){

        StateGraph st=new StateGraph(game.getGrid(),played);
       // ((ComputerPlayer)game.getPlayers()[game.getCurrent()]).generate_all_states(st);
       // print_states();
        StateGraph sk=((ComputerPlayer)game.getPlayers()[game.getCurrent()]).MaxMin(st,this.depthLevel);
        return sk.getPlayed();
    }


public ArrayList<StateGraph> getall(PlayPoint played,int depth){
        ArrayList<StateGraph> list=new ArrayList<>();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                played.setX(i);
                played.setY(j);
                StateGraph st=new StateGraph(game.getGrid(),played);
                // ((ComputerPlayer)game.getPlayers()[game.getCurrent()]).generate_all_states(st);
                // print_states();
                if(game.getController().isAvailable_forPlay(game.getGrid(),st.getPlayed())) {
                    played.setI(i);
                    played.setJ(j);
                    StateGraph sk = ((ComputerPlayer) game.getPlayers()[game.getCurrent()]).MaxMin(st, depth);
                    list.add(sk);
                }
            }
        }
        return list;


}
public PlayPoint plays(PlayPoint p,int depth){
        ArrayList<StateGraph> list=getall(p,depth);
        ArrayList<WinGrid> listWin=new ArrayList<>();
    for (StateGraph state:list) {
        WinGrid winGrid=new WinGrid(state.getGrid());
        winGrid.evaluate(state.getPlayed().getX(),state.getPlayed().getY(),state.getPlayed().getName(),state.getEval(),state.getPlayed());
        listWin.add(winGrid);

    }
    PlayPoint pp=MaxWin(listWin).getPoint();
         return MaxWin(listWin).getPoint();

}
public WinGrid MaxWin(ArrayList<WinGrid> WinGrids){
        int max=0;
        WinGrid temp=null;
    for (WinGrid w:WinGrids ) {
        if(max<w.getEval()) {
            max = w.getEval();
            temp = w;
        }
    }
    return temp;
}













}
