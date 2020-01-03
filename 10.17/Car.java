public class Car implements CarbonFootprint{
    private double anioKm;
    private double promedioMPG;
    private final int dioxidoCarbonoKm = 9;
    //constructor
    public Car( double kilometros, double MPG ){
        anioKm = kilometros;
        promedioMPG = MPG;
    }//end of constructor
    public void setAnioKm( double kilometros ){
        anioKm = kilometros;
    }
    public void setPromedioMPG( double MPG ){
        promedioMPG = MPG;
    }
    public double getAnioKm(){
        return anioKm;
    }
    public double getPromedioMPG(){
        return promedioMPG;
    }
    @Override
    public String toString(){
        return String.format( "%s: %.2f \n%s: %.2f \n",
            "Average yearly kilometros is ", getAnioKm(),
            "Average MPG is ", getPromedioMPG() );
    }
    @Override
    public double getCarbonFootprint(){
        return (( getAnioKm() * getPromedioMPG() ) * dioxidoCarbonoKm );
    }
}