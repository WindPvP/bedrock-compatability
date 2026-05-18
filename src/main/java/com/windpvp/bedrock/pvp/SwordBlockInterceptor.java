package com.windpvp.bedrock.pvp;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * One notable bug for Bedrock players is that sword blocking gets toggled on
 * when they right-click with a sword, and it doesn't turn off until they
 * right-click again. Bedrock lacks the eating / blocking animation, which 
 * leads to them being stuck in the sword blocking state without any visual indication.
 * 
 * To prevent this "freezing," we simply prevent the player from using sword blocking completely
 */
public class SwordBlockInterceptor {

	public static void register(Plugin plugin) {
		com.comphenix.protocol.ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(plugin,
				ListenerPriority.NORMAL, PacketType.Play.Client.USE_ITEM, PacketType.Play.Client.USE_ITEM_ON) {
			@Override
			public void onPacketReceiving(PacketEvent event) {
				Player player = event.getPlayer();

				if (player.getName().startsWith(".")) {

					Material itemInHand = player.getItemInHand().getType();
					if (itemInHand.name().contains("SWORD")) {
						// Cancel the packet so the server never puts the player in the
						// "eating/blocking" state
						event.setCancelled(true);
					}
				}
			}
		});
	}
}