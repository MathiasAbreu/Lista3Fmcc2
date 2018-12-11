package br.com;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

/**
 *
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 * 
 */
public class Principal {

	public static void main(String[] args) {
					
			BufferedImage imagem01 = Input.capturarImagem("src/br/com/Imagem01.png");
			BufferedImage imagem02 = Input.capturarImagem("src/br/com/Imagem02.png");
			
			int colunaImagem1 = imagem01.getWidth();
			int colunaImagem2 = imagem02.getWidth();
			
			int linhaImagem1 = imagem01.getHeight();
			int linhaImagem2 = imagem02.getHeight();
			
			int interacao = 1;
			
			int[] pixelsImagem1 = imagem01.getRGB(0, 0, colunaImagem1, linhaImagem1, null, 0, colunaImagem1);
			int[] pixelsImagem2 = imagem02.getRGB(0, 0, colunaImagem2, linhaImagem2, null, 0, colunaImagem1);
			
			while(!Arrays.equals(pixelsImagem1,pixelsImagem2)) {
				
				for(int col = 0; col < colunaImagem1; col++)
					for(int lin = 0; lin < linhaImagem1; lin++) {
						
						if(col <= colunaImagem1 && lin <= linhaImagem2) {
						
							Color corPixelImagem1 = new Color(imagem01.getRGB(col, lin));
							Color corPixelImagem2 = new Color(imagem02.getRGB(col, lin));
							
							int vermelho;
							int verde;
							int blue;
							int alpha;
							
							if(corPixelImagem1.getRed() == corPixelImagem2.getRed())
								vermelho = corPixelImagem1.getRed();
							else
								vermelho = (corPixelImagem1.getRed() < corPixelImagem2.getRed() ? corPixelImagem1.getRed() + 1 : corPixelImagem1.getRed() - 1);
							
							if(corPixelImagem1.getGreen() == corPixelImagem2.getGreen())
								verde = corPixelImagem1.getGreen();
							else
								verde = (corPixelImagem1.getGreen() < corPixelImagem2.getGreen() ? corPixelImagem1.getGreen() + 1 : corPixelImagem1.getGreen() - 1);
							
							if(corPixelImagem1.getBlue() == corPixelImagem2.getBlue())
								blue = corPixelImagem1.getBlue();
							else
								blue = (corPixelImagem1.getBlue() < corPixelImagem2.getBlue() ? corPixelImagem1.getBlue() + 1 : corPixelImagem1.getBlue() - 1);
							
							pixelsImagem1[colunaImagem1 * lin + col] = new Color(vermelho, verde, blue).getRGB();
						}
						//pixelsImagem1[colunaImagem1 * lin + col] = new Color((col + lin > 255 ? 255 : col + lin), col % 255, lin % 255).getRGB();
						//System.out.println(col + " | " + lin);
					}
				
				imagem01.setRGB(0, 0, colunaImagem1,linhaImagem1,pixelsImagem1,0,colunaImagem1);
				Input.escreverNovaImagem(imagem01,String.format("src/br/com/fade/teste%d.png",interacao));
				
				System.out.println("Imagem n°: "+ interacao);
				interacao ++;
			}
	}

}
