package unit;

/**
 * 기획에 따라 추후에 추가될 수 있는 탈 것, 소환수, NPC 등
 * 기본 공격 기능의 유무에 따라 Attack 인터페이스를 구현할 수 있다.
 * 공격하는 것은 Mortal 인터페이스를 구현한 객체만 가능하다.
 */
public interface Attack {

    /**
     * unit은 다른 unit을 공격할 수 있다.
     * @param unit 공격 대상
     * @return 공격 대미지
     */
    <T extends Mortal> int attack(T unit);

    int getDamage();

}
