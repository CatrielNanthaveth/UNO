# Pong! by: Catriel Nanthaveth (Falaang)

import pygame as pg
import sys

# inicializo pygame
pg.init()

# creo una ventana con tal resolución
size = width, height = 720, 360
screen = pg.display.set_mode(size)

# velocidad de la bola
speed = [1, 1]

# creo la puntuación, además del color del fondo de la ventana
punt1, punt2 = 0, 0
black = 0, 0, 0

# cargo los carteles del ganador y la reescalo para que quede bien en tamaño
winnerOne = pg.image.load("Pong/assets/playeronewon.png")
winnerOne = pg.transform.scale(winnerOne, (pg.Surface.get_width(winnerOne) * 3, pg.Surface.get_height(winnerOne) * 3))
winnerTwo = pg.image.load("Pong/assets/playertwowon.png")
winnerTwo = pg.transform.scale(winnerTwo, (pg.Surface.get_width(winnerTwo) * 3, pg.Surface.get_height(winnerTwo) * 3))

# cargo los carteles de la puntuación y la reescalo para que quede bien en tamaño
cero = pg.image.load("Pong/assets/0.png")
cero = pg.transform.scale(cero, (pg.Surface.get_width(cero) * 3, pg.Surface.get_height(cero) * 3))
uno = pg.image.load("Pong/assets/1.png")
uno = pg.transform.scale(uno, (pg.Surface.get_width(uno) * 3, pg.Surface.get_height(uno) * 3))
dos = pg.image.load("Pong/assets/2.png")
dos = pg.transform.scale(dos, (pg.Surface.get_width(dos) * 3, pg.Surface.get_height(dos) * 3))
tres = pg.image.load("Pong/assets/3.png")
tres = pg.transform.scale(tres, (pg.Surface.get_width(tres) * 3, pg.Surface.get_height(tres) * 3))

# cargo la imagen de la bola, la reescalo para que quede bien en tamaño,
# le asigno un rectángulo y lo muevo al centro
ball = pg.image.load("Pong/assets/ball.png")
ball = pg.transform.scale(ball, (pg.Surface.get_width(ball) * 0.05, pg.Surface.get_height(ball) * 0.05))
ballRect = ball.get_rect()
ballRect.move_ip(330, 180)

# cargo la imagen de los palitos, las reescalo para que queden bien en tamaño,
# les asigno un rectángulo y los muevo a sus respectivos bordes
stick1 = pg.image.load("Pong/assets/stick.png")
stick1 = pg.transform.scale(stick1, (pg.Surface.get_width(stick1) * 0.1, pg.Surface.get_height(stick1) * 0.1))
stick1Rect = stick1.get_rect()
stick1Rect.move_ip(20, 140)

stick2 = pg.image.load("Pong/assets/stick.png")
stick2 = pg.transform.scale(stick2, (pg.Surface.get_width(stick2) * 0.1, pg.Surface.get_height(stick2) * 0.1))
stick2Rect = stick1.get_rect()
stick2Rect.move_ip(695, 140)


while 1:
    # verifico si el usuario le dio al botón de salir para terminar el programa
    for event in pg.event.get():
        if event.type == pg.QUIT: sys.exit()
    
    # mientras la puntuación sea menor a 3, se puede jugar
    if(punt1 < 3 and punt2 < 3):

        # movimiento de la bola
        ballRect = ballRect.move(speed)

        # colisiones, si la bola golpea alguno de los palos cambio su dirección en el eje X

        if(stick1Rect.colliderect(ballRect) or stick2Rect.colliderect(ballRect)):
            speed[0] = -speed[0]

        # colisiones, si la bola golpea alguno de los límites inferiores y superiores
        # cambio su dirección en el eje Y
        if(ballRect.top < 0 or ballRect.bottom > height):
            speed[1] = -speed[1]

        # obtengo la tecla presionada
        keys = pg.key.get_pressed()

        # controles para el jugador 1

        if(keys[pg.K_w]):
            if(stick1Rect.top > 0):
                stick1Rect = stick1Rect.move(0, -1)
  
        if(keys[pg.K_s]):
            if(stick1Rect.bottom < 360):
                stick1Rect = stick1Rect.move(0, 1)

        # controles para el jugador 2

        if(keys[pg.K_UP]):
            if(stick2Rect.top > 0):
                stick2Rect = stick2Rect.move(0, -1)
  
        if(keys[pg.K_DOWN]):
            if(stick2Rect.bottom < 360):
                stick2Rect = stick2Rect.move(0, 1)

        # puntuación, cuando alguno puntea se restablecen las posiciones y se suman puntos

        if(ballRect.left < 0):
            punt2 += 1
            ballRect.update(330, 180, pg.Surface.get_width(ball), pg.Surface.get_height(ball))
            stick1Rect.update(20, 140, pg.Surface.get_width(stick1), pg.Surface.get_height(stick1))
            stick2Rect.update(695, 140, pg.Surface.get_width(stick2), pg.Surface.get_height(stick2))

        if(ballRect.right > width):
            punt1 += 1
            ballRect.update(330, 180, pg.Surface.get_width(ball), pg.Surface.get_height(ball))
            stick1Rect.update(20, 140, pg.Surface.get_width(stick1), pg.Surface.get_height(stick1))
            stick2Rect.update(695, 140, pg.Surface.get_width(stick2), pg.Surface.get_height(stick2))

        # dependiendo de la puntuación, el cartel de los puntos cambia
        if(punt1 == 0):
            num1 = cero
        elif(punt1 == 1):
            num1 = uno
        elif(punt1 == 2):
            num1 = dos
        elif(punt1 == 3):
            num1 = tres
            winner = winnerOne

        if(punt2 == 0):
            num2 = cero
        elif(punt2 == 1):
            num2 = uno
        elif(punt2 == 2):
            num2 = dos
        elif(punt2 == 3):
            num2 = tres
            winner = winnerTwo
    
        # aparecen las imágenes de todo lo que está en pantalla, además del fondo
        screen.fill(black)
        screen.blit(ball, ballRect)
        screen.blit(stick1, stick1Rect)
        screen.blit(stick2, stick2Rect)
        screen.blit(num1, (310, 20))
        screen.blit(num2, (360, 20))
    
    else:
        # el juego termina y solo quedan las imágenes
        screen.fill(black)
        screen.blit(ball, ballRect)
        screen.blit(stick1, stick1Rect)
        screen.blit(stick2, stick2Rect)
        screen.blit(num1, (300, 20))
        screen.blit(num2, (350, 20))
        screen.blit(winner, (260, 140))
        
    # actualizo las imagenes de la ventana y realiza 
    # un retraso de 5 milisegundos para ralentizar el movimiento 
    pg.display.flip()
    pg.time.delay(5)