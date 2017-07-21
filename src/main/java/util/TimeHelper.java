package util;

/**
 * Created by ivankov on 18.07.2017.
 */
public class TimeHelper {
    public static void sleep(int period){
        try{
            Thread.sleep(period);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
