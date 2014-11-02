package com.kodcu.app.aspects;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.aspectj.lang.Signature;
import com.kodcu.app.main.Startup;
/**
 * 
 * @author HAKAN
 *
 */
public aspect TraceAspect {

	private final Logger log = Logger.getLogger("com.kodcu.app.main");
	private OutputStream output;

	pointcut trace() : execution(* Startup.main(..)) && within(Startup);
	
	before() : trace(){
		startTracing();

		Runnable r = () -> {
			System.out.println("### Starting ###");
		};
		r.run();
	}

	after() : trace(){
		stopTracing();
		Runnable r = () -> {
			System.out.println("### See the trace file under MaJ/trace.txt ");
			System.out.println("### Ending ###");
		};
		r.run();
	}

	// trace all kind of method calls within the class "Startup"
	after() : call(* *(..)) && within(Startup){
		Signature signature = thisJoinPoint.getSignature();

		try {
			output.write(("Join Point::" + signature.toLongString() + "\n")
					.getBytes());
		} catch (IOException e) {
			log.log(Level.SEVERE, "Error: ", e.getMessage());
		}
	}

	private void startTracing() {
		try {
			output = new FileOutputStream("trace.txt", false);
			output.write("First ====> trace all kind of method calls within the class \"Startup\"\n".getBytes());
		} catch (IOException e) {
			log.log(Level.SEVERE, "Error: ", e.getMessage());
		}
	}

	private void stopTracing() {
		try {
			output.write("<==== Last".getBytes());
			output.close();
		} catch (IOException e) {
			log.log(Level.SEVERE, "Error: ", e.getMessage());
		}
	}

}
