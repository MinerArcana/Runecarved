package com.minerarcana.runecarved.api.runestack;

import com.minerarcana.runecarved.RunecarvedContent;
import com.minerarcana.runecarved.api.spell.ISpellItem;
import com.minerarcana.runecarved.api.spell.Spell;
import com.minerarcana.runecarved.item.ItemRunestone;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Optional;

public class RuneStack {
    private final Item itemRunestone = Items.AIR;
    private int size;
    private final Spell spell;

    public RuneStack(Spell spell, int size) {
        this.spell = spell;
        this.size = size >= 0 ? size : 0;
    }

    public int getSize() {
        return size;
    }

    public Spell getSpell() {
        return spell;
    }

    public void decreaseSize(int amount) {
        this.size -= amount;
    }

    public void increaseSize(int amount) {
        this.size += amount;
    }

    public ItemStack combineStack(ItemStack itemStack) {
        return convertToRuneStack(itemStack)
                .map(this::combineStack)
                .map(RuneStack::pullItemStack)
                .orElse(itemStack);
    }

    public RuneStack combineStack(RuneStack runeStack) {
        if (runeStack.getSpell() == this.getSpell()) {
            int amountAccepted = Math.max(runeStack.getSize(), Integer.MAX_VALUE - this.getSize());
            runeStack.decreaseSize(amountAccepted);
            this.increaseSize(amountAccepted);
        }
        return runeStack;
    }

    public ItemStack pullItemStack() {
        return pullItemStack(64);
    }

    public ItemStack pullItemStack(int requestedSize) {
        int amountPulled = Math.min(requestedSize, this.getSize());
        this.decreaseSize(amountPulled);
        return ItemRunestone.getRunestoneItemStackForSpell(this.getSpell(), amountPulled);
    }

    public static Optional<RuneStack> convertToRuneStack(ItemStack itemStack) {
        if (!itemStack.isEmpty()) {
            if (itemStack.getItem() instanceof ISpellItem) {
                return Optional.of(new RuneStack(((ISpellItem) itemStack.getItem()).getSpell(), itemStack.getCount()));
            }
        }
        return Optional.empty();
    }
}
