package kr.vanilage.main

import io.github.monun.kommand.getValue
import io.github.monun.kommand.kommand
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerChatEvent
import org.bukkit.inventory.Inventory
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin(), Listener {
    private val map : HashMap<Int, Inventory> = HashMap()
    private var nowId = 0
    private lateinit var config : FileConfiguration

    override fun onEnable() {
        this.saveDefaultConfig()
        config = this.getConfig()
        kommand {
            register("inv") {
                requires { isPlayer }
                then("id" to int()) {
                    executes {
                        val id : Int by it
                        if (map.containsKey(id)) {
                            player.openInventory(map[id]!!)
                        }
                    }
                }
            }

            register("lut") {
                requires { isOp }
                then("reload") {
                    executes {
                        this@Main.reloadConfig()
                        this@Main.saveConfig()
                        config = this@Main.getConfig()
                    }
                }
            }
        }

        Bukkit.getPluginManager().registerEvents(this, this)
    }

    @EventHandler
    fun onChat(e : PlayerChatEvent) {
        if (e.message.uppercase() == "[INV]") {
            map[nowId] = InventoryGenerator(e.player).inventory
            Bukkit.broadcast(Component.text(config.getString("message")!!.replace("{player}", e.player.name).replace("{id}", nowId.toString())))
            nowId++
            e.isCancelled = true
        }

        if (e.message.uppercase() == "[ENDER]") {
            map[nowId] = EnderChestGenerator(e.player).inventory
            Bukkit.broadcast(Component.text(config.getString("message")!!.replace("{player}", e.player.name).replace("{id}", nowId.toString())))
            nowId++
            e.isCancelled = true
        }
    }

    @EventHandler
    fun onClick(e : InventoryClickEvent) {
        if (e.view.title.contains("§c플레이어의 인벤토리")) {
            e.isCancelled = true
        }
        if (e.view.title.contains("§c플레이어의 엔더 상자")) {
            e.isCancelled = true
        }
    }
}