package unit.monster.skill;

import java.util.SplittableRandom;
import unit.Unit;
import unit.util.MonsterSkillHelper;

public class MonsterWithCounter extends MonsterSkillHelper {

    SplittableRandom random = new SplittableRandom();

    public MonsterWithCounter(Unit unit) {
        super(unit);
    }

    /**
     * 피격 당함
     * 반격에 성공하는 경우 공격력의 70%의 대미지를 상대방에게 입힌다.
     * @param OtherUnit 공격하는 대상
     * @return 주어진 대미지에 나의 아머를 차감한 결과 값
     */
    @Override
    public int takeDamage(Unit OtherUnit) {
        if (isCounter()){
            OtherUnit.takeDamage((int) ((double) this.getDamage() * 70.0 / 100.0));
        }
        return super.takeDamage(OtherUnit);
    }

    /**
     * 30% 확률로 반격을 성공한다.
     * @return 반격 성공 여부
     */
    private Boolean isCounter(){
        return this.random.nextInt(100) < 30;
    }
}
