package com.bunya.container;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.bunya.domain.Buff;
import com.bunya.domain.Skill;
import com.bunya.domain.SpellDamage;
import com.bunya.types.BuffType;
import com.bunya.types.CharacterClass;
import com.bunya.types.DamageType;
import com.bunya.types.SkillType;

public class ClassSkillContainer {
	HashMap<CharacterClass, Map<String, Skill>> map;

	public static ClassSkillContainer getInstance() {
		return new ClassSkillContainer();

	}

	private ClassSkillContainer() {
		map = new HashMap<CharacterClass, Map<String, Skill>>();
		Map<String, Skill> warriorMap = new HashMap<String, Skill>();
		Buff bleed = new Buff("Bleed", BuffType.CURSE, new SpellDamage(0.3, 10,
				DamageType.PHYSICAL), 10, 3);
		Skill cut = new Skill("Cut", SkillType.SINGLE_TARGET, new SpellDamage(
				1.4, 50, DamageType.PHYSICAL), bleed);
		Skill slash = new Skill("Slash", SkillType.SINGLE_TARGET,
				new SpellDamage(2.0, 300, DamageType.PHYSICAL));
		warriorMap.put(cut.getName().toLowerCase(), cut);
		warriorMap.put(slash.getName().toLowerCase(), slash);
		map.put(CharacterClass.WARRIOR, warriorMap);
	}

	public Map<String, Skill> get(CharacterClass characterClass) {
		Map<String, Skill> result = new HashMap<String, Skill>();
		if (characterClass == CharacterClass.GM) {
			Collection<Map<String, Skill>> mapCollection = map.values();
			for (Map<String, Skill> m : mapCollection) {
				result.putAll(m);
			}
		} else {
			result = map.get(characterClass);
		}

		return result;
	}
}
