package unit.character;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.support.ReflectionSupport;
import unit.Unit;
import unit.character.Character.Heal;
import unit.monster.Monster;
import unit.monster.MonsterAbilityInit;
import unit.monster.skill.MonsterWithCounter;

class CharacterTest {

    Character character;
    Unit monster;
    int characterBeforeHp;

    @BeforeEach
    void setUp(){
        character = new Character(CharacterAbilityInit.HUMAN);
        monster = new MonsterWithCounter(new Monster(MonsterAbilityInit.GOBLIN));
        characterBeforeHp = character.getHp();
    }

    @Test
    @DisplayName("increaseLevel 메소드 테스트")
    void increaseLevel() {
        Optional<Method> privateIncreaseMethod = ReflectionSupport
                .findMethod(Character.class, "increaseLevel");

        assertEquals(1, character.getLevel());
        privateIncreaseMethod.ifPresent(value -> ReflectionSupport.invokeMethod(value, character));
        assertEquals(2, character.getLevel());
    }

    @Test
    @DisplayName("take Damage 회피 테스트")
    void takeDamageWithDodge(){
        int attackCount = character.getHp() / (monster.getDamage() - character.getArmour());
        for (int i = 0; i < attackCount; i++) {
            monster.attack(character);
        }
        assertNotEquals((characterBeforeHp -
                ((monster.getDamage() - character.getArmour()) * attackCount)), character.getHp());
    }

    @Test
    @DisplayName("heal 성공")
    void heal() {
        int armour = character.getArmour();
        int damage = monster.getDamage();
        monster.attack(character);
        assertEquals(characterBeforeHp - (damage - armour), character.getHp());

        assertTrue(character.heal());

        assertTrue( character.getHp() > characterBeforeHp - (damage - armour));
    }

    @Test
    @DisplayName("마나 부족으로 heal 실패")
    void healMpFailure() {
        Heal heal = new Character.Heal();
        while (character.getMp() >= heal.getMp()){
            character.takeDamage(monster);
            character.heal();
        }

        assertEquals(character.getMaxHp(), character.getHp());

        character.takeDamage(monster);

        assertFalse(character.heal());
    }

    @Test
    @DisplayName("체력이 이미 가득 차있을 경우 heal 실패")
    void healAlreadyFullHpFailure() {
        assertEquals(character.getHp(), characterBeforeHp);
        assertFalse(character.heal());
    }

    @Test
    @DisplayName("steam 성공")
    void steam() {
        double attackSpeed = character.getAttackSpeed();
        double steam = new Character.Steam().getAttackSpeed();

        assertTrue(character.steam());
        assertEquals(attackSpeed + (steam * attackSpeed), character.getAttackSpeed());
    }

    @Test
    @DisplayName("steam 마나 부족으로 실패")
    void steamMpFailure() {
        int steamMp = new Character.Steam().getMp();
        double speed = character.getAttackSpeed();

        while(character.getMp() >= steamMp){
            character.takeDamage(monster);
            character.heal();
        }

        assertFalse(character.steam());
        assertEquals(speed, character.getAttackSpeed());
    }

    @Test
    @DisplayName("steam 증첩 시 실패")
    void multiSteamFailure() {
        double beforeSpeed = character.getAttackSpeed();
        double steam = new Character.Steam().getAttackSpeed();
        assertTrue(character.steam());

        assertFalse(character.steam());

        assertEquals(beforeSpeed + (beforeSpeed *  steam), character.getAttackSpeed());
    }



}