package unit.monster;

public enum MonsterAbilityInit {

    GOBLIN((short) 1, 300, 10, 1, (short) 1, 0),
    TROLL((short) 5, 400, 20, 1.2, (short) 5, 50),
    OGRE((short) 10, 500, 30, 1.5, (short) 10, 100);

    private final short level;
    private int hp;
    private final int damage;
    private final double attackSpeed;
    private final int armour;
    private final int mp;

     MonsterAbilityInit(short level, int hp, int damage, double attackSpeed, short armour, int mp) {
        this.level = level;
        this.hp = hp;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.armour = armour;
        this.mp = mp;
    }

    public short getLevel() {
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

}
