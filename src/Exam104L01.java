public class Exam104L01 {

  public static void main(String[] args) {
    GameHelper gameHelper = new GameHelper();
    System.out.println("Generated values:- ");
    gameHelper.fillTable();
    gameHelper.showTable();
    System.out.println("Is solution correct? " + gameHelper.checkResult());
    System.out.println("\n Add correct solution to check method checkResult()");
    gameHelper.gameTable = gameHelper.gameTable2;
    gameHelper.showTable();
    System.out.println("Is solution correct? " + gameHelper.checkResult());
  }

}
