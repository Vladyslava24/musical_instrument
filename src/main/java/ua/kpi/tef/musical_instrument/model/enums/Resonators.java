package ua.kpi.tef.musical_instrument.model.enums;

public enum Resonators {
    PERCUSSION(ResonatorsAmount.ABSENT),
    WIND(ResonatorsAmount.SMALL),
    STRINGED_SMALL(ResonatorsAmount.MEDIUM, ResonatorsLength.SHORT),
    STRINGED_MEDIUM(ResonatorsAmount.MEDIUM, ResonatorsLength.MEDIUM),
    STRINGED_LONG(ResonatorsAmount.BIG, ResonatorsLength.LONG),
    REED(ResonatorsAmount.MEDIUM);

    private ResonatorsAmount resonatorsAmount;
    private ResonatorsLength resonatorsLength;

    Resonators(ResonatorsAmount resonatorsAmount) {
        this.resonatorsAmount = resonatorsAmount;
    }

    Resonators(ResonatorsAmount resonatorsAmount, ResonatorsLength resonatorsLength) {
        this.resonatorsAmount = resonatorsAmount;
        this.resonatorsLength = resonatorsLength;
    }

    public ResonatorsAmount getResonatorsAmount() {
        return resonatorsAmount;
    }

    public ResonatorsLength getResonatorsLength() {
        return resonatorsLength;
    }

}
