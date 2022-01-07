import java.util.logging.Level;
import java.util.logging.Logger;

public class ParkingChecker {

    private final int totalPlaces = Parking.numPlaces;
    private final int placesArray = totalPlaces;

    /*Creating an Array to be able to access the names of CarThread*/
    private final String[] pArray = new String[placesArray];
    private final int cPlaces = totalPlaces;
    /*to store where a car was stores*/
    private int p = 0;

    public ParkingChecker() {
    }

    public synchronized void arrayChecker() {

        for (int i = 0; i < pArray.length; i++) {

            System.out.print("(*" + pArray[i] + "*)");
        }
        System.out.println("\n");
    }

    /* We need to initialize the array first */
    public void iniArray() {

        for (int i = 0; i < pArray.length; i++) {

            pArray[i] = "0";

        }

    }

    public synchronized Integer Entry(String entryName) {

        //Checking if there's free slots.
        while (Parking.numPlaces == 0) {

            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(ParkingChecker.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        for (int i = 0; i < pArray.length; i++) {
            if (pArray[i] == "0") {
                pArray[i] = entryName;
                arrayChecker();
                p = i;
                break;
            }
            //And stores the freeSlot the the Int value.
        }
        return p;
    }

    /*We need to handle the now free slot of a leaving car*/
    public synchronized void Exit(String name, int pNum) {

        System.out.println("Leaving :" + name);
        pArray[pNum] = "0";
        System.out.println("Free slots now : " + Parking.numPlaces);
        arrayChecker();

        /*Notify of a new free slot*/
        notifyAll();

    }

}
