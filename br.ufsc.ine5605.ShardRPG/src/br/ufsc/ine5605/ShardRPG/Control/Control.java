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




public class Control {

	public static void main(String[] args) throws IOException {
        final RegisterControl registerControl = new RegisterControl();
        registerControl.registerNewPlayer();
	}


	public String playerListing() throws IOException {
		String text = "";
		if (checkJsonPlayers()) {
			final String json = getJasonContent("PlayersList.json", StandardCharsets.UTF_8);
			final PlayerList playerList = new Gson().fromJson(json, PlayerList.class);
			final Map<String, Player> mapList = playerList.getPlayersList();
			for (final Player player : mapList.values()) {
				if (player != null) {
					text += "Name: "
						+ player.getName() + "\nTipo: " + player.getType() + "\nRace: " + player.getRace()
						+ "\nProgresso: " + player.getProgress() + "\nChave: " + player.getPassword() + "\n\n";
				}
			}
		}
		return text;
	}
	
	public Map<String, Player> allPlayers() throws IOException{
		final String json = getJasonContent("PlayersList.json", StandardCharsets.UTF_8);
		final PlayerList playerList = new Gson().fromJson(json, PlayerList.class);
		Map<String, Player> mapList = playerList.getPlayersList();
		return mapList;
	}


	public Boolean deletePlayer(String key) throws IOException {
		try {
			if (checkJsonPlayers()) {
				final File path = new File("PlayersList.json");
				final String json = getJasonContent("PlayersList.json", StandardCharsets.UTF_8);
				final PlayerList playerList = new Gson().fromJson(json, PlayerList.class);
				final Map<String, Player> mapList = playerList.getPlayersList();
				mapList.remove(key);

				playerList.setPlayersList(mapList);
				setJsonInFile(playerList, path);
			}
		} catch (final Exception e) {
			return false;
		}
		return true;
	}


	public Boolean changeName(String key, String name) {
		try {
			if (checkJsonPlayers()) {
				final File path = new File("PlayersList.json");
				final String json = getJasonContent("PlayersList.json", StandardCharsets.UTF_8);
				final PlayerList playerList = new Gson().fromJson(json, PlayerList.class);
				final Map<String, Player> mapList = playerList.getPlayersList();

				final Player player = mapList.get(key);
				mapList.remove(key);
				player.setName(name);
				mapList.put(key, player);

				playerList.setPlayersList(mapList);
				setJsonInFile(playerList, path);
			}
		} catch (final Exception e) {
			return false;
		}
		return true;
	}


	public String registerPlayer(Player player) throws IOException {
		try {
			if (checkJsonPlayers()) {
				registerExistingPlayer(player);
			} else {
				registerFirstPlayer(player);
			}
		} catch (final Exception e) {
			return "Não foi possivel registrar o jogador!";
		}
		return "Registro feito com sucesso!";
	}


	private Boolean registerFirstPlayer(Player player) throws IOException {

		final Map<String, Player> mapList = new HashMap<>();
		mapList.put(player.getPassword(), player);

		final PlayerList list = new PlayerList(mapList);
		try {
			final File file = new File("PlayersList.json");
			file.createNewFile();
			setJsonInFile(list, file);
		} catch (final Exception e) {
			return false;
		}
		return true;
	}


	private void setJsonInFile(final PlayerList list, final File file) throws IOException {
		final FileWriter writer = new FileWriter(file);
		writer.write(new Gson().toJson(list));
		writer.flush();
		writer.close();
	}


	private Boolean checkJsonPlayers() {
		final File file = new File("PlayersList.json");
		return (file.exists());
	}


	private Boolean registerExistingPlayer(Player player) throws IOException {
		final File file = new File("PlayersList.json");
		final String contentJson = getJasonContent(file.getAbsolutePath(), StandardCharsets.UTF_8);
		final PlayerList list = new Gson().fromJson(contentJson, PlayerList.class);
		final Map<String, Player> mapList = list.getPlayersList();

		mapList.put(player.getPassword(), player);
		list.setPlayersList(mapList);

		setJsonInFile(list, file);

		return true;
	}


	static String getJasonContent(String path, Charset encoding) throws IOException {
		final byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

}
