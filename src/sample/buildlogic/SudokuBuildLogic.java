package sample.buildlogic;

import sample.computationlogic.GameLogic;
import sample.persistence.LocalStorageImpl;
import sample.problemdomain.IStorage;
import sample.problemdomain.SudokuGame;
import sample.userinterface.IUserInterfaceContract;
import sample.userinterface.logic.ControlLogic;

import java.io.IOException;

public class SudokuBuildLogic {

    public static void build(IUserInterfaceContract.View userInterface) throws IOException{
        SudokuGame initialState;
        IStorage storage = new LocalStorageImpl();

        try{
            initialState = storage.getGameData();
        }catch (IOException e){
            initialState = GameLogic.getNewGame();
            storage.updateGameData(initialState);
        }

        IUserInterfaceContract.EventListerner uiLogic
                = new ControlLogic(storage, userInterface);

        userInterface.setListener(uiLogic);
        userInterface.updateBoard(initialState);
    }
}
