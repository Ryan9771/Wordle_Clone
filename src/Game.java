import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {

  private int numberOfGames;
  private int currAttempt;
  private String goalWord;
  private boolean gameOver;

  public Game() {
    this.numberOfGames = 0;
    this.currAttempt = 0;
    this.goalWord = Words.getWord();
    this.gameOver = false;
  }

  public String getWord() {
    return goalWord;
  }

  public int getNumberOfGames() {
    return numberOfGames;
  }

  public int getCurrAttempt() {
    return currAttempt;
  }

  public void startNewGame() {
    this.numberOfGames = 0;
    this.currAttempt = 0;
    this.goalWord = Words.getWord();
  }

  public List<Integer> returnGreen(String word) {
    List<Integer> res = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      if (word.charAt(i) == goalWord.charAt(i)) {
        res.add(i);
      }
    }
    return res;
  }

  public List<Integer> returnOrange(String word) {
    List<Integer> green = returnGreen(word);
    List<Integer> res = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      if (goalWord.contains(Character.toString(word.charAt(i)))) {
        if (!green.contains(i)) {
          res.add(i);
        }
      }
    }
    return res;
  }

  public static void main(String[] args) {
    List<Integer> test = new ArrayList<>(List.of(1, 10, 2));
    System.out.println(test);
    test.removeAll(Collections.singleton(1));
    System.out.println(test);
  }
}
