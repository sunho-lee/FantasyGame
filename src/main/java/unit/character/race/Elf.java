package unit.character.race;

import java.util.Optional;
import unit.Mortal;
import unit.character.Character;
import unit.character.CharacterAbilityInit;
import unit.character.weapon.ElfWeapon;

public class Elf extends Character {

    ElfWeapon weapon;

    public Elf(CharacterAbilityInit init) {
        super(init);
    }

    public Elf(CharacterAbilityInit init, ElfWeapon weapon) {
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
        if (this.weapon==null){
            return 0;
        }
        return super.attack(unit);
    }

    @Override
    public double getAttackSpeed() {
        if (this.weapon != null) {
            return super.getAttackSpeed() +
                    (super.getAttackSpeed() * this.weapon.getAttackSpeed());
        }
        return super.getAttackSpeed();
    }

    public void wearWeapon(ElfWeapon weapon) {
        this.weapon = weapon;
    }

    public Optional<ElfWeapon> getWeapon() {
        return Optional.ofNullable(weapon);
    }
}
