package ncn;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ncn.Agent.Chars;
import ncn.Agent.Education;
import ncn.Agent.Gender;
import ncn.Helper.FactorsCounting;

public class RunApp {

  public static void main(String args[]) throws InterruptedException, IOException {

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

    List<Agent> ag = Agent.generateNumberOfAgents(10);
    for (Agent agentos : ag) System.err.println(agentos.toString());
    
    SupervisoryBoard board = SupervisoryBoard.createSupervisoryBoard(Agent.generateNumberOfAgents(10));

    double deltaCDL = board.getCognitiveDissonanceProbability();
    int deltaCD = board.getCognitiveDissonance();
    double deltaRCL = board.getRelationshipConflictProbability();
    int deltaRC = board.getRelationshipConflict();

    String initial =
        new String("Initial CognitiveDissonanceProbability " + deltaCDL + "\r\n"
            + "Initial CognitiveDissonance " + deltaCD + "\r\n"
            + "Initial RelationshipConflictProbability " + deltaRCL + "\r\n"
            + "Initial RelationshipConflict " + deltaRC + "\r\n");

    Helper.toFile(initial, true);

    Meeting m1 = new Meeting(0, 0);

    m1.setNumOfMeetings(Helper.randInt(4, 15));

    for (int j = 0; j < m1.getNumOfMeetings(); ++j) {
      m1.setNumOfInteractions(Helper.randInt(10, 100));

      for (int i = 0; i < m1.getNumOfInteractions(); ++i) {
        board.shuffleBoard();
        Meeting.setCognitiveDissonanceInInteraction(board);
        Meeting.setRelationshipConflictInInteraction(board);
      }

      // FactorsCounting fc = new FactorsCounting(board, m1.getNumOfInteractions());
      // FactorsCounting fc = new FactorsCounting(board, 5);
      // fc.startBigTask();


      String preResult =
          new String("\r\n\r\nNUM OF ITERACTION BEFORE " + m1.getNumOfInteractions()
              + "\r\nCognitiveDissonanceProbability " + board.getCognitiveDissonanceProbability()
              + "\r\nRelationshipConflictProbability " + board.getRelationshipConflictProbability());

      Helper.toFile(preResult, false);

      board.setCognitiveDissonanceProbability(board.getCognitiveDissonance(),
          m1.getNumOfInteractions());
      board.setRelationshipConflictProbability(board.getRelationshipConflict(),
          m1.getNumOfInteractions());

      String corrected =
          new String("\r\n\r\nCORRECTED CognitiveDissonanceProbability "
              + board.getCognitiveDissonanceProbability()
              + "\r\n\r\nCORRECTED RelationshipConflictProbability "
              + board.getRelationshipConflictProbability() + "\r\n\r\nNUM OF ITERACTION AFTER "
              + m1.getNumOfInteractions());

      Helper.toFile(corrected, false);

      DecimalFormat df = new DecimalFormat("0.00");

      double afterCDL = board.getCognitiveDissonanceProbability();
      int afterCD = board.getCognitiveDissonance();
      double afterRCL = board.getRelationshipConflictProbability();
      int afterRC = board.getRelationshipConflict();

      String result =
          new String("\r\n====After " + m1.getNumOfInteractions() + " iteractions ======\r\n"
              + "====After " + m1.getNumOfMeetings() + " meetings ======\r\n\r\n"
              + "CognitiveDissonanceProbability " + df.format(afterCDL)
              + "\r\nRelationshipConflictProbability " + df.format(afterRCL)
              + "\r\n\r\nCognitiveDissonance " + afterCD + "\r\nRelationshipConflict " + afterRC);

       result += ((afterCD - deltaCD != 0) ? "\r\n\r\nCognitiveDissonance occured" : "\r\nCognitiveDissonance did not occured");
       result += ((afterRC - deltaRC != 0) ? "\r\nRelationshipConflict occured" : "\r\nRelationshipConflict did not occured");
       Helper.toFile(result, false);

    }

  }
}
