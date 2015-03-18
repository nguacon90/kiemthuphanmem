package vn.com.nguacon.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import vn.com.nguacon.service.TicketService;

public class TicketServiceEquivalencePartitionTest {
	
	private TicketService ticketService;
	
	@Before
	public void setUp(){
		ticketService = new TicketService();
	}
	
	//age = 4
	@Test
	public void test1(){
		String actual = ticketService.getPromotion(4, false);
		assertEquals("invalid age", actual);
	}
	
	//age = 5
	@Test
	public void test2(){
		String actual = ticketService.getPromotion(5, false);
		assertEquals("50%", actual);
	}
	
	// age=15, not VIP => 50%
	@Test
	public void test3(){
		String actual = ticketService.getPromotion(15, false);
		assertEquals("50%", actual);
	}
	
	// age=65, is VIP => 60%
	@Test
	public void test4(){
		String actual = ticketService.getPromotion(65, true);
		assertEquals("60%", actual);
	}

	// age = 100, not vip
	@Test
	public void test5(){
		String actual = ticketService.getPromotion(100, false);
		assertEquals("50%", actual);
	}
	
	// age = 101
	@Test
	public void test6(){
		String actual = ticketService.getPromotion(100, false);
		assertEquals("invalid age", actual);
	}
}
