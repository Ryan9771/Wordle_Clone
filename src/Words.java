import java.util.Random;

public class Words {

  private static final String[] words = {"HELLO", "THERE", "KANYE", "DREAM", "CANOE"};

  public static String getWord() {
    Random random = new Random();
    return words[random.nextInt(words.length)];
  }
}
