import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
    


    public void cria( InputStream inputStream, String nomeArquivo)throws Exception{
        //Realiza a leitura da imagem
        // ImageInputStream inputStream = 
        //                              new FileImageInputStream(new File("entrada/filme-maior.jpg"));
         //InputStream inputStream = 
           //                         new URL("https://m.media-amazon.com/images/M/MV5BNzA5ZDNlZWMtM2NhNS00NDJjLTk4NDItYTRmY2EwMWZlMTY3XkEyXkFqcGdeQXVyNzkwMjQ5NzM@.jpg")
             //                       .openStream();
         BufferedImage imagemOrigianal = ImageIO.read(inputStream);

        //criar nova imagem em memória com transparência e com tamanho novo
        int largura = imagemOrigianal.getWidth();
        int altura = imagemOrigianal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura,novaAltura, BufferedImage.TRANSLUCENT);

        //copiar a imagem origial para nova imagem (em mémoria)
        Graphics2D graphics = (Graphics2D)novaImagem.getGraphics();
        graphics.drawImage(imagemOrigianal,0,0,null);
      

        //Configurar a fonte
        var fonte = new Font(Font.SANS_SERIF,Font.BOLD,64);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);

        //escrever uma frase na nova imagem

        graphics.drawString("TOPEZERA", 120, novaAltura-100);

        //escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem,"png",new File("saida/",nomeArquivo));
    }    
}
