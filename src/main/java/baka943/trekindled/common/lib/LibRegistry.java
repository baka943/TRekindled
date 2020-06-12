package baka943.trekindled.common.lib;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import slimeknights.mantle.client.CreativeTab;
import teamroots.embers.RegistryManager;

import java.util.Collection;
import java.util.Set;

public class LibRegistry {

	private LibRegistry() {}

	public static CreativeTab tabStamp = new CreativeTab("TinkerStamp", new ItemStack(RegistryManager.stamp_flat));

	private static final Set<Item> stampItems = Sets.newHashSet();

	public static void addStampForItem(Item item) {
		stampItems.add(item);
	}

	public static Collection<Item> getStampItems() {
		return ImmutableList.copyOf(stampItems);
	}

}
