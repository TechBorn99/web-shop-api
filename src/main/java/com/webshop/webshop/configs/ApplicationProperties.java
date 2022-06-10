package com.webshop.webshop.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

    public final Security security = new Security();

    public static class Security {
        private String secret = "supersecret"; // todo: probably should remove
        private Long  expiration = 4800L;

        public String getSecret() {
            return this.secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public Long getExpiration() {
            return expiration;
        }

        public void setExpiration(Long expiration) {
            this.expiration = expiration;
        }
    }

    public Security getSecurity() { return security; }
}
