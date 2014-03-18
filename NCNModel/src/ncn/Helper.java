package ncn;

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
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
     
    }

    public void run() {
      while (numOfInteractions > 0) {
        Meeting.setCognitiveDissonanceInInteraction(board);
        Meeting.setRelationshipConflictInInteraction(board);
//         System.out.println("CHUJ MUJE");
         numOfInteractions--;
        }
    }
  }
}
