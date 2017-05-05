package clui;

import java.util.Calendar;

public class VirtualCalendar extends Calendar {
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
	
	@Override
	public void add(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void computeFields() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void computeTime() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getGreatestMinimum(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getLeastMaximum(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaximum(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMinimum(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void roll(int arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
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
