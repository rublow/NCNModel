package ncn;

import java.util.List;

public class SupervisoryBoard {

  private static final double RELATIONSHIP_CONFLICT_ACCUMULATED_FACTOR = 0.5;
  private static final double RELATIONSHIP_CONFLICT_MODIFIER_INC = 0.2;
  private static final double RELATIONSHIP_CONFLICT_MODIFIER_DEC = 0.1;
  private static final double RELATIONSHIP_CONFLICT_INIT_PROBABILITY = 0.3;

  private static final double COGNITIVE_DISSONANCE_ACCUMULATED_FACTOR = 0.8;
  private static final double COGNITIVE_DISSONANCE_MODIFIER_INC = 0.1;
  private static final double COGNITIVE_DISSONANCE_MODIFIER_DEC = 0.1;
  private static final double COGNITIVE_DISSONANCE_INIT_PROBABILITY = 0.5;

  private SupervisoryBoard(List<Agent> boardMembers, int rotation, int numOfMeetings,
      int numOfInteractions, int normsOfEffort, double cognitiveDissonanceProbability,
      int cognitiveDissonance, double relationshipConflictProbability, int relationshipConflict) {

    this.boardMembers = boardMembers;
    this.rotation = rotation;
    this.numOfMeetings = numOfMeetings;
    this.numOfInteractions = numOfInteractions;
    this.normsOfEffort = normsOfEffort;
    this.cognitiveDissonanceProbability = cognitiveDissonanceProbability;
    this.cognitiveDissonance = cognitiveDissonance;
    this.relationshipConflictProbability = relationshipConflictProbability;
    this.relationshipConflict = relationshipConflict;

  }

  public static SupervisoryBoard createSupervisoryBoard(List<Agent> lista) {
    return new SupervisoryBoard(lista, 0, 0, 0, 0, COGNITIVE_DISSONANCE_INIT_PROBABILITY, 0,
        RELATIONSHIP_CONFLICT_INIT_PROBABILITY, 0);
  }

  public static SupervisoryBoard createSupervisoryBoard(List<Agent> boardMembers, int rotation,
      int numOfMeetings, int numOfInteractions, int normsOfEffort,
      double cognitiveDissonanceProbability, int cognitiveDissonance,
      double relationshipConflictProbability, int relationshipConflict) {

    return new SupervisoryBoard(boardMembers, rotation, numOfMeetings, numOfInteractions,
        normsOfEffort, cognitiveDissonanceProbability, cognitiveDissonance,
        relationshipConflictProbability, relationshipConflict);
  }

  private List<Agent> boardMembers;

  private int rotation;
  /*
   * Rotacja w RN skala od 0 do 1 1 - wszyscy cz�onkowie RN zmienili si� w ci�gu roku 0 - sk�ad RN
   * pozosta� niezmieniony
   */

  private int numOfMeetings;
  // Liczba posiedze� (od 3 do 15) - losowo

  private int numOfInteractions;

  // Liczba interakcji podczas posiedzenia - losowo

  private int normsOfEffort;
  // Normy wykonania (wysokie - niskie, na skali od 1 do 5)

  private double cognitiveDissonanceProbability;
  /*
   * Prawdopodobie�stwo wyst�pienia konfliktu poznawczego (PKP): - wyj�ciowe 0,5 - normy wykonania -
   * wysokie +0,1; niskie -0,1 - poziom skumulowanej warto�ci konfliktu poznawczego z poprzednich
   * posiedze� - wysoki +0,1; niski -0,1 - poziom skumulowanej warto�ci sp�jno�ci z poprzednich
   * posiedze� - wysoki -0,1; niski +0,1
   */

  private int cognitiveDissonance;

  /*
   * Konflikt poznawczy (KP) - skok od 0 do 1 - wykszta�cenie - poziom (r�nica +1) - wykszta�cenie
   * - rodzaj (r�nica +1) - MBA (r�nica +0,5) - edukacja zagraniczna (r�nica +0,2) Je�li suma
   * jest wi�ksza ni� 1 => skumulowana warto�� konfliktu poznawczego ro�nie z prawdopodobie�stwem
   * PKP o 1
   */

  private double relationshipConflictProbability;

  /*
   * Prawdopodobie�stwo wyst�pienia konfliktu relacyjnego (PKR): - wyj�ciowe 0,3 - normy wykonania -
   * wysokie -0,1; niskie +0,1 - poziom skumulowanej warto�ci konfliktu afektywnego z poprzednich
   * posiedze� - wysoki +0,2; niski -0,1 - poziom skumulowanej warto�ci sp�jno�ci z poprzednich
   * posiedze� - wysoki -0,1; niski +0,2
   */

  private int relationshipConflict;

  /*
   * Konflikt relacyjny (KR) - skok od 0 do 1 // - p�e� (ta sama - 1, przeciwna 0) // - obywatelstwo
   * (to samo - 0, r�ne - 1) // - do�wiadczenie w RN (obu os�b 0, jednej osoby 0,5, �adnej 1) //
   * Je�li suma jest wi�ksza ni� 1,5 => skumulowana warto�� konfliktu // relacyjnego ro�niej z
   * prawdopodobie�stwem PKR o 1
   */

  public int getRotation() {
    return rotation;
  }

  public void setRotation(int rotation) {
    this.rotation = rotation;
  }

  public int getNumOfMeetings() {
    return numOfMeetings;
  }

  public void setNumOfMeetings(int numOfMeetings) {
    this.numOfMeetings = numOfMeetings;
  }

  public int getNumOfInteractions() {
    return numOfInteractions;
  }

  public void setNumOfInteractions(int numOfInteractions) {
    this.numOfInteractions = numOfInteractions;
  }

  public int getNormsOfEffort() {
    return normsOfEffort;
  }

  public void setNormsOfEffort(int normsOfEffort) {
    this.normsOfEffort = normsOfEffort;
  }

  public double getCognitiveDissonanceProbability() {
    return cognitiveDissonanceProbability;
  }

  public void setCognitiveDissonanceProbability(double cognitiveDissonanceProbability) {
    this.cognitiveDissonanceProbability = cognitiveDissonanceProbability;
  }

  public void setCognitiveDissonanceProbability(int cognitiveDissonance, int numOfInteractions) {
    if (cognitiveDissonance >= (int) (COGNITIVE_DISSONANCE_ACCUMULATED_FACTOR * numOfInteractions)) {
      this.cognitiveDissonanceProbability += COGNITIVE_DISSONANCE_MODIFIER_INC;
    } else {
      this.cognitiveDissonanceProbability -= COGNITIVE_DISSONANCE_MODIFIER_DEC;
    }

    this.cognitiveDissonanceProbability = Helper.doublePrecision(this.cognitiveDissonanceProbability);

    if (this.cognitiveDissonanceProbability > 1.0) {
      this.cognitiveDissonanceProbability = 1.0;
    } else if (this.cognitiveDissonanceProbability < 0.0) {
      this.cognitiveDissonanceProbability = 0.0;
    }
  }

  public int getCognitiveDissonance() {
    return cognitiveDissonance;
  }

  public void setCognitiveDissonance() {
    this.cognitiveDissonance++;
  }

  public double getRelationshipConflictProbability() {
    return relationshipConflictProbability;
  }

  public void setRelationshipConflictProbability(double relationshipConflictProbability) {
    this.relationshipConflictProbability = relationshipConflictProbability;
  }

  public void setRelationshipConflictProbability(int relationshipConflict, int numOfInteractions) {
    if (relationshipConflict >= (int) (RELATIONSHIP_CONFLICT_ACCUMULATED_FACTOR * numOfInteractions)) {
      this.relationshipConflictProbability += RELATIONSHIP_CONFLICT_MODIFIER_INC;
    } else {
      this.relationshipConflictProbability -= RELATIONSHIP_CONFLICT_MODIFIER_DEC;
    }

    this.relationshipConflictProbability =
        Helper.doublePrecision(this.relationshipConflictProbability);

    if (this.relationshipConflictProbability > 1.0) {
      this.relationshipConflictProbability = 1.0;
    } else if (this.relationshipConflictProbability < 0.0) {
      this.relationshipConflictProbability = 0.0;
    }
  }

  public int getRelationshipConflict() {
    return relationshipConflict;
  }

  public void setRelationshipConflict() {
    this.relationshipConflict++;
  }

  public List<Agent> getBoardMembers() {
    return boardMembers;
  }

  public void setBoardMembers(Agent a1) {
    boardMembers.add(a1);
  }

}
