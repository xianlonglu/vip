package com.lxl.rocketmq.demo2;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransactionProducer {

	public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, InterruptedException {
		TransactionMQProducer transactionMQProducer = new TransactionMQProducer("tx_producer");
		transactionMQProducer.setNamesrvAddr("192.168.13.102:9876");
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		transactionMQProducer.setExecutorService(executorService);
		transactionMQProducer.setTransactionListener(new TransactionListenerLocal()); // 本地事务的监听

		transactionMQProducer.start();

		for (int i = 0; i < 20; i++) {
			String orderId = UUID.randomUUID().toString();
			String body = "{'operation':'doOrder','orderId':'" + orderId + "'}";
			Message message = new Message("order_tx_topic", "TagA", orderId,
					body.getBytes(RemotingHelper.DEFAULT_CHARSET));
			transactionMQProducer.sendMessageInTransaction(message, orderId + "&" + i);
			Thread.sleep(1000);
		}

	}
}
