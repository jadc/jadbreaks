package me.jadc.jadbreaks.objects;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.jadc.jadbreaks.jb;

public class ConfigFile {
	private File customConfigFile;
	private FileConfiguration customConfig;
	
	public ConfigFile(String fileName) {
		customConfigFile = new File(jb.getInstance().getDataFolder(), fileName + ".yml");
		if(!customConfigFile.exists()) {
			customConfigFile.getParentFile().mkdirs();
			jb.getInstance().saveResource(fileName + ".yml", false);
		}
		
		customConfig = new YamlConfiguration();
		try {
			customConfig.load(customConfigFile);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public FileConfiguration getConfig() {
		return this.customConfig;
	}
	
	public void save() {
		try {
			customConfig.save(customConfigFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
