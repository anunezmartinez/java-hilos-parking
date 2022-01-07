import java.util.logging.Level;
import java.util.logging.Logger;

public class CarThread extends Thread {

    private ParkingChecker monitorObj;

    private int freePosition;

    public CarThread() {
    }

    public CarThread(String name, ParkingChecker monitorObj) {
        super(name);
        this.monitorObj = monitorObj;
    }

    /*Setters and getters of the int value freePosition*/

    public synchronized int getFreePosition() {
        return freePosition;
    }

    public synchronized void setFreePosition(int freePosition) {
        this.freePosition = freePosition;
    }

    @Override
    public void run() {

        System.out.println("A new thread has been created, name : " + getName());

        try {
            sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(CarThread.class.getName()).log(Level.SEVERE, null, ex);
        }


        /*  In order to get the position in the array, we need to use a Return method of Entry. */
        freePosition = monitorObj.Entry(getName());
        try {
            /* This is just a random Sleep time */

            sleep((int) Math.floor(Math.random() * (7000 - 999 + 1)));
        } catch (InterruptedException ex) {
            Logger.getLogger(CarThread.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Get the position in the free places of the parking as return parameter of Exit.
        monitorObj.Exit(getName(), freePosition);

    }

}