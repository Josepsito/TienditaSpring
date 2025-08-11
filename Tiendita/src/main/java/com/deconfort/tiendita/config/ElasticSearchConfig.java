package com.deconfort.tiendita.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.RestClient;

import javax.net.ssl.SSLContext;

public class ElasticSearchConfig {

    public ElasticsearchClient createClient() {
        try {
            // Crear contexto SSL que confía en todos los certificados
            SSLContext sslContext = SSLContexts.custom()
                    .loadTrustMaterial(null, new TrustAllStrategy())
                    .build();

            // Crear cliente HTTP que ignora hostname y usa sslContext
            CloseableHttpClient httpClient = HttpClients.custom()
                    .setSSLContext(sslContext)
                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                    .build();

            // Crear RestClient con HttpHost y cliente HTTP personalizado
            RestClient restClient = RestClient.builder(
                            new HttpHost("localhost", 9200, "https")
                    ).setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setSSLContext(sslContext)
                            .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE))
                    .build();

            // Crear transporte y cliente de Elasticsearch
            RestClientTransport transport = new RestClientTransport(
                    restClient, new JacksonJsonpMapper()
            );

            return new ElasticsearchClient(transport);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear cliente Elasticsearch", e);
        }
    }
}
