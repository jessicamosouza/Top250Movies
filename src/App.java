import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // fazer uma conexão HTTp e buscar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI uri = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();
        System.out.println(body);

        // extrair (parsear) só os dados que interessam (titulo, poster e avaliação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> movieList = parser.parse(body);

        // exibir e manipular os dados
        var geradora = new StickerGenerator();
        for (Map<String, String> movie : movieList) {

            String urlImagem = movie.get("image");
            String titulo = movie.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = titulo + ".png";

            geradora.create(inputStream, nomeArquivo);

            System.out.println("Movie: " + movie.get("title"));
            System.out.println( movie.get("imDbRating")+ "⭐");
        }
    }
}
