package sn.galsen.dev.coopar_dev.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("sn.galsen.dev.coopar_dev.domain")
@EnableJpaRepositories("sn.galsen.dev.coopar_dev.repos")
@EnableTransactionManagement
public class DomainConfig {
}
