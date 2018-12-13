package principal;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
		
	private JLabel label;

	public Principal() {
		
	}

	public void criarJanela(ImageIcon imagemParaMostrar) {
		
		label = new JLabel(imagemParaMostrar);
		
		add(label);
		setSize(imagemParaMostrar.getIconHeight(),imagemParaMostrar.getIconWidth());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setName("Visualizador do fade");
		setLocationRelativeTo(this);
		setVisible(true);
		
	}
	
	public void atualizaJanela(ImageIcon imagemParaMostrar) {
		
		remove(label);
		label = new JLabel(imagemParaMostrar);
		add(label);
		
		repaint();
		validate();
		
	}
	
	public static void main(String[] args) {
				
		System.out.println("Lendo imagens..");
		
		BufferedImage imagem01 = capturarImagem("src/input/Imagem02.jpg");
		BufferedImage imagem02 = capturarImagem("src/input/Imagem01.jpg");
		
		System.out.println("Gerando fade..\nO processo pode demorar até 4 minutos..");
		Principal janelaPrincipal = new Principal();
		
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
						
						int red   = corPixelImagem1.getRed();
						int green = corPixelImagem1.getGreen();
						int blue  = corPixelImagem1.getBlue();
						int alpha = corPixelImagem1.getAlpha();
						
						if(corPixelImagem1.getRed() != corPixelImagem2.getRed())
							red = (corPixelImagem1.getRed() < corPixelImagem2.getRed() ? corPixelImagem1.getRed() + 1 : corPixelImagem1.getRed() - 1);
						
						if(corPixelImagem1.getGreen() != corPixelImagem2.getGreen())
							green = (corPixelImagem1.getGreen() < corPixelImagem2.getGreen() ? corPixelImagem1.getGreen() + 1 : corPixelImagem1.getGreen() - 1);
						
						if(corPixelImagem1.getBlue() != corPixelImagem2.getBlue())
							blue = (corPixelImagem1.getBlue() < corPixelImagem2.getBlue() ? corPixelImagem1.getBlue() + 1 : corPixelImagem1.getBlue() - 1);
						
						if(corPixelImagem1.getAlpha() != corPixelImagem2.getAlpha())
							alpha = (corPixelImagem1.getAlpha() < corPixelImagem2.getAlpha() ? corPixelImagem1.getAlpha() + 1 : corPixelImagem1.getAlpha() - 1);
						
						pixelsImagem1[colunaImagem1 * lin + col] = new Color(red, green, blue, alpha).getRGB();
					}
				}
			
			imagem01.setRGB(0, 0, colunaImagem1,linhaImagem1,pixelsImagem1,0,colunaImagem1);
			escreverNovaImagem(imagem01,String.format("src/output/frame%d.png",interacao));
			
			if(interacao == 1)
				janelaPrincipal.criarJanela(new ImageIcon(imagem01));
			else
				janelaPrincipal.atualizaJanela(new ImageIcon(imagem01));
			
			interacao ++;
		}
		System.out.println("Fade concluido. N° de frames gerados: "+ (interacao - 1));
	
	}
	
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


