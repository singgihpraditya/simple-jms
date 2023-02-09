package com.training.mdb.simple.test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.NamingException;

import com.training.mdb.util.ServiceFactory;

public class TestSimpleTopicListener {
	
	public static void main(String[] args) {
	
		Connection connection = null;
		Session session = null;
		MessageProducer  sender = null;
		
		String connectionFactoryName = "jms.TrainingCF";
		String TopicName = "jms.TopicTraining";
		
		ServiceFactory serviceFactory = new ServiceFactory();
		
		try{
			ConnectionFactory cf = (ConnectionFactory) serviceFactory.getService(connectionFactoryName);
			connection = cf.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Destination destination = (Destination) serviceFactory.getService(TopicName);
			sender = session.createProducer(destination);
			
			TextMessage textMessage = session.createTextMessage();
			textMessage.setText("Halloooo topic to JMS");
			
			connection.start();
			sender.send(textMessage);
			connection.close();
			
		System.out.println("Message sent");
			
			
		}catch(NamingException n) {
			n.printStackTrace();
		}catch(JMSException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			try{
				if (sender != null) {
					sender.close();
				} 
				if(session != null){
					session.close();
				}
				if (connection != null){
					connection.close();
				} 
				}catch(JMSException e){
					e.printStackTrace();
				}
			}
		}
	}

