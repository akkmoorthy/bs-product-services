package com.bs.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@Configuration
//@ComponentScan(basePackages = "com.bs")
@EnableSolrRepositories(basePackages = {"com.bs.repository"}, multicoreSupport=true, namedQueriesLocation = "classpath:solr-named-queries.properties")
//@PropertySource("classpath:application.properties")
public class SolrConfig {

	@Bean
	public SolrClient solrClient() {
		return new HttpSolrClient("http://localhost:8983/solr");
	}

	@Bean
	public SolrTemplate solrTemplate(SolrClient client) throws Exception {
		return new SolrTemplate(client);
	}
}