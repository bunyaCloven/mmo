package com.bunya.domain;

import com.bunya.types.SkillType;

public class Skill {

	private String name;
	private SkillType type;
	private boolean passive;
	private Buff buff;
	private SpellDamage damage;

	public Skill(String name, SkillType type, SpellDamage damage) {
		this(name, type, damage, false);
	}

	public Skill(String name, SkillType type, SpellDamage damage, Buff buff) {
		this(name, type, damage);
		this.buff = buff;
	}

	public Skill(String name, SkillType type, SpellDamage damage,
			Boolean isPassive) {
		this.name = name;
		this.type = type;
		this.passive = isPassive;
		this.damage = damage;
	}

	public boolean isPassive() {
		return passive;
	}

	public String getName() {
		return name;
	}

	public SkillType getType() {
		return type;
	}

	public Buff getBuff() {
		return buff;
	}

	public SpellDamage getDamage() {
		return damage;
	}
}
