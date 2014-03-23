package ncn;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class Helper {
  public static int randInt(int min, int max) {

    // Usually this can be a field rather than a method variable
    Random rand = new Random();

    // nextInt is normally exclusive of the top value,
    // so add 1 to make it inclusive
    int randomNum = rand.nextInt((max - min) + 1) + min;

    return randomNum;
  }

  public static double doublePrecision(double d) {
    d = Math.round(d * 100.0) / 100.0;
    return d;
  }

  public static void toFile(String result, boolean clear) throws IOException, InterruptedException {


    try {
      File file = new File("results.txt");

      // if file doesnt exists, then create it
      if (!file.exists()) {
        file.createNewFile();
      } else if (clear) {
        file.delete();
        file.createNewFile();
      }


      // true = append file
      FileWriter fileWritter = new FileWriter(file.getName(), true);
      BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
     
        bufferWritter.write(result);
        bufferWritter.close();

    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("JWALNALEM WYJATEK");
      Thread.sleep(12345);
    }
  }

  public static class FactorsCounting implements Runnable {

    private Thread countFactor;

    public FactorsCounting(SupervisoryBoard board, int numOfIteractions) {
      super();
      this.board = board;
      this.numOfInteractions = numOfIteractions;
    }

    private SupervisoryBoard board;
    private int numOfInteractions;

    public void startBigTask() throws InterruptedException {
      countFactor = new Thread(this);
      countFactor.start();
      try {
        countFactor.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }

    public void run() {
      while (numOfInteractions > 0) {
        Meeting.setCognitiveDissonanceInInteraction(board);
        Meeting.setRelationshipConflictInInteraction(board);
        numOfInteractions--;
      }
    }
  }
  // DecimalFormat df = new DecimalFormat("#.00");

}
