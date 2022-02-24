package unit.monster;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.support.ReflectionSupport;
import unit.Unit;
import unit.character.Character;
import unit.character.CharacterAbilityInit;
import unit.monster.skill.MonsterWithCounter;

class MonsterWithCounterTest {

    @Test
    @DisplayName("피격 당할 시 확률에 따라 카운터 어택을 성공")
    void takeDamageWithCounterAttack() {
        Unit character = ReflectionSupport.newInstance(Character.class, CharacterAbilityInit.HUMAN);
        Unit monster = new MonsterWithCounter(new Monster(MonsterAbilityInit.GOBLIN));
        int BeforeHp = character.getHp();

        while (character.getHp() >= BeforeHp){
            character.attack(monster);
        }

        int counterDamage = (int) ( (double) monster.getDamage() * 70.0 / 100.0);
        assertEquals(BeforeHp - (counterDamage - character.getArmour()), character.getHp());
    }

    @Test
    @DisplayName("isCounter 확률 테스트")
    void isCounter(){
        Unit monster = new MonsterWithCounter(new Monster(MonsterAbilityInit.GOBLIN));
        Optional<Method> privateDieMethod = ReflectionSupport.findMethod(MonsterWithCounter.class,
                "isCounter");

        if (privateDieMethod.isPresent()){
            int count = 1000000;
            int trueResult = 0;
            int falseResult = 0;
            while (count > 0) {
                Boolean result = (Boolean) ReflectionSupport
                        .invokeMethod(privateDieMethod.get(), monster);
                if (result){
                    trueResult++;
                }else {
                    falseResult++;
                }
                count--;
            }

            System.out.println("반격 성공 수 : " + trueResult);
            System.out.println("반격 실패 수 : " + falseResult);

        }
    }
}