// REFERENCE - CAVE OF PROGRAMMING - https://www.youtube.com/user/caveofprogramming
package thread.deadlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

	private Account acc1 = new Account();
	private Account acc2 = new Account();
	
	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();
	
	private void acquireLocks(Lock firstLock, Lock secondLock) throws InterruptedException{
		
		// can unlock locks that are taken in at any time - preventing deadlock
		
		while(true){
			
			boolean gotFirstLock = false;
			boolean gotSecondLock = false; 
			
			// makes sure that locks taken in are unlocked
			try{
				gotFirstLock = firstLock.tryLock();
				gotSecondLock = secondLock.tryLock();
			}finally{
				if(gotFirstLock && gotSecondLock){
					return;
				}
				if(gotFirstLock){
					firstLock.unlock();
				}
				
				if(gotSecondLock){
					secondLock.unlock();
				}
			}
			
			Thread.sleep(1);
		}
	}
	
	public void firstThread() throws InterruptedException{
		
		Random rand = new Random();
		for (int i = 0; i < 10000; i++) {
			
			acquireLocks(lock1, lock2);

			
			try{
				Account.transfer(acc1, acc2, rand.nextInt(100));
			}finally{
				lock1.unlock();
				lock2.unlock();
			}
		}
	}
	
	public void secondThread() throws InterruptedException{
		Random rand = new Random();
		for (int i = 0; i < 10000; i++) {
			acquireLocks(lock2, lock1);
			
			try{
				Account.transfer(acc2, acc1, rand.nextInt(100));
			}finally{
				lock1.unlock();
				lock2.unlock();
			}
		}
	}
	
	public void finished(){
		System.out.println("Account 1 balance: " + acc1.getBalance());
		System.out.println("Account 2 balance: " + acc2.getBalance());
		System.out.println("Total balance: " + (acc1.getBalance() + acc2.getBalance()));
	}
}
