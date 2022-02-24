package unit.character.weapon;


public enum OrcWeapon {
    SHORT_AXE(0.1, -0.05),
    IRON_HAMMER(0.2, -0.1);

    private final double damage;
    private final double attackSpeed;

    OrcWeapon(double damage, double attackSpeed) {
        this.damage = damage;
        this.attackSpeed = attackSpeed;
    }

    public double getDamage() {
        return damage;
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }
}
