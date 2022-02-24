package unit.monster;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MonsterAbilityInitTest {

    @Test
    @DisplayName("고블린 몬스터 능력치 초기화 테스트")
    void GoblinAbilityTest(){
        MonsterAbilityInit goblin = MonsterAbilityInit.GOBLIN;

        assertEquals(1,goblin.getLevel());
        assertEquals(300,goblin.getHp());
        assertEquals(10,goblin.getDamage());
        assertEquals(1,goblin.getAttackSpeed());
        assertEquals(1,goblin.getArmour());
        assertEquals(0,goblin.getMp());
    }

    @Test
    @DisplayName("트롤 몬스터 능력치 초기화 테스트")
    void TrollAbilityTest(){
        MonsterAbilityInit troll = MonsterAbilityInit.TROLL;

        assertEquals(5,troll.getLevel());
        assertEquals(400,troll.getHp());
        assertEquals(20,troll.getDamage());
        assertEquals(1.2,troll.getAttackSpeed());
        assertEquals(5,troll.getArmour());
        assertEquals(50,troll.getMp());
    }

    @Test
    @DisplayName("오우거 몬스터 능력치 초기화 테스트")
    void OgreAbilityTest(){
        MonsterAbilityInit ogre = MonsterAbilityInit.OGRE;

        assertEquals(10,ogre.getLevel());
        assertEquals(500,ogre.getHp());
        assertEquals(30,ogre.getDamage());
        assertEquals(1.5,ogre.getAttackSpeed());
        assertEquals(10,ogre.getArmour());
        assertEquals(100,ogre.getMp());
    }


}