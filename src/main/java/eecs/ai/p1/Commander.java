package eecs.ai.p1;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import eecs.ai.p1.commands.*;

public final class Commander implements Iterable<Command>{
    // private final File filename;
    private final ArrayList<Command> commands;

    private Commander (ArrayList<Command> commands){
        this.commands = commands;
    }

    public final Commander of(File filename){
        ArrayList<Command> commands = new ArrayList<>();
        return new Commander(commands);
    }

    @Override
    public Iterator<Command> iterator() {
        return new Command(this.commands);
    }
}