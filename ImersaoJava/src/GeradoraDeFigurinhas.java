import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
    


    public void cria( InputStream inputStream, String nomeArquivo, String text)throws Exception{
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
        var fonte = new Font("Impact",Font.BOLD,110);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);

        //escrever uma frase na nova imagem
        String texto = text;
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D retangulo = fontMetrics.getStringBounds(texto, graphics);
        int larguraTexto = (int) retangulo.getWidth();
        int posicaoTextoX = (largura-larguraTexto)/2;
        int posicaoTextoY = (novaAltura-110);
        graphics.drawString(texto,posicaoTextoX, posicaoTextoY);

        FontRenderContext fontRenderContext = graphics.getFontRenderContext();
        var textLayout = new TextLayout(texto,fonte,fontRenderContext);
        Shape outline = textLayout.getOutline(null);
        AffineTransform transform = graphics.getTransform();
        transform.translate(posicaoTextoX,posicaoTextoY);
        graphics.setTransform(transform);

        var outlineStroke = new BasicStroke(largura*0.004f);
        graphics.setStroke(outlineStroke);

        graphics.setColor(Color.black);
        graphics.draw(outline);
        graphics.setClip(outline);

        //escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem,"png",new File(nomeArquivo));
    }    
}
