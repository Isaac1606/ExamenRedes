import random

def llenaMatriz(m):
    m.append(2)
    pass

def creaMatriz(filas, columnas):
    matriz = []
    for i in range(filas):
        lista = []
        a = (10*i) + 1
        for j in range(columnas):
            lista.append(a)
            a += 1
        matriz.append(lista)
    return matriz

def creaTablero(filas,columnas,dificultad):
    numeroMinas = 0
    if(dificultad == 1):
        numeroMinas = 10
    elif(dificultad == 2):
        numeroMinas = 40
    else:
        numeroMinas = 99 
    matriz = []
    for i in range(filas):
        lista = []
        a = (10*i) + 1
        for j in range(columnas):
            lista.append(a)
            a += 1
        matriz.append(lista)

    while(numeroMinas>0):
        f = random.randint(-1,filas-1)
        c = random.randint(-1,columnas-1)
        if(matriz[f][c]!=-1):
            matriz[f][c] = -1
            numeroMinas -= 1
    
    for i in range(filas):
        for j in range(columnas):
            ls= verAlrededor(matriz,i,j)
            if(matriz[i][j]!=-1):
                matriz[i][j] = ls.count(-1)

    return matriz

def creaCadenaMatriz(matriz):
    cadena = ""
    for i in range(len(matriz)):
        cadena += "/"
        for j in range(len(matriz[i])):
            cadena += str(matriz[i][j])+" "
    return cadena

def recorreMatriz(matriz,fila,columna):
    for i in range(-1,2):
        for j in range(-1,2):
            try:
                if(matriz[fila+i][columna+j]!=matriz[fila][columna] and fila+i>-1 and columna+j>-1):
                    print(f"{matriz[fila+i][columna+j]}",end=" ")
            except(IndexError):
                pass
    print(f"estan alrededor de: {matriz[fila][columna]}")

def verAlrededor(matriz,fila,columna):
    listaAlrededor = []
    for i in range(-1,2):
        for j in range(-1,2):
            try:
                if(matriz[fila+i][columna+j]!=matriz[fila][columna] and fila+i>-1 and columna+j>-1):
                    listaAlrededor.append(matriz[fila+i][columna+j])
            except(IndexError):
                pass
    return listaAlrededor

def imprimeMatriz(matriz):
    for i in range(len(matriz)):
        print()
        for j in range(len(matriz[i])):
            print(f"{matriz[i][j]:2}",end=" ")

def contarMinas(matriz):
    contador = 0
    for i in range(len(matriz)):
        for j in range(len(matriz[i])):
            if(matriz[i][j]==-1):
                contador += 1
    return contador

matriz=[]

matriz = creaMatriz(10,10)

imprimeMatriz(matriz)

print("\n")

matriz = creaTablero(9,9,1)

imprimeMatriz(matriz)

print(f"\n\ncontador de minas: {contarMinas(matriz)}")

cadena = creaCadenaMatriz(matriz)

print(f"\nLa cadena de esta matriz es: {cadena}\ny su longitud es {len(cadena)}")

matriz = creaTablero(16,16,2)

imprimeMatriz(matriz)

print(f"\n\ncontador de minas: {contarMinas(matriz)}")

cadena = creaCadenaMatriz(matriz)

print(f"\nLa cadena de esta matriz es: {cadena}\ny su longitud es {len(cadena)}")

matriz = creaTablero(16,30,3)

imprimeMatriz(matriz)

print(f"\n\ncontador de minas: {contarMinas(matriz)}")

cadena = creaCadenaMatriz(matriz)

print(f"\nLa cadena de esta matriz es: {cadena}\ny su longitud es {len(cadena)}")

