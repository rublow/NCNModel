package ncn;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import ncn.SupervisoryBoard;
import ncn.Agent.Chars;

public class Meeting {

  public Meeting(int numOfMeetings, int numOfInteractions) {
    super();
    this.numOfMeetings = numOfMeetings;
    this.numOfInteractions = numOfInteractions;
  }

  private int numOfMeetings;

  /**
   * @return the numOfMeetings
   */
  public int getNumOfMeetings() {
    return numOfMeetings;
  }

  /**
   * @param numOfMeetings the numOfMeetings to set
   */
  public void setNumOfMeetings(int numOfMeetings) {
    this.numOfMeetings = numOfMeetings;
  }

  /**
   * @return the numOfInteractions
   */
  public int getNumOfInteractions() {
    return numOfInteractions;
  }

  /**
   * @param numOfInteractions the numOfInteractions to set
   */
  public void setNumOfInteractions(int numOfInteractions) {
    this.numOfInteractions = numOfInteractions;
  }

  private int numOfInteractions;

  public static void setCognitiveDissonanceInInteraction(SupervisoryBoard board) {
    List<Agent> boardMembersTmp = board.getBoardMembers();
    for (Agent a : boardMembersTmp) {
//      System.err.println(a.getName());
    }
//    System.err.println("\n================\n");
    Collections.shuffle(boardMembersTmp);
    for (Agent a : boardMembersTmp) {
//      System.err.println(a.getName());
    }

    double tmpDissonace = 0;
//    System.err.println(boardMembersTmp.get(0).toString());
//    System.err.println(boardMembersTmp.get(1).toString());

    if (boardMembersTmp.get(0).getEducationDegree() != boardMembersTmp.get(1).getEducationDegree()) {
      ++tmpDissonace;
//      System.err.println("Edukacja stopien roznica " + tmpDissonace);
    }

    if (boardMembersTmp.get(0).getNationality() != boardMembersTmp.get(1).getNationality()) {
      ++tmpDissonace;
//      System.err.println("Edukacja rodzaj roznica " + tmpDissonace);
    }

    if (boardMembersTmp.get(0).getHasMBA() != boardMembersTmp.get(1).getHasMBA()) {
      tmpDissonace += 0.5;
//      System.err.println("MBA roznice" + tmpDissonace);
    }

    if (boardMembersTmp.get(0).getHasForeignStudies() != boardMembersTmp.get(1)
        .getHasForeignStudies()) {
      tmpDissonace += 0.2;
//      System.err.println("Studia za granica roznica " + tmpDissonace);
    }

    System.err.println("====DISSONANCE NA KONIEC==== " + tmpDissonace);
    if (tmpDissonace >= 1) {
      System.err.println("Dysonans wiekszy od 1.0");
      if (new Random().nextDouble() <= board.getCognitiveDissonanceProbability()) {
        board.setCognitiveDissonance();
      }
    }
  }

  public static void setRelationshipConflictInInteraction(SupervisoryBoard board) {
    List<Agent> boardMembersTmp = board.getBoardMembers();
    for (Agent a : boardMembersTmp) {
//      System.err.println(a.getName());
    }
//    System.err.println("\n=======Relationship=========\n");
    for (Agent a : boardMembersTmp) {
//      System.err.println(a.getName());
    }
    double tmpConflict = 0;
//    System.err.println(boardMembersTmp.get(0).toString());
//    System.err.println(boardMembersTmp.get(1).toString());

    if (boardMembersTmp.get(0).getGender() != boardMembersTmp.get(1).getGender()) {
      ++tmpConflict;
//      System.err.println("Plec rozna " + tmpConflict);
    }

    if (boardMembersTmp.get(0).getNationality() != boardMembersTmp.get(1).getNationality()) {
      ++tmpConflict;
//      System.err.println("Narodowosc rozna " + tmpConflict);
    }

    if (boardMembersTmp.get(0).getHasExperience() == Chars.inExperienced
        && boardMembersTmp.get(1).getHasExperience() == Chars.inExperienced) {
      ++tmpConflict;
//      System.err.println("Bez doswiadczenia oboje " + tmpConflict);

    } else if ((boardMembersTmp.get(0).getHasExperience() != boardMembersTmp.get(1)
        .getHasExperience())) {
      tmpConflict += 0.5;
//      System.err.println("Doswiadczenie rozne\n dla jedynki:"
//          + boardMembersTmp.get(0).getHasExperience() + "\n dla dwojki "
//          + boardMembersTmp.get(1).getHasExperience() + "\n konflikt rosnie o 0.5 " + tmpConflict);

    }

    if (tmpConflict >= 1.5) {
      System.err.println("Konflikt wiekszy od 1.5");
      if (new Random().nextDouble() <= board.getRelationshipConflictProbability()) {
        board.setRelationshipConflict();
      }
    }

  }

};
