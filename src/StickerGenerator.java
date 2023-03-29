import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import  java.awt.Font;
import java.io.InputStream;

public class StickerGenerator {

    public void create(InputStream inputStream, String sticker) throws Exception{
        // leitura da imagem
        BufferedImage originalImage = ImageIO.read(inputStream);

        // cria nova imagem em memória com transparência e com tamanho novo
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeight = height + 200;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        // copiar a imagem original para nova iamgem (em memória)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);


        // font configuration
        var font = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(font);

        // escrever nova frase para nova imagem
        graphics.drawString("BEST MOVIE", 150, newHeight - 80);

        // escrever a nova imagem em um arquivo
        ImageIO.write(newImage, "png", new File("output/" + sticker));
    }

}
