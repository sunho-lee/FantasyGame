package unit.character;

/**
 * unit의 종류별 속성을 가지고 있는 열거형 타입이다.
 */
public enum CharacterAbilityInit {

    HUMAN(1, 500, 10, 1, 5, 100, 10),
    ELF(1, 500, 15, 1.2, 1, 100, 0.07),
    ORC(1, 500, 15, 1,5, 100, 0.03);

    private final int level;
    private final int hp;
    private final int damage;
    private final double attackSpeed;
    private final int armour;
    private final int mp;
    private final double dodgePercent;

    CharacterAbilityInit(int level, int hp, int damage, double attackSpeed, int armour, int mp,
            double dodgePercent) {
        this.level = level;
        this.hp = hp;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.armour = armour;
        this.mp = mp;
        this.dodgePercent = dodgePercent;
    }

    public int getLevel() {
        return level;
    }

    public int getHp() {
        return hp;
    }

    public int getDamage() {
        return damage;
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public int getArmour() {
        return armour;
    }

    public int getMp() {
        return mp;
    }

    public double getDodgePercent() {
        return dodgePercent;
    }
}
