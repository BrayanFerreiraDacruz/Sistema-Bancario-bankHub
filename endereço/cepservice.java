package endereço;


import com.google.gson.Gson;
import Contas.Endereco;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class cepservice {
    public String buscaEnderecoPelo(String cep) throws Exception {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


        Gson gson = new Gson();
        Endereco enderecoObjeto = gson.fromJson(response.body(), Endereco.class);


        return enderecoObjeto.toString();
                
    }
}