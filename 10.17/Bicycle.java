public class Bicycle implements CarbonFootprint{
    private double anioKm;
    private final int caloriasKm = 34;
    //constructor
    public Bicycle( double kilometros ){
        anioKm = kilometros;
    }//end of constructor
    public double getAnioKm(){
        return anioKm;
    }
    public void setAnioKm( double kilometros ){
        anioKm = kilometros;
    }
    @Override
    public String toString(){
        return String.format("%s: %.2f",
            "Yearly kilometros are ", getAnioKm() );
    }
    @Override
    public double getCarbonFootprint(){
        return anioKm * caloriasKm;
    };
}//endnof Bicycle class