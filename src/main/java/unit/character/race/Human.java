package unit.character.race;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import unit.Mortal;
import unit.Unit;
import unit.character.Character;
import unit.character.CharacterAbilityInit;
import unit.character.weapon.HumanWeapon;

public class Human extends Character {

    private HumanWeapon weapon;
    private final Guard guard = new Guard();
    private final Invincible invincible = new Invincible();

    public Human(CharacterAbilityInit init) {
        super(init);
    }

    public Human(CharacterAbilityInit init, HumanWeapon weapon) {
        super(init);
        this.weapon = weapon;
    }

    /**
     * unit은 다른 unit을 공격할 수 있다. 공격한 유닛이 죽는 경우 레벨 업을 한다.
     * 무기를 착용해야 공격할 수 있다.
     * @param unit 공격 대상
     * @return 최종 공격 대미지
     */
    @Override
    public int attack(Mortal unit) {
        if (this.weapon == null){
            return 0;
        }
        return super.attack(unit);
    }

    /**
     * 캐릭터는 피격 시 30% 확률로 회피한다.
     *
     * @param otherUnit 공격하는 대상
     * @return 주어진 대미지에 나의 아머를 차감한 결과 값
     */
    @Override
    public int takeDamage(Unit otherUnit) {
        if (invincible.isInvincible()){
            return 0;
        }
        return super.takeDamage(otherUnit);
    }

    /**
     * 피격 당함
     *
     * @param damage 피격당한 대미지
     * @return 피격한 상대의 대미지에서 나의 아머를 차감한 결과 값
     */
    @Override
    public int takeDamage(int damage) {
        if (invincible.isInvincible()){
            return 0;
        }
        return super.takeDamage(damage);
    }

    @Override
    public int getDamage() {
        if (this.weapon != null){
            return (int) Math.round(super.getDamage() + (super.getDamage() * this.weapon.getDamage()));
        }
        return super.getDamage();
    }

    public boolean guard(){
        if (this.getMp() < guard.GUARD_MP || guard.isGuard){
            return false;
        }
        this.setArmour((int) Math.round(this.getArmour() + (this.getArmour() * 0.3)));
        guard.setGuard(true);
        return true;
    }

    public boolean invincible() {
        if (this.getMp() < invincible.MP || invincible.isInvincible){
            return false;
        }
        invincible.setInvincible(true);

        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                invincible.setInvincible(false);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return true;
    }

    public void wearWeapon(HumanWeapon otherWeapon){
        this.weapon = otherWeapon;
    }

    public Optional<HumanWeapon> getWeapon() {
        return Optional.ofNullable(weapon);
    }

    public static class Guard{

        private final double GUARD_INCREASE = 0.3;
        private final int GUARD_MP = 30;
        private boolean isGuard = false;

        public Guard() {
        }

        public double getGUARD_INCREASE() {
            return GUARD_INCREASE;
        }

        public int getGUARD_MP() {
            return GUARD_MP;
        }

        public boolean isGuard() {
            return isGuard;
        }

        public void setGuard(boolean guard) {
            isGuard = guard;
        }
    }

    public static class Invincible{

        private final int LEVEL = 99;
        private boolean isInvincible = false;
        private final int MP = 50;

        public Invincible( ) {

        }

        public int getMP() {
            return MP;
        }

        public int getLEVEL() {
            return LEVEL;
        }

        public boolean isInvincible() {
            return isInvincible;
        }

        public void setInvincible(boolean invincible) {
            isInvincible = invincible;
        }
    }
}
