package ru.ncedu.dmdrozhzhin.queueSortThread;

import ru.ncedu.dmdrozhzhin.quicksort.GenerateRandomArray;

public class FillingQueue {
    static int i = 1;
    QueueArray queueArray;
    FillingQueue(QueueArray queueArray){
        this.queueArray = queueArray;
    }

    public void addArray(){
        generateArrays();
    }

    private void generateArrays() {
        Integer[] integerMas = GenerateRandomArray.genIntegerRandomArr(30);
        addInQueue(integerMas);

    }
    private synchronized  void addInQueue(Integer[] arrToAdd)  {
        int masSize =  queueArray.getQueueIntegerMas().size();
        if(masSize<10) {
            System.out.println(i +" Добавляю массив в очередь ");
            i++;
            queueArray.addQueue(arrToAdd);
        }
        else {
            System.out.println("Вызываю метод wait in addInQueue ");
            try {
                wait();
                addInQueue(arrToAdd);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
