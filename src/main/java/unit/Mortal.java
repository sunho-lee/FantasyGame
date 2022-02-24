package unit;

/**
 * 언젠가 죽을 수 있는 객체를 위한 인터페이스이다. die 메소드가 필요한 객체의 경우 구현해야 한다.
 * 기획에 따라 추후에 추가될 수 있는 탈 것, 전투에 도움을 주는 NPC, 소환수 등
 * 모든 유닛이 공격받지 않을 수 있기 때문에 공격 받을 수 있는 경우
 * 해당 인터페이스를 구현한다. 공격 받는 것은 Attack 인터페이스를 구현한 객체에게만 가능하다.
 **/
public interface Mortal {

    /**
     * 피격 당함
     * @param unit 피격한 상대
     * @return 피격한 상대의 대미지에서 나의 아머를 차감한 결과 값
     */
    int takeDamage(Unit unit);

    /**
     * 피격 당함
     * @param damage 피격당한 대미지
     * @return 피격한 상대의 대미지에서 나의 아머를 차감한 결과 값
     */
    int takeDamage(int damage);

    /**
     * 객체의 죽은 여부를 확인한다.
     * 죽은 객체는 기본 공격, 스킬, 피격등을 할 수 없다.
     * @return 죽은 상태는 false 죽지 않은 상태는 true
     */
    boolean isAlive();

    int getHp();

    int getArmour();
}
