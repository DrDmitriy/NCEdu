package ru.ncedu.dmdrozhzhin.queueSortThread;

import ru.ncedu.dmdrozhzhin.quicksort.GenerateRandomArray;

public class FillingQueue implements Runnable {
    static int i = 1;
    QueueArray queueArray;

    FillingQueue(QueueArray queueArray) {
        this.queueArray = queueArray;
    }

    /*  public void addArray(){
          generateArrays();
      }
  */
    private void generateArrays() {
        Integer[] integerMas = GenerateRandomArray.genIntegerRandomArr(30);
        addInQueue(integerMas);

    }

    private void addInQueue(Integer[] arrToAdd) {
        synchronized ( queueArray) {
            int masSize = queueArray.getQueueIntegerMas().size();
            if (masSize < 10) {
                System.out.println(i + " Добавляю массив в очередь     ");

                i++;
               try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queueArray.addQueue(arrToAdd);
                queueArray.notify();
                System.out.println("   Текущий размер очереди "+ queueArray.getQueueIntegerMas().size());
            } else {
                System.out.println("Вызываю метод wait in addInQueue ");
                try {

                    queueArray.wait();


                    addInQueue(arrToAdd);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void run() {
        while (true) {
            generateArrays();
        }
    }
}
