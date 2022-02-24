package unit;

/**
 * 캐릭터와 몬스터를 포함한 기본 유닛의 변하지 않는 부분을 최상위 클래스로 재사용하기 좋게 구현하였다.
 * 기본 공격과 관련된 Attack, 피격과 죽음과 관련된 Mortal을 구현한다.
 * human, elf, orc, 몬스터 등 새로운 추가되어도 변하지 않는 부분이기에
 * 종족이 추가될 때마다 매번 구현할 필요가 없다.
 * 또한 모든 캐릭터에게 적용되는 것이나 메소드가 추가되면 한번의 추가로도 전부 적용시킬 수 있다.
 */
public class Unit implements Mortal, Attack {

    private boolean isAlive = true;

    private int maxHp;
    private int hp;
    private int damage;
    private int armour;

    protected Unit(Integer hp, Integer damage, Integer armour) {
        this.maxHp = hp;
        this.hp = hp;
        this.damage = damage;
        this.armour = armour;
    }

    /**
     * unit은 다른 unit을 공격할 수 있다.
     * @param unit 공격 대상
     * @return 최종 공격 대미지
     */
    @Override
    public int attack(Mortal unit) {
        if (this == unit) return 0;
        if (!isAlive() || !unit.isAlive()) return 0;
        return unit.takeDamage(this);
    }

    /**
     * 피격 당함
     *
     * @param unit 공격하는 대상
     * @return 주어진 대미지에 나의 아머를 차감한 결과 값
     */
    @Override
    public int takeDamage(Unit unit) {
        return this.takeDamage(unit.damage);
    }

    /**
     * 피격 당함
     *
     * @param damage 피격당한 대미지
     * @return 피격한 상대의 대미지에서 나의 아머를 차감한 결과 값
     */
    @Override
    public int takeDamage(int damage) {
        if (!isAlive()){
            return 0;
        }
        int finalDamage = damage - this.armour;
        this.hp -= finalDamage;
        if (this.hp <= 0){
            this.hp = 0;
            die();
        }
        return finalDamage;
    }

    /**
     * 객체의 죽음을 의미한다. 이후 기본 공격, 스킬, 피격등을 할 수 없다.
     */
    private void die() {
        if (isAlive()) {
            this.isAlive = false;
        }
    }

    /**
     * 객체의 죽은 여부를 확인한다. 죽은 객체는 기본 공격, 스킬, 피격등을 할 수 없다.
     *
     * @return 죽은 상태는 false 죽지 않은 상태는 true
     */
    @Override
    public boolean isAlive() {
        return this.isAlive;
    }

    protected void setDamage(int damage) {
        this.damage = damage;
    }

    protected void setArmour(int armour) {
        this.armour = armour;
    }

    protected void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public int getHp() {
        return this.hp;
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public int getArmour() {
        return this.armour;
    }

    public int getMaxHp() {
        return this.maxHp;
    }
}
