package unit;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.support.ReflectionSupport;

class UnitTest {

    Unit firstUnit;
    Unit secondUnit;

    Optional<Method> privateDieMethod;


    @BeforeEach
    void setUp() {
        firstUnit = ReflectionSupport.newInstance(Unit.class, 100, 10, 1);
        secondUnit = ReflectionSupport.newInstance(Unit.class,  100, 10, 1);
        privateDieMethod = ReflectionSupport.findMethod(Unit.class, "die");
    }

    @Test
    @DisplayName("두 유닛 모두 살아 있을 경우 기본 공격이 성공")
    void attack() {
        assertTrue(firstUnit.isAlive());
        assertTrue(secondUnit.isAlive());
        assertEquals(100,secondUnit.getHp());

        int damage = firstUnit.attack(secondUnit);

        assertEquals(9, damage);
        assertEquals(91,secondUnit.getHp());
    }

    @Test
    @DisplayName("공격받는 유닛이 죽어있는 경우 기본 공격 실패")
    void characterIsNotAliveAttackToOtherFailure() {
        assertTrue(firstUnit.isAlive());
        privateDieMethod.ifPresent(value -> ReflectionSupport.invokeMethod(value, secondUnit));
        assertFalse(secondUnit.isAlive());

        int damage = firstUnit.attack(secondUnit);

        assertEquals(0, damage);
    }

    @Test
    @DisplayName("공격하는 유닛이 죽은 상태일 경우 기본 공격 실패")
    void attackToMonsterIsNotAliveFailure() {
        assertTrue(secondUnit.isAlive());
        privateDieMethod.ifPresent(value -> ReflectionSupport.invokeMethod(value, firstUnit));
        assertFalse(firstUnit.isAlive());

        int damage = firstUnit.attack(secondUnit);

        assertEquals(0, damage);
    }

    @Test
    @DisplayName("두 유닛 모두 죽어있는 상태일 경우 기본 공격 실패")
    void attackToMonsterIsNotAliveAllFailure() {
        privateDieMethod.ifPresent(value -> ReflectionSupport.invokeMethod(value, firstUnit));
        privateDieMethod.ifPresent(value -> ReflectionSupport.invokeMethod(value, secondUnit));
        assertFalse(firstUnit.isAlive());
        assertFalse(secondUnit.isAlive());

        int damage1 = firstUnit.attack(secondUnit);
        int damage2 = secondUnit.attack(firstUnit);

        assertEquals(0, damage1);
        assertEquals(0, damage2);
    }

    @Test
    @DisplayName("자기 자신을 공격하는 경우 공격 실패")
    void attackMyselfFailure(){
        int damage = firstUnit.attack(firstUnit);

        assertEquals(0, damage);
    }

    @Test
    @DisplayName("피격당한 유닛이 살아있어 피격이 성공")
    void takeDamageSuccess() {
        assertTrue(firstUnit.isAlive());
        int damage = 10;

        int finalDamage = firstUnit.takeDamage(secondUnit.getDamage());

        assertEquals( damage - firstUnit.getArmour(), finalDamage);
    }

    @Test
    @DisplayName("피격을 당한 경우 이미 유닛이 죽은 경우 실패")
    void takeDamagedUnitDieFailure() {
        privateDieMethod.ifPresent(value -> ReflectionSupport.invokeMethod(value, firstUnit));
        assertFalse(firstUnit.isAlive());

        int finalDamage = firstUnit.takeDamage(secondUnit.getDamage());

        assertEquals( 0, finalDamage);
    }

    @Test
    @DisplayName("isAlive와 die 메소드 성공")
    void isAliveAndIsDieSuccess() {
        assertTrue(firstUnit.isAlive());
        privateDieMethod.ifPresent(value -> ReflectionSupport.invokeMethod(value, firstUnit));
        assertFalse(firstUnit.isAlive());
    }

}