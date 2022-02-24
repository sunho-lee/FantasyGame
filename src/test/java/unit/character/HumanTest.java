package unit.character;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import unit.character.race.Human;
import unit.character.weapon.HumanWeapon;
import unit.monster.Monster;
import unit.monster.MonsterAbilityInit;

class HumanTest {

    Human human;
    Monster monster;

    @BeforeEach
    void setUp(){
        human = new Human(CharacterAbilityInit.HUMAN);
        monster = new Monster(MonsterAbilityInit.GOBLIN);
    }

    @Test
    @DisplayName("무기와 함께 휴먼을 생성한 후 대미지 상승 테스트")
    void initHumanWithWeapon(){
        int humanDamage = CharacterAbilityInit.HUMAN.getDamage();
        double weaponAbility = HumanWeapon.LONG_SWORD.getDamage();

        Human human = new Human(CharacterAbilityInit.HUMAN, HumanWeapon.LONG_SWORD);

        assertEquals(humanDamage + (humanDamage * weaponAbility), human.getDamage());
    }

    @Test
    @DisplayName("휴먼이 무기를 착용하지 않는 경우 공격 실패")
    void attackWithoutWeaponFailure(){
        human = new Human(CharacterAbilityInit.HUMAN);
        Monster monster = new Monster(MonsterAbilityInit.GOBLIN);

        assertEquals(0,human.attack(monster));

        human.wearWeapon(HumanWeapon.SHORT_SWORD);
        assertNotEquals(0, human.attack(monster));
    }

    @Test
    @DisplayName("휴먼 생성 후 무기를 착용하고 변경하는 wearWeapon 메소드 테스트")
    void wearWeapon() {
        int humanDamage = CharacterAbilityInit.HUMAN.getDamage();
        double shortSwordWAbility = HumanWeapon.SHORT_SWORD.getDamage();
        double longSwordAbility = HumanWeapon.LONG_SWORD.getDamage();

        human = new Human(CharacterAbilityInit.HUMAN);
        assertEquals(humanDamage, human.getDamage());

        human.wearWeapon(HumanWeapon.SHORT_SWORD);
        assertEquals(Math.round(humanDamage + (humanDamage * shortSwordWAbility)), human.getDamage());

        human.wearWeapon(HumanWeapon.LONG_SWORD);
        assertEquals(Math.round(humanDamage + (humanDamage * longSwordAbility)), human.getDamage());
    }

    @Test
    @DisplayName("Guard 스킬 성공")
    void guard(){
        int beforeArmour = human.getArmour();
        double guardIncrease = new Human.Guard().getGUARD_INCREASE();

        human.guard();

        assertEquals(Math.round(beforeArmour + (beforeArmour * guardIncrease)), human.getArmour());
    }

    @Test
    @DisplayName("Guard 마나 부족으로 실패")
    void guardNoMpFailure (){
        int guardMp = new Human.Guard().getGUARD_MP();
        int humanGuard = human.getArmour();

        assertEquals(humanGuard, human.getArmour());

        while(human.getMp() >= guardMp){
            human.takeDamage(monster);
            human.heal();
        }

        assertFalse(human.guard());

        assertEquals(humanGuard, human.getArmour());
    }

    @Test
    @DisplayName("Guard 중첩 사용 시 사용 살패")
    void MultipleGuardFailure(){
        double guardIncrease = new Human.Guard().getGUARD_INCREASE();
        int humanGuard = human.getArmour();
        long afterGuard = Math.round(humanGuard + (humanGuard * guardIncrease));

        assertTrue(human.guard());
        assertEquals(afterGuard, human.getArmour());

        assertFalse(human.guard());
        assertEquals(afterGuard, human.getArmour());
    }

    @Test
    @DisplayName("invincible 사용 성공")
    void invincible(){
        int damage = 10;
        assertEquals(damage - human.getArmour(), human.takeDamage(damage));

        assertTrue(human.invincible());
        assertEquals(0, human.takeDamage(damage));
    }

    @Test
    @DisplayName("invincible 마나 부족으로 실패")
    void invincibleMpFailure(){
        int mp = new Human.Invincible().getMP();

        while(human.getMp() >= mp){
            human.takeDamage(monster);
            human.heal();
        }

        assertNotEquals(0, human.takeDamage(10));
    }

    @Test
    @DisplayName("invincible 중첩 사용 실패")
    void multipleInvincibleFailure(){
        int damage = 10;
        assertEquals(damage - human.getArmour(), human.takeDamage(damage));

        assertTrue(human.invincible());
        assertEquals(0, human.takeDamage(damage));

        assertFalse(human.invincible());
    }

    @Test
    @DisplayName("invincible 10초 후 해제")
    void invincibleTimeLimit(){
        int damage = 10;
        assertEquals(damage - human.getArmour(), human.takeDamage(damage));

        assertTrue(human.invincible());
        assertEquals(0, human.takeDamage(damage));

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(0, human.takeDamage(damage));

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(damage - human.getArmour(), human.takeDamage(damage));
    }

}