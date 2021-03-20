package ua.kpi.tef.musical_instrument.pojo.enums;

public enum ResonatorsAmount {
    ABSENT(0),
    SMALL(1),
    MEDIUM(5),
    BIG(10);

    private int resonatorAmount;
    public static final int MAX_AMOUNT = 50;

    ResonatorsAmount(int resonatorAmount) {
        this.resonatorAmount = resonatorAmount;
    }

    public boolean resonatorAbsence(){
        return getResonatorAmount() == 0;
    }


    public int getResonatorAmount() {
        return resonatorAmount;
    }

    public void setResonatorAmount(int resonatorAmount) {
        this.resonatorAmount = resonatorAmount;
    }
}
