package com.bs.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bs.services.IndexService;

@Component
public class SolrScheduler {
	@Autowired
	private IndexService indexService;

	@Scheduled(fixedRate = 90000000)
	public void scheduleFixedDelayTask() {
		System.out.println("Fixed delay task - " + System.currentTimeMillis() / 1000);
		indexService.indexUserData();
	}
}