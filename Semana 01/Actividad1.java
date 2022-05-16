import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Actividad1 {

	public static void main(String[] args) throws IOException {
		//Leer txt
		Path path = Paths.get("D:\\EclipseProjects\\SI-Lab01\\src\\poema.txt");
		
		BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
		String texto="";
		String linea;
		while ((linea = reader.readLine()) != null)
			texto+=linea;
		
		
		char [] caracteresTexto = texto.toCharArray();
		imprimirListaChar(caracteresTexto);
		
		/*4.1 Realizar las siguientes sustituciones: jxi, hxi, ñxn, kxl, uxv, wxv, yxz, xxr (tanto 
		mayúsculas como minúsculas)*/
		cambioLetras(caracteresTexto);
		imprimirListaChar(caracteresTexto);
		
		//4.2 Elimine las tildes 
		eliminarTildes(caracteresTexto);
		imprimirListaChar(caracteresTexto);
		
		//4.3 Convierta todas las letras a mayúsculas
		cambioMayusculas(caracteresTexto);
		imprimirListaChar(caracteresTexto);
		
		/*4.4 Elimine los espacios en blanco y los signos de puntuación Indique cuál sería el 
		alfabeto resultante y cuál su longitud GUARDE EL RESULTADO EN EL ARCHIVO “POEMA_PRE.TXT” 
		(el que deberá ser adjuntado)*/
		caracteresTexto = eliminarEspPun(caracteresTexto);
		imprimirListaChar(caracteresTexto);
		
		String ruta = "D:\\EclipseProjects\\SI-Lab01\\src\\POEMA_PRE.txt";
        String contenido = String.valueOf(caracteresTexto);
        
        File file = new File(ruta);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(contenido);
        bw.close();
        
        /*4.5 Abra el archivo generado e implementar una función que calcule una tabla de 
        frecuencias para cada letra de la ’A’ a ’Z’. La función deberá definirse como 
        frecuencias(archivo) y deberá devolver un diccionario cuyos índices son las letras 
        analizadas y cuyos valores son las frecuencias de las mismas en el texto (número de 
        veces que aparecen). Reconozca en el resultado obtenido los cinco caracteres de mayor 
        frecuencia*/
        
        Path preprocesado = Paths.get("D:\\EclipseProjects\\SI-Lab01\\src\\POEMA_PRE.txt");
		
		frecuencias(preprocesado);
		
        /*4.6 Obtener la información que el método Kasiski requiere para implementar un ataque, 
        para ello deberá recorrer el texto preprocesado y hallar los trigramas en el mismo 
        (sucesión de tres letras seguidas que se repiten) y las distancias (número de caracteres 
        entre dos trigramas iguales consecutivos)*/
        
		
        //4.7 Volver a preprocesar el archivo cambiando cada carácter según UNICODE-8
        preprocesadoUnicode(preprocesado);
		
        //4.8 Volver a preprocesar el archivo cambiando cada carácter según alfabeto de su elección
        preprocesadoNuevo(preprocesado);
        
        /*4.9 Volver a preprocesar el archivo insertando la cadena MANI cada 20 caracteres, el texto 
        resultante deberá contener un número de caracteres que sea múltiplo de 4, si es 
        necesario rellenar (padding) al final con caracteres M según se necesite*/
        preprocesadoMani(preprocesado);
        
	}
	static void preprocesadoMani(Path archivo) throws IOException{
		BufferedReader reader = Files.newBufferedReader(archivo, StandardCharsets.UTF_8);
		String texto="";
		String linea;
		while ((linea = reader.readLine()) != null)
			texto+=linea;
		
		char [] caracteresTexto = texto.toCharArray();
		
		ArrayList<Character> mani = new ArrayList<Character>();
		
		for(int i=1;i-1<caracteresTexto.length;i++) {
			mani.add(caracteresTexto[i-1]);
			if(i%20==0) {
				mani.add('M');
				mani.add('A');
				mani.add('N');
				mani.add('I');
			}
			while(i==caracteresTexto.length && mani.size()%4!=0) {
				mani.add('M');
			}
		}
		
		System.out.println(mani);
	}
	
	static void preprocesadoUnicode(Path archivo) throws IOException{
		BufferedReader reader = Files.newBufferedReader(archivo, StandardCharsets.UTF_8);
		String texto="";
		String linea;
		while ((linea = reader.readLine()) != null)
			texto+=linea;
		
		char [] caracteresTexto = texto.toCharArray();
		
		String [] caracteresUnicode = new String[caracteresTexto.length];
		
		for(int i=0;i<caracteresTexto.length;i++) {
			caracteresUnicode[i] = String.format("\\u%04x", (int) caracteresTexto[i]);
		}
		
		for(int i=0;i<caracteresUnicode.length;i++) {
			System.out.print(caracteresUnicode[i]);
		}
		
		System.out.println();
	}
	
	static void preprocesadoNuevo(Path archivo) throws IOException {
		BufferedReader reader = Files.newBufferedReader(archivo, StandardCharsets.UTF_8);
		String texto="";
		String linea;
		while ((linea = reader.readLine()) != null)
			texto+=linea;
		
		char [] caracteresTexto = texto.toCharArray();
		
		String alfabeto1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String alfabeto2 = "HIJKLMNOPQRSTUVWXYZABCDEFG";
		char[] voc1 = alfabeto1.toCharArray();
		char[] voc2 = alfabeto2.toCharArray();
		
		cambio(caracteresTexto,voc1,voc2);
		
		imprimirListaChar(caracteresTexto);
	}
	
	static void frecuencias(Path archivo) throws IOException {
		BufferedReader reader = Files.newBufferedReader(archivo, StandardCharsets.UTF_8);
		String texto="";
		String linea;
		while ((linea = reader.readLine()) != null)
			texto+=linea;
		
		char [] caracteresTexto = texto.toCharArray();
		
		String cadena = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char[] voc = cadena.toCharArray();
		int[] rep = new int[voc.length];
		
		for(int i=0;i<caracteresTexto.length;i++) {
			for(int j=0;j<voc.length;j++) {
				if(caracteresTexto[i]==voc[j]) {
					rep[j]++;
					break;
				}
			}
		}
		
		for(int i=0;i<voc.length;i++) {
			System.out.println("La letra: " + voc[i] + " se repite: " + rep[i]);
		}
		
		int[] mayorFrecuencia = {-1,-1,-1,-1,-1};
		
		int mayorAnterior = -1;
		int mayorValor = 0;
		int indice = -1;
		for(int i=0;i<mayorFrecuencia.length;i++) {
			for(int j=0;j<rep.length;j++) {
				if((rep[j]< mayorAnterior || mayorAnterior==-1) && mayorValor < rep[j]) {
					mayorValor = rep[j];
					indice = j;
				}
			}
			mayorFrecuencia[i] = indice;
			mayorAnterior = mayorValor;
			mayorValor = 0;
			indice=-1;
		}
		
		System.out.println("Mayor frecuencia");
		for(int i=0;i<mayorFrecuencia.length;i++) {
			System.out.println("La letra: " + voc[mayorFrecuencia[i]] + " se repite: " + rep[mayorFrecuencia[i]]);
		}
	}
	
	static void cambioLetras(char[] texto) {
		char[] preminus = {'j', 'h', 'ñ', 'k', 'u', 'w', 'y', 'x'};
		char[] susminus = {'i', 'i', 'n', 'l', 'v', 'v', 'z', 'r'};
		char[] premayus = {'J', 'H', 'Ñ', 'K', 'U', 'W', 'Y', 'X'};
		char[] susmayus = {'I', 'I', 'N', 'L', 'V', 'V', 'Z', 'R'};
		
		cambio(texto, preminus, susminus, premayus, susmayus);
	}
	
	static void eliminarTildes(char[] texto) {
		char[] preminus = {'á', 'é', 'í', 'ó', 'ú'};
		char[] susminus = {'a', 'e', 'i', 'o', 'u'};
		char[] premayus = {'Á', 'É', 'Í', 'Ó', 'Ú'};
		char[] susmayus = {'A', 'E', 'I', 'O', 'U'};
		
		cambio(texto, preminus, susminus, premayus, susmayus);
	}
	
	static void cambioMayusculas(char[] texto) {
		String cadenaPre = "abcdefghijklmnñopqrstuvwxyz";
		String cadenaSus = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
		char[] pre = cadenaPre.toCharArray();
		char[] sus = cadenaSus.toCharArray();
		
		cambio(texto, pre, sus);
	}
	
	static char[] eliminarEspPun(char[] texto) {
		char[] temp = new char[texto.length];
		
		int k=0;
		
		for(int i=0;i<texto.length;i++) {
			if(texto[i]!=' ' && texto[i]!=';' && texto[i]!=',' && texto[i]!='.' && texto[i]!='!' && texto[i]!='¡' && texto[i]!='?' && texto[i]!='¿') {
				temp[k]=texto[i];
				k++;
			}
		}
		
		for(int i=0;i<texto.length;i++) {
			texto[i]=temp[i];
		}
		
		temp = new char[k];
		for(int i=0;i<temp.length;i++) {
			temp[i]=texto[i];
		}
		
		return temp;
	}
	
	static void cambio(char[] texto, char[] p1, char[] s1, char[] p2, char[] s2) {
		for(int i=0;i<texto.length;i++) {
			for(int j=0;j<p1.length;j++) {
				if(texto[i]==p1[j]) {
					texto[i]=s1[j];
					break;
				}
				else if(texto[i]==p2[j]) {
					texto[i]=s2[j];
					break;
				}
			}
		}
	}
	
	static void cambio(char[] texto, char[] p1, char[] s1) {
		for(int i=0;i<texto.length;i++) {
			for(int j=0;j<p1.length;j++) {
				if(texto[i]==p1[j]) {
					texto[i]=s1[j];
					break;
				}
			}
		}
	}
	
	static void imprimirListaChar(char[] texto) {
		for(int i=0;i<texto.length;i++) {
			System.out.print(texto[i]);
		}
		System.out.println();
	}
}
