package br.ufsc.ine5605.ShardRPG.Control;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import br.ufsc.ine5605.ShardRPG.Info.Player;
import br.ufsc.ine5605.ShardRPG.Info.PlayerList;

public class JsonDao {

	private final File file = new File("PlayersList.json");
	
	private static JsonDao instance;
	
	public static JsonDao getInstance() throws Exception {
		if (instance == null) {
			instance = new JsonDao();
		}
		return instance;
	}

	public String playerListing() throws IOException {
		String text = "";
		if (checkJsonPlayers()) {
			try {
				final String json = loadJsonContent("PlayersList.json", StandardCharsets.UTF_8);
				final PlayerList playerList = new Gson().fromJson(json, PlayerList.class);
				final Map<String, Player> mapList = playerList.getPlayersList();
				for (final Player player : mapList.values()) {
					if (player != null && player.getPassword() != null) {
						text += "\nKey: " + player.getPassword() + "\nClass: " + player.getType() + "\nRace: "
							+ player.getRace() + "\nProgress: " + player.getProgress() + "\nName: "
							+ player.getName() + "\n\n";
					}
				}
			} catch (final Exception e) {
				return null;
			}
		}
		return text;
	}


	public Player getPlayer(String key) {
		key = key.toUpperCase();
		;
		final Map<String, Player> players = allPlayersMap();
		if (players.containsKey(key)) {
			return players.get(key);
		}
		return null;
	}


	public Map<String, Player> allPlayersMap() {
		try {
			final String json = loadJsonContent("PlayersList.json", StandardCharsets.UTF_8);
			final PlayerList playerList = new Gson().fromJson(json, PlayerList.class);
			final Map<String, Player> mapList = playerList.getPlayersList();
			return mapList;
		} catch (final Exception e) {
			/* GameTextScreen.println(e); */
		}
		return null;
	}


	public Boolean deletePlayer(String key) throws IOException {
		try {
			key = key.toUpperCase();
			if (checkJsonPlayers()) {
				final String json = loadJsonContent("PlayersList.json", StandardCharsets.UTF_8);
				final PlayerList playerList = new Gson().fromJson(json, PlayerList.class);
				final Map<String, Player> mapList = playerList.getPlayersList();
				mapList.remove(key);

				playerList.setPlayersList(mapList);
				persist(playerList, file);
			}
		} catch (final Exception e) {
			return false;
		}
		return true;
	}


	public Boolean changeName(String key, String name) {
		key = key.toUpperCase();
		try {
			if (checkJsonPlayers()) {
				final String json = loadJsonContent("PlayersList.json", StandardCharsets.UTF_8);
				final PlayerList playerList = new Gson().fromJson(json, PlayerList.class);
				final Map<String, Player> mapList = playerList.getPlayersList();

				final Player player = mapList.get(key);
				mapList.remove(key);
				player.setName(name);
				mapList.put(key, player);

				playerList.setPlayersList(mapList);
				persist(playerList, file);
			}
		} catch (final Exception e) {
			return false;
		}
		return true;
	}


	public String registerPlayer(Player player) throws IOException {
		try {
			if (checkJsonPlayers()) {
				registerPlayerInFile(player);
			} else {
				registerFirstPlayer(player);
			}
		} catch (final Exception e) {
			return "\nThe registry request failed. \n";
		}
		return "\nRegistered successfully! \n";
	}


	private Boolean registerFirstPlayer(Player player) throws IOException {

		final Map<String, Player> mapList = new HashMap<>();
		mapList.put(player.getPassword(), player);

		final PlayerList list = new PlayerList(mapList);
		try {
			final File file = new File("PlayersList.json");
			file.createNewFile();
			persist(list, file);
		} catch (final Exception e) {
			return false;
		}
		return true;
	}


	private void persist(final PlayerList list, final File file) throws IOException {
		final FileWriter writer = new FileWriter(file);
		writer.write(new Gson().toJson(list, PlayerList.class));
		writer.flush();
		writer.close();
	}


	private Boolean checkJsonPlayers() {
		final File file = new File("PlayersList.json");
		return (file.exists());
	}


	private Boolean registerPlayerInFile(Player player) throws IOException {
		final String contentJson = loadJsonContent(file.getAbsolutePath(), StandardCharsets.UTF_8);
		final PlayerList list = new Gson().fromJson(contentJson, PlayerList.class);
		final Map<String, Player> mapList = list.getPlayersList();

		mapList.put(player.getPassword(), player);
		list.setPlayersList(mapList);

		persist(list, file);

		return true;
	}


	public void saveGame(Player player) throws IOException {
		deletePlayer(player.getPassword());
		registerPlayerInFile(player);
	}

	public String loadJsonContent(String path, Charset encoding) throws IOException {
		final byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

}
