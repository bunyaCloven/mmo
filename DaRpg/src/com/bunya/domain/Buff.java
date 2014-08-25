package com.bunya.domain;

import com.bunya.types.BuffType;

public class Buff {

	private String name;
	private BuffType type;
	private SpellDamage damage;
	private double duration;
	private double interval;

	public Buff(String name, BuffType type, SpellDamage damage,
			double duration, double interval) {
		this.name = name;
		this.type = type;
		this.damage = damage;
		this.duration = duration;
		this.interval = interval;
	}

	public BuffType getType() {
		return type;
	}

	public double getDuration() {
		return duration;
	}

	public String getName() {
		return name;
	}

	public SpellDamage getDamage() {
		return damage;
	}

	public double getInterval() {
		return interval;
	}
}
