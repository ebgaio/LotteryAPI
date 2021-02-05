package com.loteria.projeto.lotteryapi.service.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Primo {

	public static void main(String[] args) {
		
		int interval = 0;
		int primeNumbers = 0;
		int numRand1;
		int numRand2;
		int baseRandom = 30030 * 5;
		int numAleatorio;
		int numberLottery = 0;
		int numberSize = 6;
		
		List<Integer> lottery = new ArrayList<>(numberSize);
		
		Random rand = new Random();

		do {
			numRand1 = rand.nextInt(baseRandom);
			numRand2 = rand.nextInt(baseRandom);
			if (numRand1 != numRand2) {
				if (numRand1 > numRand2) {
					interval = numRand1 - numRand2;
				} else {
					interval = numRand2 - numRand1;
				}
			}

		} while (interval < 1000);

		for (int j = 1; j <= interval; j++) {
			boolean eh = ehPrimo(j);
			if (eh) {
				primeNumbers++;
			}
		}
		
		for (int i = 0; i < numberSize; i++) {
			
			numAleatorio = (int)(Math.random() * primeNumbers ) + 1;
			while (numAleatorio > 60) {
				numAleatorio = (int)(Math.random() * primeNumbers ) + 1;
			}

			do {
				numberLottery = (int)(Math.random() * numAleatorio ) + 1;
			} while (lottery.contains(numberLottery));
	    	
	    	lottery.add(numberLottery);
	    }
		
		Collections.sort(lottery);
				
//		for (Integer integer : lottery) { 
//			System.out.println("Numero " + (integer));
//		}
                
                lottery.forEach(integer -> {
                    System.out.println("Numero " + (integer));
                });
	}
	// Modificado
	public static boolean ehPrimo(int numero) {
	
		for (int i = 2; i < numero; i++) {
			if (numero % i == 0) {
				return false;
			}
		}
		return true;
	}
}
