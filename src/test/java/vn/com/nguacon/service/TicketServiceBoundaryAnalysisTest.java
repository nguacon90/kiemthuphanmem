package vn.com.nguacon.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import vn.com.nguacon.service.TicketService;

public class TicketServiceBoundaryAnalysisTest {
	
	private TicketService ticketService;
	
	@Before
	public void setUp(){
		ticketService = new TicketService();
	}
	
	//age < 5
	@Test
	public void test1(){
		String actual = ticketService.getPromotion(4, false);
		assertEquals("invalid age", actual);
	}
	
	// 5<=age<=15, not VIP => 50%
	@Test
	public void test2(){
		String actual = ticketService.getPromotion(10, false);
		assertEquals("50%", actual);
	}
	
	// 5<=age<=15, is VIP => 60%
	@Test
	public void test3(){
		String actual = ticketService.getPromotion(10, true);
		assertEquals("60%", actual);
	}

	// 65 <= age < 100, not VIP => 50%
	@Test
	public void test4(){
		String actual = ticketService.getPromotion(75, false);
		assertEquals("50%", actual);
	}
	
	// 65<=age<100, is VIP => 60%
	@Test
	public void test5(){
		String actual = ticketService.getPromotion(75, true);
		assertEquals("60%", actual);
	}
	
	// age > 100
	@Test
	public void test6(){
		String actual = ticketService.getPromotion(120, true);
		assertEquals("invalid age", actual);
	}
	
	
}
