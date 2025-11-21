import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) {
        if (args.length < 3){
            System.out.println("Bro WTF IS THIS NOT ENOUGH ARGS LMAO!");
            System.exit(-1);
        }
        int numarThreads = Integer.parseInt(args[0]);
        String fisierArticole = args[1];
        String fisierSuplimentar = args[2];
        System.out.println("Threads: " + numarThreads);
        System.out.println("Articles file: " + fisierArticole);
        System.out.println("Supplementary file: " + fisierSuplimentar);
        
        BlockingQueue<String> fileQueue = new LinkedBlockingQueue<>();
        
        try(BufferedReader br = new BufferedReader(new FileReader(fisierArticole))){
            int numFiles = Integer.parseInt(br.readLine().trim());
            System.out.println("Number of files to process: " + numFiles);
            
            for (int i = 0; i < numFiles; i++) {
                String filePath = br.readLine().trim();
                fileQueue.add(filePath);
                System.out.println("Added to queue: " + filePath);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        
        System.out.println("\n=== Starting " + numarThreads + " threads ===\n");
        
        Thread[] threads = new Thread[numarThreads];
        for(int i = 0; i < numarThreads;i++){   
            threads[i] = new Thread(new FileProcessor(fileQueue), "Worker-" + i);
            threads[i].start();
        }
        
        for(int i = 0; i < numarThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("\n=== All threads finished ===");
    }
}

class FileProcessor implements Runnable {
    private BlockingQueue<String> fileQueue;
    public FileProcessor(BlockingQueue<String> fileQueue) {
        this.fileQueue = fileQueue;
    }
    @Override
    public void run() {
        while (true) {
            String fileName = fileQueue.poll();
            if (fileName == null) {
                break; // No more files to process
            }
            System.out.println("[" + Thread.currentThread().getName() + "] Processing file: " + fileName);
            
            // Simulate some work
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            System.out.println("[" + Thread.currentThread().getName() + "] Finished: " + fileName);
        }
        System.out.println("[" + Thread.currentThread().getName() + "] No more files, exiting");
    }
}