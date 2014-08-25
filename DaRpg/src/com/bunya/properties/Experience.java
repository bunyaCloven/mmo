package com.bunya.properties;

public enum Experience {
	LEVEL_1(100), LEVEL_2(300), LEVEL_3(500), LEVEL_4(750), LEVEL_5(1000);
	private static final int MAX_LEVEL = 5;
	private int exp;

	Experience(int exp) {
		this.exp = exp;
	}

	public int getExp() {
		return exp;
	}

	public static int getMaxLevel() {
		return MAX_LEVEL;
	}

}
