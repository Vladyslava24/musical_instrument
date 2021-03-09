package ua.kpi.tef.musical_instrument.model.enums;

public enum  ResonatorsLength {
    SHORT(10.56),
    MEDIUM(25.89),
    LONG(50.55);

    private double length;

    ResonatorsLength(double length) {
        this.length = length;
    }

    public boolean isShortResonators(){
        if(length<15.0)
            return true;
        return false;
    }

    public boolean isMediumResonators(){
        if(length>=15.0 && length<=30.0)
            return true;
        return false;
    }

    public boolean isLongResonators(){
        if(length>=30.0)
            return true;
        return false;
    }

    public double getLength() {
        return length;
    }
}

