package com.bunya.domain;

import java.util.Date;

public class AppliedBuff {

	private final Buff buff;
	private Date endDate;
	private int stack;

	public AppliedBuff(Buff buff) {
		this.buff = buff;
		this.endDate = new Date(new Date().getTime()
				+ (int) (buff.getDuration() * 1000));
		stack = 1;
	}

	public void reapply() {
		this.endDate = new Date(new Date().getTime()
				+ (int) (buff.getDuration() * 1000));
		System.out.println(buff.getType().isStackable());
		if (buff.getType().isStackable()) {
			stack++;
		}
	}

	public Buff getBuff() {
		return buff;
	}

	public Date getEndDate() {
		return endDate;
	}

	public int getStack() {
		return stack;
	}

	public SpellDamage getDamage() {
		SpellDamage baseDamage = buff.getDamage();
		return new SpellDamage(stack * baseDamage.getDamageMultiplier(), stack
				* baseDamage.getDamage(), baseDamage.getType());
	}
}
