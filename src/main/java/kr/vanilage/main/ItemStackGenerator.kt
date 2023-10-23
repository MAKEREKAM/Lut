package kr.vanilage.main

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta

class ItemStackGenerator {
    companion object {
        fun generate(type : Material, name : Component) : ItemStack {
            val stack = ItemStack(type, 1)
            val meta = stack.itemMeta
            meta.displayName(name)
            stack.itemMeta = meta
            return stack
        }

        fun generate(p : Player) : ItemStack {
            val stack = generate(Material.PLAYER_HEAD, Component.text(p.name + "의 머리", NamedTextColor.RED))
            val meta = stack.itemMeta as SkullMeta
            meta.owningPlayer = p
            stack.itemMeta = meta
            return stack
        }
    }
}