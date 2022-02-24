package unit.monster;

import unit.Unit;

/**
 * 기본 공격과 피격 죽음이 가능한 Attack, Mortal 인터페이스를 구현한 BaseUnit을 상속
 * 능력치가 생김
 */
public class Monster extends Unit {

    private final short level;
    private double attackSpeed;
    private int mp;

    public Monster(MonsterAbilityInit init) {
        super(init.getHp(), init.getDamage(), init.getArmour());
        this.level = init.getLevel();
        this.attackSpeed = init.getAttackSpeed();
        this.mp = init.getMp();
    }

    /**
     * 피격 당함
     *
     * @param unit 공격하는 대상
     * @return 주어진 대미지에 나의 아머를 차감한 결과 값
     */
    @Override
    public int takeDamage(Unit unit) {
        return super.takeDamage(unit);
    }

    public short getLevel() {
        return level;
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public int getMp() {
        return mp;
    }
}
