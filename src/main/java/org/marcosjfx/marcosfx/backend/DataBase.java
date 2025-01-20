package org.marcosjfx.marcosfx.backend;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * La clase {@code DataBase} gestiona datos relacionados con jugadores y equipos de fútbol
 * almacenados en archivos CSV y JSON. Proporciona métodos para recuperar, actualizar y
 * modificar la información de jugadores y equipos.
 */
public class DataBase {

    /**
     * Ruta al archivo CSV donde se almacena la información de los jugadores.
     */
    private final Path csvPath = Paths.get("src/main/resources/database/players.csv");

    /**
     * Ruta al archivo JSON donde se almacena la información del equipo.
     */
    private final Path jsonPath = Paths.get("src/main/resources/database/team.json");

    /**
     * Constructor de la clase {@code DataBase}.
     * Inicializa las rutas de acceso a los archivos CSV y JSON que contienen
     * la información de jugadores y equipos.
     */
    public DataBase() {
    }

    /**
     * Recupera la información de los jugadores desde un archivo CSV y la almacena en un mapa.
     * También actualizo las estadísticas de los jugadores, con el fin de que sean un poco más
     * dinámicas y haya un cambio en la aplicación que se haga cada vez que abras el mercado y que se guarde aunque
     * cierres la aplicación
     * @return un mapa que asocia los ids de los jugadores con objetos {@link Player}.
     */
    public HashMap<Integer, Player> getInformation() {

        updatePlayerStats();

        HashMap<Integer, Player> players = new HashMap<>();

        try {

            boolean header = true;
            List<String> lines = Files.readAllLines(csvPath);
            for (String line : lines) {

                if (header) {
                    header = false;
                    continue;
                }

                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                int rating = Integer.parseInt(data[2]);
                int marketValue = Integer.parseInt(data[3]);
                int age = Integer.parseInt(data[4]);
                int teamID = Integer.parseInt(data[5]);
                String clubName = data[6];
                String position = data[7];
                int goals = Integer.parseInt(data[8]);
                int assists = Integer.parseInt(data[9]);
                int saves = Integer.parseInt(data[10]);
                String playerImageURL = data[11];
                String clubImageURL = data[12];

                players.put(id, new Player(id, name, rating, marketValue, age, teamID, goals, assists, saves, clubName, position, playerImageURL, clubImageURL));

            }
        } catch (IOException e) {
            System.out.printf("Error sacando la informacion %s\n", e.getMessage());
        }

        return players;
    }


    /**
     * Actualizo las estadísticas de los jugadores en el archivo CSV aplicando cambios aleatorios
     * al valor de mercado, goles, asistencias o atajadas, dependiendo de la posición del jugador.
     */
    private void updatePlayerStats() {
        try {
            List<String> lines = Files.readAllLines(csvPath);

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if (i == 0) continue;

                String[] data = line.split(",");

                int marketValue = Integer.parseInt(data[3]);
                int goals = Integer.parseInt(data[8]);
                int assists = Integer.parseInt(data[9]);
                int saves = Integer.parseInt(data[10]);
                String position = data[7];

                int randomNumber = generateRandom();

                if(randomNumber == 0) data[3] = String.valueOf(marketValue -5000);
                else data[3] = String.valueOf(marketValue + 5000);

                if(position.equals("GK"))
                {
                    data[10] = String.valueOf(saves + randomNumber*2);
                }
                else
                {
                    data[8] = String.valueOf(goals + randomNumber);
                    data[9] = String.valueOf(assists + randomNumber * generateRandom());
                }

                lines.set(i, String.join(",", data));
            }

            Files.write(csvPath, lines);

        } catch (IOException e) {
            System.out.printf("Error updating player stats: %s\n", e.getMessage());
        }
    }

    /**
     * Genera un número aleatorio entre 0 y 3 con probabilidades definidas:
     * 0 (40%), 1 (30%), 2 (20%) y 3 (10%).
     *
     * @return un entero aleatorio entre 0 y 3.
     */
    private int generateRandom() {
        double random = Math.random();
        if (random < 0.4) return 0;
        else if (random < 0.7) return 1;
        else if (random < 0.9) return 2;
        else return 3;
    }


    /**
     * Añade un jugador al equipo almacenado en un archivo JSON si el presupuesto lo permite.
     *
     * @param player el jugador que se desea añadir.
     * @return 1 si el jugador fue añadido exitosamente, 0 si el presupuesto es insuficiente, o -1 si ocurre un error.
     */
    public int addPlayerToTeam(Player player) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> teamData = objectMapper.readValue(Files.readString(jsonPath), Map.class);
            List<Map<String, Object>> team = (List<Map<String, Object>>) teamData.get("team");

            int budget = (int) teamData.get("budget");
            budget -= player.getMarketValue();

            if (budget < 0) return 0;

            teamData.put("budget", budget);

            Map<String, Object> newPlayer = new HashMap<>();
            newPlayer.put("ID", player.getId());
            newPlayer.put("Name", player.getName());
            newPlayer.put("ClubName", player.getClubName());
            newPlayer.put("Position", player.getPosition());
            newPlayer.put("PlayerImageURL", player.getPlayerImageURL());
            newPlayer.put("ClubImageURL", player.getClubImageURL());

            team.add(newPlayer);
            objectMapper.writeValue(jsonPath.toFile(), teamData);
            return 1;

        } catch (IOException e) {
            System.out.printf("Error al añadir el jugador a el equipo: %s\n", e.getMessage());
            return -1;
        }
    }

    /**
     * Verifica si un jugador específico ya es miembro del equipo almacenado en el archivo JSON.
     *
     * @param player el jugador que se desea verificar.
     * @return {@code true} si el jugador está en el equipo, {@code false} en caso contrario.
     */
    public boolean isPlayerInTeam(Player player) {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> teamData = objectMapper.readValue(Files.readString(jsonPath), Map.class);
            List<Map<String, Object>> team = (List<Map<String, Object>>) teamData.get("team");

            return team.stream().anyMatch(p -> (int) p.get("ID") == player.getId());
        } catch (IOException e) {
            System.out.printf("Error comprobando si el jugador está en el equipo: %s\n", e.getMessage());
            return false;
        }
    }


    /**
     * Recupera la información del equipo desde un archivo JSON y la convierte en un mapa.
     *
     * @return un mapa que asocia los ids de los jugadores del equipo con objetos {@link Player}.
     */
    public HashMap<Integer, Player> getTeam() {
        HashMap<Integer, Player> team = new HashMap<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> teamData = objectMapper.readValue(Files.readString(jsonPath), Map.class);
            List<Map<String, Object>> teamList = (List<Map<String, Object>>) teamData.get("team");

            for (Map<String, Object> playerData : teamList) {
                int id = (int) playerData.get("ID");
                String name = (String) playerData.get("Name");
                String clubName = (String) playerData.get("ClubName");
                String position = (String) playerData.get("Position");
                String playerImageURL = (String) playerData.get("PlayerImageURL");
                String clubImageURL = (String) playerData.get("ClubImageURL");

                team.put(id, new Player(id,name, clubName, position, playerImageURL, clubImageURL));
            }

        } catch (IOException e) {
            System.out.printf("Error recogiendo la informacion del team.json: %s\n", e.getMessage());
        }

        return team;
    }


    /**
     * Obtiene el presupuesto disponible para compras desde el archivo JSON del equipo.
     *
     * @return el presupuesto de compra como un entero, o -1 si ocurre un error.
     */
    public int getPurchaseBudget() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> teamData = objectMapper.readValue(Files.readString(jsonPath), Map.class);
            return (int) teamData.get("budget");
        } catch (IOException e) {
            System.out.printf("Error obteniendo el presupuesto de compra: %s\n", e.getMessage());
            return -1;
        }
    }

    /**
     * Elimina a un jugador del equipo en el archivo JSON y ajusta el presupuesto en consecuencia.
     *
     * @param player el jugador que se desea eliminar del equipo.
     */
    public void removePlayerFromTeam(Player player) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> teamData = objectMapper.readValue(Files.readString(jsonPath), Map.class);
            List<Map<String, Object>> team = (List<Map<String, Object>>) teamData.get("team");

            int budget = (int) teamData.get("budget");
            budget += player.getMarketValue();
            teamData.put("budget", budget);

            team.removeIf(p -> (int) p.get("ID") == player.getId());
            objectMapper.writeValue(jsonPath.toFile(), teamData);

        } catch (IOException e) {
            System.out.printf("Error al quitar el jugador del equipo: %s\n", e.getMessage());
        }
    }
    
}
