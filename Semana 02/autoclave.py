alfabeto = ['A','B','C','D','E','F','G','H','I','J','K','L','M','N','Ñ','O','P',
'Q','R','S','T','U','V','W','X','Y','Z']

numeros_alfabeto = {'A': 0, 'B': 1, 'C': 2, 'D': 3, 'E': 4, 'F': 5, 'G': 6, 'H': 7, 'I'
: 8, 'J': 9, 'K': 10, 'L': 11, 'M': 12, 'N': 13, 'Ñ': 14, 'O': 15, 'P': 16, 'Q':
 17, 'R': 18, 'S': 19, 'T': 20, 'U': 21, 'V': 22, 'W': 23, 'X': 24, 'Y': 25, 'Z'
: 26}

def descifrado(texto):
    '''llave = input('Ingresa la llave para el descifrado: ')'''
    llave = "UNODELOSMASGRANDESCRIPTOGRAFOS"
    modulo = len(alfabeto)
    nuevo_texto = ""
    posicion = 0

    for i in range(len(texto)):
        if i >= len(llave):
            llave += nuevo_texto[posicion]
            posicion += 1
        nuevo_valor = (numeros_alfabeto[texto[i]] - numeros_alfabeto[llave[i]]) % modulo
        nuevo_texto += alfabeto[nuevo_valor]
    return nuevo_texto

if __name__ == '__main__':
    mensaje = "XHGDQESDMPKÑDEEDKNGJZPFJSUIFZOLFCINFJCESVZTGBFXCIUDAYNUUDIZYWWZBEYNVQWIVUNKZEPHDODQUZZLBDNDRWTHQSERÑIVMLERCMGIFLSORZXTSDIGLOXQSDJHWVCIWQXQJCKMBPOKMPSKMUVIMNJDNBLCSZHXHNYYUIXDBSOXHZLXWVGDJGXHWLTDWKÑSAQIMZLNBVMLXHUOQQXIQGWGUFTWKZKMOKUDNINSIFJDUOZIJBSVVOWFAIEÑGYOWPSOAP"
    mensaje = descifrado(mensaje)
    print("Texto descifrado: " + mensaje)
