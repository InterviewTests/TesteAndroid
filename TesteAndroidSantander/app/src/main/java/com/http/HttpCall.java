package com.http;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Encapsula uma chamada HTTP
 */
public class HttpCall {

    /**
     * Método HTTP
     */
    public enum Method {
        GET, POST;
    }

    /**
     * User-agent da requisição
     */
    private String userAgent;

    /**
     * URL de destino
     */
    private String url;

    /**
     * Parâmetros da requisição
     */
    private Map<String, String> params = new HashMap<>();

    public HttpCall(String url) {
        this.url = url;
    }

    /**
     * Adiciona uma parâmetro na URL
     * @param name Nome do parâmetro
     * @param value Valor do parâmetro
     * @return O próprio objeto para encadeamento de chamadas
     */
    public HttpCall addParam(String name, String value) {
        params.put(name, value);
        return this;
    }

    /**
     * Executa a requisição
     * @param method Método HTTP
     * @return Resposta da requisição
     * @throws IOException
     */
    public HttpResponse execute(Method method) throws IOException {
        StringBuilder paramsStr = null;
        if (params.size() > 0) {
            // Se existem parâmetros, monta uma String com eles
            paramsStr = new StringBuilder();

            boolean first = true;

            for (Map.Entry<String, String> param : params.entrySet()) {
                if (!first) {
                    paramsStr.append("&");
                }

                paramsStr.append(param.getKey()).append("=").append(URLEncoder.encode(param.getValue(), "UTF-8"));
                first = false;
            }
        }

        URL urlObj = null;

        if (method == Method.GET) {
            // Para requisições GET, os parâmetros são passados na URL
            urlObj = new URL(url + "?" + paramsStr);
        } else {
            urlObj = new URL(url);
        }

        // Inicia a conexão com o servidor
        HttpURLConnection urlConn = (HttpURLConnection) urlObj.openConnection();

        // Define o método de requisição
        urlConn.setRequestMethod(method.toString());

        if (userAgent != null) {
            // Define o User-Agent
            urlConn.setRequestProperty("User-Agent", userAgent);
        }

        if (method == Method.POST && paramsStr != null) {
            // Para requisições POST com parâmetros, coloca os parâmetros no corpo da mensagem
            urlConn.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(urlConn.getOutputStream())) {
                wr.writeBytes(paramsStr.toString());
            }
        }

        // Lê os dados de retorno
        int responseCode = urlConn.getResponseCode();
        String responseMessage = urlConn.getResponseMessage();
        InputStream in = urlConn.getInputStream();

        // Cria um objeto HttpResponse, que representa a resposta do servidor
        return new HttpResponse(responseCode, responseMessage, in);
    }

    /**
     * Retorna a URL de destino
     * @return
     */
    public String getUrl() {
        return url;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getUserAgent() {
        return userAgent;
    }
}
