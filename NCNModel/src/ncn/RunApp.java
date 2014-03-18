package ncn;

import java.util.Arrays;
import java.util.List;

import ncn.Agent.Chars;
import ncn.Agent.Education;
import ncn.Agent.Gender;
import ncn.Helper.FactorsCounting;

public class RunApp {

//  static class Dupa extends Thread implements Runnable {
//
//    @Override
//    public void run() {
//      // TODO Auto-generated method stub
//      for (int i = 0; i < 10; ++i) {
//        System.out.println("Dupa: " + i + " " + getId());
//        try {
//          Thread.sleep(1000);
//        } catch (InterruptedException e) {
//          // TODO Auto-generated catch block
//          e.printStackTrace();
//        }
//      }
//    }
//  }
//
//  private static Thread [] t = new Thread[5];

  public static void main(String args[]) throws InterruptedException {
//    t[0] = new Thread(new Dupa());
//    t[1] = new Thread(new Dupa());
//    t[2] = new Thread(new Dupa());
//    t[3] = new Thread(new Dupa());
//    t[4] = new Thread(new Dupa());
//
//    for(Thread y : t) {
//      y.start();
//    }
//    
//    for (int i = 0; i < 5; ++i) {
//     Thread.sleep(500);
//      System.out.println("main");
//    }
//    
//    for(Thread y : t) {
//      y.join();
//    }
//
//    System.out.println("end");
//  }
//}

 Agent a1 = Agent.createAgent();

 Agent a2 =
 Agent.createAgent("Zenek", Gender.FEMALE, "POLISH", 5, Education.Economic,
 Chars.Experienced, Chars.ForeignStudies, Chars.noPostGradStud, Chars.noMBA,
 Chars.notChairman);

 Agent a3 =
 Agent.createAgent("Janek", Gender.FEMALE, "ENGLISH", 3, Education.Technical,
 Chars.inExperienced, Chars.noForeignStudies, Chars.PostGradStud, Chars.MBA,
 Chars.notChairman);

 Agent a4 =
 Agent.createAgent("Jozek", Gender.MALE, "TURKISH", 6, Education.Law, Chars.inExperienced,
 Chars.noForeignStudies, Chars.noPostGradStud, Chars.MBA, Chars.notChairman);

 Agent a5 =
 Agent.createAgent("Zdzich", Gender.MALE, "POLISH", 7, Education.Other, Chars.Experienced,
 Chars.ForeignStudies, Chars.noPostGradStud, Chars.MBA, Chars.notChairman);

 Agent a6 =
 Agent.createAgent("Romek", Gender.MALE, "POLISH", 2, Education.Economic, Chars.Experienced,
 Chars.ForeignStudies, Chars.PostGradStud, Chars.MBA, Chars.notChairman);

 Agent a7 =
 Agent.createAgent("Franek", Gender.MALE, "POLISH", 9, Education.Technical,
 Chars.Experienced, Chars.ForeignStudies, Chars.noPostGradStud, Chars.MBA,
 Chars.notChairman);

 List<Agent> lista = Arrays.asList(a1, a2, a3, a4, a5, a6, a7);

 SupervisoryBoard board = SupervisoryBoard.createSupervisoryBoard(lista);

 double deltaCDL = board.getCognitiveDissonanceProbability();
 int deltaCD = board.getCognitiveDissonance();
 double deltaRCL = board.getRelationshipConflictProbability();
 int deltaRC = board.getRelationshipConflict();

 // Thread thread1 = new Thread(Meeting);

 System.err.println("Before CognitiveDissonanceProbability " + deltaCDL);
 System.err.println("Before CognitiveDissonance " + deltaCD);

 System.err.println("Before RelationshipConflictProbability " + deltaRCL);
 System.err.println("Before RelationshipConflict " + deltaRC);


 Meeting m1 = new Meeting(0, 0);

 m1.setNumOfMeetings(Helper.randInt(4, 15));

 for (int j = 0; j < m1.getNumOfMeetings(); ++j) {


 m1.setNumOfInteractions(Helper.randInt(10, 100));

 // for (int i = 0; i < m1.getNumOfInteractions(); ++i) {
 // Meeting.setCognitiveDissonanceInInteraction(board);
 // Meeting.setRelationshipConflictInInteraction(board);
 // }

// FactorsCounting fc = new FactorsCounting(board, m1.getNumOfInteractions());
 FactorsCounting fc = new FactorsCounting(board, 5);
 fc.startBigTask();
 
 
// System.err.println("NUM OF ITERACTION BEFORE " + m1.getNumOfInteractions());

 System.err.println("CognitiveDissonanceProbability "
 + board.getCognitiveDissonanceProbability());
 System.err.println("RelationshipConflictProbability "
 + board.getRelationshipConflictProbability());

 board.setCognitiveDissonanceProbability(board.getCognitiveDissonance(),
 m1.getNumOfInteractions());
 board.setRelationshipConflictProbability(board.getRelationshipConflict(),
 m1.getNumOfInteractions());

 System.err.println("CORRECTED CognitiveDissonanceProbability "
 + board.getCognitiveDissonanceProbability());
 System.err.println("CORRECTED RelationshipConflictProbability "
 + board.getRelationshipConflictProbability());

// System.err.println("NUM OF ITERACTION AFTER " + m1.getNumOfInteractions());

 double afterCDL = board.getCognitiveDissonanceProbability();
 int afterCD = board.getCognitiveDissonance();
 double afterRCL = board.getRelationshipConflictProbability();
 int afterRC = board.getRelationshipConflict();

// System.err.println("====After " + m1.getNumOfInteractions() + " iteractions ======\n");
// System.err.println("====After " + m1.getNumOfMeetings() + " meetings ======\n");
 System.err.println(" CognitiveDissonanceProbability " + afterCDL
 + "\nRelationshipConflictProbability " + afterRCL);
 System.err.println(" CognitiveDissonance " + afterCD + "\nRelationshipConflict " + afterRC);

 if (afterCD - deltaCD != 0) {
 System.err.println("CognitiveDissonance occured ");
 } else {
 System.err.println("CognitiveDissonance did not occured  ");
 }

 if (afterRC - deltaRC != 0) {
 System.err.println("RelationshipConflict occured ");
 } else {
 System.err.println("RelationshipConflict did not occured  ");
 }

 }

 }
 }
