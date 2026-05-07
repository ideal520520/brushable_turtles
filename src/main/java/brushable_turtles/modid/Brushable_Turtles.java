package brushable_turtles.modid;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Brushable_Turtles implements ModInitializer {
	public static final String MOD_ID = "brushable_turtles";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Brushable Turtles 已加载 - 现在刷海龟可以掉落鳞甲了！");
	}
}
