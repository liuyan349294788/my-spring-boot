package com.clockbone.web.configuration;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.sniff.Sniffer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

/**
 * @author: jun_qin
 * @create: 2019/4/25 15:03
 **/
@Configuration
public class esClient {

    private static RestClient restClient;
    private static Sniffer sniffer;

    @Bean
    public RestClient restClintInit() {
        try {
            HttpHost httpHostsArray = new HttpHost("localhost",9200,"http");

            RestClientBuilder builder = RestClient.builder(httpHostsArray);
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("", ""));

            Header[] defaultHeaders = new Header[]{new BasicHeader("header", "value")};
            builder.setDefaultHeaders(defaultHeaders);
            builder.setMaxRetryTimeoutMillis(5000);
            builder.setFailureListener(new RestClient.FailureListener() {
                @Override
                public void onFailure(HttpHost host) {
                }
            });
            builder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                @Override
                public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                    return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)
                            .setDefaultIOReactorConfig(IOReactorConfig.custom()
                                    .setIoThreadCount(6).build());
                }
            });

            builder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
                @Override
                public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
                    return requestConfigBuilder.setConnectTimeout(1000)
                            .setConnectionRequestTimeout(3000)
                            .setSocketTimeout(5000);
                }
            });
            restClient = builder.build();
            sniffer = snifferInit(restClient);
            return restClient;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * sniffer builder?
     */
    public Sniffer snifferInit(RestClient restClient) {
        return Sniffer.builder(restClient).setSniffIntervalMillis(60000).build();
    }



    /**
     * 关闭客户端
     */
    @PreDestroy
    public synchronized void clearESConnection() {
        try {
            if (null != sniffer) {
                sniffer.close();
            }
            if (null != restClient) {
                restClient.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
