import pygame
from pygame.locals import*
import random

windowWidth = 800
windowHeight = 600
fps = 60
white = (255, 255, 255)
black = (0, 0, 0)

class Ball:
    def __init__(self, img_file):
        # inicializa la imagen
        self.img = pygame.image.load(img_file).convert_alpha()
        
        # tamaño de la imagen
        self.width, self.height = self.img.get_size()

        # ubica la bola en el centro
        self.x = windowWidth/2 - self.width/2
        self.y = windowHeight/2 - self.height/2

        # dirección de la bola
        self.dir_x = random.choice([-5, 5])
        self.dir_y = random.choice([-5, 5])

        # puntuaciones
        self.player1Points = 0
        self.player2Points = 0

    def movement(self):
        self.x += self.dir_x
        self.y += self.dir_y
    
    def restart(self):
        self.x = windowWidth/2 - self.width/2
        self.y = windowHeight/2 - self.height/2

        self.dir_x = -self.dir_x
        self.dir_y = random.choice([-5, 5])

    def bounce(self):
        if(self.x <= self.width):
            self.restart()
            self.player2Points += 1

        if(self.x >= windowWidth):
            self.restart()
            self.player1Points += 1

        if(self.y <= 0):
            self.dir_y = -self.dir_y

        if(self.y + self.height >= windowHeight):
            self.dir_y = -self.dir_y

class Stick:
    def __init__(self):
        self.img = pygame.image.load("assets/stick.png").convert_alpha()

        # tamaño de la imagen
        self.width, self.height = self.img.get_size()

        # ubica los palos en el centro
        self.x = 0
        self.y = windowHeight/2 - self.height/2

        self.dir_y = 0

    def movement(self):
        self.y += self.dir_y

        if(self.y <= 0):
            self.y = 0
        
        if(self.y + self.height >= windowHeight):
            self.y = windowHeight - self.height

    def machineMovement(self, ball):
        if(self.y > ball.y):
            self.dir_y = -3
        elif(self.y < ball.y):
            self.dir_y = +3
        else:
            self.dir_y = 0

        self.y += self.dir_y

    def colission(self, ball):
        if(
            ball.x < self.x + self.width
            and ball.x > self.x
            and ball.y + ball.height > self.y
            and ball.y < self.y + self.height
        ):
            ball.dir_x = -ball.dir_x
            ball.x = self.x + self.width

    def machineColission(self, ball):
        if(
            ball.x + self.width > self.x
            and ball.x < self.x + self.width
            and ball.y + ball.height > self.y
            and ball.y < self.y + self.height
        ):

            ball.dir_x = -ball.dir_x
            ball.x = self.x - self.width

def main():
    pygame.init()
    
    window = pygame.display.set_mode((windowWidth, windowHeight))

    pygame.display.set_caption("Pong!")

    ball = Ball("assets/ball.png")

    font = pygame.font.Font(None, 60)

    stickOne = Stick()
    stickOne.x = 60

    stickTwo = Stick()
    stickTwo.x = windowWidth - 60 - stickTwo.width

    playing = True
    
    while playing:
        ball.movement()
        ball.bounce()

        stickOne.movement()
        stickTwo.machineMovement(ball)

        stickOne.colission(ball)
        stickTwo.machineColission(ball)

        window.fill(black)

        window.blit(ball.img, (ball.x, ball.y))

        window.blit(stickOne.img, (stickOne.x, stickOne.y))
        window.blit(stickTwo.img, (stickTwo.x, stickTwo.y))
        
        text = f"{ball.player1Points} : {ball.player2Points}"

        score = font.render(text, False, white)

        window.blit(score, (windowWidth/2 - font.size(text)[0]/2, 50))

        for event in pygame.event.get():
            if event.type == QUIT:
                playing = False
        
            if(event.type == pygame.KEYDOWN):
                if event.key == pygame.K_w:
                    stickOne.dir_y = -5
                
                if event.key == pygame.K_s:
                    stickOne.dir_y = 5

            if(event.type == pygame.KEYUP):
                if event.key == pygame.K_w:
                    stickOne.dir_y = 0
                
                if event.key == pygame.K_s:
                    stickOne.dir_y = 0

        pygame.display.flip()
        pygame.time.Clock().tick(fps)

    pygame.quit()

if(__name__ == '__main__'):
    main()