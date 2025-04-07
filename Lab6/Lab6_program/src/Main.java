import core.AbstractSwappingSortingAlgorithm;
import testing.MarkedValue;
import testing.Tester;
import testing.comparators.IntegerComparator;
import testing.comparators.MarkedValueComparator;
import testing.generation.*;
import testing.generation.conversion.LinkedListGenerator;
import testing.generation.conversion.MarkingGenerator;
import testing.results.swapping.Result;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {

	public static void main(String[] args) throws IOException {
		Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<Integer>(new IntegerComparator());
		
		Generator<MarkedValue<Integer>> generator = new LinkedListGenerator<Integer>(new RandomIntegerArrayGenerator(2000));
		//Generator<MarkedValue<Integer>> generator = new MarkingGenerator<Integer>(new RandomIntegerArrayGenerator(2000));

		AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> algorithm = new MergeSort<>(markedComparator);

		ArrayList<Result> resultsList = new ArrayList<>();
		Result result1 = Tester.runNTimes(algorithm, generator, 0, 20); resultsList.add(result1);
		Result result2 = Tester.runNTimes(algorithm, generator, 1, 20); resultsList.add(result2);
		Result result3 = Tester.runNTimes(algorithm, generator, 5, 20); resultsList.add(result3);
		Result result4 = Tester.runNTimes(algorithm, generator, 10, 20); resultsList.add(result4);
		Result result5 = Tester.runNTimes(algorithm, generator, 20, 20); resultsList.add(result5);
		Result result6 = Tester.runNTimes(algorithm, generator, 30, 20); resultsList.add(result6);
		Result result7 = Tester.runNTimes(algorithm, generator, 40, 20); resultsList.add(result7);
		Result result8 = Tester.runNTimes(algorithm, generator, 50, 20); resultsList.add(result8);
		Result result9 = Tester.runNTimes(algorithm, generator, 60, 20); resultsList.add(result9);
		Result result10 = Tester.runNTimes(algorithm, generator, 70, 20); resultsList.add(result10);
		Result result11 = Tester.runNTimes(algorithm, generator, 80, 20); resultsList.add(result11);
		Result result12 = Tester.runNTimes(algorithm, generator, 100, 20); resultsList.add(result12);
		Result result13 = Tester.runNTimes(algorithm, generator, 150, 20); resultsList.add(result13);
		Result result14 = Tester.runNTimes(algorithm, generator, 200, 20); resultsList.add(result14);
		Result result15 = Tester.runNTimes(algorithm, generator, 250, 20); resultsList.add(result15);
		Result result16 = Tester.runNTimes(algorithm, generator, 500, 20); resultsList.add(result16);
		Result result17 = Tester.runNTimes(algorithm, generator, 750, 20); resultsList.add(result17);
		Result result18 = Tester.runNTimes(algorithm, generator, 1000, 20); resultsList.add(result18);
		Result result19 = Tester.runNTimes(algorithm, generator, 1500, 20); resultsList.add(result19);
		Result result20 = Tester.runNTimes(algorithm, generator, 2000, 20); resultsList.add(result20);

		System.out.println("result10:");
		printStatistic("time [ms]", result10.averageTimeInMilliseconds(), result10.timeStandardDeviation());
		printStatistic("comparisons", result10.averageComparisons(), result10.comparisonsStandardDeviation());
		printStatistic("swaps", result10.averageSwaps(), result10.swapsStandardDeviation());
		System.out.println("always sorted: " + result10.sorted());
		System.out.println("always stable: " + result10.stable());

		saveStatistics("time.txt", resultsList);
		saveStatistics("timeSD.txt", resultsList);
		saveStatistics("comp.txt", resultsList);
		saveStatistics("compSD.txt", resultsList);
		saveStatistics("swap.txt", resultsList);
		saveStatistics("swapSD.txt", resultsList);
	}
	
	private static void printStatistic(String label, double average, double stdDev) {
		System.out.println(label + ": " + double2String(average) + " +- " + double2String(stdDev));
	}

	private static String double2String(double value) {
		return String.format("%.12f", value);
	}

	public static void saveStatistics(String fileName, ArrayList<Result> list) throws IOException {
		File plik = new File(fileName);
		BufferedWriter bw = new BufferedWriter(new FileWriter(plik));
		for (Result result : list) {
			switch (fileName) {
				case "time.txt":
					bw.write(double2String(result.averageTimeInMilliseconds())); bw.write("\n"); break;
				case "timeSD.txt":
					bw.write(double2String(result.timeStandardDeviation())); bw.write("\n"); break;
				case "comp.txt":
					bw.write(double2String(result.averageComparisons())); bw.write("\n"); break;
				case "compSD.txt":
					bw.write(double2String(result.comparisonsStandardDeviation())); bw.write("\n"); break;
				case "swap.txt":
					bw.write(double2String(result.averageSwaps())); bw.write("\n"); break;
				case "swapSD.txt":
					bw.write(double2String(result.swapsStandardDeviation())); bw.write("\n"); break;
			}
		}
		bw.close();
	}
}
