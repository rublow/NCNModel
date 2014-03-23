package ncn;

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
    double tmpDissonace = 0;

    if (boardMembersTmp.get(0).getEducationDegree() != boardMembersTmp.get(1).getEducationDegree()) {
      ++tmpDissonace;
    }

    if (boardMembersTmp.get(0).getNationality() != boardMembersTmp.get(1).getNationality()) {
      ++tmpDissonace;
    }

    if (boardMembersTmp.get(0).getHasMBA() != boardMembersTmp.get(1).getHasMBA()) {
      tmpDissonace += 0.5;
    }

    if (boardMembersTmp.get(0).getHasForeignStudies() != boardMembersTmp.get(1)
        .getHasForeignStudies()) {
      tmpDissonace += 0.2;
    }

//    System.err.println("====DISSONANCE NA KONIEC==== " + tmpDissonace);
    if (tmpDissonace >= 1) {
//      System.err.println("Dysonans wiekszy od 1.0");
      if (new Random().nextDouble() <= board.getCognitiveDissonanceProbability()) {
        board.setCognitiveDissonance();
      }
    }
  }

  public static void setRelationshipConflictInInteraction(SupervisoryBoard board) {
    List<Agent> boardMembersTmp = board.getBoardMembers();
    double tmpConflict = 0;

    if (boardMembersTmp.get(0).getGender() != boardMembersTmp.get(1).getGender()) {
      ++tmpConflict;
    }

    if (boardMembersTmp.get(0).getNationality() != boardMembersTmp.get(1).getNationality()) {
      ++tmpConflict;
    }

    if (boardMembersTmp.get(0).getHasExperience() == Chars.inExperienced
        && boardMembersTmp.get(1).getHasExperience() == Chars.inExperienced) {
      ++tmpConflict;

    } else if ((boardMembersTmp.get(0).getHasExperience() != boardMembersTmp.get(1)
        .getHasExperience())) {
      tmpConflict += 0.5;
    }

    if (tmpConflict >= 1.5) {
//      System.err.println("Konflikt wiekszy od 1.5");
      if (new Random().nextDouble() <= board.getRelationshipConflictProbability()) {
        board.setRelationshipConflict();
      }
    }

  }

};
