package com.milo.animation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.milo.animation.time.Pulse;

public class AnimationManager {
//private Collection<AnimationGroup> animationGroups;
private Map<Integer, AnimationGroup>animationGroups;
private Pulse pulse; 
public Pulse getPulse() {
	return pulse;
}

public void setPulse(Pulse pulse) {
	this.pulse = pulse;
}





public AnimationManager()
{
	animationGroups=new HashMap<Integer,AnimationGroup>();
}

public void addNewAnimationGroup( int frequency)
{
	//check if refresh rate group already stored
	if(getAnimationGroup(frequency)==null)
	{
		AnimationGroup newAnimationGroup = new AnimationGroup(frequency);
		//set Pulse
		newAnimationGroup.setInterfaceToManager(this);
		animationGroups.put(newAnimationGroup.getFrequency(), newAnimationGroup);
	}
}


public AnimationGroup getAnimationGroup( int frequency)
{
	return animationGroups.get(frequency);
}


public void addAnimitable(Animatable a)
{

	//locate relevant animationgroup to add to
	//if non then create one
AnimationGroup animationGroup	= getAnimationGroup(a.getFrequency());
if(animationGroup==null)
{
	addNewAnimationGroup(a.getFrequency());
	animationGroup	= getAnimationGroup(a.getFrequency());
}
animationGroup.addAnimatable(a);
System.out.println("add animateable "+a +""+a.getFrequency());
System.out.println("add animateable "+a +""+animationGroup.animatables.size());

}

public void startAnimation()throws Exception
{
	for(Integer animationGroupInt: animationGroups.keySet())
	{
		Thread t = new Thread(animationGroups.get(animationGroupInt));
		t.start();

	}
}

}
