class Case {

	private int joueur;


	public Case(){
		this.joueur=0;
	}

	public Case(int joueur){
		this.joueur=joueur;
	}

	public int getJoueur() {
	 return joueur;
	 
 }
 
 public void setJoueur(int joueur) {
	 this.joueur=joueur;
 }
 
 
 public String toString() {
	 return ""+ this.joueur ;
 }

}