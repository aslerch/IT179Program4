package ilstu.edu;

import java.util.LinkedList;

public class Main {

    public static void main(String [] args) {

        LinkedList<String> players = new LinkedList<>();
        players.add("steve");
        players.add("john");
        Game game = new Game(players);

    }

}
