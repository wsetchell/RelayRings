import sys
import pygame

class ReleSounds(object):

  def ready(self):
    pygame.mixer.init()
    # TODO(will): Pick a new sound here.
    soundFile = os.path.join(os.path.dirname(__file__), 'resources/ring.wav')
    pygame.mixer.Sound(soundFile).play

  def ring(self):
    soundFile = os.path.join(os.path.dirname(__file__), 'resources/ring.wav')
    pygame.mixer.Sound(soundFile).play
