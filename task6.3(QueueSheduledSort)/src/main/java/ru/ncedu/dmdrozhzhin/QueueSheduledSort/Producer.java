package ru.ncedu.dmdrozhzhin.QueueSheduledSort;
import ru.ncedu.dmdrozhzhin.quicksort.GenerateRandomArray;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    BlockingQueue blockingQueue;

    public Producer(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    private void generateArrays(int arraysLength) {
        while (true) {
            try {
                blockingQueue.add(GenerateRandomArray.genIntegerRandomArr(arraysLength));
                System.out.println("Элемент успешно добавлен в очередь. Текущий размер очереди = " + blockingQueue.size());
            } catch (IllegalStateException e) {
                System.out.println("Generator OFF");
                break;
            }
        }
    }

    public void run() {
        generateArrays(1000);
    }
}
