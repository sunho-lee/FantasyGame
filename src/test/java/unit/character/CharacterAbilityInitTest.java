package unit.character;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CharacterAbilityInitTest {

    @Test
    @DisplayName("휴먼 캐릭터 능력치 초기화 테스트")
    void HumanAbilityTest(){
        CharacterAbilityInit human = CharacterAbilityInit.HUMAN;

        assertEquals(1, human.getLevel());
        assertEquals(500, human.getHp());
        assertEquals(10, human.getDamage());
        assertEquals(1, human.getAttackSpeed());
        assertEquals(5, human.getArmour());
        assertEquals(100, human.getMp());
        assertEquals(10, human.getDodgePercent());
    }

    @Test
    @DisplayName("엘프 캐릭터 능력치 초기화 테스트")
    void ElfAbilityTest(){
        CharacterAbilityInit elf = CharacterAbilityInit.ELF;

        assertEquals(1, elf.getLevel());
        assertEquals(500,elf.getHp());
        assertEquals(15,elf.getDamage());
        assertEquals(1.2,elf.getAttackSpeed());
        assertEquals(1,elf.getArmour());
        assertEquals(100,elf.getMp());
        assertEquals(0.07,elf.getDodgePercent());
    }

    @Test
    @DisplayName("오크 캐릭터 능력치 초기화 테스트")
    void OrcAbilityTest(){
        CharacterAbilityInit orc = CharacterAbilityInit.ORC;

        assertEquals(1, orc.getLevel());
        assertEquals(500, orc.getHp());
        assertEquals(15, orc.getDamage());
        assertEquals(1, orc.getAttackSpeed());
        assertEquals(5, orc.getArmour());
        assertEquals(100, orc.getMp());
        assertEquals(0.03, orc.getDodgePercent());
    }
}