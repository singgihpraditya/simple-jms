package com.training.mdb.simple;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue="javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "connectionFactoryJndiName", propertyValue="jms.TrainingCF"),
		@ActivationConfigProperty(propertyName = "destinationJndiName", propertyValue="jms.QueueTraining")
}, mappedName="jms.QueueTraining")
public class SimpleQueueListenerInstance2 implements MessageListener{
	
	public void onMessage(Message message){
		
		try{
			String messageInString = "";
			if (message instanceof TextMessage){
				messageInString = ((TextMessage) message).getText();
			} 
			System.out.println("Incoming message instance 2 " + messageInString);
			try{
				Thread.sleep(5000l);
				System.out.println("Process finished instance 2");
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}catch(JMSException e){
			System.err.println("Error " + e.getMessage());
		}
	}
}
