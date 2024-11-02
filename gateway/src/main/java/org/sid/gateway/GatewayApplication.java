package org.sid.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

  //@Bean
    public RouteLocator routesDefinitionStatique(RouteLocatorBuilder builder) {
        return builder.routes()

                //les routes statiques par la configuracion java
//                .route(r->r.path("/customers/**").uri("http//localhost:8081"))
//                .route(r->r.path("/products/**").uri("http//localhost:8082"))
//1er methode
//lb c est load balancer , qd il y a une request de /customers -> aller au service customer-service
                //gateway va aller au eurika service et demmande l adresse de nom de service
               .route(r->r.path("/customers/**").uri("lb://CUSTOMERSERVICE"))
                .route(r->r.path("/products/**").uri("lb://INVENTORY-SERVICE"))
                .build();
    }

    /**
     * Configuration d'une fonction pour définir des routes dynamiques dans la passerelle (gateway).
     * Cette méthode retourne un bean de type DiscoveryClientRouteDefinitionLocator,
     * qui utilise le client de découverte pour générer automatiquement des routes.
     *
     * @param rdc - Client de découverte réactif (ReactiveDiscoveryClient)
     * @param dlp - Propriétés de configuration des routes (DiscoveryLocatorProperties)
     * @return DiscoveryClientRouteDefinitionLocator configuré pour les routes dynamiques
     */
    @Bean // Indique à Spring de gérer l'objet retourné comme un bean
    DiscoveryClientRouteDefinitionLocator dynamicRoutes(ReactiveDiscoveryClient rdc,
                                                        DiscoveryLocatorProperties dlp) {
        // Retourne une instance de DiscoveryClientRouteDefinitionLocator
        // qui permet la configuration dynamique des routes dans la passerelle
        // en se basant sur les services enregistrés dans le client de découverte
        return new DiscoveryClientRouteDefinitionLocator(rdc, dlp);
    }



}
