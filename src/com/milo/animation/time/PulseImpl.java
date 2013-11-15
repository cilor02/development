package com.milo.animation.time;

public class PulseImpl implements Pulse {
private int frequency;

/* (non-Javadoc)
 * @see com.milo.animation.time.Beat#getFrequency()
 */
public int getFrequency() {
	return frequency;
}

/* (non-Javadoc)
 * @see com.milo.animation.time.Beat#setFrequency(int)
 */
public void setFrequency(int frequency) {
	this.frequency = frequency;
}
/* (non-Javadoc)
 * cause a pause to occur for the durationof member variable frequency
 * 
 * 
 * @see com.milo.animation.time.Beat#pause()
 */
public void pause ()throws Exception
{
	Thread.sleep(frequency);
}
}
