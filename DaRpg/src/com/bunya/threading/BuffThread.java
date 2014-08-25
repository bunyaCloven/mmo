package com.bunya.threading;

import java.util.Date;

import com.bunya.domain.AppliedBuff;
import com.bunya.domain.PlayableCharacter;

public class BuffThread implements Runnable {
	private PlayableCharacter caster;
	private PlayableCharacter target;
	private AppliedBuff buff;

	public BuffThread(PlayableCharacter caster, PlayableCharacter target,
			AppliedBuff buff) {
		super();
		this.caster = caster;
		this.target = target;
		this.buff = buff;
	}

	@Override
	public void run() {
		do {
			target.damage(buff.getBuff().getName(), buff.getDamage()
					.calculateDamageOn(target), buff.getDamage().getType(),
					caster);
			double waitTime = buff.getBuff().getInterval();
			synchronized (this) {
				try {
					wait((long) (waitTime * 1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} while (new Date().before(buff.getEndDate()));
		target.getBuffs().remove(buff);
	}
}
