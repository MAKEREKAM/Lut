package kr.vanilage.main

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder

class InventoryGenerator(p: Player) : InventoryHolder {
    private val inv : Inventory = Bukkit.createInventory(this,  54, "§c플레이어의 인벤토리")

    init {
        inv.setItem(1, ItemStackGenerator.generate(p))
        inv.setItem(3, p.inventory.helmet)
        inv.setItem(4, p.inventory.chestplate)
        inv.setItem(5, p.inventory.leggings)
        inv.setItem(6, p.inventory.boots)
        inv.setItem(7, p.inventory.itemInOffHand)
        for (i in 18..53) {
            inv.setItem(i, p.inventory.getItem(i - 18))
        }
    }

    override fun getInventory(): Inventory {
        return inv
    }
}