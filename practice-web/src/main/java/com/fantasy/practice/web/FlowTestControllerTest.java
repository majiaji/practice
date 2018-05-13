package com.fantasy.practice.web;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.io.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by jiaji on 2018/4/19.
 */
public class FlowTestControllerTest {

    @Test
    public void test() throws Exception {
        ExecutorService executors = Executors.newFixedThreadPool(4);

        for (int k = 4; k <= 22; k++) {
            int finalK = k;
            executors.submit(() -> {
                try {
                    List<DNA> dnaList = Lists.newLinkedList();
                    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/Users/jiaji/Desktop/22/" + finalK + ".txt"))));
                    while (true) {
                        String str = br.readLine();
                        if (str == null) break;
                        String[] row = str.split("\\t");
                        DNA d = new DNA();
                        d.col1 = row[0];
                        d.col2 = row[1];
                        d.col3 = row[2];
                        d.col4 = row[3];
                        d.col5 = row[4];
                        d.col6 = row[5];
                        d.col7 = row[6];
                        dnaList.add(d);
                    }

                    for (int i = 0; i < dnaList.size(); i++) {
                        if (!dnaList.get(i).col7.equals("SNP") || !dnaList.get(i).col6.equals("SnpCluster"))
                            continue;
                        int j = i + 1;
                        for (; j < dnaList.size() - 1; j++) {
                            if (Long.valueOf(dnaList.get(j).col2) - Long.valueOf(dnaList.get(i).col2) > 200) {
                                break;
                            }
                        }

                        if (i % 10000 == 0) {
                            System.out.println("thread " + Thread.currentThread().getId() + " process task" + finalK + "  " + (double) i / dnaList.size());
                        }
                        addTag(dnaList.subList(i, j));
                    }
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/Users/jiaji/Desktop/22/" + finalK + ".result.txt")));
                    for (int i = 0; i < dnaList.size(); i++) {
//            if (!dnaList.get(i).delete)
//                System.out.println(JSON.toJSONString(dnaList.get(i)));
//            bw.write(JSON.toJSONString(dnaList.get(i)) + "\r\n");
                        DNA dna = dnaList.get(i);
                        if (!dna.delete) {
                            bw.write(dna.col1 + "\t" + dna.col2 + "\t" + dna.col3 + "\t" + dna.col4 + "\t" +
                                    dna.col5 + "\t" + dna.col6 + "\t" + dna.col7 + "\r\n");
                        }
                    }
                    bw.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executors.awaitTermination(24, TimeUnit.HOURS);
    }

    private void addTag(List<DNA> subList) {
        if (subList.size() <= 1 || subList.size() > 5 || (subList.size() >= 2 && (!subList.get(1).col7.equals("SNP") || !subList.get(1).col6.equals("SnpCluster")))) {
            return;
        }

        for (DNA each : subList) {
            if (each.getCol7().equals("SNP") && each.getCol6().equals("SnpCluster")) {
                each.delete = false;
            } else {
                return;
            }
        }
    }


    class DNA {
        String col1;
        String col2;
        String col3;
        String col4;
        String col5;
        String col6;
        String col7;
        boolean delete = true;

        public String getCol1() {
            return col1;
        }

        public void setCol1(String col1) {
            this.col1 = col1;
        }

        public String getCol2() {
            return col2;
        }

        public void setCol2(String col2) {
            this.col2 = col2;
        }

        public String getCol3() {
            return col3;
        }

        public void setCol3(String col3) {
            this.col3 = col3;
        }

        public String getCol4() {
            return col4;
        }

        public void setCol4(String col4) {
            this.col4 = col4;
        }

        public String getCol5() {
            return col5;
        }

        public void setCol5(String col5) {
            this.col5 = col5;
        }

        public String getCol6() {
            return col6;
        }

        public void setCol6(String col6) {
            this.col6 = col6;
        }

        public String getCol7() {
            return col7;
        }

        public void setCol7(String col7) {
            this.col7 = col7;
        }

        public boolean isDelete() {
            return delete;
        }

        public void setDelete(boolean delete) {
            this.delete = delete;
        }
    }
}