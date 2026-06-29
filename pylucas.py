import numpy as np
import matplotlib.pyplot as plt
from PIL import Image

def eye_positions(img):
    plt.figure()
    plt.imshow(img, cmap='gray')
    x1, y1 = plt.ginput(1, timeout=-1)[0]
    x2, y2 = plt.ginput(1, timeout=-1)[0]
    plt.close()
    return (x1, y1), (x2, y2)

def extract_face(img):
    img_grey = img.convert('L')
    c1, c2 = eye_positions(img_grey)

    angle = np.degrees(np.arctan2(c2[1] - c1[1], c2[0] - c1[0]))

    rotation = img.rotate(angle, expand=False)

    w, h = rotation.size
    R = np.array([[np.cos(np.radians(-angle)), - np.sin(np.radians(-angle))],
                  [np.sin(np.radians(-angle)), np.cos(np.radians(-angle))]])
    center = np.array([[w/2], [h/2]])
    new_c1 = (R @ (np.array(c1).reshape(2, 1) - center) + center).flatten()
    new_c2 = (R @ (np.array(c2).reshape(2, 1) - center) + center).flatten()

    scaler = np.sqrt((new_c2[0] - new_c1[0])**2)
    cx = (new_c1[0] + new_c2[0]) / 2
    cy = (new_c1[1] + new_c2[1]) / 2
    box = (cx - scaler, cy - scaler, cx + scaler, cy + scaler)
    return rotation.crop(box).resize((100,100))

if _name_ == "_main_":
    img = Image.open(r'C:\Lucas\Angleterre\Informatique\Ai\images\lena.png').rotate(90, expand=True)
    face = extract_face(img)
    plt.figure()
    plt.imshow(face, cmap='gray')
    plt.show()