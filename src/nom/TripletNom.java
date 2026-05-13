package nom;

public class TripletNom {
    private CoupleNom couple;
    private double score;

    public TripletNom(CoupleNom couple, double score) {
        this.couple = couple;
        this.score = score;
    }

    public CoupleNom getCouple() {
        return this.couple;
    }

    public double getScore() {
        return this.score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
