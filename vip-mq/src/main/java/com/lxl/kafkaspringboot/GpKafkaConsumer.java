package com.lxl.kafkaspringboot;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.lxl.kafka.KafkaProperties;

import java.util.Optional;

/**
 */
@Component
public class GpKafkaConsumer {

	@KafkaListener(topics = { KafkaProperties.TOPIC2, "test" })
	public void listener(ConsumerRecord record) {
		Optional msg = Optional.ofNullable(record.value());
		if (msg.isPresent()) {
			System.out.println(msg.get() + ":" + record.value() + ":" + record.partition() + ":" + record.topic());
		}
	}
}
