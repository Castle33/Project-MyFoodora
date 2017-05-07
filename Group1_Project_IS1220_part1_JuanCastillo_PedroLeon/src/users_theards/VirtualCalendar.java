package users_theards;

import java.io.Serializable;
import java.util.Calendar;
/**
 * Singleton pattern applied so there only exists one virtual calendar.
 * Main attribute is timeMult that determines how many seconds this calendar simulates for 1 real second.
 * Main method is getVirtualDate that returns the virtual date for a multTime parameter defined.
 * @author Pedro León
 *
 */
public class VirtualCalendar implements Serializable{
	private static VirtualCalendar instance = null;
	private static final long serialVersionUID = -1073852164817064065L;
	private static Calendar currentTime;
	private static Calendar lastVirtualTime = Calendar.getInstance();
	private static Calendar lastTime = Calendar.getInstance();
	private final static int timeMult = 120; // 4464 sec = 74.4 min = 1.24 h per real second
	
	private VirtualCalendar() {
	}
	
	public static VirtualCalendar getUnique(){
		if(instance == null){
			instance = new VirtualCalendar();
		}
		return instance;
	}
	
	public Calendar getVirtualDate(){
		currentTime = Calendar.getInstance();
		long currentTimeInMillis = currentTime.getTimeInMillis();
		long lastTimeInMillis = lastTime.getTimeInMillis();
		long timeLapse = currentTimeInMillis - lastTimeInMillis;
		long fakeTimeLapse = timeLapse * timeMult;
		lastTime = currentTime;
		lastVirtualTime.add(Calendar.SECOND, Math.toIntExact(fakeTimeLapse/1000));
		
		return lastVirtualTime;
	}
	
	public Calendar getVirtualDate(Calendar givenTime){
		currentTime = Calendar.getInstance();
		long currentTimeInMillis = currentTime.getTimeInMillis();
		long lastTimeInMillis = givenTime.getTimeInMillis();
		long timeLapse = lastTimeInMillis - currentTimeInMillis;
		long fakeTimeLapse = timeLapse * timeMult;
		givenTime.add(Calendar.SECOND, Math.toIntExact(fakeTimeLapse/1000));
		
		return givenTime;
	}

	/**
	 * @return the lastTime
	 */
	public static Calendar getLastTime() {
		return lastTime;
	}

	/**
	 * @param lastTime the lastTime to set
	 */
	public static void setLastTime(Calendar lastTime) {
		VirtualCalendar.lastTime = lastTime;
	}
	
	/**
	 * @return the lastVirtualTime
	 */
	public static Calendar getLastVirtualTime() {
		return lastVirtualTime;
	}

	/**
	 * @param lastVirtualTime the lastVirtualTime to set
	 */
	public static void setLastVirtualTime(Calendar lastVirtualTime) {
		VirtualCalendar.lastVirtualTime = lastVirtualTime;
	}

	/**
	 * @return the currentTime
	 */
	public static Calendar getCurrentTime() {
		return currentTime;
	}

	/**
	 * @param currentTime the currentTime to set
	 */
	public static void setCurrentTime(Calendar currentTime) {
		VirtualCalendar.currentTime = currentTime;
	}
}
