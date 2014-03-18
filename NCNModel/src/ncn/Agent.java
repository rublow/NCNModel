package ncn;

public class Agent {

  private final Gender gender;
  private final String nationality;
  private final int educationDegree;
  private final Education education;
  private final Chars hasExperience;
  private final Chars hasForeignStudies;
  private final Chars hasPostGradStud;
  private final Chars hasMBA;
  private final Chars isChairman;
  String Name;

  private Agent(String Name, Gender gender, String nationality, int educationDegree,
      Education education, Chars hasExperience, Chars hasForeignStudies, Chars hasPostGradStud,
      Chars hasMBA, Chars chairman) {

    this.Name = Name;
    this.gender = gender;
    this.nationality = nationality;
    this.educationDegree = educationDegree;
    this.education = education;

    if (hasExperience == Chars.Experienced) {
      this.hasExperience = Chars.Experienced;
    } else {
      this.hasExperience = Chars.inExperienced;
    }

    if (hasForeignStudies == Chars.ForeignStudies) {
      this.hasForeignStudies = Chars.ForeignStudies;
    } else {
      this.hasForeignStudies = Chars.noForeignStudies;
    }

    if (hasPostGradStud == Chars.PostGradStud) {
      this.hasPostGradStud = Chars.PostGradStud;
    } else {
      this.hasPostGradStud = Chars.noPostGradStud;
    }

    if (hasMBA == Chars.MBA) {
      this.hasMBA = Chars.MBA;
    } else {
      this.hasMBA = Chars.noMBA;
    }

    if (chairman == Chars.Chairman) {
      this.isChairman = Chars.Chairman;
    } else {
      this.isChairman = Chars.notChairman;
    }
  };

  public String getName() {
    return Name;
  }

  public static Agent createAgent(String s, Gender gender, String nationality, int educationDegree,
      Education education, Chars hasExperience, Chars hasForeignStudies, Chars hasPostGradStud,
      Chars hasMBA, Chars chairman) {

    return new Agent(s, gender, nationality, educationDegree, education, hasExperience,
        hasForeignStudies, hasPostGradStud, hasMBA, chairman);
  }

  public static Agent createAgent() {

    return new Agent("Tomek", Gender.MALE, "POLISH", 3, Education.Other, Chars.Experienced,
        Chars.ForeignStudies, Chars.noPostGradStud, Chars.noMBA, Chars.notChairman);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Agent " + Name + " [gender=" + gender + ", nationality=" + nationality + ", educationDegree="
        + educationDegree + ", education=" + education + ", hasExperience=" + hasExperience
        + ", hasForeignStudies=" + hasForeignStudies + ", hasPostGradStud=" + hasPostGradStud
        + ", hasMBA=" + hasMBA + ", isChairman=" + isChairman + "]";
  }

  public enum Gender {
    MALE, FEMALE;
  }

  public enum Education {
    Technical, Economic, Law, Other;
  }

  public enum Chars {
    Experienced(), ForeignStudies, PostGradStud, MBA, Chairman, inExperienced, noForeignStudies, noPostGradStud, noMBA, notChairman;
    
    // private Chars(boolean x) {
    // this.x = x;
    // }
    //Experienced(true)
    // public boolean getX( {
    // return x;
    // })
    //
    // boolean x;
  }

  /**
   * @return the gender
   */
  public Gender getGender() {
    return gender;
  }

  /**
   * @return the nationality
   */
  public String getNationality() {
    return nationality;
  }

  /**
   * @return the educationDegree
   */
  public int getEducationDegree() {
    return educationDegree;
  }

  /**
   * @return the education
   */
  public Education getEducation() {
    return education;
  }


  /**
   * @return the hasExperience
   */
  public Chars getHasExperience() {
    return hasExperience;
  }

  /**
   * @return the hasForeignStudies
   */
  public Chars getHasForeignStudies() {
    return hasForeignStudies;
  }

  /**
   * @return the hasPostGradStud
   */
  public Chars getHasPostGradStud() {
    return hasPostGradStud;
  }

  /**
   * @return the hasMBA
   */
  public Chars getHasMBA() {
    return hasMBA;
  }

  /**
   * @return the isChairman
   */
  public Chars getIsChairman() {
    return isChairman;
  }

  /**
   * @return the hasForeignStudies
   */

}
