import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {     
        //Realizar uma conexão via HTTP e buscar os top 250 filmes 
        String url = "https://gist.githubusercontent.com/lucasfugisawa/cbb0d10ee3901bd0541468e431c629b3/raw/1fe1f3340dfe5b5876a209c0e8226d090f6aef10/Top250Movies.json";
        URI endereco  = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request,BodyHandlers.ofString());
        String body = response.body();     
        //Extrarir somente os dados interessantes (Titulo, Imagem e classificação) "parsear os dados"
        var parser = new JsonParser();
        List<Map<String, String>> ListaDeFilme = parser.parse(body);
       
        
        var diretorio = new File("saida/");
        diretorio.mkdir();
        
        //Exibir e manipular os dados conforme desejado. 
      
          var geradora = new GeradoraDeFigurinhas();
          for (int index = 0; index < 5;  index++) {
            var filme = ListaDeFilme.get(index);

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = diretorio+titulo+".png";


            geradora.cria(inputStream, nomeArquivo);

            //impressão de dados com decoração \u001b declaração inicial [1m valor ou conjução desejada
            System.out.println("\u001b[1m"+titulo+"u001b[m");
            
            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            int numeroDeEstrelas = (int) classificacao;
                    //imprimir o numero conforme classificação
                    if (numeroDeEstrelas >8 && numeroDeEstrelas<=10){ 
                        //imprimir a quantidade de estrelas conforme a classificação de acordo com imDbRatting
                        for (int n = 1; n <= numeroDeEstrelas; n++) {
                            System.out.print("⭐");              
                        }
                        //condição de corte 2 se maior ou igual 5 ou menor igual a 8
                    } else if(numeroDeEstrelas >=5 && numeroDeEstrelas <=8){
                        for (int n = 1; n <= numeroDeEstrelas; n++) {
                        System.out.print("👍");          
                        }
                        //condição para classificações menores que 5
                    } else {
                        for (int n = 1; n <= numeroDeEstrelas; n++) {
                            System.out.print("👎");          
                        }
                    }
                    System.out.println("\n");  
                    }
                      
        }
                   
  
    } 
