import java.util.ArrayList;
public class CarbonFootprintInterfaceTest{
    public static void main( String[] args ){
        ArrayList< CarbonFootprint > categories = new ArrayList< CarbonFootprint >();//creates array of objects of type CarbonFootprint
        categories.add(new Bicycle( 200.00 ));
        categories.add(new Building( 4000.52 ));
        categories.add(new Car( 5845.25, 20.5 ));
        System.out.println(" Data of each object:\n ");
        for( CarbonFootprint item : categories ){
            System.out.printf("%s \n%s: %.2f\n",item.toString(),"Carbon footprint is ",item.getCarbonFootprint());
        }//end for loop     
    }//end of main
}//end of CarbonFootprintInterfaceTest