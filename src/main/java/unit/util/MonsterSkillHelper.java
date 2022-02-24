package unit.util;

import unit.Unit;

public class MonsterSkillHelper extends Unit {

    private final Unit unit;

    protected MonsterSkillHelper(Unit unit) {
        super(unit.getHp(), unit.getDamage(), unit.getArmour());
        this.unit = unit;
    }

    /**
     * 피격 당함
     *
     * @param OtherUnit 공격하는 대상
     * @return 주어진 대미지에 나의 아머를 차감한 결과 값
     */
    @Override
    public int takeDamage(Unit OtherUnit) {
        return this.unit.takeDamage(OtherUnit);
    }


}
