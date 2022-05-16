alfabeto = ['A','B','C','D','E','F','G','H','I','J','K','L','M','N','Ñ','O','P',
'Q','R','S','T','U','V','W','X','Y','Z']

numeros_alfabeto = {'A': 0, 'B': 1, 'C': 2, 'D': 3, 'E': 4, 'F': 5, 'G': 6, 'H': 7, 'I'
: 8, 'J': 9, 'K': 10, 'L': 11, 'M': 12, 'N': 13, 'Ñ': 14, 'O': 15, 'P': 16, 'Q':
 17, 'R': 18, 'S': 19, 'T': 20, 'U': 21, 'V': 22, 'W': 23, 'X': 24, 'Y': 25, 'Z'
: 26}

def preprocesadoMensaje(texto):
    texto = texto.upper()
    texto = texto.replace(" ","")
    texto = texto.replace(",","")
    texto = texto.replace(".","")
    texto = texto.replace(";","")
    texto = texto.replace("Á","A")
    texto = texto.replace("É","E")
    texto = texto.replace("Í","I")
    texto = texto.replace("Ó","O")
    texto = texto.replace("Ú","U")
    return texto

def preprocesadoLlave(texto,llave):
    nueva_llave = preprocesadoMensaje(llave)

    if len(nueva_llave) > len(texto):
        nueva_llave = nueva_llave[0:len(texto)]
    elif len(nueva_llave) < len(texto):
        while len(nueva_llave) < len(texto):
            nueva_llave = nueva_llave + nueva_llave[0:len(texto)-len(nueva_llave)]
    return nueva_llave

def cifrado(texto):
    llave = input('Ingresa la llave para el cifrado: ')
    llave = preprocesadoLlave(texto,llave)
    modulo = len(alfabeto)
    nuevo_texto = ""
    for i in range(len(texto)):
        nuevo_texto += alfabeto[(numeros_alfabeto[texto[i]] + numeros_alfabeto[llave[i]]) % modulo]
    return nuevo_texto

def descifrado(texto):
    llave = input('Ingresa la llave para el descifrado: ')
    llave = preprocesadoLlave(texto,llave)
    modulo = len(alfabeto)
    nuevo_texto = ""
    for i in range(len(texto)):
        nuevo_texto += alfabeto[(numeros_alfabeto[texto[i]] - numeros_alfabeto[llave[i]]) % modulo]
    return nuevo_texto

def frecuencias(texto):
    repeticiones = [0] * 27

    for i in range(len(texto)):
        for j in range(len(alfabeto)):
            if texto[i] == alfabeto[j]:
                repeticiones[j]+=1
                break

    for i in range(len(alfabeto)):
        print("La letra: " + alfabeto[i] + " se repite: " + str(repeticiones[i]))
        
    return repeticiones
    
if __name__ == '__main__': 
    '''mensaje = "La perfección se logra no cuando no hay nada más que añadir, sino cuando no hay nada más que quitar"
    mensaje = preprocesadoMensaje(mensaje)
    mensaje = cifrado(mensaje)
    print("Texto cifrado: " + mensaje)
    
    frecuencias(mensaje)
    
    mensaje = descifrado(mensaje)
    print("Texto descifrado: " + mensaje)'''

    mensaje = "GYLKWQRVEBTPXDJRQDDVQNPHHGQGUWRNPPWHRGCONLJOHMÑCOXEEAVASIÑDOEQPETAPVHEOPEKRXYAEVRUHAÑVNRSIVPZBSXINLEWSMGBSHEEITVDEENSVR"
    mensaje = descifrado(mensaje)
    print("Texto descifrado: " + mensaje)