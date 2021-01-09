class Plateau {
	Case plt [] [] = new Case[8][8] ;

	public Plateau(){
		for(int i=0; i<plt.length; i++)
			for(int j=0; j<plt.length; j++)
				this.plt[i][j] = new Case();
	}

	public Case getXY(int x, int y){
		return this.plt[x][y];
	}

	public void setPlt(int x, int y, int nb){
	   	 this.plt[x][y].setJoueur(nb);
	}  

	/**
     * Permet de vérifier si le plateau est plein.
     * @return Vrai si c'est le cas, faux sinon.
     */
	public boolean plein(){
		for(int i=0; i<plt.length; i++){
			for(int j=0; j<plt.length; j++){
				if(this.plt[i][j].getJoueur()==0){
					return false;
				}
			}
		}
		return true;
	}


	/**
     * methode qui permet de vérifier si deux case adjacentes sont differentes de 0 et n'ont pas la même valeur
     * @return Vrai si c'est le cas, faux sinon.
     */
	public boolean entoure(){
		for(int i=0; i<plt.length; i++){
			for(int j=0; j<plt.length; j++){

				//si la case d'apres ne sort pas du plateau
				if(j+1!=plt.length||j+1<plt.length){
					//si la case actuelle et la case d'apres n'ont pas la meme valeur
					if(this.plt[i][j].getJoueur()!=this.plt[i][j+1].getJoueur()){
						//si la case actuelle et la case d'apres ont une valeur differente de 0
						if(this.plt[i][j].getJoueur()!=0 && this.plt[i][j+1].getJoueur()!=0){
							//on retourne vrai
							return true;
						}
					}
				}
				//cas de figure different mais meme schema
				if(j-1>0){
					if(this.plt[i][j].getJoueur()!=this.plt[i][j-1].getJoueur()){
						if(this.plt[i][j].getJoueur()!=0 && this.plt[i][j-1].getJoueur()!=0){
							return true;
						}
					}
				}
				if(i+1!=plt.length||i+1<plt.length){
					if(this.plt[i][j].getJoueur()!=this.plt[i+1][j].getJoueur()){
						if(this.plt[i][j].getJoueur()!=0 && this.plt[i+1][j].getJoueur()!=0){
							return true;
						}
					}
				}
				if(i-1>0){
					if(this.plt[i][j].getJoueur()!=this.plt[i-1][j].getJoueur()){
						if(this.plt[i][j].getJoueur()!=0 && this.plt[i-1][j].getJoueur()!=0){
							return true;
						}
					}
				}
				if(i-1>0&&j-1>0){
					if(this.plt[i][j].getJoueur()!=this.plt[i-1][j-1].getJoueur()){
						if(this.plt[i][j].getJoueur()!=0 && this.plt[i-1][j-1].getJoueur()!=0){
							return true;
						}
					}
				}
				if((i+1!=plt.length||i+1<plt.length)&&(j+1!=plt.length||j+1<plt.length)){
					if(this.plt[i][j].getJoueur()!=this.plt[i+1][j+1].getJoueur()){
						if(this.plt[i][j].getJoueur()!=0 && this.plt[i+1][j+1].getJoueur()!=0){
							return true;
						}
					}
				}
				if(i-1>0&&(j+1!=plt.length||j+1<plt.length)){
					if(this.plt[i][j].getJoueur()!=this.plt[i-1][j+1].getJoueur()){
						if(this.plt[i][j].getJoueur()!=0 && this.plt[i-1][j+1].getJoueur()!=0){
							return true;
						}
					}
				}
				if((i+1!=plt.length||i+1<plt.length)&&j-1>0){
					if(this.plt[i][j].getJoueur()!=this.plt[i+1][j-1].getJoueur()){
						if(this.plt[i][j].getJoueur()!=0 && this.plt[i+1][j-1].getJoueur()!=0){
							return true;
						}
					}
				}
			}
		}
		// sinon on retourne faux	
		return false;
	}

	/**
     * methode qui permet de retourner les pions concernés si la méthode entoure() retourne vrai
     */
	public void setPion(){
		for(int i=0; i<plt.length; i++){
			for(int j=0; j<plt.length; j++){
				//changer les pions horizontalement suivant le pion actuelle
				//si la case d'apres ne sort pas du plateau
				if(j+1<plt.length){
					//si la case actuelle et la case d'apres n'ont pas la meme valeur
					if(this.plt[i][j].getJoueur()!=this.plt[i][j+1].getJoueur()){
						//k prend la valeur de j 
						int k = j;
						Case temp;
						boolean a =false;
						while(a==false){
							//tant que a different de true on parcoure les cases suivantes
							k=k+1;
							temp = this.plt[i][k];
							//si la case suivante sort du plateau on sort de la boucle 
							if((k + 1) >= plt.length){
								a=true;
							}
							//si la case suivante est egale à 0 on sort de la boucle
							else if(temp.getJoueur()==0){
								a=true;
							}
							//si la case suivante est égale à la case courante on change la valeur de toutes les cases parcourus par la valeur de la case acuelle et on retourne vrai
							else if(this.plt[i][j].getJoueur()==temp.getJoueur()){
								do{
									k = k - 1;
									temp = this.plt[i][k];
									temp.setJoueur(this.plt[i][j].getJoueur());
								} while(k - 1 > 0 && this.plt[i][k-1].getJoueur() != temp.getJoueur());
									

								a=true;
							}
						}
					}
				}
				//changer les pions horizontalement precedant le pion actuelle
				if(j-1>0){
					if(this.plt[i][j].getJoueur()!=this.plt[i][j-1].getJoueur()){
						int k = j;
						Case temp;
						boolean a =false;
						while(a==false){
							k=k-1;
							temp = this.plt[i][k];
							if((k-1)<0){
								a=true;
							}
							else if(temp.getJoueur()==0){
								a=true;
							}
							else if(this.plt[i][j].getJoueur()==temp.getJoueur()){
								do{
									k = k + 1;
									temp = this.plt[i][k];
									temp.setJoueur(this.plt[i][j].getJoueur());
								} while((k + 1) <= plt.length && this.plt[i][k+1].getJoueur() != temp.getJoueur());
									
								a=true;
							}
						}
					}
				}

				if(i < plt.length - 1){

					if(this.plt[i][j].getJoueur() != this.plt[i+1][j].getJoueur()){

						int k = i;
						Case temp; 
						boolean a =false;
						while(a==false){

							k=k+1;
							temp = this.plt[k][j];
							if((k + 1) >= plt.length) {
								a=true;
							}
							else if(temp.getJoueur()==0){
								a=true;
							}
							else if(this.plt[i][j].getJoueur()==temp.getJoueur()){
								
								do{
									k = k - 1;
									temp = this.plt[k][j];
									temp.setJoueur(this.plt[i][j].getJoueur());
							
								} while(k - 1 > 0 && this.plt[k - 1][j].getJoueur() != temp.getJoueur());
									

								a=true;
							}
						}
					}
				}
				 //changer les pions horizontalement precedant le pion actuelle
				if(i-1>0){
				 	if(this.plt[i][j].getJoueur()!=this.plt[i-1][j].getJoueur()){
				 		int k = i;
						Case temp;
						boolean a =false;
						while(a==false){
							k=k-1;
							temp = this.plt[k][j];
							if((k-1)<0){
								a=true;
							}
							else if(temp.getJoueur()==0){
								a=true;
							}
							else if(this.plt[i][j].getJoueur()==temp.getJoueur()){
								do{
									k = k + 1;
									temp = this.plt[k][j];
									temp.setJoueur(this.plt[i][j].getJoueur());
								} while((k + 1) <= plt.length && this.plt[k+1][j].getJoueur() != temp.getJoueur());
									
								a=true;
							}
						}
				 	}
				}
				//changer les pions diagonalement vers le haut precedant le pion actuelle
				if(i-1>0&&j-1>0){
				 	if(this.plt[i][j].getJoueur()!=this.plt[i-1][j-1].getJoueur()){
				 		int k = i;
				 		int l = j;
				 		Case temp;
				 		boolean a =false;
				 		while(a==false){
				 			k=k-1;
				 			l=l-1;
				 			temp = this.plt[k][l];
				 			if(k-1<0 || l-1<0){
				 				a=true;
				 			}
				 			else if(temp.getJoueur()==0){
				 				a=true;
				 			}
				 			else if(this.plt[i][j].getJoueur()==temp.getJoueur()){
				 				do{
				 					k=k+1;
				 					l=l+1;
				 					temp = this.plt[k][l];
				 					temp.setJoueur(this.plt[i][j].getJoueur());
				 				}while((k + 1) <= plt.length && (l + 1)<= plt.length && this.plt[k+1][l+1].getJoueur() != temp.getJoueur());
				 				a=true;
				 			}
				 		}
				 	}
				}
				 //changer les pions diagonalement vers le bas suivant le pion actuelle
				if((i+1!=plt.length||i+1<plt.length)&&(j+1!=plt.length||j+1<plt.length)){
				 	if(this.plt[i][j].getJoueur()!=this.plt[i+1][j+1].getJoueur()){
				 		int k = i;
				 		int l = j;
				 		Case temp;
				 		boolean a =false;
				 		while(a==false){
				 			k=k+1;
				 			l=l+1;
				 			temp = this.plt[k][l];
				 			if((k + 1) >= plt.length || (l + 1) >= plt.length){
				 				a=true;
				 			}
				 			else if(temp.getJoueur()==0){
				 				a=true;
				 			}
				 			else if(this.plt[i][j].getJoueur()==temp.getJoueur()){
				 				do{
				 					k=k-1;
				 					l=l-1;
				 					temp = this.plt[k][l];
				 					temp.setJoueur(this.plt[i][j].getJoueur());
				 				}while(k - 1 > 0 && l - 1 > 0 && this.plt[k-1][l-1].getJoueur() != temp.getJoueur());
				 				a=true;
				 			}
				 		}
					}
				}
				 //changer les pions diagonalement vers le bas precedant le pion actuelle
				 if(i-1>0&&(j+1!=plt.length||j+1<plt.length)){
				 	if(this.plt[i][j].getJoueur()!=this.plt[i-1][j+1].getJoueur()){
				 		int k = i;
				 		int l = j;
				 		Case temp;
				 		boolean a =false;
				 		while(a==false){
				 			k=k-1;
				 			l=l+1;
				 			temp = this.plt[k][l];
				 			if(k-1<0 || (l + 1) >= plt.length){
				 				a=true;
				 			}
				 			else if(temp.getJoueur()==0){
				 				a=true;
				 			}
				 			else if(this.plt[i][j].getJoueur()==temp.getJoueur()){
				 				do{
				 					k=k+1;
				 					l=l-1;
				 					temp = this.plt[k][l];
				 					temp.setJoueur(this.plt[i][j].getJoueur());
				 				}while((k + 1) <= plt.length && l - 1 > 0 && this.plt[k+1][l-1].getJoueur() != temp.getJoueur());
				 				a=true;
				 			}
				 		}
				 	}
				 }
				 //changer les pions diagonalement vers le haut suivant le pion actuelle
				if((i+1!=plt.length||i+1<plt.length)&&j-1>0){
				 	if(this.plt[i][j].getJoueur()!=this.plt[i+1][j-1].getJoueur()){
				 		int k = i;
				 		int l = j;
				 		Case temp;
				 		boolean a =false;
				 		while(a==false){
				 			k=k+1;
				 			l=l-1;
				 			temp = this.plt[k][l];
				 			if(l-1<0 || (k + 1) >= plt.length){
				 				a=true;
				 			}
				 			else if(temp.getJoueur()==0){
				 				a=true;
				 			}
				 			else if(this.plt[i][j].getJoueur()==temp.getJoueur()){
				 				do{
				 					k=k-1;
				 					l=l+1;
				 					temp = this.plt[k][l];
				 					temp.setJoueur(this.plt[i][j].getJoueur());
				 				}while((l + 1) <= plt.length && k - 1 > 0 && this.plt[k-1][l+1].getJoueur() != temp.getJoueur());
				 				a=true;
				 			}
				 		}
				 	}
				}
			}
		}
	}

	/**
     * Permet de vérifier si une case est déjà occupé.
     * @param i pour les lignes du tableau.
     * @param j pour les colonnes du tableau.
     * @return la valeur de la case si elle n'est pas occupée, 1 sinon.
     */
	public int valide(int i, int j){
		if(this.plt[i][j].getJoueur()==0){
			return this.plt[i][j].getJoueur();
		}
		return 1;
	}

	/**
     * Permet de compter les pions gagnants si c'est le joueur 1 qui gagne.
     * @return le nombre de pions gagants du joueur1 .
     */
	public int gagneF(){
		int j1=0;
		for(int i=0; i<plt.length; i++){
			for(int j=0; j<plt.length; j++){
				if(this.plt[i][j].getJoueur()==1){
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
	public int gagneS(){
		int j2=0;
		for(int i=0; i<plt.length; i++){
			for(int j=0; j<plt.length; j++){
				if(this.plt[i][j].getJoueur()==2){
					j2 = j2 + 1;
				}
			}
		}
		return j2;
	}


	public String toString() {
		String pl ="";
		String pp ="";
  		for(int i=0; i<plt.length; i++){
			for(int j=0; j<plt.length; j++){
    			pl += plt[i][j] + " | ";
    			if(j==7){
    				pl += "\n" + "-------------------------------" + "\n";
    			}
    		}
  		}
  		return pl;

 	}


}
