package java4;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void quickSort(ArrayList<Integer> array, int low, int high) {
        if (array.size() == 0)
            return;
        if (low >= high)
            return;
        int middle = low + (high - low) / 2;
        int opora = array.get(middle);
        int i = low, j = high;
        while (i <= j) {
            while (array.get(i) < opora) {
                i++;
            }

            while (array.get(j) > opora) {
                j--;
            }
            if (i <= j) {
                int temp = array.get(i);
                array.set(i, array.get(j));
                array.set(j, temp);
                i++;
                j--;
            }
        }
        if (low < j)
            quickSort(array, low, j);

        if (high > i)
            quickSort(array, i, high);
    }
    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/java4/starterFile.txt"));
        for(int i=0; i<170000000; i++){
            Random random = new Random();
            int j = random.nextInt(1, 100000);
            writer.write(Integer.toString(j) + '\n');
        }
        writer.close();

        File file = new File("src/java4/starterFile.txt");
        System.out.println(file.length());
        File starterFileSizeC = new File("src/java4/starterFile.txt");
        FileInputStream starterFile = new FileInputStream("src/java4/starterFile.txt");
        BufferedInputStream buffStream = new BufferedInputStream(starterFile);
        long fileSize = starterFileSizeC.length();
        ArrayList<Integer> seria = new ArrayList<>();
        int rest = (int)fileSize%10;
        byte[] seriaBytes = new byte[(int)fileSize/10];

        StringBuilder number = new StringBuilder();
        BufferedWriter writerSeria = new BufferedWriter(new FileWriter("src/java4/seriasFile.txt"));
        for(int i=0; i<10;i++){
            if(i==9){
                byte[] seriaBytes2 = new byte[(int)fileSize/10+rest];
                buffStream.read(seriaBytes2,0, (int)((fileSize/10)+rest));
                seria.clear();
                for(int j=0; j<seriaBytes2.length; j++){
                    if ( seriaBytes2[j] == 10 && !number.isEmpty()) {
                        seria.add(Integer.parseInt(number.toString()));
                        number.delete(0, number.length());
                    }else if(seriaBytes2[j] != 13){
                        number.append((char) seriaBytes2[j]);
                    }
                }
            }else{
                buffStream.read(seriaBytes,0, (int)fileSize/10);
                seria.clear();
                for(int j=0; j<seriaBytes.length; j++){
                    if ( seriaBytes[j] == 10 && !number.isEmpty()) {
                        seria.add(Integer.parseInt(number.toString()));
                        number.delete(0, number.length());
                    }else if(seriaBytes[j] != 13){
                        number.append((char) seriaBytes[j]);
                    }
            }

            }
            quickSort(seria, 0, seria.size() - 1);
            for (int el : seria) {
                writerSeria.write(Integer.toString(el) + '\n');
            }
            writerSeria.write("-" + '\n');
        }
        System.out.println("Serias sorted");
        writerSeria.close();
        BufferedReader readerfile2 = new BufferedReader(new FileReader("src/java4/seriasFile.txt"));
        String linefile2;
        long lines = 0;
        while((linefile2=readerfile2.readLine())!=null){
            if(linefile2.equals("-")) lines++;
        }
        System.out.println(lines);

        int res[] = fibonacciCounter(lines);
        System.out.println("fibonacci number" + res[2]);
        BufferedWriter writerF1 = new BufferedWriter(new FileWriter("src/java4/lab1F1.txt"));
        BufferedReader readerF1 = new BufferedReader(new FileReader("src/java4/seriasFile.txt"));
        int counter = 0;
        while(counter<res[0]){
            String line1 = readerF1.readLine();
            writerF1.write(line1 + '\n');
            if(line1.equals("-")) counter++;
        }
        writerF1.close();
        BufferedWriter writerF2 = new BufferedWriter(new FileWriter("src/java4/lab1F2.txt"));
        int counter2 = 0;
        while(counter2<res[1]){
            String line2 = readerF1.readLine();
            if(line2!=null) {
                writerF2.write(line2 + '\n');
                if(line2.equals("-")) counter2++;
            }else{

                writerF2.write("-"+'\n');
                counter2++;
            }
        }
        writerF2.close();
        readerF1.close();
        System.out.println(counter);
        System.out.println(counter2);
        BufferedWriter wr3 = new BufferedWriter(new FileWriter("src/java4/lab1F3.txt"));
        wr3.write("");
        long min = -1;

        while(min!=0){
            min = polyphaseSort();
            System.out.println(min);

        }

    }
    public static int[] fibonacciCounter(long amount){
        int i = 1;
        int j = 1;
        int f = 0;
        while(f<amount){
            f = i + j;
            int x = i;
            i = f;
            j = x;
        }
        System.out.println(f-j);
        System.out.println(j);
        System.out.println(f);
        int[] a = {f-j,j,f};
        return a;
    }
    public static long polyphaseSort() throws IOException{
        long min = 0;
        long linesF1 = 0;
        BufferedReader readerFile1 = new BufferedReader(new FileReader("src/java4/lab1F1.txt"));
        String lF1;
        while((lF1=readerFile1.readLine())!=null){
            if(lF1.equals("-")) linesF1++;
        }
        long linesF2 = 0;
        BufferedReader readerFile2 = new BufferedReader(new FileReader("src/java4/lab1F2.txt"));
        String lF2;
        while((lF2=readerFile2.readLine())!=null){
            if(lF2.equals("-")) linesF2++;
        }
        long linesF3 = 0;
        BufferedReader readerFile3 = new BufferedReader(new FileReader("src/java4/lab1F3.txt"));
        String lF3;
        while((lF3=readerFile3.readLine())!=null){
            if(lF3.equals("-")) linesF3++;
        }
        String file1;
        String file2;
        String file3;
        if(linesF1==0) {
            file3 = "src/java4/lab1F1.txt";
            if(linesF2>linesF3){
                file2 = "src/java4/lab1F2.txt";
                file1 = "src/java4/lab1F3.txt";
                min = linesF3;
            }else{
                file1 = "src/java4/lab1F2.txt";
                file2 = "src/java4/lab1F3.txt";
                min = linesF2;
            }
        }else if(linesF2==0){
            file3 = "src/java4/lab1F2.txt";
            if(linesF1>linesF3){
                file2 = "src/java4/lab1F1.txt";
                file1 = "src/java4/lab1F3.txt";
                min = linesF3;
            }else{
                file2 = "src/java4/lab1F3.txt";
                file1 = "src/java4/lab1F1.txt";
                min = linesF1;
            }
        }else{
            file3 = "src/java4/lab1F3.txt";
            if(linesF2>linesF1){
                file1 = "src/java4/lab1F1.txt";
                file2 = "src/java4/lab1F2.txt";
                min = linesF1;
            }else{
                file1 = "src/java4/lab1F2.txt";
                file2 = "src/java4/lab1F1.txt";
                min = linesF2;
            }
        }
        System.out.println("MIN" + min);
        if(min==0) return min;
        BufferedReader readerF1 = new BufferedReader(new FileReader(file1));
        BufferedReader readerF2 = new BufferedReader(new FileReader(file2));
        BufferedWriter writerF3 = new BufferedWriter(new FileWriter(file3));
        long counterMin = 0;
        String num1 = readerF1.readLine();
        String num2 = readerF2.readLine();
        while(counterMin<min){
            if(!num1.equals("-")&&!num2.equals("-")){
                if(Integer.parseInt(num1)<Integer.parseInt(num2)){
                    writerF3.write(num1+'\n');
                    num1 = readerF1.readLine();
                }else{
                    writerF3.write(num2+'\n');
                    num2 = readerF2.readLine();
                }
            }else if(num1.equals("-")){
                while(!num2.equals("-")){
                    writerF3.write(num2+'\n');
                    num2 = readerF2.readLine();
                }
                writerF3.write("-"+'\n');
                counterMin++;
                num2 = readerF2.readLine();
                num1 = readerF1.readLine();
            }else{
                while(!num1.equals("-")){
                    writerF3.write(num1+'\n');
                    num1 = readerF1.readLine();
                }
                writerF3.write("-"+'\n');
                counterMin++;
                num2 = readerF2.readLine();
                num1 = readerF1.readLine();
            }
        }
        readerF1.close();
        writerF3.close();
        BufferedWriter writerTemporary = new BufferedWriter(new FileWriter("src/java4/lab1FTemp.txt"));
        String line;
        while((line=readerF2.readLine())!=null){
            writerTemporary.write(line +'\n');
        }
        readerF2.close();
        writerTemporary.close();
        BufferedWriter writerF1 =  new BufferedWriter(new FileWriter(file1));
        writerF1.flush();
        writerF1.close();
        BufferedWriter writerF2 = new BufferedWriter(new FileWriter(file2));
        writerF2.flush();
        BufferedReader readerTemporary = new BufferedReader(new FileReader("src/java4/lab1FTemp.txt"));
        String tempLine;
        int counter = 0;
        tempLine = num2;
        writerF2.write(tempLine+'\n');
        if(tempLine!=null){
            if(tempLine.equals("-"))counter++;
        }
        while ((tempLine=readerTemporary.readLine())!=null){
            writerF2.write(tempLine+'\n');
            if(tempLine.equals("-"))counter++;
        }
        System.out.println(counter);
        readerTemporary.close();
        writerF2.close();
        BufferedWriter writerTempfl = new BufferedWriter(new FileWriter("src/java4/lab1FTemp.txt"));
        writerTempfl.flush();
        writerTempfl.close();
        return min;
    }

}
