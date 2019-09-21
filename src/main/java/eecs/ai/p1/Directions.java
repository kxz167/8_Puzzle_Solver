package eecs.ai.p1;

public enum Directions{
    UP(-3), DOWN(3), LEFT(-1), RIGHT(1);

    private int value;

    Directions(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

}