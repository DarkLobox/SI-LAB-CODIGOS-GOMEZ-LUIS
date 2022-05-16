alfabeto = ['A','B','C','D','E','F','G','H','I','J','K','L','M','N','Ñ','O','P',
'Q','R','S','T','U','V','W','X','Y','Z']

numeros_alfabeto = {'A': 0, 'B': 1, 'C': 2, 'D': 3, 'E': 4, 'F': 5, 'G': 6, 'H': 7, 'I'
: 8, 'J': 9, 'K': 10, 'L': 11, 'M': 12, 'N': 13, 'Ñ': 14, 'O': 15, 'P': 16, 'Q':
 17, 'R': 18, 'S': 19, 'T': 20, 'U': 21, 'V': 22, 'W': 23, 'X': 24, 'Y': 25, 'Z'
: 26}

def preprocesado(texto):
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

def cifrado(texto):
    desplazamiento = int(input('Ingresa el desplazamiento: '))
    modulo = len(alfabeto)
    nuevo_texto = ""
    for i, c in enumerate(texto):
        nuevo_texto += alfabeto[(numeros_alfabeto[c] + desplazamiento) % modulo]
    return nuevo_texto

def cifrado_desplazamiento_tres(texto):
    modulo = len(alfabeto)
    nuevo_texto = ""
    for i, c in enumerate(texto):
        nuevo_texto += alfabeto[(numeros_alfabeto[c] + 3) % modulo]
    return nuevo_texto

def descifrado(texto):
    desplazamiento = int(input('Ingresa el desplazamiento: '))
    modulo = len(alfabeto)
    nuevo_texto = ""
    for i, c in enumerate(texto):
        nuevo_texto += alfabeto[(numeros_alfabeto[c] - desplazamiento) % modulo]
    return nuevo_texto

if __name__ == '__main__':
    mensaje = "La perfección se logra no cuando no hay nada más que añadir, sino cuando no hay nada más que quitar"
    mensaje = preprocesado(mensaje)
    mensaje = cifrado(mensaje)
    print("Texto cifrado: " + mensaje)
    mensaje = descifrado(mensaje)
    print("Texto descifrado: " + mensaje)