package unit.character.weapon;

public enum HumanWeapon {
    SHORT_SWORD(0.05),
    LONG_SWORD( 0.1);

    private final double damage;

    HumanWeapon(double damage ) {
        this.damage = damage;
    }

    public double getDamage() {
        return damage;
    }

}