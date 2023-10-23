package kr.vanilage.main

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder

class EnderChestGenerator(p: Player) : InventoryHolder {
    private val inv : Inventory = Bukkit.createInventory(this,  27, "§c플레이어의 엔더 상자")

    init {
        for (i in 0..26) {
            inv.setItem(i, p.enderChest.getItem(i))
        }
    }

    override fun getInventory(): Inventory {
        return inv
    }
}