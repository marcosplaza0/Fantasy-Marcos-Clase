package org.marcosjfx.marcosfx.backend;

/**
 * Clase que representa un jugador, incluyendo datos como su nombre, edad, equipo, posición,
 * estadísticas (goles, asistencias, atajadas) y otros detalles relevantes.
 */
public class Player {

   /**
    * Identificador único del jugador.
    */
   private int id;
   /**
    * Nombre del jugador.
    */
   private String name;
   /**
    * Calificación general del jugador.
    */
   private int rating;
   /**
    * Valor de mercado del jugador.
    */
   private int marketValue;
   /**
    * Edad del jugador.
    */
   private int age;
   /**
    * ID del equipo del jugador.
    */
   private int teamID;
   /**
    * Goles metidos por el jugador.
    */
   private int goals;
   /**
    * Asistencias metidas por el jugador.
    */
   private int assists;
   /**
    * Paradas del jugador.
    */
   private int saves;
   /**
    * Nombre del club del jugador.
    */
   private String clubName;
   /**
    * Posicion en la que juega el jugador.
    */
   private String position;
   /**
    * URL de una imagen del jugador.
    */
   private String playerImageURL;
   /**
    * URL de una imagen del equipo del jugador.
    */
   private String clubImageURL;

   /**
    * Constructor que inicializa todos los atributos del jugador.
    *
    * @param id             Identificador del jugador.
    * @param name           Nombre del jugador.
    * @param rating         Calificación general del jugador.
    * @param marketValue    Valor de mercado del jugador.
    * @param age            Edad del jugador.
    * @param teamID         Identificador del equipo del jugador.
    * @param goals          Número de goles del jugador.
    * @param assists        Número de asistencias del jugador.
    * @param saves          Número de atajadas del jugador.
    * @param clubName       Nombre del club del jugador.
    * @param position       Posición del jugador en el campo.
    * @param playerImageURL Enlace a la imagen del jugador.
    * @param clubImageURL   Enlace a la imagen del club.
    */
   public Player(int id, String name, int rating, int marketValue, int age, int teamID, int goals, int assists, int saves, String clubName, String position, String playerImageURL, String clubImageURL) {
      this.id = id;
      this.name = name;
      this.rating = rating;
      this.marketValue = marketValue;
      this.age = age;
      this.teamID = teamID;
      this.goals = goals;
      this.assists = assists;
      this.saves = saves;
      this.clubName = clubName;
      this.position = position;
      this.playerImageURL = playerImageURL;
      this.clubImageURL = clubImageURL;
   }

   /**
    * Constructor que inicializa atributos básicos del jugador.
    *
    * @param id             Identificador del jugador.
    * @param name           Nombre del jugador.
    * @param clubName       Nombre del club del jugador.
    * @param position       Posición del jugador.
    * @param playerImageURL Enlace a la imagen del jugador.
    * @param clubImageURL   Enlace a la imagen del club.
    */
   public Player(int id, String name, String clubName, String position, String playerImageURL, String clubImageURL) {
      this.id = id;
      this.name = name;
      this.clubName = clubName;
      this.position = position;
      this.playerImageURL = playerImageURL;
      this.clubImageURL = clubImageURL;
   }

   /**
    * Obtiene el número de goles marcados por el jugador.
    *
    * @return Número de goles del jugador.
    */
   public int getGoals() {
      return goals;
   }

   /**
    * Establece el número de goles marcados por el jugador.
    *
    * @param goals Número de goles a asignar.
    */
   public void setGoals(int goals) {
      this.goals = goals;
   }

   /**
    * Obtiene el número de asistencias del jugador.
    *
    * @return Número de asistencias del jugador.
    */
   public int getAssists() {
      return assists;
   }

   /**
    * Establece el número de asistencias del jugador.
    *
    * @param assists Número de asistencias a asignar.
    */
   public void setAssists(int assists) {
      this.assists = assists;
   }

   /**
    * Obtiene el número de paradas realizadas por el jugador.
    *
    * @return Número de paradas del jugador.
    */
   public int getSaves() {
      return saves;
   }

   /**
    * Establece el número de paradas realizadas por el jugador.
    *
    * @param saves Número de paradas a asignar.
    */
   public void setSaves(int saves) {
      this.saves = saves;
   }

   /**
    * Obtiene el identificador único del jugador.
    *
    * @return Identificador único del jugador.
    */
   public int getId() {
      return id;
   }

   /**
    * Establece el identificador único del jugador.
    *
    * @param id Identificador único del jugador.
    */
   public void setId(int id) {
      this.id = id;
   }

   /**
    * Obtiene el nombre del jugador.
    *
    * @return Nombre del jugador.
    */
   public String getName() {
      return name;
   }

   /**
    * Establece el nombre del jugador.
    *
    * @param name Nombre del jugador.
    */
   public void setName(String name) {
      this.name = name;
   }

   /**
    * Obtiene la calificación general del jugador.
    *
    * @return Calificación general del jugador.
    */
   public int getRating() {
      return rating;
   }

   /**
    * Establece la calificación general del jugador.
    *
    * @param rating Calificación general a asignar.
    */
   public void setRating(int rating) {
      this.rating = rating;
   }

   /**
    * Obtiene el valor de mercado del jugador.
    *
    * @return Valor de mercado en entero.
    */
   public int getMarketValue() {
      return marketValue;
   }

   /**
    * Establece el valor de mercado del jugador.
    *
    * @param marketValue Valor de mercado a asignar.
    */
   public void setMarketValue(int marketValue) {
      this.marketValue = marketValue;
   }

   /**
    * Obtiene la edad del jugador.
    *
    * @return Edad del jugador.
    */
   public int getAge() {
      return age;
   }

   /**
    * Establece la edad del jugador.
    *
    * @param age Edad a asignar.
    */
   public void setAge(int age) {
      this.age = age;
   }

   /**
    * Obtiene el ID del equipo del jugador.
    *
    * @return ID del equipo del jugador.
    */
   public int getTeamID() {
      return teamID;
   }

   /**
    * Establece el ID del equipo del jugador.
    *
    * @param teamID ID del equipo a asignar.
    */
   public void setTeamID(int teamID) {
      this.teamID = teamID;
   }

   /**
    * Obtiene el nombre del club al que pertenece el jugador.
    *
    * @return Nombre del club del jugador.
    */
   public String getClubName() {
      return clubName;
   }

   /**
    * Establece el nombre del club al que pertenece el jugador.
    *
    * @param clubName Nombre del club del jugador a asignar.
    */
   public void setClubName(String clubName) {
      this.clubName = clubName;
   }

   /**
    * Obtiene la posición del jugador en el campo.
    *
    * @return Posición del jugador.
    */
   public String getPosition() {
      return position;
   }

   /**
    * Establece la posición del jugador en el campo.
    *
    * @param position Posición a asignar.
    */
   public void setPosition(String position) {
      this.position = position;
   }

   /**
    * Obtiene la URL de la imagen del jugador.
    *
    * @return URL de la imagen del jugador.
    */
   public String getPlayerImageURL() {
      return playerImageURL;
   }

   /**
    * Establece la URL de la imagen del jugador.
    *
    * @param playerImageURL URL de la imagen a asignar.
    */
   public void setPlayerImageURL(String playerImageURL) {
      this.playerImageURL = playerImageURL;
   }

   /**
    * Obtiene la URL de la imagen del club del jugador.
    *
    * @return URL de la imagen del club del jugador.
    */
   public String getClubImageURL() {
      return clubImageURL;
   }

   /**
    * Establece la URL de la imagen del club del jugador.
    *
    * @param clubImageURL URL de la imagen del club a asignar.
    */
   public void setClubImageURL(String clubImageURL) {
      this.clubImageURL = clubImageURL;
   }

}
