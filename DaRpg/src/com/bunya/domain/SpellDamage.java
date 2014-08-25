package com.bunya.domain;

import com.bunya.types.DamageType;

public final class SpellDamage {

	private final double damageMultiplier;
	private final double damage;
	private final DamageType type;

	public SpellDamage(double damageMultiplier, double damage, DamageType type) {
		this.damageMultiplier = damageMultiplier;
		this.damage = damage;
		this.type = type;
	}

	public double getDamageMultiplier() {
		return damageMultiplier;
	}

	public double getDamage() {
		return damage;
	}

	public DamageType getType() {
		return type;
	}

	public int calculateDamageOn(PlayableCharacter target) {
		return (int) (getDamageMultiplier()
				* target.getCharacterClass().getSpellPower() + getDamage());
	}

}
