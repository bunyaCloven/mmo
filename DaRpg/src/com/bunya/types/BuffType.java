package com.bunya.types;

public enum BuffType {
	BUFF(false, false), DEBUFF(true, false), //
	EMPOWER(false, true), CURSE(true, true);

	private boolean negative;
	private boolean stackable;

	private BuffType(boolean isNegative, boolean isStackable) {
		negative = isNegative;
		stackable = isStackable;
	}

	public boolean isNegative() {
		return negative;
	}

	public boolean isStackable() {
		return stackable;
	}
}
