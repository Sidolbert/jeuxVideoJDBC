package jeuxVideoJDBC;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Main {

	//exemple d'utilisation d'une BDD depuis un programme Java
	//avec la librairie JDBC
	
	//liste qui stockera tous mes genres récupérés en BDD
	public static ArrayList<Genre> genres = new ArrayList<Genre>();
	
	
	public static void main(String[] args) {
		
		//on récupère les id de connexion depuis un fichier externe
		String[] config = loadConfig("config.txt");
		
		//attention aux exceptions non-gérées quand on fait appel à une BDD...
		try {
			//on crée une connexion vers la BDD qu'on utilise
			//attention : sans le connecteur dans le projet ce code ne fonctionne pas !
			//j'utilise la méthode statique getConnection pour créer la connexion
			//paramètre 1 : l'url de ma base, sur le modèle "jdbc:[variable selon SGBD]//[adresseBDD]:[port]/[nomBDD]"
			//paramètre 2 : username de connexion à la BDD
			//paramètre 3 : mdp de connexion à la BDD
			//éviter de mettre les identifiants de connexion en dur dans le code
			
			Connection dbc = DriverManager.getConnection(config[0], config[1], config[2]);
			System.out.println(dbc);
			
			//on fait une requête : on essaye de récupérer les genres de jeux
			Statement jeux = dbc.createStatement();
			System.out.println(jeux);
			//on exécute la requête et on récupère le jeu de résultats
			ResultSet rs = jeux.executeQuery("SELECT * FROM genre");
			System.out.println(rs);
			//tant qu'il reste des lignes à lire
			while(!rs.isLast()) {
				//on lit une ligne
				rs.next();
				//on crée un objet de type Genre
				Genre g = new Genre();
				g.setGenre_id(rs.getInt("genre_id"));
				g.setGenre_titre(rs.getString("genre_titre"));
				g.setGenre_description(rs.getString("genre_description"));
				genres.add(g);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//on teste que tout a bien fonctionné
		System.out.println(genres);
		
		

	}
	
	//méthode lisant un fichier externe et récupérant dans un tableau les identifiants de connexion
	//syntaxe type du fichier :
	/*
	 * 
	 * 
	 * jdbc:mysql://localhost:3306/[nomBDD]
	 * [username]
	 * [password]
	 * 
	 * 
	 */

	public static String[] loadConfig(String path) {
		String[] config = new String[3];
		//si le fichier n'est pas trouvé, erreur
		try {
			//on crée un lecteur de fichier
			FileReader fr = new FileReader(path);
			//on crée un lecteur avancé pour plus de facilité
			BufferedReader br = new BufferedReader(fr);
			//on lit les trois lignes du fichier dans le tableau config
			for(int i=0; i<3;i++) {
				config[i] = br.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return config;
	}

}
