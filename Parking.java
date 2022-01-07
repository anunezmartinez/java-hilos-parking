public class Parking {
    public static int numPlaces;
    /*Thread Counter*/
    private final int numCars;

    public Parking(int numPlaces, int numCars) {
        Parking.numPlaces = numPlaces;
        this.numCars = numCars;
    }


    public void startThreads() {
        ParkingChecker cObj = new ParkingChecker();
        /*Initialized a new array and a new one to store the slots*/
        cObj.iniArray();
        CarThread[] aCar = new CarThread[numCars];
        for (int i = 0; i < numCars; i++) {
            /* Store the new registered slots in the CarThread object*/
            aCar[i] = new CarThread("C" + i, cObj);

        }

        for (CarThread carThread : aCar) {
            carThread.start();
        }

    }

}
