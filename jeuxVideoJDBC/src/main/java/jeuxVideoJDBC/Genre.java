package jeuxVideoJDBC;

//classe représentant des genres récupérés depuis la BDD
public class Genre {
	
	//les attributs correspondent à ceux de la table
	private int genre_id;
	private String genre_titre;
	private String genre_description;
	
	//constructeur vide, on remplira après
	public Genre() {
		
	}

	//mes getters et setters, je vais en avoir besoin
	public int getGenre_id() {
		return genre_id;
	}

	public void setGenre_id(int genre_id) {
		this.genre_id = genre_id;
	}

	public String getGenre_titre() {
		return genre_titre;
	}

	public void setGenre_titre(String genre_titre) {
		this.genre_titre = genre_titre;
	}

	public String getGenre_description() {
		return genre_description;
	}

	public void setGenre_description(String genre_description) {
		this.genre_description = genre_description;
	}

	@Override
	public String toString() {
		return "Genre [genre_id=" + genre_id + ", genre_titre=" + genre_titre + ", genre_description="
				+ genre_description + "]";
	}
	
	
	
	
	
}
