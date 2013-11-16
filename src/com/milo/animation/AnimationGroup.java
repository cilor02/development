package com.milo.animation;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.milo.animation.time.Pulse;

public class AnimationGroup implements Runnable{

private int frequency;
private AnimationManager interfaceToManager;
public void setInterfaceToManager(AnimationManager aMgr) {
	// TODO Auto-generated method stub
	this.interfaceToManager = aMgr;
}



public void run() {
	// TODO Auto-generated method stub
	try{
		getPulse().setFrequency(frequency);
	notifyAnimatables();
	System.out.println("notify animatables ");

	}catch(Exception e)
	{System.out.println("e "+e);}
}
public Pulse getPulse() {
	return interfaceToManager.getPulse();
}



HashSet<Animatable> animatables;

public AnimationGroup(int freq)
{
	frequency=freq;
	animatables = new HashSet<Animatable>();
}

public void addAnimatable(Animatable a)
{
	animatables.add(a);
}



public void notifyAnimatables()throws Exception{
	//run perpetual heart beat of animation group
	try{
	for(;;){
		System.out.println("animatables 1"+animatables);
	getPulse().pause();
	System.out.println("animatables 2"+animatables);
	System.out.println("animatables  size "+animatables.size());
	for(Animatable a:animatables)
	{
		System.out.println("notified animatable b4 "+a);
		a.animate();
		System.out.println("notified animatable "+a);

	}

	}
	
	}catch (Exception e)
	{
		System.out.println("E "+ e);
	}
}



public int getFrequency() {
	return frequency;
}

public void setFrequency(int frequency) {
	this.frequency = frequency;
}

public HashSet<Animatable> getAnimatables() {
	return animatables;
}

public void setAnimatables(HashSet<Animatable> animatables) {
	this.animatables = animatables;
}
}
