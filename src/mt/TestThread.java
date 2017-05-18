package mt;

import java.text.ParseException;
import java.text.SimpleDateFormat;

class RDemo implements Runnable {
	   private int p=0;
	   private Thread t;
	   private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	   ThreadLocal<SimpleDateFormat> lsdf;
	   RDemo() {
		   lsdf = new ThreadLocal<SimpleDateFormat>() {
	            protected SimpleDateFormat initialValue() {
	                return new SimpleDateFormat("yyyy-MM-dd");
	            }
	        };
	   }
	   
	   public void run() {
	      try {
	         for(int i = 0; i < 10; i++) {
					if(p==1){try {
						System.out.println(lsdf.get().parse("2001-11-01"));
						
					} catch (Exception e) {
						System.out.println("lsdf exception");
					}
					} else{
					try {
						System.out.println(sdf.parse("2001-11-01"));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println("sdf exception");
					}
					}
					Thread.sleep(100);
	         }
	      }catch (InterruptedException e) {
	      }
	   }
	   
	   public void start (int i) {
		   this.p = i;
	      if (t == null) {
	         t = new Thread(this);
	         t.start();
	      }
	   }
	}

	public class TestThread {

	   public static void main(String args[]) throws ParseException {
		  SimpleDateFormat sdd = new SimpleDateFormat("yyyy-MM-dd");
		  System.out.println("date to display :\n" + sdd.parse("2001-11-01"));
		  System.out.println("Thread safe SimpleDataFormat\n======================\n");
		  RDemo R1 = new RDemo();
	      R1.start(1);
	      RDemo R2 = new RDemo();
	      R2.start(1);
	      
	      try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      System.out.println("Only SimpleDataFormat\n======================\n");
	      RDemo R3 = new RDemo();
	      R3.start(0);
	      RDemo R4 = new RDemo();
	      R4.start(0);
	      
	   }   
	}