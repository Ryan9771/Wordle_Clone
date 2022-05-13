import java.util.*;

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
    gameOver = res.size() == 5;
    return res;
  }

  public List<Integer> returnOrange(String word) {
    final Map<Character, Integer> map =
        new HashMap<>(); // uses a map to keep track of each letter's frequency.
    List<Integer> green = returnGreen(word);
    List<Integer> res = new ArrayList<>();

    // Loop through green indexes and mark as full accordingly

    for (int i : green) {
      map.merge(word.charAt(i), 1, Integer::sum);
    }

    for (int i = 0; i < 5; i++) {
      char charAt = word.charAt(i);
      if (goalWord.contains(Character.toString(charAt))) {
        if (map.containsKey(charAt)) { // check if map contains key
          if (map.get(charAt)
              < goalWord.chars().filter(ch -> ch == charAt).count()) { // if curr Freq < goal freq
            if (!green.contains(i)) {
              map.merge(charAt, 1, Integer::sum);
              res.add(i); // Then add to the res
            }
          }
        } else { // if letter in word and not in map, add it to the map
          map.put(charAt, 1);
          if (!green.contains(i)) {
            res.add(i);
          }
        }
      }
    }
    return res;
  }

  public boolean isGameOver() {
    return gameOver;
  }
}
