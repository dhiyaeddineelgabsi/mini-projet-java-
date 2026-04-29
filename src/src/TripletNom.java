public class TripletNom {

    private CoupleNom couple;
    private double score;
    //constructor
    public TripletNom(CoupleNom couple,double score){
        this.couple=couple;
        this.score=score;
    }
    //getters
    public CoupleNom getCouple(){
        return(this.couple);
    }
    public double getScore(){
        return(this.score);
    }

    //setters
    public void setScore(double score){
        this.score=score;
    }

}
