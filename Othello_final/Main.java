import java.util.Scanner;
import MG2D.* ;
import MG2D.geometrie.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

class Main {
    public static void main(String args[]) {
	
		Plateau plateau = new Plateau() ;

		//mode console
		plateau.setPlt(3, 3, 1);
		plateau.setPlt(3, 4, 2);
		plateau.setPlt(4, 3, 2);
		plateau.setPlt(4, 4, 1);

		/*int v1=0;
		int v2=0;
		int i1=0;
		int i2=0;
		int i3=0;
		int i4=0;

		do{
			//joueur1 qui change la case avec la valeur 1
			System.out.println("\n"+plateau);

		// Un seul scanner suffit car il permet la saisie de manière globale
			do{
				Scanner sc = new Scanner(System.in);
			
				//on rentre l'abscisse de la case voulue(de 1 à 8)
				String str1 = sc.next();

				//on rentre l'ordonnée de la case voulue(de 1 à 8)
				String str2 = sc.next();
				i1 = Integer.parseInt(str1);
				i2 = Integer.parseInt(str2);
				v1 = plateau.valide(i1-1, i2-1);
				System.out.println(v1+ "\n");
			}while(v1!=0);// le joueur joue tant qu'il ne pose pas son pion sur une case non occupée

			plateau.setPlt(i1-1, i2-1, 1);
		
			//si le la case voulue est adjacente à une case différente(ex : case voulue qui a la valeur 1 et case adjacente qui a la valeur 2)
			if(plateau.entoure()){
				//on regarde si il y a des pions à retourner
				plateau.setPion();
			}
			System.out.println("\n"+plateau);

			do{
				Scanner sc3 = new Scanner(System.in);
				Scanner sc4 = new Scanner(System.in);
				String str3 = sc3.next();
				String str4 = sc4.next();
				i3 = Integer.parseInt(str3);
				i4 = Integer.parseInt(str4);
				v2 = plateau.valide(i3-1, i4-1);
				System.out.println(v2+"\n");
			}while(v2!=0);

			plateau.setPlt(i3-1, i4-1, 2);
		
			//idem
			if(plateau.entoure()){
				//idem
				plateau.setPion();
			}
		}while(!plateau.plein());//tant que le plateau n'est pas remplie

		//on compte le nombre de pions du gagnants et on l'affiche sur le terminal
		int g1 = plateau.gagneF();
		int g2 = plateau.gagneS();
		System.out.println(g1+" "+g2);
		if(g1>g2){
			System.out.println("le joueur1 gagne");
		}
		else{
			System.out.println("le joueur2 gagne");
		}*/



		//mode graphique
		Fenetre f = new Fenetre ( "Othello", 1200, 650) ;
		f.setBackground(new Color(34,139,34));
		f.rafraichir();
		InterfaceGraphique i = new InterfaceGraphique(f);

		do{
			// le premier joueur pose son pion
			int[] co = i.getCoup(f, 1, plateau);

			int m1 = (Integer) co[0];
			int m2 = (Integer) co[1];

			//on retourne les pions si on retrouve des pions adjacents differents du pion pose 
			// on met en parametre le numero de ligne et de colonne du tableau (m1 et m2)
			i.setRetourner(f, m1, m2);
			//si le plateau est plein
			if(i.pionPlein()){
				// on affciche le score 
				i.affichageF(f);
				int g1 = i.bilanF();
				int g2 = i.bilanS();
				System.out.println(g1+" "+g2);
				if(g1>g2){
					i.vainq(f, g1, g2, 1);
				}
				else{
					i.vainq(f, g2, g1, 2);
				}
				// on ferme la fenetre
				i.getClo(f);
			}

			//le deuxieme joueur pose son pion
			int[] cou = i.getCoup(f, 2, plateau);
			
			int m3 = (Integer) cou[0];
			int m4 = (Integer) cou[1];

			i.setRetourner(f, m3, m4);
			if(i.pionPlein()){
				i.affichageF(f);
				int g1 = i.bilanF();
				int g2 = i.bilanS();
				System.out.println(g1+" "+g2);
				if(g1>g2){
					i.vainq(f, g1, g2, 1);
				}
				else{
					i.vainq(f, g2, g1, 2);
				}

				i.getClo(f);
			}

		}while(!i.pionPlein());//tant que le plateau n'est pas remplie
    }
}


