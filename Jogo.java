package Pacote_do_jogo;
import java.util.Scanner;

public class Jogo {

	private char matriz[][] = new char[3][3];
	private char sinal = 'H';
	private int nJogadas = 0;
	Scanner scan = new Scanner(System.in);

	Jogo() {
		// Incializar Matriz
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				matriz[i][j] = ' ';
			}
		}
		System.out.print("\nSeu marcador será o X");

	}

	// ---------------- Ação do jogador -----------------------

	void jogar() {
		boolean vencedor;
		int linha = 0;
		int coluna = 0;
		int resposta = 1;
		while(resposta == 1) {
			inicializar();
			this.nJogadas = 0;
			vencedor = false;
			imprimir();
			while (!vencedor && this.nJogadas <= 4) {
				
				do {
					linha = lerDadosLinha();
					coluna = lerDadosColuna();
					linha--;
					coluna--;
				} while (getCords(linha, coluna));
	
				this.matriz[linha][coluna] = 'X';
				
	
				imprimir();
	
				vencedor = ganhou();
				if (vencedor != false)
					break;
	
				// Resposta da máquina --------------------
				System.out.println("\nAnalisando jogo...\n");
				bootPlay(this.nJogadas);
				
				imprimir();
	
				vencedor = ganhou();
				if (vencedor != false)
					break;
	
	
				this.nJogadas++;
			}
			if (this.nJogadas == 5)
				System.out.printf("\n ------------- DEU VELHA!! --------------");
			else
				System.out.printf("\n ------------- O jogador < %c > ganhou o jogo!! --------------", this.sinal);
			
			System.out.println("\nDeseja continuar?? <1 para SIM // 0 para NÃO> ");
			resposta = scan.nextInt();
		}
		
	}
	// ------------- Ferramentas para o jogo ------------------

	private void inicializar() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				this.matriz[i][j] = ' ';
			}
		}
	}
	
	// LEITURA DE DADOS ------------------------------------
private int lerDadosLinha() {
		int linha;
		System.out.println("\nDigite a LINHA <1 a 3>");
		linha = scan.nextInt();
		return linha;

	}

private int lerDadosColuna() {
		int coluna;
		System.out.println("\nDigite a COLUNA <1 a 3>");
		coluna = scan.nextInt();
		return coluna;
	}

	// VERIFICAÇÕES DOS VALORES ---------------------------------------------
private boolean getCords(int linha, int coluna) {

		if (verificarDigito(linha, coluna) || verificarEspaco(linha, coluna)) {
			return true;
		}
		return false;
	}

private boolean verificarEspaco(int linha, int coluna) {

		if (this.matriz[linha][coluna] != ' ') {
			System.out.println("\nLocal inválido!\n");
			return true;
		}
		return false;
	}

private boolean verificarDigito(int linha, int coluna) {

		if (linha > 2 || coluna > 2 || linha < 0 || coluna < 0) {
			System.out.println("Valor inválido!");
			return true;
		}
		return false;
	}

	// VERIFICÃO DO TABULEIRO

private boolean ganhou() {
		if (verificarLinhas() || verificarColunas() || verificarDiagonais())
			return true;
		return false;
	}

private boolean verificarLinhas() {
		int contX = 0;
		int contO = 0;

		for (int i = 0; i < 3; i++) {
			contX = 0;
			contO = 0;
			for (int j = 0; j < 3; j++) {

				if (this.matriz[i][j] == 'X')
					contX++;
				else if (this.matriz[i][j] == 'O')
					contO++;
			}
			if (contX == 3) {
				this.sinal = 'X';
				return true;
			} else if (contO == 3) {
				this.sinal = 'O';
				return true;
			}

		}

		return false;

	}

private boolean verificarColunas() {
		int contX = 0;
		int contO = 0;

		for (int i = 0; i < 3; i++) {
			contX = 0;
			contO = 0;
			for (int j = 0; j < 3; j++) {

				if (this.matriz[j][i] == 'X')
					contX++;
				else if (this.matriz[j][i] == 'O')
					contO++;
			}
			if (contX == 3) {
				this.sinal = 'X';
				return true;
			} else if (contO == 3) {
				this.sinal = 'O';
				return true;
			}

		}
		return false;
	}

private boolean verificarDiagonais() {
		if (this.matriz[0][0] == 'X' && this.matriz[1][1] == 'X' && this.matriz[2][2] == 'X') {
			this.sinal = 'X';
			return true;
		} else if (this.matriz[0][0] == 'O' && this.matriz[1][1] == 'O' && this.matriz[2][2] == 'O') {
			this.sinal = 'O';
			return true;
		} else if ((this.matriz[2][0] == 'X' && this.matriz[1][1] == 'X' && this.matriz[0][2] == 'X')) {
			this.sinal = 'X';
			return true;
		} else if ((this.matriz[2][0] == 'O' && this.matriz[1][1] == 'O' && this.matriz[0][2] == 'O')) {
			this.sinal = 'O';
			return true;
		}

		return false;
	}

	// RESPOSTA DA MÁQUINA ------------------------

private void bootPlay(int jogadas) {
		boolean marcador = false;
		if (jogadas == 0)
			primeiraJogada();
		else {
			marcador = nivel1();
			if (!marcador)
				marcador = nivel2();
		}
	}

private void primeiraJogada() {
		if (this.matriz[1][1] == ' ')
			this.matriz[1][1] = 'O';
		else
			this.matriz[0][0] = 'O';

	}

private boolean nivel1() {
	 
	 int temXLinha = -1;
	 int temXColuna = -1;
	 int temXDiagonal1 = -1;
	 int temXDiagonal2 = -1;
	 int contX = 0;
	 int contO = 0;
	 
	 // =========================== Verificação das linhas =======================
	 for(int i= 0;i < 3; i++ ) { 
		 contX = 0;
		 contO = 0;
		 for(int j = 0; j < 3; j++) {
				 
			 if(this.matriz[i][j] == 'X')
				 contX++;
			 else if(this.matriz[i][j] == 'O')
				 contO++;
			 
			 if( contO == 2 && contX== 0 && j == 2){// Condição para possível vitoria
				for(int f = 0; f < 3; f++) {
					if(this.matriz[i][f] == ' ') 
						this.matriz[i][f] = 'O';	
				}
				return true;
			 }
			 else if(contO == 0 && contX == 2 && j == 2){//Condição para defesa
				 temXLinha = i;
				
			}
				
		}
				 
	}
	

	// ======================= Verificação das colunas ================================
	for(int i= 0;i < 3; i++ ) { 
		contX = 0;
		contO = 0;
		for(int j = 0; j < 3; j++) { 
			if(this.matriz[j][i] == 'X')
				contX++;
			else if(this.matriz[j][i] == 'O')
				contO++;
			 
			if(contO == 2 && contX == 0 && j == 2){// Condição para vencer o jogo
				for(int f = 0; f < 3; f++) {
					if(this.matriz[f][i] == ' ') 
						this.matriz[f][i] = 'O';
				}
				return true;
					 
			}
			else if(contO == 0 && contX == 2 && j == 2) {
					 temXColuna = i;
			}
		 }
	 }

	 // ================= Verificação de diagonais ==============================
	int l = 0;
	int c = 0;
	contX = 0;
	contO = 0;
	 
	while (l < 3) {
		if (this.matriz[l][c] == 'X')
			contX++;
		else if(this.matriz[l][c] == 'O')
			contO++;
		 
		if(l == 2 && contX == 0 && contO == 2) {
			l = 0;
		 	c = 0;
		 	while(l < 3) {
		 		if(this.matriz[l][c] == ' ') {
		 			this.matriz[l][c] = 'O';
		 			return true;
		 		}
		 		l++;
		 		c++;
		 	}//while
		 	
		 }//if
		 else if(l == 2 && contX == 2 && contO == 0) {
			 temXDiagonal1 = 1;// Diagonal -> \
		 }
		 
	l++;
	c++;
		 	 
	}//while
	 
	 l = 2;
	 c = 0;
	 contX = 0;
	 contO = 0;
	 
	 while (c < 3) {
		 if (this.matriz[l][c] == 'X')
			contX++;
		 else if(this.matriz[l][c] == 'O')
			contO++;
		 
		 if(c == 2 && contX == 0 && contO == 2) {// Condição de vitoria do O
			l = 2;
		 	c = 0;
		 	while(c < 3) {
		 		if(this.matriz[l][c] == ' ') {
		 			this.matriz[l][c] = 'O';
		 			return true;
		 		}
		 		l--;
		 		c++;
		 	}//while
		 	
		 }//if
		 else if(c == 2 && contX == 2 && contO == 0) {
			 temXDiagonal2 = 1;
		 }
		 
	l--;
	c++;
		 	 
	}//while
	
	 //========= Reação para defesa após verificar possível vitoria ==============
	 
	 if(temXLinha != -1) {
		for(int g = 0; g< 3; g++) {
			if(this.matriz[temXLinha][g] == ' ')
				this.matriz[temXLinha][g] = 'O';
			
		}
		return true;
	 }
	 else if(temXColuna != -1) {
		 for(int g = 0; g< 3; g++) {
				if(this.matriz[g][temXColuna] == ' ')
					this.matriz[g][temXColuna] = 'O'; 
				
		 }
		 return true;
	 }else if(temXDiagonal1 != -1) {
		l = 0;
		c = 0; 
		while (l < 3) {
			if(this.matriz[l][c] == ' ') {
				this.matriz[l][c] = 'O';
					return true;
			}
			l++;
			c++;
			return true;
		}//while
		
		return true;
		
	 }else if(temXDiagonal2 != -1) {
		l = 2;
		c = 0; 
		while (c < 3) {	
			if(this.matriz[l][c] == ' ') {
				this.matriz[l][c] = 'O';
					return true;
			}
			l--;
			c++;
		}//while
		return true;
	 }
			
	 return false;
}

	private boolean nivel2() {
		int cont = 0;
		
		if(this.nJogadas == 1) {// Verificar meios 
			System.out.println("Entrou nos meios");
			
			
			if(this.matriz[0][1] == 'X')
				cont++;
			if(this.matriz[1][0] == 'X')
				cont++;
			if(this.matriz[2][1] == 'X')
				cont++;
			if(this.matriz[1][2] == 'X')
				cont++;
			
			if(cont == 2) {// Tentar marcar diagonais
				if(this.matriz[0][0] == ' ') {
					this.matriz[0][0] = 'O';
					return true;
				}
				
				else if(this.matriz[1][2] == ' ') {
					this.matriz[0][0] = 'O';
					return true;
				}
				
				else if(this.matriz[2][0] == ' ') {
					this.matriz[0][0] = 'O';
					return true;
				}
				
				else if(this.matriz[0][2] == ' ') {
					this.matriz[0][2] = 'O';
					return true;
				}
			}
		}
		
		
		if (this.matriz[1][0] == ' ') {
			this.matriz[1][0] = 'O';
			
			return true;
		}
	
	
		else if (this.matriz[1][2] == ' ') {
			this.matriz[1][2] = 'O';
			
			return true;
		}
	
	
		else if (this.matriz[0][1] == ' ') {
			this.matriz[0][1] = 'O';
			
			return true;
		}
	

		else if (this.matriz[2][1] == ' ') {
			this.matriz[2][1] = 'O';
			
			return true;
		}
			
		
		for (int i = 0; i < 3; i++) { // Caso sobre espaço
			for (int j = 0; j < 3; j++) {
				if (this.matriz[i][j] == ' ') {
					this.matriz[i][j] = 'O';
					return true;
				}
			}
		}
		return false;
	}

	// IMPRESSÃO DO TABULEIRO -------------------------------------
	void imprimir() {
		System.out.print("\n\n   1 2 3");
		for (int i = 0; i < 3; i++) {

			System.out.print("\n");
			
			// Adicionar linhas
			System.out.print("  -");
			
			System.out.print("-");
			for (int h = 0; h < 3; h++)
				System.out.print("--");
			// -------------
			System.out.print("\n");
			System.out.printf("%d ",i + 1);
			System.out.print("|");

			for (int j = 0; j < 3; j++) {
				System.out.print(this.matriz[i][j]);
				System.out.print("|");
			}

		}
		System.out.print("\n");

		// Adicionar linha
		System.out.print("  -");
		for (int h = 0; h < 3; h++)
			System.out.print("--");
	}
}
