package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import game.Board;
import game.EndGamer;
import game.Token;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EndGamerTest {

  String username1;
  String username2;
  String avatar1;
  String avatar2;
  Board board;
  EndGamer endGamer;
  ArrayList<Token> tokens;

  @BeforeEach
  public void setUp() {
    this.username1 = "User1";
    this.username2 = "User2";
    this.avatar1 = "Avatar1";
    this.avatar2 = "Avatar2";
    Token token1 = new Token(username1, avatar1, avatar1);
    Token token2 = new Token(username2, avatar2, avatar2);
    tokens.add(token1);
    tokens.add(token2);

    this.board = new Board(tokens);
  }

  @AfterEach
  public void tearDown() {
    username1 = null;
    username2 = null;
    avatar1 = null;
    avatar2 = null;
    board = null;
    endGamer = null;
  }

  @Test
  public void testTie() {
    endGamer = new EndGamer(board);
    // assertEquals(EndGamer.TIE, endGamer.getWinner()); // -1 for tie, 0 for
    // token1, 1 for token2
  }

  @Test
  public void testToken1WinWithGold() {
    board.getTokens().get(0).addGold(10);
    endGamer = new EndGamer(board);
    // assertEquals(EndGamer.TOKEN1, endGamer.getWinner());
  }

  @Test
  public void testToken2WinWithGold() {
    board.getTokens().get(1).addGold(10);
    endGamer = new EndGamer(board);
    // assertEquals(EndGamer.TOKEN2, endGamer.getWinner());
  }

  @Test
  public void testToken1WinWithReputation() {
    board.getTokens().get(0).addReputation(5);
    endGamer = new EndGamer(board);
    // assertEquals(EndGamer.TOKEN1, endGamer.getWinner());
  }

  @Test
  public void testToken2WinWithReputation() {
    board.getTokens().get(1).addReputation(5);
    endGamer = new EndGamer(board);
    // assertEquals(EndGamer.TOKEN2, endGamer.getWinner());
  }
}
