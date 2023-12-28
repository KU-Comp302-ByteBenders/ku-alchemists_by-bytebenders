package tests;

import static org.junit.jupiter.api.Assertions.*;

import game.Board;
import game.EndGamer;
import org.junit.Test;

public class EndGamerTest {

  @Test
  public void testEndGame() {
    String username1 = "User1";
    String username2 = "User2";
    String avatar1 = "Avatar1";
    String avatar2 = "Avatar2";
    Board board = new Board(username1, username2, avatar1, avatar2);

    EndGamer endGamer = new EndGamer(board);

    assertEquals(-1, endGamer.getWinner()); // -1 for tie, 0 for token1, 1 for token2
  }
}
