import MG2D.* ;
import MG2D.geometrie.*;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

class InterfaceGraphique extends Plateau{
	Cercle c [] [] = new Cercle[8][8] ;

	/**
     * Permet de creer le plateau initial dans la fenetre.
     * @param f pour ajouter les éléments à la fenetre
     */
	public InterfaceGraphique(Fenetre f){
		//on crée les 64 cases
		int a =450;
		int b = 500;
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				c[i][j]=new Cercle(Couleur.GRIS, new Point(a, b), 20, true);
				f.ajouter(c[i][j]);
				f.rafraichir();
				a=a+45;
			}
			b=b-45;
			a = 450;
		}

		//on place les cases du milieu
		c[3][3].setCouleur(Couleur.BLANC);
		c[3][4].setCouleur(Couleur.NOIR);
		c[4][3].setCouleur(Couleur.NOIR);
		c[4][4].setCouleur(Couleur.BLANC);


		f.rafraichir();
	}

	/**
     * Méthode qui permet de poser les pions
     * @param f1 pour ajouter les éléments à la fenetre
     * @param joueur pour savoir quelle joueur pose son pion 
     * @param pt pour modifier le plateau 
     * @return le numero de ligne et de colonne et j du tableau
     */
	public int[] getCoup(Fenetre f1, int joueur, Plateau pt){
		int[] tabint= {5,5};

		Souris souris = f1.getSouris();
		Font fonte = new Font(" TimesRoman ",Font.BOLD,30);
		Font fonteg = new Font(" TimesRoman ",Font.BOLD,30);
		Texte t1 = new Texte(Couleur.NOIR, "le joueur2 joue", fonte, new Point(275, 350) );
		Texte t2 = new Texte(Couleur.BLANC, "le joueur1 joue", fonte, new Point(275, 350) );
		Texte ag = new Texte(Couleur.JAUNE, "Cliquez uniquement sur les cercles gris !!", fonteg, new Point(600, 580) );

		// si c'est le joueur1 on place le texte adapté à son tour de jeu(texte t2)
		if(joueur==1){
			f1.supprimer(ag);
			f1.supprimer(t1);
			f1.ajouter(t2);
			f1.ajouter(ag);
			f1.rafraichir();
		}
		// si c'est le joueur2 on place le texte adapté à son tour de jeu(texte t1)
		else if(joueur==2){
			f1.supprimer(ag);
			f1.supprimer(t2);
			f1.ajouter(t1);
			f1.ajouter(ag);
			f1.rafraichir();
		}
		else if(joueur==3){
			f1.supprimer(ag);
			f1.supprimer(t2);
			f1.supprimer(t1);
			f1.supprimer(ag);
			f1.rafraichir();
		}

		while(!souris.getClicGauche()){
			try{
			Thread.sleep(1);
			}
			catch(Exception e){}
		}

		//si le clic est fait à un endroit où se trouve un pion on change la couleur du pion selon le joueur(blanc ou noir)
		Point pj1=new Point(souris.getPosition());
		int aj = pj1.getX();
		int aj1 = pj1.getY();
		for(int i=0; i<plt.length; i++){
			for(int j=0; j<plt.length; j++){
				Point p = c[i][j].getO();
				int a = p.getX();
				int a1 = p.getY();
				if(aj>(a-20)&&aj<(a+20)){
					if(aj1>(a1-20)&&aj1<(a1+20)){
						if(joueur==1){
							c[i][j].setCouleur(Couleur.BLANC);
							// on modifie et remplie le plateau
							pt.setPlt(i, j, 1);	
							//On met a jour l ' a f f i c h a g e
							f1.rafraichir();
							int[] monResultat = { i , j };
							return monResultat;
						}
						else if(joueur==2){
							c[i][j].setCouleur(Couleur.NOIR);
							// on modifie et remplie le plateau
							pt.setPlt(i, j, 2);
							//On met a jour l ' a f f i c h a g e
							f1.rafraichir();
							int[] monResultat = { i , j };
							return monResultat;
						}
					}
				}
			}
		}
		return tabint;
	}

	/**
     * Méthode qui permet de retourner les pions piégés.
     * @param f1 pour ajouter les éléments à la fenetre 
     * @param i pour les lignes du tableau  
     * @param j pour les colonnes du tableau
     */
	public void setRetourner(Fenetre f1, int i, int j){
		//changer les pions horizontalement suivant le pion actuelle
		//si la case d'apres ne sort pas du plateau
		if(j+1<plt.length){
			//si la case actuelle et la case d'apres n'ont pas la meme couleur
			if(!(c[i][j].getCouleur().equals(c[i][j+1].getCouleur()))){
				//si la case actuelle et la case d'apres sont des pions non gris
				if(!(c[i][j].getCouleur().equals(Couleur.GRIS)) && !(c[i][j+1].getCouleur().equals(Couleur.GRIS))){
					//k prend la valeur de j 
					int k = j;
					Cercle temp;
					boolean a =false;
					//tant que a different de true on parcoure les cases suivantes
					while(a==false){					
						k=k+1;
						temp = c[i][k];
						//si la d'apres est la dernier case du plateau et qu'elle est de la même couleur que la case actuelle
						if( k + 1 >= plt.length && (k==plt.length-1 && temp.getCouleur().equals(c[i][j].getCouleur()))){
							do{
								k = k - 1;
								temp = c[i][k];
								temp.setCouleur(c[i][j].getCouleur());
							} while(k - 1 > 0 && !(c[i][k-1].getCouleur().equals(temp.getCouleur())));
								

							a=true;
						}
						//si la case suivante sort du plateau on sort de la boucle 
						else if((k + 1) >= plt.length){
							a=true;
						}
						//si la case suivante est de couleur grise on sort de la boucle
						else if(temp.getCouleur().equals(Couleur.GRIS)){
							a=true;
						}
						//si la case suivante est identique(de même couleur) à la case courante on change la couleur de toutes les cases parcourus par la couleur de la case acuelle et a=vrai
						else if(c[i][j].getCouleur().equals(temp.getCouleur())){
							do{
								k = k - 1;
								temp = c[i][k];
								temp.setCouleur(c[i][j].getCouleur());
							} while(k - 1 > 0 && !(c[i][k-1].getCouleur().equals(temp.getCouleur())));
								

							a=true;
						}
					}
				}
			}
		}
		//changer les pions horizontalement precedant le pion actuelle
		if(j-1>0){
			if(!(c[i][j].getCouleur().equals(c[i][j-1].getCouleur()))){
				if(!(c[i][j].getCouleur().equals(Couleur.GRIS)) && !(c[i][j-1].getCouleur().equals(Couleur.GRIS))){
					int k = j;
					Cercle temp;
					boolean a =false;
					while(a==false){
						k=k-1;
						temp = c[i][k];
						if( k - 1 < 0 && (k==0 && temp.getCouleur().equals(c[i][j].getCouleur()))){
							do{
								k = k + 1;
								temp = c[i][k];
								temp.setCouleur(c[i][j].getCouleur());
							} while((k + 1) <= plt.length && !(c[i][k+1].getCouleur().equals(temp.getCouleur())));
								
							a=true;
						} 
						else if((k-1)<0){
							a=true;
						}
						else if(temp.getCouleur().equals(Couleur.GRIS)){
							a=true;
						}
						else if(c[i][j].getCouleur().equals(temp.getCouleur())){
							do{
								k = k + 1;
								temp = c[i][k];
								temp.setCouleur(c[i][j].getCouleur());
							} while((k + 1) <= plt.length && !(c[i][k+1].getCouleur().equals(temp.getCouleur())));
								
							a=true;
						}
					}
				}
			}
		}
		//changer les pions verticalement suivant le pion actuelle
		if(i < plt.length - 1){
			if(!(c[i][j].getCouleur().equals(c[i+1][j].getCouleur()))){
				if(!(c[i][j].getCouleur().equals(Couleur.GRIS)) && !(c[i+1][j].getCouleur().equals(Couleur.GRIS))){
					int k = i;
					Cercle temp;
					boolean a =false;
					while(a==false){

						k=k+1;
						temp = c[k][j];
						if( k + 1 >= plt.length && (k==plt.length-1 && temp.getCouleur().equals(c[i][j].getCouleur()))){
							do{
								k = k - 1;
								temp = c[k][j];
								temp.setCouleur(c[i][j].getCouleur());
							} while(k - 1 > 0 && !(c[k-1][j].getCouleur().equals(temp.getCouleur())));
							a=true;
						}
						else if((k + 1) >= plt.length) {
							a=true;
						}
						else if(temp.getCouleur().equals(Couleur.GRIS)){
							a=true;
						}
						else if(c[i][j].getCouleur().equals(temp.getCouleur())){
							do{
								k = k - 1;
								temp = c[k][j];
								temp.setCouleur(c[i][j].getCouleur());
							} while(k - 1 > 0 && !(c[k-1][j].getCouleur().equals(temp.getCouleur())));
								

							a=true;
						}
					}
				}
			}
		}
		 //changer les pions horizontalement precedant le pion actuelle
		if(i-1>0){
		 	if(!(c[i][j].getCouleur().equals(c[i-1][j].getCouleur()))){
		 		if(!(c[i][j].getCouleur().equals(Couleur.GRIS)) && !(c[i-1][j].getCouleur().equals(Couleur.GRIS))){
			 		int k = i;
					Cercle temp;
					boolean a =false;
					while(a==false){
						k=k-1;
						temp = c[k][j];
						if( k - 1 < 0 && (k==0 && temp.getCouleur().equals(c[i][j].getCouleur()))){
							do{
								k = k + 1;
								temp = c[k][j];
								temp.setCouleur(c[i][j].getCouleur());
							} while((k + 1) <= plt.length && !(c[k+1][j].getCouleur().equals(temp.getCouleur())));
								
							a=true;
						} 
						else if((k-1)<0){
							a=true;
						}
						else if(temp.getCouleur().equals(Couleur.GRIS)){
							a=true;
						}
						else if(c[i][j].getCouleur().equals(temp.getCouleur())){
							do{
								k = k + 1;
								temp = c[k][j];
								temp.setCouleur(c[i][j].getCouleur());
							} while((k + 1) <= plt.length && !(c[k+1][j].getCouleur().equals(temp.getCouleur())));
								
							a=true;
						}
					}
				}
		 	}
		}
		//changer les pions diagonalement vers le haut precedant le pion actuelle
		if(i-1>0&&j-1>0){
		 	if(!(c[i][j].getCouleur().equals(c[i-1][j-1].getCouleur()))){
		 		if(!(c[i][j].getCouleur().equals(Couleur.GRIS)) && !(c[i-1][j-1].getCouleur().equals(Couleur.GRIS))){
			 		int k = i;
			 		int l = j;
			 		Cercle temp;
			 		boolean a =false;
			 		while(a==false){
			 			k=k-1;
			 			l=l-1;
			 			temp = c[k][l];
			 			if((k - 1 < 0 && (k==0 && temp.getCouleur().equals(c[i][j].getCouleur()))) || (l - 1 < 0 && (l==0 && temp.getCouleur().equals(c[i][j].getCouleur()))) ){
			 				do{
			 					k=k+1;
			 					l=l+1;
			 					temp = c[k][l];
			 					temp.setCouleur(c[i][j].getCouleur());
			 				}while((k + 1) <= plt.length && (l + 1)<= plt.length && !(c[k+1][l+1].getCouleur().equals(temp.getCouleur())));
			 				a=true;
			 			}
			 			else if(k-1<0 || l-1<0){
			 				a=true;
			 			}
			 			else if(temp.getCouleur().equals(Couleur.GRIS)){
			 				a=true;
			 			}
			 			else if(c[i][j].getCouleur().equals(temp.getCouleur())){
			 				do{
			 					k=k+1;
			 					l=l+1;
			 					temp = c[k][l];
			 					temp.setCouleur(c[i][j].getCouleur());
			 				}while((k + 1) <= plt.length && (l + 1)<= plt.length && !(c[k+1][l+1].getCouleur().equals(temp.getCouleur())));
			 				a=true;
			 			}
			 		}
			 	}
		 	}
		}
		 //changer les pions diagonalement vers le bas suivant le pion actuelle
		if((i+1!=plt.length||i+1<plt.length)&&(j+1!=plt.length||j+1<plt.length)){
		 	if(!(c[i][j].getCouleur().equals(c[i+1][j+1].getCouleur()))){
		 		if(!(c[i][j].getCouleur().equals(Couleur.GRIS)) && !(c[i+1][j+1].getCouleur().equals(Couleur.GRIS))){
			 		int k = i;
			 		int l = j;
			 		Cercle  temp;
			 		boolean a =false;
			 		while(a==false){
			 			k=k+1;
			 			l=l+1;
			 			temp = c[k][l];
			 			if((k + 1 >= plt.length && (k==plt.length-1 && temp.getCouleur().equals(c[i][j].getCouleur()))) || (l + 1 >= plt.length && (l==plt.length-1 && temp.getCouleur().equals(c[i][j].getCouleur()))) ){
			 				do{
			 					k=k-1;
			 					l=l-1;
			 					temp = c[k][l];
			 					temp.setCouleur(c[i][j].getCouleur());
			 				}while(k - 1 > 0 && l - 1 > 0 && !(c[k-1][l-1].getCouleur().equals(temp.getCouleur())));
			 				a=true;
			 			}
			 			else if((k + 1) >= plt.length || (l + 1) >= plt.length){
			 				a=true;
			 			}
			 			else if(temp.getCouleur().equals(Couleur.GRIS)){
			 				a=true;
			 			}
			 			else if(c[i][j].getCouleur().equals(temp.getCouleur())){
			 				do{
			 					k=k-1;
			 					l=l-1;
			 					temp = c[k][l];
			 					temp.setCouleur(c[i][j].getCouleur());
			 				}while(k - 1 > 0 && l - 1 > 0 && !(c[k-1][l-1].getCouleur().equals(temp.getCouleur())));
			 				a=true;
			 			}
			 		}
			 	}
			}
		}
		 //changer les pions diagonalement vers le bas precedant le pion actuelle
		 if(i-1>0&&(j+1!=plt.length||j+1<plt.length)){
		 	if(!(c[i][j].getCouleur().equals(c[i-1][j+1].getCouleur()))){
		 		if(!(c[i][j].getCouleur().equals(Couleur.GRIS)) && !(c[i-1][j+1].getCouleur().equals(Couleur.GRIS))){
			 		int k = i;
			 		int l = j;
			 		Cercle temp;
			 		boolean a =false;
			 		while(a==false){
			 			k=k-1;
			 			l=l+1;
			 			temp = c[k][l];
			 			if(((k - 1 < 0 && (k==0 && temp.getCouleur().equals(c[i][j].getCouleur())))) || (l + 1 >= plt.length && (l==plt.length-1 && temp.getCouleur().equals(c[i][j].getCouleur())))){
			 				do{
			 					k=k+1;
			 					l=l-1;
			 					temp = c[k][l];
			 					temp.setCouleur(c[i][j].getCouleur());
			 				}while((k + 1) <= plt.length && l - 1 > 0 && !(c[k+1][l-1].getCouleur().equals(temp.getCouleur())));
			 				a=true;
			 			}
			 			else if(k-1<0 || (l + 1) >= plt.length){
			 				a=true;
			 			}
			 			else if(temp.getCouleur().equals(Couleur.GRIS)){
			 				a=true;
			 			}
			 			else if(c[i][j].getCouleur().equals(temp.getCouleur())){
			 				do{
			 					k=k+1;
			 					l=l-1;
			 					temp = c[k][l];
			 					temp.setCouleur(c[i][j].getCouleur());
			 				}while((k + 1) <= plt.length && l - 1 > 0 && !(c[k+1][l-1].getCouleur().equals(temp.getCouleur())));
			 				a=true;
			 			}
			 		}
			 	}
		 	}
		 }
		 //changer les pions diagonalement vers le haut suivant le pion actuelle
		if((i+1!=plt.length||i+1<plt.length)&&j-1>0){
		 	if(!(c[i][j].getCouleur().equals(c[i+1][j-1].getCouleur()))){
		 		if(!(c[i][j].getCouleur().equals(Couleur.GRIS)) && !(c[i+1][j-1].getCouleur().equals(Couleur.GRIS))){
			 		int k = i;
			 		int l = j;
			 		Cercle temp;
			 		boolean a =false;
			 		while(a==false){
			 			k=k+1;
			 			l=l-1;
			 			temp = c[k][l];
			 			if(((l - 1 < 0 && (l==0 && temp.getCouleur().equals(c[i][j].getCouleur())))) || (k + 1 >= plt.length && (k==plt.length-1 && temp.getCouleur().equals(c[i][j].getCouleur())))){
			 				do{
			 					k=k-1;
			 					l=l+1;
			 					temp = c[k][l];
			 					temp.setCouleur(c[i][j].getCouleur());
			 				}while((l + 1) <= plt.length && k - 1 > 0 && !(c[k-1][l+1].getCouleur().equals(temp.getCouleur())));
			 				a=true;
			 			}
			 			else if(l-1<0 || (k + 1) >= plt.length){
			 				a=true;
			 			}
			 			else if(temp.getCouleur().equals(Couleur.GRIS)){
			 				a=true;
			 			}
			 			else if(c[i][j].getCouleur().equals(temp.getCouleur())){
			 				do{
			 					k=k-1;
			 					l=l+1;
			 					temp = c[k][l];
			 					temp.setCouleur(c[i][j].getCouleur());
			 				}while((l + 1) <= plt.length && k - 1 > 0 && !(c[k-1][l+1].getCouleur().equals(temp.getCouleur())));
			 				a=true;
			 			}
			 		}
			 	}
		 	}
		}
	}

	/**
     * Permet de vérifier si le plateau est plein.
     * @return Vrai si c'est le cas, faux sinon.
     */
	public boolean pionPlein(){
		for(int i=0; i<plt.length; i++){
			for(int j=0; j<plt.length; j++){
				if(c[i][j].getCouleur().equals(Couleur.GRIS)){
					return false;
				}
			}
		}
		return true;
	}

	/**
     * Permet de compter les pions gagnants si c'est le joueur 1 qui gagne.
     * @return le nombre de pions gagants du joueur1 .
     */
	public int bilanF(){
		int j1=0;
		for(int i=0; i<plt.length; i++){
			for(int j=0; j<plt.length; j++){
				if(c[i][j].getCouleur().equals(Couleur.BLANC)){
					j1 = j1 + 1;
				}
			}
		}
		return j1;
	}

	/**
     * Permet de compter les pions gagnants si c'est le joueur 2 qui gagne.
     * @return le nombre de pions gagants du joueur2 .
     */
	public int bilanS(){
		int j2=0;
		for(int i=0; i<plt.length; i++){
			for(int j=0; j<plt.length; j++){
				if(c[i][j].getCouleur().equals(Couleur.NOIR)){
					j2 = j2 + 1;
				}
			}
		}
		return j2;
	}

	/**
     * Permet d'afficher le gagnant avec son nombre de pions.
     * @param f1 pour ajouter les éléments à la fenetre
     * @param v1 pour les pions du gagnant
     * @param v2 pour les pions du perdant
     * @param joueur pour le joueur gagnant
     */
	public void vainq(Fenetre f1, int v1, int v2, int joueur){
		if(joueur==1){
			//si c'est le joueur 1 qui gagne
			Font fonte = new Font(" TimesRoman ",Font.BOLD,30);
			Texte tv = new Texte(Couleur.BLANC, "le joueur1 gagne avec "+v1 +" pions gagnants", fonte, new Point(600, 580) );
			f1.ajouter(tv);
			f1.rafraichir();
		}
		else if(joueur==2){
			// si c'est le joueur 2 qui gagne
			Font fonte = new Font(" TimesRoman ",Font.BOLD,30);
			Texte tv = new Texte(Couleur.NOIR, "le joueur2 gagne avec "+v1 +" pions gagnants", fonte, new Point(600, 580) );
			f1.ajouter(tv);
			f1.rafraichir();
		}
	}

	/**
     * Permet d'enlever les éléments inutile à la fin(les textes)
     * @param f1 pour ajouter les éléments à la fenetre
     */
	public void affichageF(Fenetre f1){
		f1.effacer();
		for(int i=0; i<plt.length; i++){
			for(int j=0; j<plt.length; j++){
				f1.ajouter(c[i][j]);
			}
		}


















		
		f1.rafraichir();
	}

	/**
     * Permet de fermer la fenetre
     * @param f1 pour ajouter les éléments à la fenetre
     */
	public void getClo(Fenetre f1){
			Font fonte = new Font(" TimesRoman ",Font.BOLD,30);
			Texte t1 = new Texte(Couleur.BLEU, "Clique n'importe ou sur l ecran pour fermer ", fonte, new Point(600, 90) );
			int cx;
			int cy;

			f1.ajouter(t1);
			f1.rafraichir();

			Souris souris = f1.getSouris();

			while(!souris.getClicGauche()){
				try{
				Thread.sleep(1);
				}
				catch(Exception e){}
			}

			//clic qui se fait n'importe où sur l'ecran
			Point pf=new Point (souris.getPosition());
			cx = pf.getX();
			cy = pf.getY();
			f1.fermer();

		}

}
