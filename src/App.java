import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // fazer uma conexão HTTp e buscar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();
        System.out.println(body);

        // extrair (parsear) só os dados que interessam (titulo, poster e avaliação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //  exibir e manipular os dados
        for (Map<String, String> filme : listaDeFilmes) {
            System.out.println("Nome do Filme: " + filme.get("title"));
            System.out.println(filme.get("image"));
            String rating = filme.get("imDbRating");
            for (int i = 0; i < Math.floor(Float.parseFloat(rating)); i++) {
                System.out.print("⭐");
            }
            System.out.print("\n\n");
        }
    }
}
