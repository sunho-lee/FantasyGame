package unit.character;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import unit.character.race.Elf;
import unit.character.weapon.ElfWeapon;
import unit.monster.Monster;
import unit.monster.MonsterAbilityInit;

class ElfTest {

    @Test
    @DisplayName("엘프가 무기와 함께 객체를 생성한 후 공격 속도 상승 테스트")
    void initElfWithWeapon() {
        double elfAttackSpeed = CharacterAbilityInit.ELF.getAttackSpeed();
        double shortBowAttackSpeed = ElfWeapon.SHORT_BOW.getAttackSpeed();

        Elf elf = new Elf(CharacterAbilityInit.ELF, ElfWeapon.SHORT_BOW);

        assertEquals(elfAttackSpeed + (elfAttackSpeed * shortBowAttackSpeed),
                elf.getAttackSpeed());
    }

    @Test
    @DisplayName("엘프가 무기를 착용하지 않는 경우 공격 실패")
    void attackWithoutWeaponFailure(){
        Elf elf = new Elf(CharacterAbilityInit.ELF);
        Monster monster = new Monster(MonsterAbilityInit.GOBLIN);

        assertEquals(0,elf.attack(monster));

        elf.wearWeapon(ElfWeapon.SHORT_BOW);
        assertNotEquals(0, elf.attack(monster));
    }


    @Test
    @DisplayName("엘프 생성 후 무기를 착용하고 변경하는 wearWeapon 메소드 테스트")
    void wearWeapon() {
        double elfAttackSpeed = CharacterAbilityInit.ELF.getAttackSpeed();
        double shortBowAttackSpeed = ElfWeapon.SHORT_BOW.getAttackSpeed();
        double ironBowAttackSpeed = ElfWeapon.IRON_BOW.getAttackSpeed();

        Elf elf = new Elf(CharacterAbilityInit.ELF);
        assertEquals(elfAttackSpeed, elf.getAttackSpeed());

        elf.wearWeapon(ElfWeapon.SHORT_BOW);
        assertEquals(elfAttackSpeed + (elfAttackSpeed * shortBowAttackSpeed), elf.getAttackSpeed());

        elf.wearWeapon(ElfWeapon.IRON_BOW);
        assertEquals(elfAttackSpeed + (elfAttackSpeed * ironBowAttackSpeed), elf.getAttackSpeed());
    }


}