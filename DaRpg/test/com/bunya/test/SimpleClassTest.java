package com.bunya.test;

import java.util.ConcurrentModificationException;

import org.junit.Test;

import com.bunya.domain.AppliedBuff;
import com.bunya.domain.PlayableCharacter;
import com.bunya.types.CharacterClass;
import com.google.gson.Gson;

public class SimpleClassTest {
	@Test
	public void testLevelUp() {
		PlayableCharacter warrior = new PlayableCharacter("meiswarrior",
				CharacterClass.WARRIOR);
		warrior.gainExperience(10000);
		Gson gson = new Gson();
		System.out.println(gson.toJson(warrior).replace(",", ",\n"));
	}

	@Test
	public void testAttack() {
		PlayableCharacter ogun = new PlayableCharacter("Heirofheaven",
				CharacterClass.WARRIOR);
		PlayableCharacter can = new PlayableCharacter("Bunya",
				CharacterClass.ROGUE);
		String damageAmount = ogun.attack(can);
		System.out.println(damageAmount);
	}

	@Test
	public void testSpellCast() {
		PlayableCharacter ogun = new PlayableCharacter("HeirofHeaven",
				CharacterClass.WARRIOR);
		PlayableCharacter can = new PlayableCharacter("Bunya",
				CharacterClass.ROGUE);
		System.out.println("Before: " + can.getHp());
		ogun.setTarget(can);
		System.out.println(ogun.cast("Cut"));
		System.out.println("After: " + can.getHp());
	}

	public static void main(String[] args) {
		final PlayableCharacter ogun = new PlayableCharacter("HeirofHeaven",
				CharacterClass.WARRIOR);
		final PlayableCharacter can = new PlayableCharacter("Bunya",
				CharacterClass.ROGUE);
		showBuffs(can, .3);
		ogun.setTarget(can);
		ogun.cast("Cut");
		ogun.cast("Cut");
		ogun.cast("Slash");
		ogun.cast("anan");// Null pointers bitches!!!
		System.out.println("Hp: " + can.getHp());

	}

	public static void showBuffs(final PlayableCharacter character,
			final double intervalSec) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				do {
					synchronized (this) {
						try {
							for (AppliedBuff b : character.getBuffs()) {
								System.out.print(new Gson().toJson(b));
							}
						} catch (ConcurrentModificationException e) {
						}
						System.out.println();
						try {
							wait((int) (intervalSec * 1000));
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				} while (!character.getBuffs().isEmpty());
			}
		}).start();
	}
}
