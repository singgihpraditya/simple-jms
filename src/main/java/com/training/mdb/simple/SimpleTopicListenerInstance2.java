package com.training.mdb.simple;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig ={
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue="javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "connectionFactoryJndiName", propertyValue="jms.TrainingCF"),
		@ActivationConfigProperty(propertyName = "destinationJndiName", propertyValue="jms.TopicTraining")
})
public class SimpleTopicListenerInstance2 implements MessageListener{
	public void onMessage(Message message) {
		System.out.println("instance 2 : ");
		
		try{
			String messageInString = "";
			if (message instanceof TextMessage) {
				messageInString = ((TextMessage) message).getText();
			}
			System.out.println("Incoming message (Instance 2) :" + messageInString);
		}catch(JMSException j) {
			System.err.print("Error 2 :" + j.getMessage());
		}
	}

}
