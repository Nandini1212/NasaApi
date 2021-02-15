import java.util.ArrayList;
import java.util.List;

public class MainClass {
    public static void main(String[]args) {
        //user inputs taken
        Location boston = new Location("Boston", 42.354558, 71.054254, "N", "W");
        Location ncr = new Location("NCR", 28.574389, 77.312638, "N", "E");
        Location sanFran = new Location("San Francisco", 37.793700, 122.403906, "N", "W");
        Location london = new Location("London", 51.51,    0.12, "N", "W");
        List<Location> DelphixLocations = new ArrayList<>();

        //Test case 1 : add all locations shared in assignment, original case
        //DelphixLocations.add(boston);
        //DelphixLocations.add(sanFran);
        DelphixLocations.add(ncr);
        DelphixLocations.add(london);

        //Test case 2 : add only 2 locations , comment other test cases
        //DelphixLocations.add(ncr);
        //DelphixLocations.add(sanFran);

        //Test case 3 : send empty location list, comment other test cases

        if (!DelphixLocations.isEmpty()) {
            BrightestStar shootingStarService = new BrightestStar();
            //after receiving shooting stars for every location, we print the brightest shooting star
            System.out.println(shootingStarService.getBrightestStar(DelphixLocations, "2017-01-01"));
        }
        else
            System.out.println("INSUFFICIENT_DATA");
    }
}
