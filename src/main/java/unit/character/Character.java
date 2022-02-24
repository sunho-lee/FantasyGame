package unit.character;

import java.util.SplittableRandom;
import unit.Unit;
import unit.Mortal;

/**
 * 초기 기획인 human, elf, orc를 구현하는 객체이다.
 * 기본 공격, 피격, 죽음이 가능한 Unit을 구현한 BaseUnit을 구현
 * 레벨업이 가능하다.
 * Inventory 구현을 통한 레벨 업, 무기 장착과 Skill을 사용할 수 있다.
 */
public class Character extends Unit {

    private int level;
    private double attackSpeed;
    private int mp;
    private double dodgePercent;

    private Heal healInfo = new Heal();
    private Steam steamInfo = new Steam();

    private SplittableRandom random = new SplittableRandom();

    protected Character(CharacterAbilityInit init) {
        super(init.getHp(), init.getDamage(), init.getArmour());
        this.level = init.getLevel();
        this.attackSpeed = init.getAttackSpeed();
        this.mp = init.getMp();
        this.dodgePercent = init.getDodgePercent();
    }

    /**
     * unit은 다른 unit을 공격할 수 있다.
     * 공격한 유닛이 죽는 경우 레벨 업을 한다.
     * @param unit 공격 대상
     * @return 최종 공격 대미지
     */
    @Override
    public int attack(Mortal unit) {
        int finalDamage = super.attack(unit);

        if (!unit.isAlive()){
            increaseLevel();
        }
        return finalDamage;
    }

    /**
     * 레벨 업
     */
    private void increaseLevel() {
        this.level++;
    }

    /**
     * 캐릭터는 피격 시 30% 확률로 회피한다.
     * @param otherUnit 공격하는 대상
     * @return 주어진 대미지에 나의 아머를 차감한 결과 값
     */
    @Override
    public int takeDamage(Unit otherUnit) {
        if (isDodge(this.dodgePercent)){
            return 0;
        }
        return super.takeDamage(otherUnit.getDamage());
    }

    /**
     * 기본 공격을 회피할 확률 계산
     * @return 회피 성공 여부
     */
    private Boolean isDodge(double dodgePercent){
        return this.random.nextDouble(100) < dodgePercent;
    }

    public boolean heal(){
        if (super.getHp() == super.getMaxHp() || this.mp < healInfo.getMp()){
            return false;
        }
        int afterHp = super.getHp() + healInfo.getHp();
        if ( afterHp >= super.getMaxHp()){
            super.setHp(super.getMaxHp());
        }else {
            super.setHp(afterHp);
        }
        this.mp -= healInfo.getMp();

        return true;
    }

    public boolean steam(){
        if (this.mp < steamInfo.getMp() || steamInfo.isSteam()){
            return false;
        }
        this.mp -= steamInfo.getMp();
        this.setAttackSpeed(this.attackSpeed + (this.attackSpeed * steamInfo.getAttackSpeed()));
        steamInfo.setSteam(true);
        return true;
    }

    @Override
    protected void setDamage(int damage) {
        super.setDamage(damage);
    }

    @Override
    protected void setArmour(int armour) {
        super.setArmour(armour);
    }

    @Override
    protected void setMaxHp(int maxHp) {
        super.setMaxHp(maxHp);
    }

    public void setAttackSpeed(double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public int getDamage() {
        return super.getDamage();
    }

    public double getAttackSpeed() {
        return this.attackSpeed;
    }

    public int getMp() {
        return mp;
    }

    public double getDodgePercent() {
        return dodgePercent;
    }

    public static class Heal{
        private final int hp = 100;
        private final int mp = 30;

        public Heal() { }

        public int getHp() {
            return hp;
        }

        public int getMp() {
            return mp;
        }
    }

    public static class Steam{
        private final double attackSpeed = 0.2;
        private final int mp = 30;
        private boolean isSteam = false;

        public Steam() {

        }

        public void setSteam(boolean steam) {
            isSteam = steam;
        }

        public boolean isSteam() {
            return isSteam;
        }

        public double getAttackSpeed() {
            return attackSpeed;
        }

        public int getMp() {
            return mp;
        }
    }
}
