package com.homework.trianglepyramid;

public class TrianglePyramid {

	public void pyramidTriagle(int n) {
		/**
		 * �cgenin dik kenar�n� kullanarak tekrar eden say�s�n� bulabiliriz.�r : verilan
		 * �ekilde 4 '*' y�ksekli�ine sahip o zaman d�ng� 4 defa tekrar edecek
		 * diyebiliriz.
		 */
		for (int i = 0; i < n; i++) {

			// D�ng� i�erisindeki her sat�rda olan bo�luk say�s�
			/*
			 * burada ki temel usus istenilen say�da bo�luk 5 * y�kseklikte olsayd� her
			 * sat�rda istenilen bo�luk ilgili sat�r de�eri kadar az olacakt�r. 5 sat�rda 0
			 * bo�luk olurken 3 sat�rda 5-3=2 bo�luk olacakt�r.
			 */
			for (int j = n - i; j > 1; j--) {
				// bo�luklar� ekrana yazmak.
				System.out.print(" ");
			}

			/*
			 * bo�luklar� ekledikten sonra geri kalan row kadar * ekleriz. 3 sat�rda 5-3 =2
			 * bo�luk 3 * olur.
			 */
			for (int j = 0; j <= i; j++) {
				// y�ld�zlar� yazmak.
				System.out.print("* ");
			}

			// her sat�r �zerinden i�lem bittikten sonra bir sonraki for de�eri col
			// olackt�r. println() ile alt sat�ra geceriz.col'a ge�i� yapar�z.
			System.out.println();
		}
	}

	public static void main(String[] args) {
		
		TrianglePyramid pyramid= new TrianglePyramid();
		pyramid.pyramidTriagle(5);

	}

}
