public class Building implements CarbonFootprint{
    private double kilowattMes;
    private final int MESES = 12;
    //private double carbonFootprint;
    //constructor
    public Building( double consumoMes ){
        kilowattMes = consumoMes;
    }//end of constructor
    public void setKilowattMes( double consumoMes ){
        kilowattMes = consumoMes;
    }
    public double getKilowattMes(){
        return kilowattMes;
    }
    @Override 
    public String toString(){
        return String.format("%s: %.2f\n", 
        "the monthly consumption is ", getKilowattMes() );
    }
    @Override
    public double getCarbonFootprint(){
        return getKilowattMes() * MESES;
    }
}