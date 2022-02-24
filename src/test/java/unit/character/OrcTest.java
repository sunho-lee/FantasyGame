package unit.character;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import unit.character.race.Orc;
import unit.character.weapon.OrcWeapon;
import unit.monster.Monster;
import unit.monster.MonsterAbilityInit;

public class OrcTest {

    @Test
    @DisplayName("무기와 함께 오크를 생성한 후 대미지 상승, 공격속도 하락 테스트")
    void initOrcWithWeapon(){
        int orcDamage = CharacterAbilityInit.ORC.getDamage();
        double orcAttackSpeed = CharacterAbilityInit.ORC.getAttackSpeed();
        double weaponDamage = OrcWeapon.SHORT_AXE.getDamage();
        double weaponAttackSpeed = OrcWeapon.SHORT_AXE.getAttackSpeed();

        Orc orc = new Orc(CharacterAbilityInit.ORC, OrcWeapon.SHORT_AXE);

        assertEquals(Math.round(orcDamage + (orcDamage * weaponDamage)), orc.getDamage());
        assertEquals(orcAttackSpeed + (orcAttackSpeed * weaponAttackSpeed),
                orc.getAttackSpeed());
    }

    @Test
    @DisplayName("오크가 무기를 착용하지 않는 경우 공격 실패")
    void attackWithoutWeaponFailure(){
        Orc orc = new Orc(CharacterAbilityInit.ORC);
        Monster monster = new Monster(MonsterAbilityInit.GOBLIN);

        assertEquals(0,orc.attack(monster));

        orc.wearWeapon(OrcWeapon.SHORT_AXE);
        assertNotEquals(0, orc.attack(monster));
    }

    @Test
    @DisplayName("오크 생성 후 무기를 착용하고 변경하는 wearWeapon 메소드 테스트")
    void wearWeapon() {
        int orcDamage = CharacterAbilityInit.ORC.getDamage();
        double orcAttackSpeed = CharacterAbilityInit.ORC.getAttackSpeed();

        double shortAxeDamage = OrcWeapon.SHORT_AXE.getDamage();
        double shortAxeAttackSpeed = OrcWeapon.SHORT_AXE.getAttackSpeed();

        double ironHammerDamage = OrcWeapon.IRON_HAMMER.getDamage();
        double ironHammerAttackSpeed = OrcWeapon.IRON_HAMMER.getAttackSpeed();

        Orc orc = new Orc(CharacterAbilityInit.ORC);
        assertEquals(orcDamage, orc.getDamage());
        assertEquals(orcAttackSpeed, orc.getAttackSpeed());

        orc.wearWeapon(OrcWeapon.SHORT_AXE);
        assertEquals(Math.round(orcDamage + (orcDamage * shortAxeDamage)), orc.getDamage());
        assertEquals(orcAttackSpeed + orcAttackSpeed * shortAxeAttackSpeed, orc.getAttackSpeed());

        orc.wearWeapon(OrcWeapon.IRON_HAMMER);
        assertEquals(Math.round(orcDamage + (orcDamage * ironHammerDamage)), orc.getDamage());
        assertEquals(orcAttackSpeed + (orcAttackSpeed * ironHammerAttackSpeed), orc.getAttackSpeed());
    }
}
