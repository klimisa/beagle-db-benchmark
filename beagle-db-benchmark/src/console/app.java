package console;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import beagle.benckmark.infrastucture.Benchmark;
import beagle.benckmark.infrastucture.BenchmarkThread;
import beagle.benckmark.infrastucture.SimpleBenchmark;

public class app {
	
	public static void main(String[] args)
	{
		BenchmarkThread tbenchmark = new BenchmarkThread();
		
		ExecutorService executor = Executors.newFixedThreadPool(100);
		for (int i = 0; i < 2; i++) {
			executor.submit(tbenchmark);
		}
		
		executor.shutdown();
		
//		Benchmark benchmark = new SimpleBenchmark();
//		benchmark.runBenchmark();		
	}
}
