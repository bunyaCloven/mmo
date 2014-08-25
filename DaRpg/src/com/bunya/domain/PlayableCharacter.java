package com.bunya.domain;

import java.util.LinkedList;
import java.util.List;

import com.bunya.container.ClassSkillContainer;
import com.bunya.properties.Experience;
import com.bunya.threading.BuffThread;
import com.bunya.types.CharacterClass;
import com.bunya.types.DamageType;

public class PlayableCharacter {

	private String name;
	private int hp, hpMAX;
	private int ep, epMAX;
	private CharacterClass characterClass;
	private int level;
	private int experience;
	private int skillEXP;
	private PlayableCharacter target;
	private PlayableCharacter focus;
	private volatile List<AppliedBuff> buffs;

	public PlayableCharacter(String name, CharacterClass characterClass) {
		this.name = name;
		this.characterClass = characterClass;
		buffs = new LinkedList<AppliedBuff>();
		level = 1;
		hpMAX = characterClass.getFortitude() * 10;
		hp = hpMAX;
		epMAX = characterClass.getCapacity();
		ep = epMAX;
		experience = 0;
		skillEXP = 0;
	}

	public String damage(String name, int amount, DamageType type,
			PlayableCharacter attacker) {
		amount = (hp > amount) ? amount : hp;
		hp -= amount;
		return amount + "";
	}

	public synchronized void applyBuff(Buff buff, PlayableCharacter attacker) {
		boolean found = false;
		for (AppliedBuff a : buffs) {
			if (a.getBuff().getName() == buff.getName()) {
				a.reapply();
				found = true;
				break;
			}
		}
		if (!found) {
			AppliedBuff b = new AppliedBuff(buff);
			buffs.add(b);
			new Thread(new BuffThread(attacker, this, b)).start();
		}
	}

	public String cast(Skill skill, PlayableCharacter target) {
		String result = null;
		if (skill != null) {
			result = target.damage(skill.getName(), skill.getDamage()
					.calculateDamageOn(target), skill.getDamage().getType(),
					this);
			if (skill.getBuff() != null) {
				target.applyBuff(skill.getBuff(), this);
			}
		}
		return result;
	}

	public String cast(Skill skill) {
		return cast(skill, getTarget());
	}

	public String cast(String skillName) {
		return cast(getSkill(skillName));
	}

	public String attack(PlayableCharacter target) {
		return target.damage("Auto attack", characterClass.getAttackPower(),
				DamageType.PHYSICAL, this);
	}

	public Skill getSkill(String skillName) {
		return ClassSkillContainer.getInstance().get(getCharacterClass())
				.get(skillName.toLowerCase());
	}

	public String getName() {
		return name;
	}

	public CharacterClass getCharacterClass() {
		return characterClass;
	}

	public int getLevel() {
		return level;
	}

	public int getExperience() {
		return experience;
	}

	public void gainExperience(int expAmount) {
		experience += expAmount;
		while (Experience.getMaxLevel() != level
				&& experience > getExpProperty(level).getExp()) {
			experience -= Experience.values()[level - 1].getExp();
			level++;
		}
	}

	public int getSkillEXP() {
		return skillEXP;
	}

	public void gainSkillEXP(int skillExpAmount) {
		skillEXP += skillExpAmount;
	}

	public int getHp() {
		return hp;
	}

	public int getHpMAX() {
		return hpMAX;
	}

	public int getEp() {
		return ep;
	}

	public int getEpMAX() {
		return epMAX;
	}

	private static Experience getExpProperty(int i) {
		return Experience.values()[i];
	}

	public PlayableCharacter getTarget() {
		return target;
	}

	public void setTarget(PlayableCharacter target) {
		this.target = target;
	}

	public PlayableCharacter getFocus() {
		return focus;
	}

	public void setFocus(PlayableCharacter focus) {
		this.focus = focus;
	}

	public List<AppliedBuff> getBuffs() {
		return buffs;
	}

}
