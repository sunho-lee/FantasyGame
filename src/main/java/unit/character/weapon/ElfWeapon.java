package unit.character.weapon;

public enum ElfWeapon {

    SHORT_BOW(0.05),
    IRON_BOW(0.1);

    private final double attackSpeed;

    ElfWeapon(double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }
}
