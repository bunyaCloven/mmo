package com.bunya.types;

import java.util.Map;

import com.bunya.container.ClassSkillContainer;
import com.bunya.domain.Skill;

public enum CharacterClass {
	WARRIOR("Warrior", 100, 100, 150, 150, 10, 100, 100, 100), //
	SMASH("Hanzo", 100, 100, 250, 100, 10, 100, 100, 100), //
	MONK("chopchop", 100, 100, 100, 200, 10, 80, 130, 100), //
	PYRO("i set fire", 100, 50, 100, 100, 100, 100, 100, 100), //
	ROGUE("unseen", 100, 7, 250, 150, 10, 70, 70, 90), //
	GM("Game Master", 100, 1000, 100, 100, 100, 100, 100, 100);// ILLEGIBLE

	private String className;
	private int attackPower;
	private int attackSpeed;
	private int spellPower;
	private int fortitude;
	private int capacity;
	private int armor;
	private int spellArmor;
	private int resilience;
	private Map<String, Skill> skillsMap;

	private CharacterClass() {
		skillsMap = ClassSkillContainer.getInstance().get(this);
	}

	private CharacterClass(String name, int hp, int cap, int ap, int as,
			int sp, int arm, int marm, int resil) {
		this();
		className = name;
		fortitude = hp;
		capacity = cap;
		attackPower = ap;
		attackSpeed = as;
		spellPower = sp;
		armor = arm;
		spellArmor = marm;
		resilience = resil;
	}

	public Skill getSkill(String skillName) {
		return skillsMap.get(skillName);
	}

	public String getClassName() {
		return className;
	}

	public int getAttackPower() {
		return attackPower;
	}

	public int getAttackSpeed() {
		return attackSpeed;
	}

	public int getSpellPower() {
		return spellPower;
	}

	public int getArmor() {
		return armor;
	}

	public int getSpellArmor() {
		return spellArmor;
	}

	public int getResilience() {
		return resilience;
	}

	public int getFortitude() {
		return fortitude;
	}

	public int getCapacity() {
		return capacity;
	}

}
