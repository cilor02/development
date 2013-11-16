package com.milo.animation.time;

public interface Pulse {

	public abstract int getFrequency();

	public abstract void setFrequency(int frequency);

	public abstract void pause() throws Exception;

}