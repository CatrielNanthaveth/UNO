# Parcial práctico por Catriel Nanthaveth, 44589999 (individual)

import networkx as nx

class cambio():

    def __init__(self):
        pass

    def hacerTodo(self, archivo):

        # Creo un grafo dirigido
        grafo = nx.DiGraph()

        # Abro un archivo en modo lectura
        with open(archivo, mode="r") as archivo:

            # Separo la primer linea y escribo los datos importantes 
            linea1 = archivo.readline().split()

            totalEsquinas = int(linea1[0])
            esquinaOrigen = linea1[1]
            destino = linea1[2]

            # Agrego los nodos (esquinas) al grafo
            for esquina in range(1, totalEsquinas + 1):
                grafo.add_node(str(esquina))

            # Separo la segunda linea y anoto los datos importantes
            linea2 = archivo.readline().split()

            totalCalles = int(linea2[0])

            # Agrego las aristas (calles) al grafo
            for linea in range(1, totalCalles + 1):
                lineaX = archivo.readline().split()
                grafo.add_edge(lineaX[0], lineaX[1], peso = int(lineaX[2]), numero = str(linea))

        # Creo una versión no dirigida del grafo
        grafoNoDrigido = grafo.to_undirected()

        # Busco el camino más corto absoluto, o sea: no tengo en cuenta la dirección
        caminoCortoAbsoluto = nx.shortest_path(grafoNoDrigido, esquinaOrigen, destino, 'peso', 'dijkstra')
        
        # Creo variables para almacenar las calles a las que cambio su dirección y la distancia
        callesCambiadas = []
        distanciaMinima = 0

        # Recorro el camino más corto
        for i in range(0, len(caminoCortoAbsoluto) - 1):
            
            # Guardo los atributos de la arista entre los nodos
            atributos = grafoNoDrigido.get_edge_data(caminoCortoAbsoluto[i], caminoCortoAbsoluto[i + 1])
            
            # Si la calle es contramano, remuevo esa calle y pongo una orientada. Anoto la calle cambiada
            if(not grafo.has_edge(caminoCortoAbsoluto[i], caminoCortoAbsoluto[i + 1])):
                callesCambiadas.append(atributos["numero"])
                grafo.remove_edge(caminoCortoAbsoluto[i + 1], caminoCortoAbsoluto[i])
                grafo.add_edge(caminoCortoAbsoluto[i], caminoCortoAbsoluto[i + 1])

            # Sumo la distancia
            distanciaMinima += atributos["peso"]

        stringCallesCambiadas = ""

        for calle in callesCambiadas:
            stringCallesCambiadas += " " + calle

        with open("salida.out", "w") as salida:
            salida.write(str(distanciaMinima) + stringCallesCambiadas)

caminito = cambio()
caminito.hacerTodo("entrada.in")