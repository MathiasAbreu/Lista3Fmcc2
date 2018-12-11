package principal;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 *
 * @author Caio Fernandes Moreira - caio.moreira@ccc.ufcg.edu.br
 * @author Klaywert Danillo Ferreira De Souza - klaywert.souza@ccc.ufcg.edu.br
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 * 
 */
public class LeituraEscrita {
	
	public static BufferedImage capturarImagem(String caminhoDaImagem) {
		
		try {
			
			BufferedImage imagem = ImageIO.read(new File(caminhoDaImagem));
			return imagem;
		
		} catch (IOException e) {

			throw new RuntimeException("Erro ao abrir Imagem: " + caminhoDaImagem + "!");
			
		}
	}

	public static void escreverNovaImagem(BufferedImage imagem, String caminhoDaImagem) {
		
		try {
			
			ImageIO.write(imagem, "PNG", new File(caminhoDaImagem));
			
		} catch (IOException e ) {
			
			throw new RuntimeException("Erro ao gerar imagem do fade");
		}
		
	}
}
