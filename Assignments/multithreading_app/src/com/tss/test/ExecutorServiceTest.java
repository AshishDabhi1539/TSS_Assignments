package com.tss.test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.tss.task.NewTask;

public class ExecutorServiceTest {
	
	public static void main(String[] args) {
		//ExecutorService service = Executors.newCachedThreadPool();
		//ExecutorService service = Executors.newSingleThreadExecutor();
		/*ExecutorService service = Executors.newFixedThreadPool(2);
		service.submit(new MyTask());
		service.submit(new MyTask());		
		service.submit(new MyTask2());
		service.submit(new MyTask2());
		
		service.shutdown();*/
		ExecutorService service = Executors.newSingleThreadExecutor();

        try {
            Future<Integer> future = service.submit(new NewTask());

            int singleResult = future.get();
            System.out.println("Result from single task using Future: " + singleResult);

            List<Callable<Integer>> tasks = Arrays.asList(
                    new NewTask(),
                    new NewTask(),
                    new NewTask()
            );

            int fastestResult = service.invokeAny(tasks);
            System.out.println("Result from fastest task using invokeAny: " + fastestResult);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }
	}

}
