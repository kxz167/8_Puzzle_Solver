package eecs.ai.p1;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import eecs.ai.p1.commands.*;

public final class Commander implements Iterable<Command>{
    // private final File filename;
    private final ArrayList<Command> commands;
    private final Board gameBoard;

    private Commander (ArrayList<Command> commands, Board gameBoard){
        this.commands = commands;
        this.gameBoard = gameBoard;
    }

    public final Commander of(File filename, Board gameBoard){
        ArrayList<Command> commands = new ArrayList<>();
        return new Commander(commands, gameBoard);
    }

    @Override
    public Iterator<Command> iterator() {
        return new Command(this.commands, this.gameBoard);
    }
}