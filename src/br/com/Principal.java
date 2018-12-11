package br.com;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * 
 */

/**
 *
 * @author Caio Fernandes Moreira - caio.moreira@ccc.ufcg.edu.br
 * @author Klaywert Danillo Ferreira De Souza - klaywert.souza@ccc.ufcg.edu.br
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 * 
 */
public class Principal {

	public static void main(String[] args) {
					
			BufferedImage imagem01 = Input.capturarImagem("src/br/com/Imagem01.png");
			BufferedImage imagem02 = Input.capturarImagem("src/br/com/Imagem02.png");
			
			int coluna = imagem01.getWidth();
			int linha = imagem01.getHeight();
			
			int[] pixels = imagem01.getRGB(0, 0, coluna, linha, null, 0, coluna);
			
			
			for(int col = 0; col < coluna / 2; col++)
				for(int lin = 0; lin < linha; lin++) {

					pixels[coluna * lin + col] = new Color((col + lin > 255 ? 100 : col + lin), col % 255, lin % 255).getRGB();
					//System.out.println(col + " | " + lin);
				}
			
			imagem01.setRGB(0, 0, coluna,linha,pixels,0,coluna);
			Input.escreverNovaImagem(imagem01,"src/br/teste01.png");

	}

}
